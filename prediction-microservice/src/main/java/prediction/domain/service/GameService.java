package prediction.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.Game;
import prediction.domain.Predictor;
import prediction.domain.exception.InvalidGame;
import prediction.exception.InvalidPredictor;

import java.text.MessageFormat;

/**
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@Service
public class GameService {

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    @Value("${services.event.game.info}")
    private String gameInfoUrl;

    /**
     * Get game info
     * @param eventId
     * @param gameId
     * @return
     */
    public Game getGameInfo(String eventId,String gameId){
        String completeUrl = MessageFormat.format(gameInfoUrl,eventId,gameId);
        ResponseEntity<Game> response = this.restTemplate.getForEntity(completeUrl, Game.class);
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody();
        }else {
            throw new InvalidGame(gameId);
        }
    }
    
}
