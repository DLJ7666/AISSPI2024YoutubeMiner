package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippet;

import java.util.ArrayList;
import java.util.List;

@Service
public class videoSnippetService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "Poner token";

    public YoutubeVideoSnippet getYoutubeVideoSnippet(String id) {
        YoutubeVideoSnippet res = null;
        String uri = String.format("https://api.youtube.com/videos/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeVideoSnippet> request = new HttpEntity<>(null, headers);

        ResponseEntity<YoutubeVideoSnippet> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeVideoSnippet.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<YoutubeVideoSnippet> getYoutubeVideoSnippets() {
        List<YoutubeVideoSnippet> res = new ArrayList<>();
        String uri = "https://api.youtube.com/videos";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeVideoSnippet> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeVideoSnippet> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeVideoSnippet.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
