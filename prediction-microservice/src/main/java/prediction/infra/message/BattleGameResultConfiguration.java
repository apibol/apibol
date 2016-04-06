
package prediction.infra.message;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import prediction.domain.service.BattleGameResultListener;
import prediction.domain.service.BattlePredictionDispatcher;

/**
 *  Configure Listener to process game results
 *
 * @author Claudio E. de Oliveira on on 05/04/16.
 */
@Component
public class BattleGameResultConfiguration {

    @Value("${rabbit.queue.results}")
    private String queueName;

    @Value("${rabbit.exchange.event}")
    private String topicName;

    private final ConnectionFactory connectionFactory;

    private final BattleGameResultListener battleGameResultListener;

    @Autowired
    public BattleGameResultConfiguration(ConnectionFactory connectionFactory,BattleGameResultListener battleGameResultListener) {
        this.connectionFactory = connectionFactory;
        this.battleGameResultListener = battleGameResultListener;
    }

    @Bean
    public SimpleMessageListenerContainer container() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(this.battleGameResultListener);
        return container;
    }

}
