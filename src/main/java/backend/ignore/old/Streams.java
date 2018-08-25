package backend.ignore.old;

import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;
import backend.IncomingEvent;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class Streams {
	
	Source<String, NotUsed> rawStream;
	//TODO do I need more than one stream?
	Source<IncomingEvent, NotUsed> eventStream;
	final ActorSystem system = ActorSystem.create("BigPanda");
	//TODO do i need this?
	final Materializer materializer = ActorMaterializer.create(system);
	
	
	public Streams(){
	//TODO fill this
	}
}
