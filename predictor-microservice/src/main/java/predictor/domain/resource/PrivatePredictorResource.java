package predictor.domain.resource;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.service.PredictorService;

/**
 * Predictor Resource
 *
 * @author Claudio E. de Oliveira on 13/03/16.
 */
@RestController
@RequestMapping(value = "/private")
public class PrivatePredictorResource {

    private final PredictorService predictorService;

    @Autowired
    public PrivatePredictorResource(PredictorService predictorService) {
        this.predictorService = predictorService;
    }

    @ApiOperation(value = "Get participant of predictor by id ", nickname = "Get predictor participant")
    @ApiResponses({
            @ApiResponse(message = "Predictor retrieved with success", code = 200),
            @ApiResponse(message = "Predictor not found", code = 404)
    })
    @RequestMapping(value = "/{id}/participant/{participantId}", method = RequestMethod.GET)
    public ResponseEntity<Participant> participantInfo(@PathVariable("id") String id, @PathVariable("participantId") String participantId) {
        return new ResponseEntity<>(this.predictorService.findByPredictorAndParticipantId(id, participantId), HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve predictor by id ", nickname = "Get predictor by id")
    @ApiResponses({
            @ApiResponse(message = "Predictor retrieved with success", code = 200),
            @ApiResponse(message = "Predictor not found", code = 404)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Predictor> findOne(@PathVariable("id") String id) {
        return new ResponseEntity<>(this.predictorService.findOne(id), HttpStatus.OK);
    }

}
