package user.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import user.infra.DatabaseConfig;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringBootApplication
@Import(DatabaseConfig.class)
@ComponentScan(basePackages = "user")
@EnableDiscoveryClient
@EnableHystrix
public class UserApplication implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(UserApplication.class, args);
    }

}
