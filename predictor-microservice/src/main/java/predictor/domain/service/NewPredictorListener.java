package predictor.domain.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import predictor.domain.Event;

/**
 * New predictor by event listener
 *
 * @author Claudio E. de Oliveira on on 26/04/16.
 */
@Component
@Log4j2
public class NewPredictorListener implements MessageListener {

    private final PredictorByEventService predictorByEventService;

    @Autowired
    public NewPredictorListener(PredictorByEventService predictorByEventService) {
        this.predictorByEventService = predictorByEventService;
    }

    @Override
    public void onMessage(Message message) {
        log.info("[RECEIVE-NEW-PREDICTOR-BY-EVENT] Receive new event");
        String json = new String(message.getBody());
        Event event = new Gson().fromJson(json, Event.class);
        this.predictorByEventService.byEvent(event);
    }

}
