
package youtubeminer.model.comment;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeCommentSnippet {

    private TopLevelYoutubeComment topLevelYoutubeComment;

    @JsonProperty("topLevelComment")
    public TopLevelYoutubeComment getTopLevelComment() {
        return topLevelYoutubeComment;
    }

    @JsonProperty("topLevelComment")
    public void setTopLevelComment(TopLevelYoutubeComment topLevelYoutubeComment) {
        this.topLevelYoutubeComment = topLevelYoutubeComment;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(YoutubeCommentSnippet.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("topLevelComment");
        sb.append('=');
        sb.append(((this.topLevelYoutubeComment == null)?"<null>":this.topLevelYoutubeComment));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
