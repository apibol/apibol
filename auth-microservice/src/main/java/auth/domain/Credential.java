package auth.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Credential for OAuth
 *
 * @author Claudio E. de Oliveira on on 19/04/16.
 */
@Data
@EqualsAndHashCode(of = {"email"})
public class Credential {

    private String id;

    private String email;

    private String nickname;

    private String password;

    private Set<Scope> scopes = new HashSet<>();

    /**
     * Default constructor
     */
    Credential(){}

    /**
     * Constructor
     *
     * @param id
     * @param email
     * @param nickname
     * @param password
     */
    private Credential(String id,String email,String nickname,String password){
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * Constructor
     *
     * @param email
     * @param nickname
     * @param password
     */
    private Credential(String email,String nickname,String password){
        this.email = email;
        this.nickname = nickname;
        this.password = password;
    }

    /**
     * Credential factory
     *
     * @param email
     * @param nickname
     * @param password
     * @return
     */
    public static Credential fromVO(String email,String nickname,String password){
        return new Credential(email,nickname,password);
    }

    /**
     * Credential factory
     *
     * @param id
     * @param email
     * @param nickname
     * @param password
     * @return
     */
    public static Credential fromDatabase(String id,String email,String nickname,String password){
        return new Credential(id,email,nickname,password);
    }

    /**
     * Assign new id in credential
     *
     * @param id
     * @return
     */
    public Credential assignNewId(String id){
        this.id = id;
        return this;
    }

    /**
     * Assign encoded password
     *
     * @param encodedPassword - the encoded password
     * @return
     */
    public Credential assignEncodedPassword(String encodedPassword){
        this.password = encodedPassword;
        return this;
    }

    /**
     * Add scope in credential
     *
     * @param scope
     * @return
     */
    public Credential addScope(String scope){
        this.scopes.add(Scope.create(scope));
        return this;
    }

}
