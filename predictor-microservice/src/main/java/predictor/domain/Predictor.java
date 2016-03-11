package predictor.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Data
@Document(collection = "predictor")
public class Predictor {

    @Id
    private String id;
    
    private String eventId;
    
    private String name;
    
    private Set<Participant> participants = new HashSet<>();

    public Predictor addParticipant(Participant participant){
        this.participants.add(participant);
        return this;
    }
    
}
