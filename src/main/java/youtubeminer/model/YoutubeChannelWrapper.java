package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeChannelWrapper {

    @JsonProperty("id")
    private String id;

    @JsonProperty("snippet")
    private YoutubeChannel datos;



}
