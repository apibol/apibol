package predictor.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

/**
 * It represents a group of participants grouped in on event.
 *
 * @author Claudio E. de Oliveira on 10/03/16.
 */
@Data
@Document(collection = "predictor")
public class Predictor {

    @Id
    private String id;

    private String eventId;

    private Set<Participant> participants = new HashSet<>();

    Predictor() {
    }

    private Predictor(String eventId, Participant participant) {
        this.eventId = eventId;
        this.addParticipant(participant);
    }

    public static Predictor createPredictor(String eventId, Participant participant) {
        return new Predictor(eventId, participant);
    }

    public Predictor addParticipant(Participant participant) {
        this.participants.add(participant);
        return this;
    }

    public Predictor removeParticipant(Participant participant) {
        this.participants.remove(participant);
        return this;
    }

    public Participant participantInfo(String participantId) {
        return this.participants.stream().filter(part -> part.getId().equals(participantId)).findFirst().get();
    }

}
