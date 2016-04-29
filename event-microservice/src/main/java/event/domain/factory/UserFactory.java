package event.domain.factory;

import domain.SystemUser;
import event.domain.User;

/**
 * User factory
 *
 * @author Claudio E. de Oliveira on on 28/04/16.
 */
public class UserFactory {

    /**
     * Convert User from system user
     *
     * @param systemUser
     * @return
     */
    public static User fromSystemUser(SystemUser systemUser){
        User user = new User();
        user.setNickname(systemUser.getNickname());
        user.setEmail(systemUser.getEmail());
        user.setId(systemUser.getId());
        return user;
    }

}
