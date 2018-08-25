//package backend.ignore.old;
//
//import backend.ApplicationConfiguration;
//import io.reactivex.Observable;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//
//@Slf4j
//@Component
//
//public class Old_Reader implements Runnable {
//
//	@Autowired
//	private ApplicationConfiguration applicationConfiguration;
//
//	public void run() {
//		try {
//			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
//
//			InputStream processInputStream = process.getInputStream();
//			BufferedReader reader = new BufferedReader(new InputStreamReader(processInputStream));
//
//			while (true) {
//				String event = reader.readLine();
//				log.info("event: {}", event);
//
//				Old_ApplicationManager.setRawJsonStream(Observable.just(event));
//			}
//		}
//		catch (IOException e) {
//			log.error("Error reading event stream: {}", e);
//		}
//	}
//}
//
