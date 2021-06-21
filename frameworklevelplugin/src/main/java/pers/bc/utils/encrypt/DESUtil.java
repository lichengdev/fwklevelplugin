package pers.bc.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * 提供des加密算法
 * @qualiFild nc.pub.itf.tools.encrypt.DESUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class DESUtil
{
    
    // 向量
    private final static String iv = "01234567";
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 3DES加密
     * 
     * @param plainText 普通文本
     * @param secretKey
     * @return
     * @throws Exception
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:50:11
     * @version 1.0 <br>
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeySpecException <br>
     *             *********************************************************** <br>
     */
    public static String encrypt(String secretKey, String plainText) throws IllegalBlockSizeException, BadPaddingException,
            UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeySpecException
    {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
        byte[] encryptData = cipher.doFinal(plainText.getBytes(PubConsUtilbc.UTF_8));
        return Base64.encodeToString(encryptData);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 3DES解密
     * 
     * @param encryptText 加密文本
     * @param secretKey
     * @return
     * @throws Exception
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:50:29
     * @version 1.0 <br>
     * @throws InvalidKeyException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeySpecException 
     * @throws NoSuchPaddingException 
     * @throws InvalidAlgorithmParameterException 
     * @throws BadPaddingException 
     * @throws IllegalBlockSizeException 
     * @throws UnsupportedEncodingException 
     ************************************************************* <br>
     */
    public static String decode(String secretKey, String encryptText) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException  
    {
        Key deskey = null;
        DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
        byte[] decryptData = cipher.doFinal(Base64.decode(encryptText));
        return new String(decryptData, PubConsUtilbc.UTF_8);
    }
    
}
