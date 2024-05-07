
package youtubeminer.model.comment;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeComment {

    @JsonProperty("snippet")
    private YoutubeCommentSnippet snippet;

    @JsonProperty("snippet")
    public YoutubeCommentSnippet getCommentSnippet() {
        return snippet;
    }

    @JsonProperty("snippet")
    public void setCommentSnippet(YoutubeCommentSnippet snippet) {
        this.snippet = snippet;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(YoutubeComment.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
