package pers.bc.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * ASE加密
 * @qualiFild nc.pub.itf.tools.encrypt.ASEUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class ASEUtil
{
    /**
     * 加密 1.构造密钥生成器 2.根据ecnodeRules规则初始化密钥生成器 3.产生密钥 4.创建和初始化密码器 5.内容加密 6.返回字符串
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     */
    public static String AESEncode(String encodeRules, String content) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        
        // 1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        // 2.根据ecnodeRules规则初始化密钥生成器
        // 生成一个128位的随机源,根据传入的字节数组
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encodeRules.getBytes());
        keygen.init(128, random);
        // 3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        // 4.获得原始对称密钥的字节数组
        byte[] raw = original_key.getEncoded();
        // 5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");
        // 6.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // 8.获取加密内容的字节数组(这里要设置为utf-8)不然内容中如果有中文和英文混合中文就会解密为乱码
        byte[] byte_encode = content.getBytes(PubConsUtilbc.UTF_8);
        // 9.根据密码器的初始化方式--加密：将数据加密
        byte[] byte_AES = cipher.doFinal(byte_encode);
        // 10.将加密后的数据转换为字符串
        // 这里用Base64Encoder中会找不到包
        // 解决办法：
        // 在项目的Build path中先移除JRE System Library，再添加库JRE System
        // Library，重新编译后就一切正常了。
        // 11.将字符串返回
        return new String(Base64Ext.encode(byte_AES, Base64Ext.NO_WRAP));
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 解密 解密过程： 1.同加密1-4步 2.将加密后的字符串反纺成byte[]数组 3.将加密内容解密
     * @see
     * @param encodeRules
     * @param content
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-7
     * @time 下午4:33:20
     * @version 1.0 <br>
     ************************************************************* <br>
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws UnsupportedEncodingException
     * @throws NoSuchPaddingException
     */
    public static String AESDncode(final String encodeRules, String content) throws InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchPaddingException
    {
        
        // 1.构造密钥生成器，指定为AES算法,不区分大小写
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        // 2.根据ecnodeRules规则初始化密钥生成器
        // 生成一个128位的随机源,根据传入的字节数组
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encodeRules.getBytes());
        keygen.init(128, random);
        // 3.产生原始对称密钥
        SecretKey original_key = keygen.generateKey();
        // 4.获得原始对称密钥的字节数组
        byte[] raw = original_key.getEncoded();
        // 5.根据字节数组生成AES密钥
        SecretKey key = new SecretKeySpec(raw, "AES");
        // 6.根据指定算法AES自成密码器
        Cipher cipher = Cipher.getInstance("AES");
        // 7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, key);
        // 8.将加密并编码后的内容解码成字节数组
        byte[] byte_content = Base64Ext.decode(content.getBytes(), Base64Ext.NO_WRAP);
        /*
         * 解密
         */
        byte[] byte_decode = cipher.doFinal(byte_content);
        return new String(byte_decode, PubConsUtilbc.UTF_8);
        
    }
    
    /**
     * 加密字符串
     * 
     * @param secretKey
     * @param Str
     * @return
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String encrypt(String secretKey, String Str) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        return BlowfishUtil.str2HexStr(AESEncode(secretKey, Str));
    }
    
    /**
     * 解密字符串
     * 
     * @param secretKey
     * @param str
     * @return
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String decode(String secretKey, String str) throws InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchPaddingException
    {
        return AESDncode(secretKey, BlowfishUtil.hexStr2Str(str));
    }
}
