package user.infra;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Configuration
public class UtilsInfraProducer {
    
    @Bean
    private Gson gson(){
        return new Gson();
    }

}
