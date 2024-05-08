package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.comment.YoutubeComment;

import java.util.ArrayList;
import java.util.List;

@Service
public class commentService {


    @Autowired
    RestTemplate restTemplate;
    private static final String TOKEN = "Poner token";

    public YoutubeComment getYoutubeComment(String videoId, String commentId) {
        YoutubeComment res = null;
        String uri = String.format("https://api.youtube.com/videos/%s/comments/%s", videoId, commentId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeComment> request = new HttpEntity<>(null, headers);

        ResponseEntity<YoutubeComment> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeComment.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<YoutubeComment> getYoutubeComments(String videoId) {
        List<YoutubeComment> res = new ArrayList<>();
        String uri = String.format("https://api.youtube.com/videos/%s/comments", videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeComment> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeComment> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeComment.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
