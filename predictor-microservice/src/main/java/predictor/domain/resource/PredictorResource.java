package predictor.domain.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.service.PredictorService;

import java.security.Principal;
import java.util.List;

/**
 * Predictor Resource
 *
 * @author Claudio E. de Oliveira on 13/03/16.
 */
@RestController
@RequestMapping(value = "/")
@Api(value = "/predictor", description = "Operations to associate participants in events")
public class PredictorResource {

    private final PredictorService predictorService;

    @Autowired
    public PredictorResource(PredictorService predictorService) {
        this.predictorService = predictorService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "Create a predictor", nickname = "Create Predictor")
    @ApiResponses({
            @ApiResponse(message = "Predictor created with success", code = 201),
            @ApiResponse(message = "Check your parameters", code = 400)
    })
    public ResponseEntity<Predictor> create(@RequestBody PredictorDTO predictorDTO) {
        Predictor predictor = this.predictorService.create(predictorDTO);
        return new ResponseEntity<>(predictor, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Join participant in a event", nickname = "Join participant")
    @ApiResponses({
            @ApiResponse(message = "Participant joined with success", code = 200),
            @ApiResponse(message = "Check your parameters", code = 400),
            @ApiResponse(message = "Predictor not found", code = 404)
    })
    @RequestMapping(value = "/{predictorId}/join", method = RequestMethod.POST)
    public ResponseEntity<Predictor> join(@PathVariable("predictorId") String predictorId,Principal principal,@RequestBody HashDTO hashDTO) {
        JoinPredictorDTO joinPredictorDTO = new JoinPredictorDTO();
        joinPredictorDTO.setUserId(principal.getName());
        Predictor predictor = this.predictorService.join(predictorId, hashDTO.getHash(), joinPredictorDTO);
        return new ResponseEntity<>(predictor, HttpStatus.OK);
    }

    @ApiOperation(value = "Retrieve all predictors", nickname = "All predictors")
    @ApiResponses({
            @ApiResponse(message = "Listed with success", code = 200),
            @ApiResponse(message = "Any predictors found", code = 404)
    })
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Predictor>> all() {
        return new ResponseEntity<>(this.predictorService.all(), HttpStatus.OK);
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

    @ApiOperation(value = "Delete predictor by id ", nickname = "Delete predictor by id")
    @ApiResponses({
            @ApiResponse(message = "Predictor retrieved with success", code = 200)
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id") String id, Principal credential) {
        this.predictorService.deletePredictor(id, credential.getName());
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

    @ApiOperation(value = "Get participant predictors ", nickname = "Participant predictors")
    @RequestMapping(value = "/my-predictors", method = RequestMethod.GET)
    public ResponseEntity<List<Predictor>> myPredictors(Principal principal) {
        return new ResponseEntity<>(this.predictorService.myPredictors(principal.getName()), HttpStatus.OK);
    }

}
