package youtubeminer.model.videoSnippet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceId {

    @JsonProperty("videoId")
    String id;

    @JsonProperty("videoId")
    public String getId() {
        return id;
    }

    public ResourceId(String id) {
        this.id = id;
    }

    public ResourceId() {
        super();
    }

    @JsonProperty("videoId")
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ResourceId{" +
                "id='" + id + '\'' +
                '}';
    }
}
