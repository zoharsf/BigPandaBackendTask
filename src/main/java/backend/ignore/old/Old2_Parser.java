package backend.ignore.old;

import akka.NotUsed;
import akka.stream.javadsl.Flow;
import backend.IncomingEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class Old2_Parser implements Runnable {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	public void run() {
		String rawEvent = null;
		try {
			//TODO get rawEvent from stream
			
			IncomingEvent jsonEvent = objectMapper.readValue(rawEvent, IncomingEvent.class);
			log.info("Parsed event: {}", jsonEvent);
			
			//TODO put on stream
		}
		catch (IOException e) {
			log.error("Unable to run incoming event: {}", rawEvent);
		}
	}
	
	public IncomingEvent parseRawMessage(String rawEvent) {
		IncomingEvent jsonEvent = null;
		try {
			jsonEvent = objectMapper.readValue(rawEvent, IncomingEvent.class);
			log.info("Parsed event: {}", jsonEvent);
			
		}
		catch (IOException e) {
			log.error("Unable to run incoming event: {}", rawEvent);
		}
		return jsonEvent;
	}
	
	Flow<String, IncomingEvent, NotUsed> parseStream() {
		return Flow.of(String.class).map(this::parseRawMessage);
	}
}
