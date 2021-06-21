package pers.bc.utils.test;

import pers.bc.utils.image.QRCodeUtilbc;

/**
 * 二维码工具类
 * @qualiFild pers.bc.utils.image.QRCodeUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public class QRCodeUtilTest
{
    public static void main(String[] args) throws Exception
    {
        String text = "http://www.baidu.com";
        // 不含Logo
        // QRCodeUtil.encode(text, null, "e:\\", true);
        // 含Logo，不指定二维码图片名
        // QRCodeUtil.encode(text, "e:\\csdn.jpg", "e:\\", true);
        // 含Logo，指定二维码图片名
        QRCodeUtilbc.encode(text, "e:\\csdn.jpg", "e:\\", "qrcode", true);
    }
}
