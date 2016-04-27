package event.domain.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Sender Service (RabbitMQ)
 * @author Claudio E. de Oliveira on on 30/03/16.
 */
@Component
public class SenderBattleResultService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.results}")
    private String resultsQueue;

    @Autowired
    public SenderBattleResultService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send battle result to topic
     * @param battleResult
     */
    public void sendResult(BattleResult battleResult){
        this.rabbitTemplate.convertAndSend(this.resultsQueue,battleResult);
    }

}
