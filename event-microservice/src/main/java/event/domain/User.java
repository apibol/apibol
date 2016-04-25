package event.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Claudio E. de Oliveira on 28/02/16.
 */
@Data
@EqualsAndHashCode(of = {"nickname"})
public class User {
    
    private String id;
    
    private String nickname;
    
    private String email;
    
}
