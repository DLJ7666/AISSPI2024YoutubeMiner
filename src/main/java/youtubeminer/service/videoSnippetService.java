package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippet;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippetList;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippetSearch;

import java.util.ArrayList;
import java.util.List;

@Service
public class videoSnippetService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyDQlxgZcRO6tpIEeDXhqBEU53lHNRoetC0";

    public YoutubeVideoSnippet getYoutubeVideoSnippet(String id) {
        YoutubeVideoSnippet res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=playlistId&part=snippet&part=contentDetails&playlistId=%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeVideoSnippet> request = new HttpEntity<>(null, headers);

        ResponseEntity<YoutubeVideoSnippet> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeVideoSnippet.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public YoutubeVideoSnippetSearch getYoutubeVideoSnippets(String playListId) {
        YoutubeVideoSnippetSearch res = null;
        String uri = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=" + playListId;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeVideoSnippetSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeVideoSnippetSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeVideoSnippetSearch.class);
        if (response.getBody() != null) {
            res=response.getBody();
        }
        return res;
    }

    public YoutubeVideoSnippetList getYoutubeVideoList(String channelId, Integer page) {
        YoutubeVideoSnippetList res = null;
        Integer pagina = page;
        if(pagina==null) {
            pagina = 1;
        }
        String uri = String.format("https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=", channelId, pagina);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeVideoSnippetList> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeVideoSnippetList> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeVideoSnippetList.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }
}
