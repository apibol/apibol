package predictor.domain;

import lombok.Getter;

/**
 * Join predictor request
 *
 * @author Claudio E. de Oliveira on on 10/05/16.
 */
public class JoinPredictorRequest {

    @Getter
    private final Participant participant;

    @Getter
    private final String hash;

    private JoinPredictorRequest(Participant participant, String hash) {
        this.participant = participant;
        this.hash = hash;
    }

    public static JoinPredictorRequest create(Participant participant, String hash){
        return new JoinPredictorRequest(participant,hash);
    }

    public String getParticipantId(){
        return this.participant.getId();
    }

}
