package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class userService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "Poner token";

    public YoutubeUser getYoutubeUser(String id) {
        YoutubeUser res = null;
        String uri = String.format("https://api.youtube.com/users/%s", id);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeUser> request = new HttpEntity<>(null, headers);

        ResponseEntity<YoutubeUser> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeUser.class);

        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<YoutubeUser> getYoutubeUsers() {
        List<YoutubeUser> res = new ArrayList<>();
        String uri = "https://api.youtube.com/users";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeUser> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeUser> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeUser.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
