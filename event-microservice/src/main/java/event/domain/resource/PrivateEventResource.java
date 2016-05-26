package event.domain.resource;

import event.domain.Game;
import event.domain.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Private Events Resources
 *
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@RestController
@RequestMapping("/private")
@Api(value = "/event", description = "Operations about events")
public class PrivateEventResource {

    private final EventService eventService;

    @Autowired
    public PrivateEventResource(EventService eventService) {
        this.eventService = eventService;
    }

    @RequestMapping(value = "/{id}/game/{gameId}", method = RequestMethod.GET)
    @ApiOperation(value = "Get game in Event", nickname = "Get Game")
    public ResponseEntity<Game> findGameById(@PathVariable("id") String eventId, @PathVariable("gameId") String gameId) {
        return new ResponseEntity<>(this.eventService.findGameById(eventId, gameId), HttpStatus.OK);
    }

}
