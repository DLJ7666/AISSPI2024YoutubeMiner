package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.caption.YoutubeCaptionSearch;

import java.util.ArrayList;
import java.util.List;

@Service
public class captionService {
    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyDQlxgZcRO6tpIEeDXhqBEU53lHNRoetC0";

    public YoutubeCaption getYoutubeCaption(String captionId) {
        YoutubeCaption res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/captions?id=%s", captionId);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeCaption> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCaption> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeCaption.class);
        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public YoutubeCaptionSearch getYoutubeCaptions(String videoId) {
        YoutubeCaptionSearch res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/captions?part=id&part=snippet&videoId=%s",
                videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeCaptionSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeCaptionSearch.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }


}
