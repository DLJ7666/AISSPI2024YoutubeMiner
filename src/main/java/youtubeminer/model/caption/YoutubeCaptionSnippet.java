package youtubeminer.model.caption;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeCaptionSnippet {

    @JsonProperty("name")
    private String name;
    @JsonProperty("language")
    private String language;

    @JsonProperty("language")
    public String getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(String language) {
        this.language = language;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "YoutubeCaptionSnippet{" +
                "name='" + name + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
