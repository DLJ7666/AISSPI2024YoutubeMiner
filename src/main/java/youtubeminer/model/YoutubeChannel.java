package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeChannel {


    @JsonProperty("title")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishedAt")
    private String createdTime;

    private List<YoutubeVideo> videos;

    public YoutubeChannel(String name, String description, String createdTime) {
        this.name = name;
        this.description = description;
        this.createdTime = createdTime;
        this.videos = new ArrayList<>();
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCreatedTime() { return createdTime; }

    public void setCreatedTime(String createdTime) { this.createdTime = createdTime; }

    public List<YoutubeVideo> getVideos() { return videos; }

    public void setVideos(List<YoutubeVideo> videos) { this.videos = videos; }

    @Override
    public String toString() {
        return "YoutubeChannel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdTime='" + createdTime + '\'' + ", videos=" + videos +
                "}";
    }
}
