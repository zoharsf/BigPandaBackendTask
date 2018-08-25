package backend.ignore.old.Akka.example;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import lombok.extern.slf4j.Slf4j;

/**
 * counter - actor that keeps state
 */
@Slf4j
public class App {
	
	static class Counter extends AbstractActor {
		@Override
		public Receive createReceive() {
			return null;
		}
		
		// protocol
		static class Event {}
		
		public static Props props() {
			return Props.create(Counter.class);
		}
	}
	
	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create("sample1");
		
		final ActorRef counter = system.actorOf(Counter.props(), "counter");
		
		for (int i = 0; i < 5; i++) {
			new Thread(() -> {
				for (int j = 0; j < 5; j++) {
					counter.tell(new Counter.Event(), ActorRef.noSender());
				}
			}).start();
		}
	}
}