package prediction.infra.data;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.event.ValidatingMongoEventListener;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import prediction.infra.converter.*;

import java.util.Arrays;

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

    @Bean
    public CustomConversions customConversions() {
        return new CustomConversions(Arrays.asList(new InstantToLongConverter(), new LongToInstantConverter(),
                new LocalDateToStringConverter(), new StringToLocalDateConverter(), new LocalDateTimeToStringConverter(),
                new StringToLocalDateTimeConverter()));
    }

    @Bean
    public MappingMongoConverter mongoConverter() throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory());
        MappingMongoConverter mongoConverter = new MappingMongoConverter(dbRefResolver, mappingContext);
        mongoConverter.setCustomConversions(customConversions());
        mongoConverter.afterPropertiesSet();
        return mongoConverter;
    }

}
