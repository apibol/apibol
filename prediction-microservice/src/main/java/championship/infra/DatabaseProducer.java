package championship.infra;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.springframework.context.annotation.Bean;

import java.net.MalformedURLException;

/**
 * @author Claudio E. de Oliveira on 27/02/16.
 */
public class DatabaseProducer {

    @Bean(name="couchDbConnector")
    public CouchDbConnector databaseProducer() throws MalformedURLException {
        HttpClient httpClient = new StdHttpClient.Builder().url("http://localhost:5984").build();
        CouchDbInstance instance = new StdCouchDbInstance(httpClient);
        CouchDbConnector connector = new StdCouchDbConnector("prediction",instance);
        connector.createDatabaseIfNotExists();
        return connector;
    }

}
