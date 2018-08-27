package backend;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import akka.stream.javadsl.Source;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CompletionStage;

@Slf4j
@Component
public class ApplicationManager implements CommandLineRunner {
	
	@Autowired
	private ApplicationConfiguration applicationConfiguration;
	
	private final ActorSystem actorSystem = ActorSystem.create("BigPanda");
	
	public ApplicationManager() {}
	
	public void run(String... args) {
		
		log.info("\n\n######################################################\nApplication is running, starting to read event stream.\n######################################################\n\n");
		
		beginReadingEventStream();
	}
	
	//reader
	private void beginReadingEventStream() {
		try {
			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
			InputStream processInputStream = process.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
			
			while (true) {
				String event = bufferedReader.readLine();
				log.info("Reader: {}", event);
				streamRawJson(event);
			}
		}
		catch (IOException e) {
			log.error("Error reading event stream: {}", e.getMessage());
		}
	}
	
	private void streamRawJson(String rawEvent) {
		Source.single(rawEvent).via(parseStream()).runWith(addToCache(), ActorMaterializer.create(actorSystem));
	}
	
	//parser
	private IncomingEvent parseRawMessage(String rawEvent) {
		ObjectMapper objectMapper = new ObjectMapper();
		IncomingEvent jsonEvent = null;
		try {
			jsonEvent = objectMapper.readValue(rawEvent, IncomingEvent.class);
			log.info("Parser: {}", jsonEvent);
			
		}
		catch (IOException e) {
			log.error("Unable to parse incoming event: {}", rawEvent);
		}
		return jsonEvent;
	}
	
	private Flow<String, IncomingEvent, NotUsed> parseStream() {
		return Flow.of(String.class).map(this::parseRawMessage);
	}
	
	private static Sink<IncomingEvent, CompletionStage<Done>> addToCache() {
		return Flow.of(IncomingEvent.class).toMat(Sink.foreach(EventCache::cacheIncomingEvent), Keep.right());
	}
}

