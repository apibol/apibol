package user.infra;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Configuration
public class DataSourceProducer {
    

    @Bean(name = "dbConnector")
    public void dbConnector() {
        RethinkDB rethinkDB = RethinkDB.r;
        Connection connection = rethinkDB.connection().hostname("localhost").port(28015).connect();
        rethinkDB.db("user").tableCreate("users").run(connection);
    }

}
