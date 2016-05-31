package auth.infra.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * Database utils producer
 *
 * @author Claudio E. de Oliveira on on 18/04/16.
 */
@Configuration
public class DataSourceProducer {

    @Bean
    @Profile(value = "default")
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3307/auth");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setMaxTotal(10);
        return dataSource;
    }

    @Bean
    @Profile(value = "docker")
    public DataSource containerDataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://authdb:3307/auth");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setMaxTotal(10);
        return dataSource;
    }

    @Bean
    @Profile(value = "default")
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(this.dataSource());
    }

    @Bean
    @Profile(value = "docker")
    public JdbcTemplate containerJdbcTemplate(){
        return new JdbcTemplate(this.containerDataSource());
    }

}
