package youtubeminer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageInfo {

    @JsonProperty("totalResults")
    private Integer totalResults;

    @JsonProperty("resultsPerPage")
    private Integer perPage;

    public PageInfo(Integer totalResults, Integer perPage) {
        this.totalResults = totalResults;
        this.perPage = perPage;
    }

    public Integer getTotalResults() { return totalResults; }

    public void setTotalResults(Integer totalResults) { this.totalResults = totalResults; }

    public Integer getPerPage() { return perPage; }

    public void setPerPage(Integer perPage) { this.perPage = perPage; }

    @Override
    public String toString() {
        return "PageInfo{" +
                "totalResults=" + totalResults +
                ", perPage=" + perPage +
                '}';
    }
}
