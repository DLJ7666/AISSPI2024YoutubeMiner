package youtubeminer.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YoutubeVideoCommentList {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("page")
    private Integer pagina;

    @JsonProperty("per_page")
    private Integer elemPorPag;

    @JsonProperty("data")
    private List<YoutubeComment> comments;

    public YoutubeVideoCommentList(Integer total, Integer pagina, Integer elemPorPag, List<YoutubeComment> comments) {
        this.total = total;
        this.pagina = pagina;
        this.elemPorPag = elemPorPag;
        this.comments = comments;
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

    public List<YoutubeComment> getComments() {
        return comments;
    }

    public void setVideos(List<YoutubeComment> comments) {
        this.comments = comments;
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
                ", videos=" + comments +
                '}';
    }
}

