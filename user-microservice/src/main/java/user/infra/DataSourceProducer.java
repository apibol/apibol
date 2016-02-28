package user.infra;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.gen.ast.Db;
import com.rethinkdb.net.Connection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Configuration
public class DataSourceProducer {
    
    @Bean(name = "dbConnector")
    public Connection dbConnector() {
        RethinkDB rethinkDB = rethinkDB();
        return rethinkDB.connection().hostname("localhost").port(28015).connect();
    }
    
    @Bean(name = "rethinkDB")
    public RethinkDB rethinkDB(){
        return RethinkDB.r;
    }

    @Bean(name = "database")
    public Db database(){
        return rethinkDB().db("users");
    }

}
