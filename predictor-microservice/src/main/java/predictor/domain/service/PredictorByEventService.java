package predictor.domain.service;

import org.springframework.stereotype.Service;
import predictor.domain.Event;
import predictor.domain.Predictor;

import java.util.UUID;

/**
 * Convert the event on predictor
 *
 * @author Claudio E. de Oliveira on on 26/04/16.
 */
@Service
public class PredictorByEventService {

    /**
     * New predictor by event
     *
     * @param event
     * @return
     */
    public Predictor byEvent(Event event){
        Predictor newPredictor = Predictor.createPredictor(UUID.randomUUID().toString(),event.getId(), event.getOpen(), event.getOwner());
        event.getParticipants().forEach(participant -> newPredictor.newInvitation(participant.getId()));
        return newPredictor;
    }

}
