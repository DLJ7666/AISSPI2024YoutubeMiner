package youtubeminer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.channel.YoutubeChannel;

import java.util.ArrayList;
import java.util.List;

@Service
public class channelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyDQlxgZcRO6tpIEeDXhqBEU53lHNRoetC0";

    public YoutubeChannel getYoutubeChannel(String id){
        YoutubeChannel res = null;
        String uri = String.format(
                "https://www.googleapis.com/youtube/v3/channels?part=id&part=snippet&part=contentDetails&id=%s", id);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer" + TOKEN);
        HttpEntity<YoutubeChannel> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeChannel> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeChannel.class);
        if(response.getBody() != null){
            res = response.getBody();
        }
        return res;
    }

    public List<YoutubeChannel> getYoutubeChannels() {
        List<YoutubeChannel> res = new ArrayList<>();
        String uri = "https://www.googleapis.com/youtube/v3/channels?part=id&id=%s";
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + TOKEN);
        HttpEntity<YoutubeChannel> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeChannel> response = restTemplate.exchange(uri, HttpMethod.GET, request, YoutubeChannel.class);
        if (response.getBody() != null) {
            res.add(response.getBody());
        }
        return res;
    }
}
