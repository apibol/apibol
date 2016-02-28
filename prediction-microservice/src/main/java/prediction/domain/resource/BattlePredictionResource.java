package prediction.domain.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prediction.domain.BattlePrediction;
import prediction.domain.service.BattlePredictionService;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@RestController
@RequestMapping(value = "/prediction")
public class BattlePredictionResource {

    private final BattlePredictionService battlePredictionService;

    @Autowired
    public BattlePredictionResource(BattlePredictionService battlePredictionService) {
        this.battlePredictionService = battlePredictionService;
    }

    @RequestMapping(value = "/championship/{championshipId}/game/{gameId}",method = RequestMethod.POST)
    public ResponseEntity<BattlePrediction> makePrediction(@PathVariable("championshipId")String championshipId,@PathVariable("gameId")String gameId,@RequestBody BattlePrediction battlePrediction) {
        return new ResponseEntity<BattlePrediction>(battlePrediction, HttpStatus.CREATED);
    }

}
