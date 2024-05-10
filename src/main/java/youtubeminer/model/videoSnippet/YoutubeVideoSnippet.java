
package youtubeminer.model.videoSnippet;

import com.fasterxml.jackson.annotation.*;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.comment.YoutubeComment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeVideoSnippet {

    @JsonProperty("resourceId")
    private YoutubeVideoSnippetId id;
    @JsonProperty("snippet")
    private YoutubeVideoSnippetDetails snippet;

    // These attributes have been manually added
    @JsonProperty("comments")
    private List<YoutubeComment> comments;

    @JsonProperty("captions")
    private List<YoutubeCaption> youtubeCaptions;

    public YoutubeVideoSnippet() {
        this.comments = new ArrayList<>();
        this.youtubeCaptions = new ArrayList<>();
    }

    @JsonProperty("comments")
    public List<YoutubeComment> getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(List<YoutubeComment> comments) {
        this.comments = comments;
    }

    @JsonProperty("captions")
    public List<YoutubeCaption> getCaptions() { return youtubeCaptions; }

    @JsonProperty("captions")
    public void setCaptions(List<YoutubeCaption> youtubeCaptions) {
        this.youtubeCaptions = youtubeCaptions;
    }

    @JsonProperty("id")
    public YoutubeVideoSnippetId getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(YoutubeVideoSnippetId id) {
        this.id = id;
    }

    @JsonProperty("snippet")
    public YoutubeVideoSnippetDetails getSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setSnippet(YoutubeVideoSnippetDetails snippet) {
        this.snippet = snippet;
    }

    public void addTexttracks(Collection<YoutubeCaption> texttracks) {
        this.youtubeCaptions.addAll(texttracks);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(YoutubeVideoSnippet.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
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
