package user.domain.repository;

import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import user.domain.User;

import java.util.UUID;

/**
 * @author Claudio E. de Oliveira on 25/02/16.
 */
@Service
public class UserRepository {
    
    private final Bucket userBucket;

    private final Gson gson;
    
    @Autowired
    public UserRepository(@Qualifier("userBucket")Bucket userBucket,Gson gson) {
        this.userBucket = userBucket;
        this.gson = gson;
    }
    
    public User addUser(String email,String nickname){
        String id = UUID.randomUUID().toString();
        User newUser = User.createUser(nickname, email, id);
        this.userBucket.store(email,newUser);
        return newUser;
    }
    
    public User getUser(String email) throws RiakRetryFailedException {
        String userJson = this.userBucket.fetch(email).execute().getValueAsString();
        return this.gson.fromJson(userJson, User.class);
    }
    
}
