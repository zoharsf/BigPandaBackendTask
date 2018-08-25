//package backend.ignore.old;
//
////import backend.ignore.old.Observers.RawJsonObserver;
//
//import backend.ApplicationConfiguration;
//import backend.EventCache;
//import backend.IncomingEvent;
//import io.reactivex.Observable;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//
//@Slf4j
////@Component
//public class Old_ApplicationManager implements CommandLineRunner {
//	@Autowired
//	private Old_Reader oldReader;
//
//	@Autowired
//	private Old_Parser oldParser;
//
//	@Autowired
//	private ApplicationConfiguration applicationConfiguration;
//
//	@Autowired
//	private EventCache eventCache;
//
//	@Getter
//	@Setter
//	private static Observable<String> rawJsonStream;
//
//	@Getter
//	@Setter
//	private static Observable<IncomingEvent> eventStream;
//
//	public void run(String... args) throws Exception {
//
//		log.info("\n\n######################################################\nApplication is running, starting to read event stream.\n######################################################\n\n");
//
//		//		ExecutorService executor = Executors.newFixedThreadPool(2 + applicationConfiguration.getNumberOfParserThreads());
//		//		executor.submit(reader);
//		//		for (int i = 0; i < applicationConfiguration.getNumberOfParserThreads(); i++)
//		//		{
//		//			executor.submit(parser);
//		//		}
//		//		executor.submit(eventCache);
//
//		//////////////////////////////////////////////////////////////////
//		//testing1:
//		//		try {
//		//			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
//		//
//		//			InputStream processInputStream = process.getInputStream();
//		//			BufferedReader reader = new BufferedReader(new InputStreamReader(processInputStream));
//		//			while (true) {
//		//				Observable.just(reader.readLine())
//		//						  .doOnNext(new Consumer<String>() {
//		//						  	@Override
//		//							public void accept(String jsonEvent) throws Exception {Parser.run(jsonEvent);}
//		//						  })
//		//						  .subscribeOn(Schedulers.newThread())
//		//						  .map(String::length)
//		//						  .subscribe(length -> System.out.println("item length " + length + "received on " + Thread.currentThread().getName()));
//		//
//		//			}
//		//		}
//		//		catch (IOException e) {
//		//			log.error("Error reading event stream: {}", e);
//		//		}
//		/////////////////////////////////////////////////////////////////////
//
//		//testing2:
//
////		final ActorSystem system = ActorSystem.create("bigPanda");
////		final Materializer mat = ActorMaterializer.create(system);
////		Source<IncomingEvent, NotUsed> eventStream;
////
////		try {
////			Process process = new ProcessBuilder(applicationConfiguration.getStreamSource()).start();
////
////			InputStream processInputStream = process.getInputStream();
////			BufferedReader reader = new BufferedReader(new InputStreamReader(processInputStream));
////
////			while (true) {
////				//read string
////				String stringEvent = reader.readLine();
////				log.info("event: {}", stringEvent);
////
////				//put on stream
////				eventStream.runWith(Sink.foreach(event -> eventCache.insert(event)), mat);
////
////				eventStream.map(receivedString -> {
////					log.debug(receivedString);
////					return receivedString;
////				});
////				//run from stream
////
////				//replace with parsed object
////
////				//cache and discard object
////				final CompletionStage<EventCache> eventCache = eventStream.runWith(Sink.seq(), mat);
////
////			}
////		}
////		catch (IOException e) {
////			log.error("Error reading event stream: {}", e);
//		}
//		//////////////////////////////////////////////////////////////////
//
////	}
//}
