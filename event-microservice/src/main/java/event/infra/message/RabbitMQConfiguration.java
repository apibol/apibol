package event.infra.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Create RabbitMQ Resources
 *
 * @author Claudio E. de Oliveira on on 30/03/16.
 */
@Configuration
@RefreshScope
public class RabbitMQConfiguration {

    @Value("${rabbit.queue.results}")
    private String resultsQueue;

    @Value("${rabbit.queue.event}")
    private String newEventQueue;

    @Value("${rabbit.exchange.event}")
    private String topicName;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(this.connectionFactory);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange eventExchange() {
        return new TopicExchange(this.topicName);
    }

    @Bean(name = "resultsQueue")
    public Queue resultsQueue() {
        return new Queue(this.resultsQueue, false);
    }

    @Bean(name = "newEventQueue")
    public Queue newEventQueue() {
        return new Queue(this.newEventQueue, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean(name = "resultsBinding")
    public Binding resultsBinding() {
        return BindingBuilder.bind(resultsQueue()).to(eventExchange()).with(this.resultsQueue);
    }

    @Bean(name = "newEventBinding")
    public Binding newEventBinding() {
        return BindingBuilder.bind(newEventQueue()).to(eventExchange()).with(this.newEventQueue);
    }

}
