package event.domain.service;

import event.domain.BattleResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Sender Service (RabbitMQ)
 * @author Claudio E. de Oliveira on on 30/03/16.
 */
@Component
public class SenderService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.results}")
    private String queueName;

    @Autowired
    public SenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send battle result to topic
     * @param battleResult
     */
    public void sendResult(BattleResult battleResult){
        this.rabbitTemplate.convertAndSend(this.queueName,battleResult);
    }

}
