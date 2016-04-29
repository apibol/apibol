package domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Model of logged user
 *
 * @author Claudio E. de Oliveira on on 28/04/16.
 */
@Data
@EqualsAndHashCode(of = {"nickname"})
public class SystemUser {

    private String id;

    private String nickname;

    private String email;

}
