package predictor.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * Invitation to participate in a predictor
 *
 * @author Claudio E. de Oliveira on on 25/04/16.
 */
@Data
@EqualsAndHashCode(of = {"userId"})
public class Invitation {

    private String userId;

    private LocalDateTime createdAt;

    /**
     * Default constructor
     */
    Invitation() {
    }

    /**
     * Constructor
     *
     * @param userId
     */
    public Invitation(String userId) {
        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Factory Method
     *
     * @param userId
     * @return
     */
    public static Invitation createNew(String userId) {
        return new Invitation(userId);
    }

}
