package predictor.domain;

import java.time.LocalDateTime;

/**
 * When the invitation is accepted
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class AcceptedInvitation {

    private String userId;

    /**
     * Default constructor
     */
    AcceptedInvitation() {
    }

    /**
     * Constructor
     *
     * @param userId
     */
    public AcceptedInvitation(String userId) {
        this.userId = userId;
    }

    /**
     * Factory Method
     *
     * @param userId
     * @return
     */
    public static AcceptedInvitation createNew(String userId) {
        return new AcceptedInvitation(userId);
    }

}
