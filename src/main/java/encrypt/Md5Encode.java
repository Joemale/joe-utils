package encrypt;

import constant.EncryptConsts;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encode {


    private static String hexDigits[] = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};


    private static String byteArrayToHexString(byte[] by) {
        StringBuffer buffer = null;
        if(by.length > 0) {
            buffer = new StringBuffer();
            for(int i = 0; i < by.length; i++) {
                buffer.append(byteToHexString(by[i]));
            }
            return buffer.toString();
        }
        return null;
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String encode(String str) {
        // 加密后的密文
        String resultString = null;
        try {
            MessageDigest md = MessageDigest.getInstance(EncryptConsts.ENCRYPT_MD5);
            resultString =  byteArrayToHexString(md.digest(str.getBytes()));
            return resultString;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return resultString;
    }





}
