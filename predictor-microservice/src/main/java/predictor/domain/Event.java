package predictor.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * Event model
 *
 * @author Claudio E. de Oliveira on 12/03/16.
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class Event {
    
    private String id;

    private Boolean open = Boolean.FALSE;

    private Set<Participant> participants = new HashSet<>();

    private Participant owner;


}
