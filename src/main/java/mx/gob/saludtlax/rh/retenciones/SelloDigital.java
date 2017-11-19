
package mx.gob.saludtlax.rh.retenciones;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.ejb.Stateless;

import org.apache.commons.ssl.Base64;
import org.apache.commons.ssl.PKCS8Key;

@Stateless
public class SelloDigital implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1012987024277482735L;

    public String generarSello(InputStream keyFile, String password,
            String cadenaOriginal)
            throws GeneralSecurityException, IOException {
        String selloDigital = "";

        PrivateKey llavePrivada = getPrivateKey(keyFile, password);
        selloDigital = generaSelloDigital(llavePrivada, cadenaOriginal);

        return selloDigital;
    }

    private PrivateKey getPrivateKey(InputStream keyFile, String password)
            throws GeneralSecurityException, IOException {

        PKCS8Key pkcs = new PKCS8Key(keyFile, password.toCharArray());
        byte[] decryptor = pkcs.getDecryptedBytes();
        PKCS8EncodedKeySpec apec = new PKCS8EncodedKeySpec(decryptor);
        PrivateKey pk = null;
        if (pkcs.isDSA()) {
            pk = KeyFactory.getInstance("DSA").generatePrivate(apec);
        } else {
            pk = KeyFactory.getInstance("RSA").generatePrivate(apec);
        }

        return pk;
    }

    private String generaSelloDigital(PrivateKey key, String cadenaOriginal)
            throws NoSuchAlgorithmException, InvalidKeyException,
            SignatureException, UnsupportedEncodingException {
        Signature sign = Signature.getInstance("SHA1withRSA");
        sign.initSign(key, new SecureRandom());
        sign.update(cadenaOriginal.getBytes());
        // sign.update(cadenaOriginal.getBytes());
        byte[] signature = sign.sign();
        return new String(Base64.encodeBase64(signature));
    }

}
