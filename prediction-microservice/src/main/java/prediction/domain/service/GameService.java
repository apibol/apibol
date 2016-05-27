package prediction.domain.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import prediction.domain.Game;
import prediction.domain.exception.InvalidGame;

import java.text.MessageFormat;

/**
 * @author Claudio E. de Oliveira on 20/03/16.
 */
@Service
@RefreshScope
@Log4j2
public class GameService {

    @Autowired
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
            log.error(String.format("[GET-GAME-INFO] Error on retrieve game %s information",gameId));
            throw new InvalidGame(gameId);
        }
    }
    
}
