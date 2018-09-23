package encrypt;

import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaEncode {

    /**
     *  SHA-1 加密
     * @param str
     * @return
     */
    public static String SHA_1_Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] by = md.digest(str.getBytes());
           return  Hex.encodeHexString(by);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SHA-256 加密
     * @param str
     * @return
     */
    public static String SHA_256_Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] by = md.digest(str.getBytes());
            return Hex.encodeHexString(by);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SHA-224 加密
     * @param str
     * @return
     */
    public static String SHA_224_Encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-224");
            byte[] by =  md.digest(str.getBytes());
            return Hex.encodeHexString(by);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }



}
