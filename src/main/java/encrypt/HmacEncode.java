package encrypt;

import utils.StringUtil;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

public class HmacEncode {

    private static final String HMAC_SHA256 = "HMACSHA256";
    private static final String HMAC_MD5 = "HMACMD5";
    private static final String HMAC_SHA1 = "HMACSHA1";
//    private static final String HMAC_SHA224  = "HMACSHA224";
//    private static final String HMAC_SHA384  = "HMACSHA384";
    private static final String HMAC_SHA512  = "HMACSHA512";
    // 默认secret
    private static final String SECRET_KEY = "46964aeddfe512a4fb0b8789de20035c";
    public static String Hamc_Sha256_Encode(String message, String secret) {
       return encode(HMAC_SHA256,message,secret);
    }

    public static String Hamc_Md5_Encode(String message, String secret) {
        return encode(HMAC_MD5,message,secret);
    }

    public static String Hamc_Sha1_Encode(String message, String secord) {
        return encode(HMAC_SHA1,message,secord);
    }

    /**
     * 224/384 暂时取消提供。
     * @param message
     * @param secord
     * @return
     */
//    public static String Hamc_Sha224_Encode(String message, String secord) {
//        return encode(HMAC_SHA224,message,secord);
//    }
//    public static String Hamc_Sha384_Encode(String message, String secord) {
//        return encode(HMAC_SHA384,message,secord);
//    }
    public static String Hamc_Sha512_Encode(String message, String secord) {
        return encode(HMAC_SHA512,message,secord);
    }

    private static String encode(String encodeType, String message, String secret) {

        String hash = null;
        try {
            // 设置默认秘钥

            if(StringUtil.isBlank(secret)) {
                secret = SECRET_KEY;
            }
            Mac hmacMd = Mac.getInstance(encodeType);
            SecretKeySpec secretKey = new SecretKeySpec(secret.getBytes(),encodeType);
            // 初始化key
            hmacMd.init(secretKey);
            byte[] by = hmacMd.doFinal(message.getBytes());
            hash = byteArrayToNexString(by);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * 将加密后的字节数组转换成字符串
     * @param b
     * @return
     */
    private static String byteArrayToNexString(byte[] b) {
        StringBuilder builder = new StringBuilder();
        String temp;
        for(int i = 0; b != null && i < b.length; i++) {
            // 由于byte是9位，而Integer 是32位, 有24位数字会生成随机数,则需要通过0XFF将他设置为0
            temp = Integer.toHexString(b[i] & 0XFF);
            if(temp.length() == 1) {
                builder.append('0');
            }else {
                builder.append(temp);
            }

        }
        return builder.toString();
    }


//    public static void main(String[] args) {
//        // 224 384
//        System.err.println(Hamc_Sha224_Encode("这是一些简单的加密测试",
//                ""));
//    }
}
