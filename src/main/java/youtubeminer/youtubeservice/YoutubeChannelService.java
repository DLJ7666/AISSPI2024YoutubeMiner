package youtubeminer.youtubeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import youtubeminer.model.channel.YoutubeChannel;
import youtubeminer.model.channel.YoutubeChannelSearch;

@Service
public class YoutubeChannelService {

    @Autowired
    RestTemplate restTemplate;

    private static final String TOKEN = "AIzaSyCUWuAfA7E_Z8VZI5Uzik_Yk526tj-BdgQ";

    public YoutubeChannel getYoutubeChannel(String id){
        YoutubeChannelSearch aux = null;
        YoutubeChannel res = null;
        String uri = String.format("https://www.googleapis.com/youtube/v3/channels?"+
                "key=%s&part=id&part=snippet&part=contentDetails&id=%s", TOKEN, id);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<YoutubeChannelSearch> request = new HttpEntity<>(null, headers);
        ResponseEntity<YoutubeChannelSearch> response = restTemplate.exchange(uri, HttpMethod.GET, request,
                YoutubeChannelSearch.class);
        if(response.getBody() != null){
            aux = response.getBody();
            if (!aux.getItems().isEmpty()) {
                res = aux.getItems().get(0);
            }
        }
        return res;
    }
}
