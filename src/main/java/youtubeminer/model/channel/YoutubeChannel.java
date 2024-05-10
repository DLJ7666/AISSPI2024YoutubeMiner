package youtubeminer.model.channel;

import com.fasterxml.jackson.annotation.*;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippet;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeChannel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("snippet")
    private YoutubeChannelSnippet snippet;

    @JsonProperty("contentDetails")
    private ContentDetails playListIdWrapper;

    // This attribute has been added manually
    @JsonProperty("videos")
    private List<YoutubeVideoSnippet> videos;

    public YoutubeChannel() {
        videos = new ArrayList<>();
    }

    @JsonProperty("videos")
    public List<YoutubeVideoSnippet> getVideos(){ return videos; }

    @JsonProperty("videos")
    public void setVideos(List<YoutubeVideoSnippet> videos) {
        this.videos = videos;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("snippet")
    public YoutubeChannelSnippet getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(YoutubeChannelSnippet snippet) {
        this.snippet = snippet;
    }

    public ContentDetails getPlayListIdWrapper() {
        return playListIdWrapper;
    }

    public void setPlayListIdWrapper(ContentDetails playListIdWrapper) {
        this.playListIdWrapper = playListIdWrapper;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(YoutubeChannel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append(("contentDetails="));
        sb.append((this.playListIdWrapper == null)?"<null>":this.playListIdWrapper);
        sb.append("snippet");
        sb.append('=');
        sb.append(((this.snippet == null)?"<null>":this.snippet));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
