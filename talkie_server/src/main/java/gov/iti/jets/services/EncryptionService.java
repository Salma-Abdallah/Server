package gov.iti.jets.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionService {
    private Logger logger = LoggerFactory.getLogger(EncryptionService.class);

    public String encrypt(String data, String key) {
        byte[] encryptedValue = null;

        try {
            Cipher encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedValue = encryptionCipher.doFinal(data.getBytes());

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
                 | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.getMessage());
        }

        return Base64.getEncoder().encodeToString(encryptedValue);
    }

    public String decrypt(String data, String key) {
        byte[] decryptedValue = null;

        try {
            Cipher decryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKey secretKey = new SecretKeySpec(key.getBytes(), "AES");
            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey);
            decryptedValue = decryptionCipher.doFinal(Base64.getDecoder().decode(data));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            logger.error(e.getMessage());
        }

        return new String(decryptedValue);
    }
    
}
