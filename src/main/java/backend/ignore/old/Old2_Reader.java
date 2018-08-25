//package backend;
//
//import akka.Done;
//import akka.stream.ActorMaterializer;
//import akka.stream.javadsl.Source;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.concurrent.CompletionStage;
//
//@Slf4j
//@Component
//public class Reader implements Runnable {
//
//	@Autowired
//	ApplicationConfiguration applicationConfiguration;
//
//	public void run() {
//		try {
//			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
//			InputStream processInputStream = process.getInputStream();
//			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processInputStream));
//
//			while (true) {
//				String event = bufferedReader.readLine();
//				log.info("event: {}", event);
//				streamRawJson(event);
//			}
//		}
//		catch (IOException e) {
//			log.error("Error reading event stream: {}", e);
//		}
//	}
//
//	//example
//	CompletionStage<Done> streamRawJson(String rawEvent) {
//		return Source.single(rawEvent)
//					 .via(Parser.parseRawMessage())
//					 .runWith(storeAverages(), ActorMaterializer.create(actorSystem))
//					 .whenComplete((d, e) -> {
//						if (d != null) {
//							System.out.println("Import finished ");
//						}
//						else {
//							log.error("error");
//						}
//					 });
//	}
//}
