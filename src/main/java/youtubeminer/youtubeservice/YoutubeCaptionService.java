package youtubeminer.youtubeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.caption.YoutubeCaption;
import youtubeminer.model.caption.YoutubeCaptionSearch;

@Service
public class YoutubeCaptionService {
    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyCUWuAfA7E_Z8VZI5Uzik_Yk526tj-BdgQ";

    public YoutubeCaption getYoutubeCaption(String captionId, String videoId) {
        YoutubeCaption res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/captions?"+
                "key=%s&part=id&part=snippet&videoId=%s&id=%s", TOKEN, videoId, captionId);
        HttpHeaders headers = new HttpHeaders();
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
        String uri = String.format("https://www.googleapis.com/youtube/v3/captions?"+
                "key=%s&part=id&part=snippet&videoId=%s", TOKEN, videoId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeCaptionSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeCaptionSearch.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }

    public YoutubeCaptionSearch getYoutubeCaptions(String videoId, String pageToken) {
        YoutubeCaptionSearch res = null;
        if(pageToken==null) {
            res = getYoutubeCaptions(videoId);
        } else {
            String uri = String.format("https://www.googleapis.com/youtube/v3/captions?"+
                    "key=%s&part=id&part=snippet&videoId=%s&pageToken=%s", TOKEN, videoId, pageToken);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<YoutubeCaptionSearch> request = new HttpEntity<>(null, headers);
            ResponseEntity<YoutubeCaptionSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                    YoutubeCaptionSearch.class);
            if (response.getBody() != null) {
                res = response.getBody();
            }
        }
        return res;
    }
}
