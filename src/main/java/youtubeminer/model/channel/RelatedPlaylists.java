package youtubeminer.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RelatedPlaylists {

    @JsonProperty("uploads")
    private String uploadsPlayListId;

    public RelatedPlaylists(String uploadsPlayListId) {
        this.uploadsPlayListId = uploadsPlayListId;
    }

    public String getUploadsPlayListId() {
        return uploadsPlayListId;
    }

    public void setUploadsPlayListId(String uploadsPlayListId) {
        this.uploadsPlayListId = uploadsPlayListId;
    }

    @Override
    public String toString() {
        return "RelatedPlaylists{" +
                "uploadsPlayListId='" + uploadsPlayListId + '\'' +
                '}';
    }
}
