package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.comment.YoutubeComment;
import youtubeminer.model.comment.YoutubeCommentSearch;
import youtubeminer.model.videoSnippet.YoutubeVideoSnippetId;

import java.util.ArrayList;
import java.util.List;

@Service
public class commentService {


    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyDQlxgZcRO6tpIEeDXhqBEU53lHNRoetC0";

    public YoutubeComment getYoutubeComment(String videoId, String commentId) {
        YoutubeComment res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/comments?key=%spart=id&part=snippet&id=%s",
                TOKEN, commentId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeComment> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeComment> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeComment.class);
        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<YoutubeComment> getYoutubeComments(String videoId) {
        List<YoutubeComment> res = new ArrayList<>();
        String uri = String.format("https://www.googleapis.com/youtube/v3/commentThreads?"+
                        "key=%s&textFormat=plainText&part=snippet&part=id&videoId=%s", TOKEN, videoId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeComment> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeComment> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeComment.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }

    public YoutubeCommentSearch getYoutubeCommentList(YoutubeVideoSnippetId videoId) {
        YoutubeCommentSearch res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/commentThreads?"+
                "key=%s&textFormat=plainText&part=snippet&part=id&videoId=%s", TOKEN, videoId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeCommentSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeCommentSearch.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }

//    public YoutubeCommentSnippet__1 getYoutubeCommentSnippet(YoutubeCommentSnippet commentId, Integer page) {
//        YoutubeCommentSnippet__1 res = null;
//        Integer pagina = page;
//        if(pagina==null) {
//            pagina = 1;
//        }
//        String uri = String.format(, commentId, pagina);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "Bearer " + TOKEN);
//        HttpEntity<YoutubeCommentSnippet__1> request = new HttpEntity<>(null, headers);
//        ResponseEntity<YoutubeCommentSnippet__1> response = restTemplate.exchange(uri, HttpMethod.GET, request,
//                YoutubeCommentSnippet__1.class);
//        if (response.getBody() != null) {
//            res = response.getBody();
//        }
//        return res;
//    }
}
