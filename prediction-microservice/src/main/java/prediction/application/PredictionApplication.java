package prediction.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import prediction.infra.DatabaseProducer;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringCloudApplication
@Import(DatabaseProducer.class)
@ComponentScan(basePackages = "prediction")
@EnableZuulProxy
public class PredictionApplication implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PredictionApplication.class, args);
    }

}
