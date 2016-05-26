package prediction.domain.service;

import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
        log.info(String.format("[SENDER-SERVICE] Sending new user points for user %s",userPoints.getNickname()));
        this.rabbitTemplate.convertAndSend(this.queueName,userPoints);
        log.info(String.format("[SENDER-SERVICE] New points sent for %s",userPoints.getNickname()));
    }

}
