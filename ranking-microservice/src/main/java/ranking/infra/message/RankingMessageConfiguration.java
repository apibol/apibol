package ranking.infra.message;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ranking.domain.service.UpdatePointsListener;

/**
 * Update Points Listener configuration
 * @author Claudio E. de Oliveira on on 05/04/16.
 */
@Component
public class RankingMessageConfiguration {

    @Value("${rabbit.queue.userpoints}")
    private String queueName;

    private final ConnectionFactory connectionFactory;

    private final UpdatePointsListener updatePointsListener;

    @Autowired
    public RankingMessageConfiguration(ConnectionFactory connectionFactory, UpdatePointsListener updatePointsListener) {
        this.connectionFactory = connectionFactory;
        this.updatePointsListener = updatePointsListener;
    }

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(this.updatePointsListener);
        return container;
    }

}
