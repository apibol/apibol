package prediction.domain.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import prediction.domain.BattlePrediction;
import prediction.domain.resource.model.BattlePredictionDTO;
import prediction.domain.service.BattlePredictionService;

import java.security.Principal;
import java.util.List;

/**
 * Operations about predictions
 *
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "/predictions", description = "Operations to receive and search predictions")
public class BattlePredictionResource {

    private final BattlePredictionService battlePredictionService;

    @Autowired
    public BattlePredictionResource(BattlePredictionService battlePredictionService) {
        this.battlePredictionService = battlePredictionService;
    }

    @RequestMapping(value = "/{predictionId}", method = RequestMethod.DELETE)
    public void deletePrediction(@PathVariable("predictionId") String predictionId, Principal credential) {
        this.battlePredictionService.deletePredictionById(predictionId, credential.getName());
    }

    @RequestMapping(value = "/predictor/{predictorId}/game/{gameId}", method = RequestMethod.POST)
    public ResponseEntity<BattlePrediction> doPrediction(@PathVariable("predictorId") String predictorId,@PathVariable("gameId") String gameId, @RequestBody BattlePredictionDTO battlePrediction, Principal credential) {
        return new ResponseEntity<>(this.battlePredictionService.doPrediction(predictorId,gameId,battlePrediction, credential.getName()), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Retrieve predictions by predictor", nickname = "Predictions by Predictor")
    @ApiResponses({
            @ApiResponse(message = "Listed with success", code = 200),
            @ApiResponse(message = "Predictions not found", code = 404)
    })
    @RequestMapping(value = "/predictor/{predictorId}", method = RequestMethod.GET)
    public ResponseEntity<List<BattlePrediction>> findByPredictor(@PathVariable("predictorId") String predictorId, Principal credential) {
        return new ResponseEntity<>(this.battlePredictionService.findParticipantPredictions(predictorId, credential.getName()), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve prediction in predictor by game", nickname = "Prediction game")
    @ApiResponses({
            @ApiResponse(message = "Listed with success", code = 200),
            @ApiResponse(message = "Prediction not found", code = 404)
    })
    @RequestMapping(value = "/predictor/{predictorId}/game/{gameId}", method = RequestMethod.GET)
    public ResponseEntity<List<BattlePrediction>> findByPredictionsByGame(@PathVariable("predictorId") String predictorId, @PathVariable("gameId") String gameId, Principal credential) {
        return new ResponseEntity<>(this.battlePredictionService.findByPredictorIdAndGame(predictorId, gameId, credential.getName()), HttpStatus.OK);
    }

}
