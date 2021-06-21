package pers.bc.utils.encrypt;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * 提供常见的加密方法
 * @qualiFild nc.pub.itf.tools.encrypt.SecurityHelper.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class SecurityHelper
{
    

    /**
     * 
     * *********************************************************** <br>
     * 说明： 安全散列算法
     * @see
     * @param inputStr
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:58:18
     * @version 1.0 <br>
     ************************************************************* <br>
     * @throws NoSuchAlgorithmException
     */
    public static String SecureHA(String inputStr) throws NoSuchAlgorithmException
    {
        return SecureHA.getResult(inputStr);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 转十六进制
     * @see
     * @param input
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:59:02
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String toHex(byte input[])
    {
        if (input == null) return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++)
        {
            int current = input[i] & 0xff;
            if (current < 16) output.append("0");
            output.append(Integer.toString(current, 16));
        }
        
        return output.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：BASE64解密
     * @see
     * @param key
     * @return
     * @throws Exception
     * @byte[]
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:59:22
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] decryptBASE64(String key) throws Exception
    {
        return Base64Ext.decode(key.getBytes(), Base64Ext.NO_WRAP);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： BASE64加密
     * @see
     * @param key
     * @return
     * @throws Exception
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:59:32
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String encryptBASE64(byte[] key) throws Exception
    {
        return new String(Base64Ext.encode(key, Base64Ext.NO_WRAP));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 字符串加密函数MD5实现
     * @see
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:59:40
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static String md5(String password) throws NoSuchAlgorithmException
    {
        MessageDigest md;
        // 生成一个MD5加密计算摘要
        md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(password.getBytes());
        // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
        // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
        String pwd = new BigInteger(1, md.digest()).toString(16);
        return pwd;
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：提供ASE加密算法
     * @see
     * 
     * @param secretKey 秘钥
     * @param str 加密的字符串
     * 
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午9:59:46
     * @version 1.0 <br>
     ************************************************************* <br>
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String aesEncrypt(String secretKey, String str) throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException
    {
        return BlowfishUtil.str2HexStr(ASEUtil.AESEncode(secretKey, str));
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：提供ASE解密算法
     * @see
     * 
     * @param secretKey 秘钥
     * @param str 解密的字符串
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:00:16
     * @version 1.0 <br>
     ************************************************************* <br>
     * @throws NoSuchPaddingException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String aseDecode(String secretKey, String str) throws InvalidKeyException, NoSuchAlgorithmException,
            IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, NoSuchPaddingException
    {
        return ASEUtil.AESDncode(secretKey, BlowfishUtil.hexStr2Str(str));
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：提供des加密算法
     * @see
     * 
     * @param secretKey 秘钥
     * @param str 加密的字符串
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:00:30
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    public static String desEncrypt(String secretKey, String str) throws Exception
    {
        return DESUtil.encrypt(secretKey, str);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：提供des解密算法
     * @see
     * 
     * @param secretKey 秘钥
     * @param str 解密的字符串
     * @return
     * @throws Exception
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:01:25
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String dseDecode(String secretKey, String str) throws Exception
    {
        return DESUtil.decode(secretKey, str);
    }
    
}
