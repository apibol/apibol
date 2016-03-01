package discovery.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class DiscoveryApplication implements HealthIndicator{

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplication.class, args);
    }

    @Override
    public Health health() {
        return Health.up().build();
    }
    
}
