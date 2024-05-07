package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YTChannelPlaylistsId {

    @JsonProperty("uploads")
    private String uploadsId;

    public YTChannelPlaylistsId(String uploadsId) {
        this.uploadsId = uploadsId;
    }

    public String getUploadsId() {
        return uploadsId;
    }

    public void setUploadsId(String uploadsId) {
        this.uploadsId = uploadsId;
    }

    @Override
    public String toString() {
        return "YTChannelPlaylistsId{" +
                "uploadsId='" + uploadsId + '\'' +
                '}';
    }
}
