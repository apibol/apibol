package prediction.domain.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import prediction.domain.UserPoints;

/**
 * Sender Service (RabbitMQ)
 * @author Claudio E. de Oliveira on on 30/03/16.
 */
@Component
public class SenderService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.userpoints}")
    private String queueName;

    @Autowired
    public SenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Send user points to update in ranking microservice
     * @param userPoints
     */
    public void sendPoints(UserPoints userPoints){
        this.rabbitTemplate.convertAndSend(this.queueName,userPoints);
    }

}
