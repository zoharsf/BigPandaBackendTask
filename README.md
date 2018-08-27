# BigPandaBackendTask

## Implementation of a non-blocking producer/consumer stream processing service that exposes an HTTP api

### Stream origin:
A blackbox generator that spits out an infinite stream of lines of event data encoded in JSON.

### The generator blackbox may be downloaded from either of these links:
* Linux: https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-linux-amd64
* Mac OS X: https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-macosx-amd64
* Windows: https://s3-us-west-1.amazonaws.com/bp-interview-artifacts/generator-windows-amd64.exe

### Service Requirements:
The service should consume the output of the generator and gather the following stats:
* A count of events by event type.
* A count of words encountered in the data field of the events.

### Running:
* Open src\main\resources\application.properties.
* Edit the application.stream-source property with the location of the generator.
* Run Maven clean and Maven Install.
* Locate generated bigPanda-1.0-SNAPSHOT.jar (should be located in the project's target directory.
* Run in command line (replace <PATH_TO_JAR> with the full path to the located jar filew from the previous bullet):
```
java -jar <PATH_TO_JAR>
```

### Future improvements:
* Beautify statistics HTML page.
* Get to know the Akka framework better and improve use of Streams.
* Add an external properties file to allow changing properties without needing to rebuild service.
* Support multiple IncomingEvent types in order to allow use with more than one type of generator blackboxes.

### Notes:
* Gathered stats should be exposed via an HTTP interface.
* Stream may encounter corrupt JSON lines and should handle such events well and without interruption.
* We are looking for simple readable code which is not over-engineered.
* The design of your solution should decouple the reads from the writes. Try to think on what it means when scaling such a service.
* You can implement this exercise in either Java or Scala.
* The task should take no more than 2-3 hours.
* If you already know some reactive framework (For example: RXJava, RxScala, Play, Akka, VertX, Reactor or anything similar) use what you know! if you don't know any of these frameworks read a bit and understand what your implementation can benefit from these proposed frameworks.
* Add a README file with instructions on running the project. In the README file, please note 3 things you would improve in your submission.
