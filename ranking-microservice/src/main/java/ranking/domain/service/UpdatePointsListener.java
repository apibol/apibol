package ranking.domain.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ranking.domain.RequestUpdateUserPoints;

/**
 * @author Claudio E. de Oliveira on on 31/03/16.
 */
@Service
@Log4j2
public class UpdatePointsListener implements MessageListener {

    private final UpdateUserPointsService updateUserPointsService;

    @Autowired
    public UpdatePointsListener(UpdateUserPointsService updateUserPointsService) {
        this.updateUserPointsService = updateUserPointsService;
    }

    @Override
    public void onMessage(Message message) {
        log.info("[RECEIVE-UPDATE-USER-POINTS] Receive update user points");
        String json = new String(message.getBody());
        RequestUpdateUserPoints updateUserPoints = new Gson().fromJson(json, RequestUpdateUserPoints.class);
        this.updateUserPointsService.update(updateUserPoints);
    }

}
