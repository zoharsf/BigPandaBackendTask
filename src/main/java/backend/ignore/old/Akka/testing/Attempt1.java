package backend.ignore.old.Akka.testing;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import backend.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
public class Attempt1 {
	
	@Autowired
	private static ApplicationConfiguration applicationConfiguration;
	
	static class Reader extends AbstractActor {
		
		@Override
		public Receive createReceive() {
			return null;
		}
		
		static class Event {}
		
		private void onMessage(Event event) {
			log.debug("Event received: {}", event);
		}
		
		public static Props props() {
			return Props.create((Reader.class));
		}
	}
	
	public static void main(String[] args) {
		Attempt1 attempt1 = new Attempt1();
		attempt1.go();
	}
	
	private void go() {
		ActorSystem actorSystem = ActorSystem.create("BigPanda");
		final ActorRef reader = actorSystem.actorOf(Reader.props(), "Reader");
		
		try {
			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
			
			InputStream processInputStream = process.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
			
			for (int i = 0; i < 100; i++) {
				String event = bufferedReader.readLine();
				log.info("event: {}", event);
				System.out.println(i);
				reader.tell(event, ActorRef.noSender());
			}
		}
		catch (IOException e) {
			log.error("Error reading event stream: {}", e);
		}
		
	}
}