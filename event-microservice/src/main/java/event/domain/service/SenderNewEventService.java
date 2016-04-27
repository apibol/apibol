package event.domain.service;

import event.domain.Event;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * New Event notifier
 *
 * @author Claudio E. de Oliveira on on 26/04/16.
 */
@Component
public class SenderNewEventService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbit.queue.event}")
    private String newEventQueue;

    @Autowired
    public SenderNewEventService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Notify the new event creation
     *
     * @param event
     */
    public void notifyNewEvent(Event event){
        this.rabbitTemplate.convertAndSend(this.newEventQueue,event);
    }

}
