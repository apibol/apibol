package prediction.infra.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Rest template producer
 *
 * @author Claudio E. de Oliveira on on 27/05/16.
 */
@Configuration
public class RestTemplateProducer {

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
