package prediction.domain.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prediction.domain.BattlePrediction;
import prediction.domain.resource.model.BattlePredictionDTO;
import prediction.domain.service.BattlePredictionService;

import java.util.List;

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

    @RequestMapping(value = "/{predictionId}", method = RequestMethod.DELETE)
    public void deletePrediction(@PathVariable("predictionId") String predictionId) {
        this.battlePredictionService.deletePredictionById(predictionId);
    }

    @RequestMapping(value = "/predictor/{predictorId}", method = RequestMethod.POST)
    public ResponseEntity<BattlePrediction> makePrediction(@PathVariable("predictorId") String predictorId, @RequestBody BattlePredictionDTO battlePrediction) {
        battlePrediction.assignPredictor(predictorId);
        return new ResponseEntity<>(this.battlePredictionService.doPrediction(battlePrediction), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/predictor/{predictorId}", method = RequestMethod.GET)
    public ResponseEntity<List<BattlePrediction>> findByPredictor(@PathVariable("predictorId") String predictorId) {
        return new ResponseEntity<>(this.battlePredictionService.findByPredictorId(predictorId), HttpStatus.OK);
    }

    @RequestMapping(value = "/predictor/{predictorId}/game/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<List<BattlePrediction>> findByPredictionsByGame(@PathVariable("predictorId") String predictorId,@PathVariable("gameId") String gameId) {
        return new ResponseEntity<>(this.battlePredictionService.findByPredictorIdAndGame(predictorId,gameId), HttpStatus.OK);
    }

}
