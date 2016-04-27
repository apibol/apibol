package predictor.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import predictor.domain.service.HashKeyService;

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

    private String key;

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
        this.key = HashKeyService.encrypt(this.userId);
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
