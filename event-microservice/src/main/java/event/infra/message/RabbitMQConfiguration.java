package event.infra.message;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
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
    private String queueName;

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
    public TopicExchange exchange() {
        return new TopicExchange(this.topicName);
    }

    @Bean
    public Queue queue() {
        return new Queue(this.queueName, false);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(this.queueName);
    }

}
