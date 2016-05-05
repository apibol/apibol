package predictor.infra.message;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import predictor.domain.service.NewPredictorListener;

/**
 * New predictor listener
 *
 * @author Claudio E. de Oliveira on on 26/04/16.
 */
@Component
public class NewPredictorListenerConfiguration {

    @Value("${rabbit.queue.event}")
    private String newPredictorQueue;

    private final ConnectionFactory connectionFactory;

    private final NewPredictorListener newPredictorListener;

    @Autowired
    public NewPredictorListenerConfiguration(ConnectionFactory connectionFactory,NewPredictorListener newPredictorListener) {
        this.connectionFactory = connectionFactory;
        this.newPredictorListener = newPredictorListener;
    }

    @Bean(name = "newPredictorContainer")
    public SimpleMessageListenerContainer newPredictorContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(newPredictorQueue);
        container.setMessageListener(this.newPredictorListener);
        return container;
    }

}
