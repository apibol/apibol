package championship.application;

import championship.infra.DatabaseProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringBootApplication
@Import(DatabaseProducer.class)
@ComponentScan(basePackages = "championship")
@EnableDiscoveryClient
@EnableZuulProxy
@EnableHystrix
public class ChampionshipApplication implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ChampionshipApplication.class, args);
    }

}
