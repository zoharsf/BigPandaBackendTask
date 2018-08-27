package backend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
class IncomingEvent {
	@JsonProperty("event_type")
	private String eventType;
	@JsonProperty("data")
	private String data;
	@JsonProperty("timestamp")
	private long timestamp;
}
