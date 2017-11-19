
package mx.gob.saludtlax.rh.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Crypto {

    public Crypto() {
    }

    public static String hmac(String mensaje) {
        try {
            Mac magic = Mac.getInstance("HmacSHA512");
            String sal = "el universo me dio gratis, yo doy gratis";
            byte[] salt = sal.getBytes("UTF-8");
            byte[] valueBytes = mensaje.getBytes("UTF-8");
            Key key = new SecretKeySpec(salt, "HmacSHA512");
            magic.init(key);
            return Base64.encodeBase64String(magic.doFinal(valueBytes));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException | IllegalStateException ex) {
            throw new RuntimeException(ex);
        }
    }
}