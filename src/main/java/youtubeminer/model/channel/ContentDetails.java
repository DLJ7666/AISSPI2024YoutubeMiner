package youtubeminer.model.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentDetails {

    @JsonProperty("relatedPlaylists")
    private RelatedPlaylists playListsIds;

    public ContentDetails(RelatedPlaylists playListsIds) {
        this.playListsIds = playListsIds;
    }

    public RelatedPlaylists getPlayListsIds() {
        return playListsIds;
    }

    public void setPlayListsIds(RelatedPlaylists playListsIds) {
        this.playListsIds = playListsIds;
    }

    @Override
    public String toString() {
        return "ContentDetails{" +
                "playListsIds=" + playListsIds +
                '}';
    }
}
