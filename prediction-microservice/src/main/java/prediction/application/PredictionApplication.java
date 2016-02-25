package prediction.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringBootApplication
public class PredictionApplication implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PredictionApplication.class, args);
    }

}
