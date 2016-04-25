package predictor.domain;

import java.time.LocalDateTime;

/**
 * When the invitation is accepted
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
public class AcceptedInvitation {

    private String userId;

    private LocalDateTime acceptedAt;

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
        this.acceptedAt = LocalDateTime.now();
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
