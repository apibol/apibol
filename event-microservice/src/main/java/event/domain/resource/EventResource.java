package event.domain.resource;

import event.domain.Event;
import event.domain.resource.model.EventDTO;
import event.domain.service.EventService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@RestController
@RequestMapping("/event")
@Api(value = "/event", description = "Operations about events")
public class EventResource {
    
    private final EventService eventService;

    @Autowired
    public EventResource(EventService eventService) {
        this.eventService = eventService;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Event> create(@RequestBody EventDTO championship){
        Event savedEvent = this.eventService.create(championship);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Event>> all(){
        return new ResponseEntity<>(this.eventService.all(), HttpStatus.OK);
    }
    
}
