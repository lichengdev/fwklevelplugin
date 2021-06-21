package pers.bc.utils.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.List;

import pers.bc.utils.pub.AppDebug;
import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 文件编码相关的一些工具函数
 * @qualiFild com.pub.utils.file.FileEncodingUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
@SuppressWarnings("resource")
public class FileEncodingUtil
{
    /**
     * ************************************************** 以下方式利用mozilla的jchardet作为探测工具
     */
    // EncodingDetect
    private static boolean found = false;
    
    /**
     * 如果完全匹配某个字符集检测算法, 则该属性保存该字符集的名称. 否则(如二进制文件)其值就为默认值 null, 这时应当查询属性
     */
    private static String encoding = null;
    
    /**
     * 把指定文件或目录转换成指定的编码
     * 
     * @param fileName 要转换的文件
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @throws MylbcException
     */
    public static void convert(String fileName, String fromCharsetName, String toCharsetName) throws MylbcException
    {
        convert(new File(fileName), fromCharsetName, toCharsetName, null);
    }
    
    /**
     * 把指定文件或目录转换成指定的编码
     * 
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @throws MylbcException
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName) throws MylbcException
    {
        convert(file, fromCharsetName, toCharsetName, null);
    }
    
    /**
     * 把指定文件或目录转换成指定的编码
     * 
     * @param fileName 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @param filter 文件名过滤器
     * @throws MylbcException
     */
    public static void convert(String fileName, String fromCharsetName, String toCharsetName, FilenameFilter filter) throws MylbcException
    {
        convert(new File(fileName), fromCharsetName, toCharsetName, filter);
    }
    
    /**
     * 把指定文件或目录转换成指定的编码
     * 
     * @param file 要转换的文件或目录
     * @param fromCharsetName 源文件的编码
     * @param toCharsetName 要转换的编码
     * @param filter 文件名过滤器
     * @throws MylbcException
     */
    public static void convert(File file, String fromCharsetName, String toCharsetName, FilenameFilter filter) throws MylbcException
    {
        if (file.isDirectory())
        {
            List<File> list = ArrayUtilbc.isEmptyObj(filter) ? FileUtilbc.listFileFilter(file, filter) : FileUtilbc.listFile(file);
            if (!ArrayUtilbc.isEmptyObj(list))
            {
                for (File f : list)
                {
                    convert(f, fromCharsetName, toCharsetName, filter);
                }
            }
        }
        else
        {
            if (filter == null || filter.accept(file.getParentFile(), file.getName()))
            {
                String fileContent = getFileContentFromCharset(file, fromCharsetName);
                saveFile2Charset(file, toCharsetName, fileContent);
            }
        }
    }
    
    /**
     * 以指定编码方式读取文件，返回文件内容
     * 
     * @param file 要转换的文件
     * @param fromCharsetName 源文件的编码
     * @throws MylbcException
     */
    public static String getFileContentFromCharset(File file, String fromCharsetName) throws MylbcException
    {
        String str = "";
        if (!Charset.isSupported(fromCharsetName))
        {
            throw new UnsupportedCharsetException(fromCharsetName);
        }
        try
        {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(inputStream, fromCharsetName);
            char[] chs = new char[(int) file.length()];
            reader.read(chs);
            str = new String(chs).trim();
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return str;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 得到文件的编码<br>
     * @see <br>
     * @param filePath 文件路径
     * @return 文件的编码<br>
     * @String <br>
     * @methods pers.bc.utils.file.FileEncodingUtil#getJavaEncode <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月20日 <br>
     * @time 下午1:01:57 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getJavaEncode(String filePath)
    {
        BytesEncodingDetect s = new BytesEncodingDetect();
        String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(new File(filePath))];
        
        // UTF-16LE 特殊处理
        if ("Unicode".equals(fileCode))
        {
            byte[] tempByte = BytesEncodingDetect.getFileBytes(new File(filePath));
            if (tempByte[0] == -1)
            {
                fileCode = "UTF-16LE";
            }
        }
        return fileCode;
    }
    
    /**
     * *********************************************************** <br>
     * *说明：得到文件的编码 <br>
     * @see <br>
     * @param file 文件路径
     * @return 文件的编码 <br>
     * @String <br>
     * @methods pers.bc.utils.file.FileEncodingUtil#getJavaEncode <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月20日 <br>
     * @time 下午1:02:19 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getJavaEncode(File file)
    {
        BytesEncodingDetect s = new BytesEncodingDetect();
        String fileCode = BytesEncodingDetect.javaname[s.detectEncoding(file)];
        return fileCode;
    }
    
    public static void readFile(String file, String code)
    {
        
        BufferedReader fr;
        try
        {
            String myCode = code != null && !"".equals(code) ? code : "UTF8";
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), myCode);
            
            fr = new BufferedReader(read);
            String line = null;
            int flag = 1;
            // 读取每一行，如果结束了，line会为空
            while ((line = fr.readLine()) != null && line.trim().length() > 0)
            {
                if (flag == 1)
                {
                    line = line.substring(1);// 去掉文件头
                    flag++;
                }
                // 每一行创建一个Student对象，并存入数组中
                // System.out.println(line);
            }
            fr.close();
            
        }
        catch (Exception e)
        {
            AppDebug.exception(e);
        }
        
    }
    // /**
    // * 传入一个文件(File)对象，检查文件编码
    // *
    // * @param file File对象实例
    // * @return 文件编码，若无，则返回null
    // * @throws FileNotFoundException
    // * @throws IOException
    // * @throws MylbcException
    // */
    // public static String guestFileEncoding(File file) throws IOException, MylbcException
    // {
    // return geestFileEncoding(file, new org.mozilla.intl.chardet.nsDetector());
    // }
    
    // /**
    // * 获取文件的编码 chardet-1.0.jar
    // *
    // * @param file File对象实例
    // * @param languageHint 语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese; 4 : Traditional
    // * Chinese; 5 : Korean; 6 : Dont know (default)
    // * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
    // * @throws FileNotFoundException
    // * @throws IOException
    // * @throws MylbcException
    // */
    // public static String guestFileEncoding(File file, int languageHint) throws IOException, MylbcException
    // {
    // return geestFileEncoding(file, new org.mozilla.intl.chardet.nsDetector(languageHint));
    // }
    
    // /**
    // * 获取文件的编码
    // *
    // * @param path 文件路径
    // * @return 文件编码，eg：UTF-8,GBK,GB2312形式，若无，则返回null
    // * @throws FileNotFoundException
    // * @throws IOException
    // * @throws MylbcException
    // */
    // public static String guestFileEncoding(String path) throws IOException, MylbcException
    // {
    // return guestFileEncoding(new File(path));
    // }
    
    // /**
    // * 获取文件的编码
    // *
    // * @param path 文件路径
    // * @param languageHint 语言提示区域代码 eg：1 : Japanese; 2 : Chinese; 3 : Simplified Chinese; 4 : Traditional
    // * Chinese; 5 : Korean; 6 : Dont know (default)
    // * @return 返回文件的编码
    // * @throws FileNotFoundException
    // * @throws IOException
    // * @throws MylbcException
    // */
    // public static String guestFileEncoding(String path, int languageHint) throws FileNotFoundException,
    // IOException, MylbcException
    // {
    // return guestFileEncoding(new File(path), languageHint);
    // }
    
    // /**
    // *
    // * *********************************************************** <br>
    // * 说明： 获取文件的编码 chardet-1.0.jar
    // *
    // * @param file 需要处理文件的编码
    // * @param det nsDetector
    // * @return 返回文件编码
    // * @see
    // * @String
    // * @author licheng
    // * @date Created on 2019年8月5日
    // * @time 上午12:07:48
    // * @version 1.0 <br>
    // ************************************************************* <br>
    // * @throws MylbcException
    // */
    // private static String geestFileEncoding(File file, nsDetector det) throws MylbcException
    // {
    // det.Init(new org.mozilla.intl.chardet.nsICharsetDetectionObserver()
    // {
    // public void Notify(String charset)
    // {
    // found = true;
    // encoding = charset;
    // }
    // });
    // byte[] buf = new byte[1024];
    // int len;
    // boolean done = false;
    // boolean isAscii = true;
    // try
    // {
    // BufferedInputStream imp = new BufferedInputStream(new FileInputStream(file));
    // while ((len = imp.read(buf, 0, buf.length)) != -1)
    // {
    // // Check if the stream is only ascii.
    // if (isAscii)
    // {
    // isAscii = det.isAscii(buf, len);
    // }
    //
    // // DoIt if non-ascii and not done yet.
    // if (!isAscii && !done)
    // {
    // done = det.DoIt(buf, len, false);
    // }
    // }
    // det.DataEnd();
    // }
    // catch (IOException e)
    // {
    // throw new MylbcException(e);
    // }
    //
    // if (isAscii)
    // {
    // encoding = "ASCII";
    // found = true;
    // }
    //
    // if (!found)
    // {
    // String prob[] = det.getProbableCharsets();
    // if (prob.length > 0)
    // {
    // // 在没有发现情况下，则取第一个可能的编码
    // encoding = prob[0];
    // }
    // else
    // {
    // return null;
    // }
    // }
    // return encoding;
    // }
    
    /**
     * *********************************************************** <br>
     * *说明：以指定编码方式写文本文件，存在会覆盖 <br>
     * @see <br>
     * @param file 要写入的文件
     * @param toCharsetName 要转换的编码
     * @param content 文件内容
     * @throws MylbcException <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileEncodingUtil#saveFile2Charset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月20日 <br>
     * @time 下午1:02:57 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void saveFile2Charset(File file, String toCharsetName, String content) throws MylbcException
    {
        if (!Charset.isSupported(toCharsetName))
        {
            throw new UnsupportedCharsetException(toCharsetName);
        }
        try
        {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outWrite = new OutputStreamWriter(outputStream, toCharsetName);
            outWrite.write(content);
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
}
