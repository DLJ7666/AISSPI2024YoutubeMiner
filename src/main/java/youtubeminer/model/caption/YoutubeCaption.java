package youtubeminer.model.caption;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeCaption {

    @JsonProperty("id")
    private String id;
    @JsonProperty("snippet")
    private YoutubeCaptionSnippet snippet;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("snippet")
    public YoutubeCaptionSnippet getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(YoutubeCaptionSnippet snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "YoutubeCaption{" +
                "id='" + id + '\'' +
                ", snippet=" + snippet +
                '}';
    }
}
