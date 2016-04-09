package predictor.domain.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import predictor.domain.Participant;
import predictor.domain.Predictor;
import predictor.domain.service.PredictorService;

import java.util.List;

/**
 * Predictor Resource
 *
 * @author Claudio E. de Oliveira on 13/03/16.
 */
@RestController
@RequestMapping(value = "/predictor")
public class PredictorResource {

    private final PredictorService predictorService;

    @Autowired
    public PredictorResource(PredictorService predictorService) {
        this.predictorService = predictorService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Predictor> create(@RequestBody PredictorDTO predictorDTO) {
        Predictor predictor = this.predictorService.create(predictorDTO);
        return new ResponseEntity<>(predictor, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{predictorId}/join",method = RequestMethod.POST)
    public ResponseEntity<Predictor> join(@PathVariable("predictorId")String predictorId,@RequestBody JoinPredictorDTO joinPredictorDTO) {
        Predictor predictor = this.predictorService.join(predictorId,joinPredictorDTO);
        return new ResponseEntity<>(predictor, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Predictor>> all() {
        return new ResponseEntity<>(this.predictorService.all(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Predictor> findOne(@PathVariable("id")String id) {
        return new ResponseEntity<>(this.predictorService.findOne(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public void deleteOne(@PathVariable("id")String id) {
        this.predictorService.deletePredictor(id);
    }

    @RequestMapping(value = "/{id}/participant/{participantId}",method = RequestMethod.GET)
    public ResponseEntity<Participant> participantInfo(@PathVariable("id")String id,@PathVariable("participantId")String participantId) {
        return new ResponseEntity<>(this.predictorService.findByPredictorAndParticipantId(id,participantId), HttpStatus.OK);
    }
    
}
