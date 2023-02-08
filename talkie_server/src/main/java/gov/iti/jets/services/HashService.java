package gov.iti.jets.services;

import gov.iti.jets.TalkieApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Properties;

public class HashService {

    private static final String HASH_ALGORITHM = loadKey();
    public String getHashedValue(String data, String salt) {
        byte[] hashedValue = null;

        KeySpec spec = new PBEKeySpec(data.toCharArray(), salt.getBytes(), 5000, 128);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(HASH_ALGORITHM);
            hashedValue = factory.generateSecret(spec).getEncoded();

        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return Base64.getEncoder().encodeToString(hashedValue);
    }

    private static String loadKey(){
        String key = null;
        try (InputStream inputStream = TalkieApplication.class.getClassLoader().getResourceAsStream("security.properties")) {
            Properties props = new Properties();
            props.load(inputStream);
            key = props.getProperty("HASH_ALGORITHM");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

}
