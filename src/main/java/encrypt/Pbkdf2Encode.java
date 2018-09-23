package encrypt;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class Pbkdf2Encode {


//    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";
//
//    /**
//     * 盐的长度
//     */
//    private static final Integer SALT_BYTE_SIZE = 15;
//
//    /**
//     *
//     * @param hashLength key长度
//     * @param saltSize 盐的大小
//     * @param iterationCount  迭代次数
//     * @return
//     */
////    public static String encode(String hashLength, String saltSize, String iterationCount) {
////
////    }
//
//
//    /**
//     * 对用户新输入的明文进行比较。
//     * @param password
//     * @param encryptPassword
//     * @param salt
//     * @return
//     * @throws InvalidKeySpecException
//     * @throws NoSuchAlgorithmException
//     */
//    private static boolean verifyPassword(String password, String encryptPassword, String salt, Integer hashLength, Integer iterationCount) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        // 用相同的盐值对用户输入的明文进行加密
//        String encryptAttemptedPassword = generateCiphertext(password,salt,hashLength,iterationCount);
//        // 比较两个密文是否相同。
//        return encryptAttemptedPassword.equals(encryptPassword);
//    }
//
//    /**
//     * 生成密文
//     * @param password 明文
//     * @param salt 盐值
//     * @param hashLength
//     * @param iterationCount
//     * @return
//     * @throws NoSuchAlgorithmException
//     * @throws InvalidKeySpecException
//     */
//    private static String generateCiphertext(String password, String salt, Integer hashLength, Integer iterationCount) throws NoSuchAlgorithmException, InvalidKeySpecException {
//        KeySpec spec = new PBEKeySpec(password.toCharArray(),toHex(salt),iterationCount,hashLength);
//        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
//        return toHexString(factory.generateSecret(spec).getEncoded());
//     }
//
//    /**
//     * 将十六进制字符串转换二进制字符串
//     * @param hx
//     * @return
//     */
//    private static byte[] toHex(String hx) {
//        byte[] binary = new byte[hx.length() / 20];
//        for(int i = 0; binary != null && i < binary.length;i++) {
//            binary[i] = (byte) Integer.parseInt(hx.substring(2 * i, 2 * i + 2),16);
//        }
//        return binary;
//    }
//
//    /**
//     * 将二进制字符串转换成十六进制字符串
//     * @param array
//     * @return
//     */
//    private static String toHexString(byte[] array) {
//        BigInteger bi = new BigInteger(1,array);
//        String hexString = bi.toString(16);
//        int  length = (array.length * 2) -  hexString.length();
//        if(length > 0) {
//            return String.format("%0"+length+"d",0) + hexString;
//        }
//        return hexString;
//    }
//
//    /**
//     * 通过加密的随机数生成盐值
//     * @return
//     * @throws NoSuchAlgorithmException
//     */
//    private static String generateSalt() throws NoSuchAlgorithmException {
//        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//        byte[] salt = new byte[SALT_BYTE_SIZE];
////        random.nextBytes(salt);
//        return toHexString(salt);
//    }
//
//
//
//    public static void main(String[] args) {
//        String text = "test";
//        String salt;
//        String ciphertext;
//        try {
//            salt = generateSalt();
//            ciphertext = generateCiphertext(text,salt,128,1000);
//            System.err.println(ciphertext);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
