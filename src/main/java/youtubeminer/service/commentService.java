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

    private static final String TOKEN = "AIzaSyCUWuAfA7E_Z8VZI5Uzik_Yk526tj-BdgQ";

    public YoutubeComment getYoutubeComment(String commentId) {
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

    public YoutubeCommentSearch getYoutubeComments(String videoId) {
        YoutubeCommentSearch res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/commentThreads?"+
                "key=%s&textFormat=plainText&part=snippet&part=id&videoId=%s", TOKEN, videoId);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeCommentSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeCommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeCommentSearch.class);
        if (response.getBody() != null) {
            res = response.getBody();
        }
        return res;
    }

    public YoutubeCommentSearch getYoutubeComments(String videoId, String pageToken) {
        YoutubeCommentSearch res = null;
        if(pageToken==null) {
            res = getYoutubeComments(videoId);
        } else {
            String uri = String.format("https://www.googleapis.com/youtube/v3/commentThreads?key=%s"+
                    "&textFormat=plainText&part=snippet&part=id&videoId=%s&pageToken=%s", TOKEN, videoId, pageToken);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<YoutubeCommentSearch> request = new HttpEntity<>(null, headers);
            ResponseEntity<YoutubeCommentSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                    YoutubeCommentSearch.class);
            if (response.getBody() != null) {
                res = response.getBody();
            }
        }
        return res;
    }

}
