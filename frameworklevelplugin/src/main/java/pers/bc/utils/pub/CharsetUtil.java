package pers.bc.utils.pub;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * Description: 编码相关的封装类
 * @qualiFild nc.pub.itf.tools.pub.CharsetUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class CharsetUtil
{
    /**
     * *********************************************************** <br>
     * 说明：将字节数组转换成16进制字符串 <br>
     * @see <br>
     * @param src
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CharsetUtil#bytesToHexString <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:22:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String bytesToHexString(byte[] src)
    {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0)
        {
            return null;
        }
        for (int i = 0; i < src.length; i++)
        {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将16进制字符串转换成字节数组 <br>
     * @see <br>
     * @param hexString
     * @return <br>
     * @byte[] <br>
     * @methods pers.bc.utils.pub.CharsetUtil#hexStringToBytes <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:22:37 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static byte[] hexStringToBytes(String hexString)
    {
        if (hexString == null || hexString.equals(""))
        {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++)
        {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将单个字符转换成字节 <br>
     * @see <br>
     * @param c
     * @return <br>
     * @byte <br>
     * @methods pers.bc.utils.pub.CharsetUtil#charToByte <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:22:23 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static byte charToByte(char c)
    {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将字符编码转换成US-ASCII码s <br>
     * @see <br>
     * @param str
     * @return
     * @throws UnsupportedEncodingException <br>
     * @String <br>
     * @methods pers.bc.utils.pub.CharsetUtil#toASCII <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:22:30 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String toASCII(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.US_ASCII);
    }
    
    /**
     * 将字符编码转换成ISO-8859-1码
     */
    public final static String toISO_8859_1(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.ISO_8859_1);
    }
    
    /**
     * 将字符编码转换成UTF-8码
     */
    public static String toUTF_8(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.UTF_8);
    }
    
    /**
     * 将字符编码转换成UTF-16BE码
     */
    public final static String toUTF_16BE(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.UTF_16BE);
    }
    
    /**
     * 将字符编码转换成UTF-16LE码
     */
    public final static String toUTF_16LE(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.UTF_16LE);
    }
    
    /**
     * 将字符编码转换成UTF-16码
     */
    public final static String toUTF_16(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.UTF_16);
    }
    
    /**
     * 将字符编码转换成GBK码
     */
    public final static String toGBK(String str) throws UnsupportedEncodingException
    {
        return changeCharset(str, PubConsUtilbc.GBK);
    }
    
    /**
     * 字符串编码转换的实现方法
     * 
     * @param str 待转换编码的字符串
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String newCharset) throws UnsupportedEncodingException
    {
        if (str != null)
        {
            
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
    
    public final static String getDefaultCharSet() throws UnsupportedEncodingException
    {
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream(), PubConsUtilbc.UTF_8);
        String enc = writer.getEncoding();
        return enc;
    }
    
    /**
     * 字符串编码转换的实现方法
     * 
     * @param str 待转换编码的字符串
     * @param oldCharset 原编码
     * @param newCharset 目标编码
     * @return
     * @throws UnsupportedEncodingException
     */
    public final static String changeCharset(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException
    {
        if (str != null)
        {
            // 用旧的字符编码解码字符串。解码可能会出现异常。
            byte[] bs = str.getBytes(oldCharset);
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
    
    /**
     * Unicode转换成GBK字符集
     * 
     * @param input 待转换字符串
     * @return 转换完成字符串
     */
    public final static String toGBKWithUTF8(String input) throws UnsupportedEncodingException
    {
        if (StringUtil.isEmpty(input))
        {
            return "";
        }
        else
        {
            String s1;
            s1 = new String(input.getBytes(PubConsUtilbc.ISO_8859_1), PubConsUtilbc.GBK);
            return s1;
        }
    }
    
    /**
     * GBK转换成Unicode字符集
     * 
     * @param input 待转换字符串
     * @return 转换完成字符串
     */
    public final static String toUnicodeWithGBK(String input) throws UnsupportedEncodingException
    {
        if (StringUtil.isEmpty(input))
        {
            return "";
        }
        else
        {
            String s1;
            s1 = new String(input.getBytes(PubConsUtilbc.GBK), PubConsUtilbc.ISO_8859_1);
            return s1;
        }
    }
    
}
