package predictor.domain.service;

import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

import static com.google.common.io.BaseEncoding.base64;

/**
 * Encrypt / Decrypt utils
 *
 * @author Claudio E. de Oliveira on on 26/04/16.
 */
@Log4j2
public class HashKeyService {

    private static final String ALG = "SHA-256";

    private static final byte[] SECRET = new byte[]{'A','$','u','9','#','0','X'};

    /**
     * Decrypt value
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData){
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALG);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decodedValue = base64().decode(encryptedData);
            byte[] decValue = c.doFinal(decodedValue);
            String decryptedValue = new String(decValue);
            return decryptedValue;
        } catch (Exception e) {
            log.error("Error on decrypt value",e);
            throw new RuntimeException("Error on decrypt value");
        }
    }

    /**
     * Encrypt value
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) {
        try {
            Key key = generateKey();
            Cipher c = Cipher.getInstance(ALG);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(data.getBytes());
            String encryptedValue = base64().encode(encVal);
            return encryptedValue;
        } catch (Exception e) {
            log.error("Error on encrypt value",e);
            throw new RuntimeException("Error on encrypt value");
        }
    }

    /**
     * Generate secret key
     *
     * @return
     * @throws Exception
     */
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(SECRET, ALG);
        return key;
    }

}
