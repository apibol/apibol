package event.application;

import event.infra.data.DatabaseProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Claudio E. de Oliveira on 24/02/16.
 */
@SpringCloudApplication
@Import(DatabaseProducer.class)
@ComponentScan(basePackages = "event")
@EnableZuulProxy
@EnableSwagger2
@EnableHystrix
@EnableResourceServer
public class EventApplication extends WebMvcConfigurerAdapter implements HealthIndicator {

    @Override
    public Health health() {
        return Health.up().build();
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(EventApplication.class, args);
    }

    @Bean
    public ApiKey apiKey() {
        return new ApiKey("api-key", "eef561e2697a25553fd643ef09be2066be4042ede0b2695739abf1b629a06388", "header");
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
                .apiInfo(new ApiInfo("Bolão API - Events","API para gerenciamento de bolões - Eventos","0.0.1","",contact(),"",""));
    }

    @Bean
    public Contact contact(){
        return new Contact("Claudio E. de Oliveira","www.apibol.com.br","claudioed.oliveira@gmail.com");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
