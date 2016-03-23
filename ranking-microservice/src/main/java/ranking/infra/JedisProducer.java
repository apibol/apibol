package ranking.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Jedis Producers 
 * @author Claudio E. de Oliveira on 22/03/16.
 */
@Configuration
@RefreshScope
public class JedisProducer {
    
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private Integer port;
    
    @Bean(name = "jedisPool")
    public JedisPool jedisPool(){
        return new JedisPool(this.host,this.port);
    }

}
