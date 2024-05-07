package youtubeminer.model.caption;

import com.fasterxml.jackson.annotation.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeCaptionSearch {

    @JsonProperty("items")
    private List<YoutubeCaption> items;

    @JsonProperty("items")
    public List<YoutubeCaption> getItems() {
        return items;
    }

    @JsonProperty("items")
    public void setItems(List<YoutubeCaption> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "YoutubeCaptionSearch{" +
                "items=" + items +
                '}';
    }
}
