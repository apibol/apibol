package prediction.domain.service;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import prediction.domain.BattleResult;

/**
 * Listener to process game result
 *
 * @author Claudio E. de Oliveira on on 05/04/16.
 */
@Log4j2
@Component
public class BattleGameResultListener implements MessageListener {

    private final BattlePredictionDispatcher battlePredictionDispatcher;

    @Autowired
    public BattleGameResultListener(BattlePredictionDispatcher battlePredictionDispatcher) {
        this.battlePredictionDispatcher = battlePredictionDispatcher;
    }

    @Override
    public void onMessage(Message message) {
        log.info("[RECEIVE-GAME-RESULT] Receive game result");
        String json = new String(message.getBody());
        final BattleResult battleResult = new Gson().fromJson(json, BattleResult.class);
        this.battlePredictionDispatcher.computePoints(battleResult);
    }

}
