package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.caption.YoutubeCaption;

import java.util.ArrayList;
import java.util.List;

@Service
public class captionService {
    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "Poner token";

    public YoutubeCaption getYoutubeCaption(String videoId, String texttrackId) {
        YoutubeCaption res = null;
        String uri = String.format("https://api.youtube.com/videos/%s/texttracks/%s", videoId, texttrackId);

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

    public List<YoutubeCaption> getYoutubeCaptions(String videoId) {
        List<YoutubeCaption> res = new ArrayList<>();
        String uri = String.format("https://api.youtube.com/videos/%s/texttracks", videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeCaption> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCaption> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeCaption.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }


}
