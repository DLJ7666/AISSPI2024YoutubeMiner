package youtubeminer.model.videoSnippet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeVideoSnippetList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<YoutubeVideoSnippet> videos;

    public YoutubeVideoSnippetList(Integer total, Integer pagina, Integer elemPorPag, List<YoutubeVideoSnippet> videos) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.videos = videos;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPagina() {
        return pagina;
    }

    public void setPagina(Integer pagina) {
        this.pagina = pagina;
    }

    public Integer getElemPorPag() {
        return elemPorPag;
    }

    public void setElemPorPag(Integer elemPorPag) {
        this.elemPorPag = elemPorPag;
    }

    public List<YoutubeVideoSnippet> getVideos() {
        return videos;
    }

    public void setVideos(List<YoutubeVideoSnippet> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "VimeoVideoList{" +
                "total=" + total +
                ", pagina=" + pagina +
                ", elemPorPag=" + elemPorPag +
                ", videos=" + videos +
                '}';
    }

    public Integer getNumPags() {
        return getTotal()/getElemPorPag()+1;
    }
}
