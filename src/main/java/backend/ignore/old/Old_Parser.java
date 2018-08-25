//package backend.ignore.old;
//
//import backend.IncomingEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.reactivex.Observable;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//import static io.reactivex.Observable.just;
//
//@Slf4j
////@Component
//public class Old_Parser implements Runnable {
//
//	//	Observer<String> rawJsonObserver = ApplicationManager.getRawJsonObserver();
//
//	public void run() {
//		ObjectMapper objectMapper = new ObjectMapper();
//		Observable<String> rawJsonStream = Old_ApplicationManager.getRawJsonStream();
//		IncomingEvent incomingEvent;
//		final String[] jsonEventString = new String[1];
//		String jsonEvent = "";
//
//
//
//
////		Observer<String> rawTestObserver = getRawTestObserver();
////		rawJsonStream.doOnNext(c -> log.info("onNext called."))
////					 .subscribeOn(Schedulers.newThread())
////					 .observeOn(Schedulers.newThread())
////					 .subscribe(rawTestObserver);
////		Schedulers.start();
////
////		ApplicationManager.getRawJsonStream().doOnNext(c -> log.error("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"))
////				  .subscribeOn(Schedulers.newThread())
////				  .subscribe(rawTestObserver);
//
//		try {
//			Thread.sleep(3000);
//		}
//		catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		while (true) {
//			if (rawJsonStream == null) {
//				rawJsonStream = Old_ApplicationManager.getRawJsonStream();
//
//				//rawJsonStream.subscribe(s -> jsonEventString[0] = s);
//			}
//			else {
//				//final String[] rawEvent = new String[1];
//				//rawJsonStream.subscribe(s -> rawEvent[0] = String.valueOf(s));
//
//				//rawJsonStream.subscribe(s -> jsonEventString[0] = s);
//				//log.info("Raw event: {}", jsonEventString[0]);
//
//				//jsonEvent = (String)((ObservableJust) ApplicationManager.getRawJsonStream()).call();
//
//				try {
//					//incomingEvent = objectMapper.readValue(jsonEventString[0], IncomingEvent.class);
//					incomingEvent = objectMapper.readValue(jsonEvent, IncomingEvent.class);
//					log.info("Parsed event: {}", incomingEvent);
//					Old_ApplicationManager.setEventStream(just(incomingEvent));
//				}
//				catch (IOException e) {
//					log.error("Unable to run incoming event: {}", jsonEventString[0]);
//				}
//			}
//			//else {
//				//rawJsonStream = ApplicationManager.getRawJsonStream();
//			//}
//		}
//	}
//
////	private Observer<String> getRawTestObserver() {
////
////		return new Observer<String>() {
////			@Override
////			public void onSubscribe(Disposable d) {
////				log.info("New subscriber to RawJsonObserver.");
////			}
////
////			@Override
////			public void onNext(String rawJsonEvent) {
////				log.info("New event on RawJsonObserver: {}", rawJsonEvent);
////				IncomingEvent incomingEvent;
////				ObjectMapper objectMapper = new ObjectMapper();
////				try {
////					incomingEvent = objectMapper.readValue(rawJsonEvent, IncomingEvent.class);
////					log.info("Parsed event: {}", incomingEvent);
////					ApplicationManager.setEventStream(just(incomingEvent));
////				}
////				catch (IOException e) {
////					log.error("Error parsing raw event: {}.", e.getMessage());
////				}
////			}
////
////			@Override
////			public void onError(Throwable e) {
////				log.error("Error in RawJsonObserver: {}.", e.getMessage());
////			}
////
////			@Override
////			public void onComplete() {
////				log.info("RawJsonObserver completed.");
////			}
////		};
////	}
//
//	public static void parse(String jsonEvent) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		log.error("Parsing");
//		try {
//			IncomingEvent incomingEvent = objectMapper.readValue(jsonEvent, IncomingEvent.class);
//			log.info("Parsed event: {}", incomingEvent);
//			Old_ApplicationManager.setEventStream(just(incomingEvent));
//		}
//		catch (IOException e) {
//			log.error("Unable to run incoming event: {}", jsonEvent);
//		}
//	}
//}
//
