package predictor.domain.service;

import lombok.extern.log4j.Log4j2;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
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

    private static final String ALG = "AES/CBC/PKCS7PADDING";

    private static final String IV = "A$u9#0XA$u9#0X&#";

    private static final String SECRET = "D8&p767@#$D8&@#$";

    /**
     * Decrypt value
     *
     * @param encryptedData
     * @return
     * @throws Exception
     */
    public static String decrypt(String encryptedData){
        try {
            return decrypt(encryptedData.getBytes("UTF-8"), SECRET);
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
            final byte[] encVal = encrypt(data, SECRET);
            String encryptedValue = base64().encode(encVal);
            return encryptedValue;
        } catch (Exception e) {
            log.error("Error on encrypt value",e);
            throw new RuntimeException("Error on encrypt value");
        }
    }


    private static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALG);
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
    }

    private static String decrypt(byte[] cipherText, String encryptionKey) throws Exception{
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return new String(cipher.doFinal(cipherText),"UTF-8");
    }

}
