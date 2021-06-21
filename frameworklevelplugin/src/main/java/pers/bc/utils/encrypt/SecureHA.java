package pers.bc.utils.encrypt;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Secure Hash Algorithm，安全散列算法
 * @qualiFild nc.pub.itf.tools.encrypt.SecureHA.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class SecureHA
{
    
    public static final String KEY_SHA = "SHA";
    
    public static String getResult(String inputStr) throws NoSuchAlgorithmException
    {
        BigInteger sha = null;
        byte[] inputData = inputStr.getBytes();
        MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
        messageDigest.update(inputData);
        sha = new BigInteger(messageDigest.digest());
        
        return sha.toString(32);
    }
    
}
