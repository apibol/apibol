package prediction.infra.data;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "prediction.domain.repository")
public class DatabaseProducer extends AbstractMongoConfiguration {

    @Value("${data.mongodb.host}")
    private String host;

    @Value("${data.mongodb.port}")
    private Integer port;

    @Value("${data.mongodb.username}")
    private String username;

    @Value("${data.mongodb.database}")
    private String database;

    @Value("${data.mongodb.password}")
    private String password;

    @Bean
    public ValidatingMongoEventListener validatingMongoEventListener() {
        return new ValidatingMongoEventListener(validator());
    }
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
    @Override
    public String getDatabaseName() {
        return database;
    }
    @Bean
    public Mongo mongo() throws Exception {
        return new MongoClient(new ServerAddress(host, port));
    }

}
