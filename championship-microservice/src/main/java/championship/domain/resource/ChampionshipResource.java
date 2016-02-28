package championship.domain.resource;

import championship.domain.Championship;
import championship.domain.service.ChampionshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@RestController
@RequestMapping("/championship")
public class ChampionshipResource {
    
    private final ChampionshipService championshipService;

    @Autowired
    public ChampionshipResource(ChampionshipService championshipService) {
        this.championshipService = championshipService;
    }
    
    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<Championship> create(@RequestBody Championship championship){
        Championship savedChampionship = this.championshipService.create(championship);
        return new ResponseEntity<>(savedChampionship, HttpStatus.CREATED);
    }
    
}
