package backend;

import akka.Done;
import akka.stream.javadsl.Flow;
import akka.stream.javadsl.Keep;
import akka.stream.javadsl.Sink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class EventCache implements Runnable {
	private static Map<String, Integer> eventTypeAppearances = new ConcurrentHashMap<>();
	private static Map<String, Integer> wordAppearances = new ConcurrentHashMap<>();
	private static boolean upToDate = false;
	private static String cacheAsString = "";
	
	public static void addEventType(String eventType) {
		eventTypeAppearances.put(eventType, eventTypeAppearances.get(eventType) + 1);
		log.debug("Added event to cache: {}", eventType);
		upToDate = false;
	}
	
	public static void addWordAppearances(String word) {
		wordAppearances.put(word, wordAppearances.get(word) + 1);
		log.debug("Added word to cache: {}", word);
		upToDate = false;
	}
	
	public void run() {
		//TODO get IncomingEvent incomingEvent from stream
		IncomingEvent incomingEvent = null;
		addEventType(incomingEvent != null ? incomingEvent.getEventType() : null);
		addWordAppearances(incomingEvent.getData());
	}
	
	public static CompletionStage cacheIncomingEvent(IncomingEvent incomingEvent) {
		return CompletableFuture.supplyAsync(() -> {
			addEventType(incomingEvent.getEventType());
			addWordAppearances(incomingEvent.getData());
			return incomingEvent;
		});
	}
	
	public static Sink<IncomingEvent, CompletionStage<Done>> addToCache() {
		return Flow.of(IncomingEvent.class)
				   .mapAsyncUnordered(4, EventCache::cacheIncomingEvent)
				   .toMat(Sink.ignore(), Keep.right());
	}
	
	@Override
	public synchronized String toString() {
		if (upToDate) {
			return cacheAsString;
		}
		else {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Event Type Appearances:\n");
			for (String eventType : eventTypeAppearances.keySet()) {
				stringBuilder.append(eventType + " = " + eventTypeAppearances.get(eventType) + "\n");
			}
			stringBuilder.append("\n\nWord Appearances:\n");
			for (String word : wordAppearances.keySet()) {
				stringBuilder.append(word + " = " + wordAppearances.get(word) + "\n");
			}
			cacheAsString = stringBuilder.toString();
			upToDate = true;
		}
		return cacheAsString;
	}
}
