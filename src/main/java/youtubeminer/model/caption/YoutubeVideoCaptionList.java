package youtubeminer.model.caption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippet;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeVideoCaptionList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<YoutubeCaption> captions;

    public YoutubeVideoCaptionList(Integer total, Integer pagina, Integer elemPorPag, List<YoutubeCaption> captions) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.captions = captions;
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

    public List<YoutubeCaption> getCaptions() {
        return captions;
    }

    public void setVideos(List<YoutubeCaption> captions) {
        this.captions = captions;
    }

    public Integer getNumPags() {
        return getTotal()/getElemPorPag()+1;
    }

    @Override
    public String toString() {
        return "VimeoVideoList{" +
                "total=" + total +
                ", pagina=" + pagina +
                ", elemPorPag=" + elemPorPag +
                ", videos=" + captions +
                '}';
    }
}
