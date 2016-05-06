package prediction.domain;

import domain.SystemUser;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Claudio E. de Oliveira on 19/03/16.
 */
@Data
public class Predictor {
    
    private String id;

    private String eventId;

    private Set<User> participants = new HashSet<>();

    /**
     * Indicates if user is predictor participant
     *
     * @param systemUser
     * @return
     */
    public boolean isParticipant(SystemUser systemUser){
        return this.participants.stream().anyMatch(el -> el.getNickname().equals(systemUser.getNickname()));
    }

}
