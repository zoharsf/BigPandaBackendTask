//package backend.ignore.old.Observers;
//
//import backend.ignore.old.Observers.ApplicationManager;
//import backend.IncomingEvent;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.reactivex.Observable;
//import io.reactivex.Observer;
//import io.reactivex.disposables.Disposable;
//import lombok.Data;
//import lombok.extern.slf4j.Slf4j;
//
//import java.io.IOException;
//
//@Data
//@Slf4j
//public class RawJsonObserver implements Observer<String> {
//
//	io.reactivex.Observer<String> rawJsonObserver;
//	IncomingEvent incomingEvent;
//	ObjectMapper objectMapper;
//
//	@Override
//	public void onSubscribe(Disposable d) {
//		log.info("New subscriber to RawJsonObserver.");
//	}
//
//	@Override
//	public void onNext(String rawJsonEvent) {
//		log.info("New event on RawJsonObserver: {}", rawJsonEvent);
//
//		try {
//			incomingEvent = objectMapper.readValue(rawJsonEvent, IncomingEvent.class);
//			log.info("Parsed event: {}", incomingEvent);
//			ApplicationManager.setEventStream(Observable.just(incomingEvent));
//		}
//		catch (IOException e) {
//			log.error("Error parsing raw event: {}.", e.getMessage());
//		}
//	}
//
//	@Override
//	public void onError(Throwable e) {
//		log.error("Error in RawJsonObserver: {}.", e.getMessage());
//	}
//
//	@Override
//	public void onComplete() {
//		log.info("RawJsonObserver completed.");
//	}
//}
