package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YTChannelContentDetails {

    @JsonProperty("relatedPlaylists")
    private YTChannelPlaylistsId relatedPlaylists;

    public YTChannelContentDetails(YTChannelPlaylistsId relatedPlaylists) {
        this.relatedPlaylists = relatedPlaylists;
    }

    public YTChannelPlaylistsId getRelatedPlaylists() {
        return relatedPlaylists;
    }

    public void setRelatedPlaylists(YTChannelPlaylistsId relatedPlaylists) {
        this.relatedPlaylists = relatedPlaylists;
    }

    @Override
    public String toString() {
        return "YTChannelContentDetails{" +
                "relatedPlaylists=" + relatedPlaylists +
                '}';
    }
}
