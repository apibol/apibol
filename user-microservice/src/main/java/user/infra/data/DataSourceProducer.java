package user.infra.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Database Connections producer
 *
 * @author Claudio E. de Oliveira on on 18/04/16.
 */
@Configuration
public class DataSourceProducer {

    @Bean
    @Profile(value = "default")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/user");
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
        dataSource.setUrl("jdbc:mysql://dbinstance:3306/user");
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
