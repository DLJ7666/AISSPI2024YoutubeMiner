package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeChannelList {

    @JsonProperty("pageInfo")
    private PageInfo pageInfo;

    @JsonProperty("items")
    private YoutubeChannelWrapper channel;

    public YoutubeChannelList(PageInfo pageInfo, YoutubeChannelWrapper channel) {
        this.pageInfo = pageInfo;
        this.channel = channel;
    }

    public PageInfo getPageInfo() { return pageInfo; }

    public void setPageInfo(PageInfo pageInfo) { this.pageInfo = pageInfo; }

    public YoutubeChannelWrapper getChannel() { return channel; }

    public void setChannel(YoutubeChannelWrapper channel) { this.channel = channel; }

    @Override
    public String toString() {
        return "YoutubeChannelList{" +
                "pageInfo=" + pageInfo +
                ", channel=" + channel +
                '}';
    }
}
