package backend;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class EventCache {
	private static Map<String, Integer> eventTypeAppearances = new ConcurrentHashMap<>();
	private static Map<String, Integer> wordAppearances = new ConcurrentHashMap<>();
	private static boolean upToDate = false;
	private static String cacheAsString = "";
	
	private static void addEventType(String eventType) {
		eventTypeAppearances.put(eventType, eventTypeAppearances.get(eventType) == null ? 1 : eventTypeAppearances.get(eventType) + 1);
		log.debug("Added event to cache: {}", eventType);
		upToDate = false;
	}
	
	private static void addWordAppearances(String word) {
		wordAppearances.put(word, wordAppearances.get(word) == null ? 1 : wordAppearances.get(word) + 1);
		log.debug("Added word to cache: {}", word);
		upToDate = false;
	}
	
	static CompletionStage cacheIncomingEvent(IncomingEvent incomingEvent) {
		return CompletableFuture.supplyAsync(() -> {
			addEventType(incomingEvent.getEventType());
			log.info("Cache eventType: {}", incomingEvent.getEventType());
			addWordAppearances(incomingEvent.getData());
			log.info("Cache word: {}", incomingEvent.getData());
			return incomingEvent;
		});
	}
	
	@Override
	public synchronized String toString() {
		if (upToDate) {
			return cacheAsString;
		}
		else {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("<html><body>");
			stringBuilder.append("<h2>Event Type Appearances:</h2><ul>");
			for (String eventType : eventTypeAppearances.keySet()) {
				stringBuilder.append("<li>").append(eventType).append(" = ").append(eventTypeAppearances.get(eventType)).append("</li>");
			}
			stringBuilder.append("</ul><h2>Word Appearances:</h2><ul>");
			for (String word : wordAppearances.keySet()) {
				stringBuilder.append("<li>").append(word).append(" = ").append(wordAppearances.get(word)).append("</li>");
			}
			stringBuilder.append("</ul></body></html>");
			cacheAsString = stringBuilder.toString();
			upToDate = true;
		}
		return cacheAsString;
	}
}
