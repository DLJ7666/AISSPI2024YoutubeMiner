package youtubeminer.youtubeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippet;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippetSearch;

@Service
public class YoutubeVideoSnippetService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyCUWuAfA7E_Z8VZI5Uzik_Yk526tj-BdgQ";

    public YoutubeVideoSnippet getYoutubeVideoSnippet(String id) {
        YoutubeVideoSnippet res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/videos?"+
                "key=%s&part=id&part=snippet&part=contentDetails&id=%s",TOKEN, id);
        HttpHeaders headers = new HttpHeaders();
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
        String uri = String.format("https://www.googleapis.com/youtube/v3/playlistItems?"+
                "key=%s&part=id&part=snippet&playlistId=%s", TOKEN, playListId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeVideoSnippetSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeVideoSnippetSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeVideoSnippetSearch.class);
        if (response.getBody() != null) {
            res=response.getBody();
        }
        return res;
    }

    public YoutubeVideoSnippetSearch getYoutubeVideoSnippets(String playListId, String pageToken) {
        YoutubeVideoSnippetSearch res = null;
        if(pageToken==null) {
            res = getYoutubeVideoSnippets(playListId);
        } else {
            String uri = String.format("https://www.googleapis.com/youtube/v3/playlistItems?" +
                    "key=%s&part=id&part=snippet&playlistId=%s&pageToken=%s", TOKEN, playListId, pageToken);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<YoutubeVideoSnippetSearch> request = new HttpEntity<>(null, headers);
            ResponseEntity<YoutubeVideoSnippetSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                    YoutubeVideoSnippetSearch.class);
            if (response.getBody() != null) {
                res = response.getBody();
            }
        }
        return res;
    }
}
