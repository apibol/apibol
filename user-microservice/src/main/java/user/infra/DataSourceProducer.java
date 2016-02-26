package user.infra;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.bucket.Bucket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Configuration
public class DataSourceProducer {
    
    @Bean(name = "riakClient")
    public IRiakClient riakClient() throws RiakException {
        return RiakFactory.httpClient();
    }
    
    @Bean(name = "userBucket")
    public Bucket userBucket() throws RiakException {
        return riakClient().fetchBucket("user").execute();
    }

}
