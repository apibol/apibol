package domain;

import lombok.Data;

/**
 * Register request new user
 *
 * @author Claudio E. de Oliveira on 03/05/16.
 */
@Data
public class NewUser {

    String id;

    String email;

    String nickname;

}
