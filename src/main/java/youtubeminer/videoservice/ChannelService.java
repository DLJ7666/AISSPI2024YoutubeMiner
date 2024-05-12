package youtubeminer.videoservice;

import imports.model.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChannelService {

    @Autowired
    RestTemplate restTemplate;

    public Channel creaCanal(String id, String nombre, String descripcion, String fechaCreacion) {
        Channel channel = null;
        String uri = "http://localhost:42000/api/videominer/channels";
        Channel datos = new Channel(id, nombre,descripcion,fechaCreacion);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Channel> request = new HttpEntity<>(datos, headers);
        ResponseEntity<Channel> response = restTemplate.exchange(uri, HttpMethod.POST, request, Channel.class);
        if (response.getStatusCode().value()==201) {
            channel = response.getBody();
        }
        return channel;
    }

}
