package pers.bc.utils.file;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

/**
 * 流相关的操作方法封装
 * @qualiFild nc.pub.itf.tools.pub.StreamUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class StreamUtilbc implements PubConsUtilbc
{
    
    private static LoggerUtilbc log = LoggerUtilbc.getInstance("filelogs");
    
    /**
     * *********************************************************** <br>
     * 说明：write an ouput stream into a byte[] <br>
     * @see <br>
     * @param file
     * @param data <br>
     * @void <br>
     * @methods pers.bc.utils.file.StreamUtilbc#writeByteArrayToFile <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-4 <br>
     * @time 18:23:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void writeByteArrayToFile(File file, byte[] data)
    {
        OutputStream out = null;
        try
        {
            if (!file.exists())
            {
                FileUtilbc.createFiles(file.getPath());
            }
            out = new FileOutputStream(file);
            out.write(data);
            out.flush();
        }
        catch (Exception e)
        {
            log.exception("writeByteArrayToFile()", e);
        }
        finally
        {
            FileUtilbc.close(out);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Read an input stream into a byte[] <br>
     * @see <br>
     * @param file
     * @return
     * @throws FileNotFoundException
     * @throws IOException <br>
     * @byte[] <br>
     * @methods com.pub.utils.file.StreamUtil#readFileToByteArray <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 上午11:14:44 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] readFileToByteArray(File file) throws FileNotFoundException, IOException
    {
        return stream2Byte(new FileInputStream(file));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Read an input stream into a byte[] <br>
     * @see <br>
     * @param filePatch
     * @return
     * @throws FileNotFoundException
     * @throws IOException <br>
     * @byte[] <br>
     * @methods com.pub.utils.file.StreamUtil#readFileToByteArray <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 上午11:14:47 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] readFileToByteArray(String filePatch) throws FileNotFoundException, IOException
    {
        return readFileToByteArray(new File(filePatch));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param in
     * @return
     * @throws IOException <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StreamUtil#streamToString <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午10:23:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static String streamToString(InputStream in) throws IOException
    {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;)
        {
            out.append(new String(b, 0, n));
        }
        
        FileUtilbc.close(in);
        return out.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： Read an input stream into a byte[]
     * @see
     * @byte[] @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:10:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static byte[] stream2Byte(InputStream is) throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = 0;
        byte[] b = new byte[1024];
        while ((len = is.read(b, 0, b.length)) != -1)
        {
            baos.write(b, 0, len);
        }
        byte[] buffer = baos.toByteArray();
        
        FileUtilbc.close(baos, is);
        return buffer;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： InputStream 转为 byte
     * @see
     * @byte[] @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:11:09
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static byte[] inputStream2Byte(InputStream inStream) throws Exception
    {
        int count = 0;
        while (count == 0)
        {
            count = inStream.available();
        }
        byte[] b = new byte[count];
        inStream.read(b);
        
        FileUtilbc.close(inStream);
        return b;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： byte 转为 InputStream
     * @see
     * @InputStream
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:11:20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static InputStream byte2InputStream(byte[] b) throws Exception
    {
        InputStream is = new ByteArrayInputStream(b);
        return is;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 将流另存为文件
     * @see
     * @void
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:11:30
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static void streamSaveAsFile(InputStream is, File outfile)
    {
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(outfile);
            
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) > 0)
            {
                fos.write(buffer, 0, len);
            }
            
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            FileUtilbc.close(is, fos);
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @boolean
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:11:43
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isBlank(String str)
    {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) return true;
        for (int i = 0; i < strLen; i++)
        {
            if (Character.isWhitespace(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将InputStream转换成String<br>
     * @see <br>
     * @param in
     * @return
     * @throws IOException <br>
     * @String <br>
     * @methods pers.bc.utils.file.StreamUtilbc#InputStreamTOString <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:56:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String InputStreamTOString(InputStream in) throws IOException
    {
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = 0;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        
        byte[] byteArray = outStream.toByteArray();
        FileUtilbc.close(in, outStream);
        
        return new String(byteArray, UTF_8);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将InputStream转换成某种字符编码的String <br>
     * @see <br>
     * @param in
     * @param encoding
     * @return
     * @String <br>
     * @methods pers.bc.utils.file.StreamUtilbc#InputStreamTOString <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @throws IOException
     * @time 上午11:55:12 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String InputStreamTOString(InputStream in, String encoding) throws IOException
    {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        
        byte[] byteArray = outStream.toByteArray();
        FileUtilbc.close(in, outStream);
        return new String(byteArray, encoding);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将String转换成InputStream<br>
     * @see <br>
     * @param in
     * @return
     * @throws Exception <br>
     * @InputStream <br>
     * @methods pers.bc.utils.file.StreamUtilbc#StringTOInputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:54:22 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static InputStream StringTOInputStream(String in) throws Exception
    {
        return new ByteArrayInputStream(in.getBytes(UTF_8));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将String转换成InputStream <br>
     * @see <br>
     * @param in
     * @return
     * @throws IOException
     * @throws Exception <br>
     * @byte[] <br>
     * @methods pers.bc.utils.file.StreamUtilbc#StringTObyte <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:54:12 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] StringTObyte(String in) throws IOException, Exception
    {
        return InputStreamTOByte(StringTOInputStream(in));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将InputStream转换成byte数组<br>
     * @see <br>
     * @param in
     * @return
     * @throws IOException <br>
     * @byte[] <br>
     * @methods pers.bc.utils.file.StreamUtilbc#InputStreamTOByte <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:53:53 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static byte[] InputStreamTOByte(InputStream in) throws IOException
    {
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);
        data = null;
        
        byte[] byteArray = outStream.toByteArray();
        FileUtilbc.close(in, outStream);
        
        return byteArray;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将byte数组转换成InputStream <br>
     * @see <br>
     * @param in
     * @return
     * @throws Exception <br>
     * @InputStream <br>
     * @methods pers.bc.utils.file.StreamUtilbc#byteTOInputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:53:42 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static InputStream byteTOInputStream(byte[] in) throws Exception
    {
        
        ByteArrayInputStream is = new ByteArrayInputStream(in);
        return is;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将byte数组转换成String<br>
     * @see <br>
     * @param in
     * @return
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.file.StreamUtilbc#byteTOString <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:53:33 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String byteTOString(byte[] in) throws Exception
    {
        return InputStreamTOString(byteTOInputStream(in), UTF_8);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将byte数组转换成String <br>
     * @see <br>
     * @param in
     * @return
     * @throws IOException
     * @throws Exception <br>
     * @String <br>
     * @methods pers.bc.utils.file.StreamUtilbc#getString <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:54:39 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getString(String in) throws IOException, Exception
    {
        return byteTOString(StringTObyte(in));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： InputStream 转换成byte[]<br>
     * @see <br>
     * @param is
     * @return
     * @throws IOException <br>
     * @byte[] <br>
     * @methods pers.bc.utils.file.StreamUtilbc#getBytes <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:52:42 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public byte[] getBytes(InputStream is) throws IOException
    {
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = new byte[BUFFER_SIZE];
        int len = 0;
        
        while ((len = is.read(b, 0, BUFFER_SIZE)) != -1)
        {
            baos.write(b, 0, len);
        }
        
        baos.flush();
        
        byte[] bytes = baos.toByteArray();
        
        System.out.println(new String(bytes));
        
        return bytes;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据文件路径创建文件输入流处理 以字节为单位（非 unicode ） <br>
     * @see <br>
     * @param filepath
     * @return
     * @throws FileNotFoundException <br>
     * @FileInputStream <br>
     * @methods pers.bc.utils.file.StreamUtilbc#getFileInputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:52:29 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static FileInputStream getFileInputStream(String filepath) throws FileNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream(filepath);
        return fileInputStream;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据文件对象创建文件输入流处理 以字节为单位（非 unicode ） <br>
     * @see <br>
     * @param file
     * @return
     * @throws FileNotFoundException <br>
     * @FileInputStream <br>
     * @methods pers.bc.utils.file.StreamUtilbc#getFileInputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:52:06 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static FileInputStream getFileInputStream(File file) throws FileNotFoundException
    {
        FileInputStream fileInputStream = new FileInputStream(file);
        return fileInputStream;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据文件对象创建文件输出流处理 以字节为单位（非 unicode ） <br>
     * @see <br>
     * @param file
     * @param append
     * @return
     * @throws FileNotFoundException <br>
     * @FileOutputStream <br>
     * @methods pers.bc.utils.file.StreamUtilbc#getFileOutputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:51:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static FileOutputStream getFileOutputStream(File file, boolean append) throws FileNotFoundException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(file, append);
        
        return fileOutputStream;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 根据文件路径创建文件输出流处理 以字节为单位（非 unicode ）<br>
     * @see <br>
     * @param filepath
     * @param append
     * @return
     * @throws FileNotFoundException <br>
     * @FileOutputStream <br>
     * @methods pers.bc.utils.file.StreamUtil#getFileOutputStream <br>
     * @author licheng <br>
     * @date Created on 2020年1月6日 <br>
     * @time 上午11:51:03 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static FileOutputStream getFileOutputStream(String filepath, boolean append) throws FileNotFoundException
    {
        FileOutputStream fileOutputStream = new FileOutputStream(filepath, append);
        return fileOutputStream;
    }
    
    public static File getFile(String filepath)
    {
        return new File(filepath);
    }
    
    public static ByteArrayOutputStream getByteArrayOutputStream()
    {
        return new ByteArrayOutputStream();
    }
    
    private static int reverseBytes(Integer source)
    {
        if (source == null) source = 0;
        return Integer.reverseBytes(source);
    }
    
    private static long reverseBytes(Double source)
    {
        if (source == null) source = 0.0d;
        return Long.reverseBytes(Double.doubleToLongBits(source));
    }
    
    public static void writeStr(DataOutputStream dop, String value) throws IOException
    {
        if (value == null || value.trim().length() == 0)
        {
            dop.writeInt(reverseBytes(0));
        }
        else
        {
            dop.writeInt(reverseBytes(value.getBytes().length));
            dop.write(value.getBytes());
        }
    }
    
    public static void writeDouble(DataOutputStream dop, Double value) throws IOException
    {
        dop.writeLong(reverseBytes(value));
    }
    
    public static void writeInt(DataOutputStream dop, Integer value) throws IOException
    {
        dop.writeInt(reverseBytes(value));
    }
    
    public static void writeBoolean(DataOutputStream dop, boolean value) throws IOException
    {
        dop.writeBoolean(value);
    }
    
    // /////////////////////////////////////////////////////////////////////////////////////
    /**
     * *********************************************************** <br>
     * 说明：把对象写入文 <br>
     * @see <br>
     * @param obj <br>
     * @void <br>
     * @methods pers.bc.utils.file.StreamUtilbc#witeObjToFile <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-3 <br>
     * @time 19:44:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     * @return
     */
    public static String witeObjToFile(Object obj)
    {
        return witeObjToFile(obj, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：把对象写入文 <br>
     * @see <br>
     * @param obj
     * @param filePatch
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.file.StreamUtilbc#witeObjToFile <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-3 <br>
     * @time 20:34:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String witeObjToFile(Object obj, String filePatch)
    {
        if (PubEnvUtilbc.isEmpty(filePatch))
        {
            URL resource = obj.getClass().getResource("");
            filePatch = resource.getPath();
        }
        String[] split = obj.getClass().getName().split("\\.");
        
        File file = new File(filePatch + File.separator + split[split.length - 1] + ".data");
        FileOutputStream out = null;
        try
        {
            if (file.exists())
            {
                FileUtilbc.backToFile(file.getPath(), file.getName());
            }
            out = new FileOutputStream(file);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(obj);
            
            FileUtilbc.flush(objOut);
            FileUtilbc.close(objOut);
            
            return file.getPath();
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            FileUtilbc.close(out);
        }
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明：把对象从文件当中读出来 <br>
     * @see <br>
     * @param filePatch
     * @return <br>
     * @Object <br>
     * @methods pers.bc.utils.file.StreamUtilbc#readObjFromFile <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-3 <br>
     * @time 20:19:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object readObjFromFile(String filePatch)
    {
        File file = new File(filePatch);
        if (!file.exists()) //
            return null;
        
        Object obj = null;
        FileInputStream in = null;
        ObjectInputStream objIn = null;
        try
        {
            // 获取最后一个.的位置
            int lastIndexOf = file.getName().lastIndexOf(".");
            // 获取文件的后缀名 .data
            String suffix = file.getName().substring(lastIndexOf);
            if (!PubEnvUtilbc.equals(".data", suffix)) //
                throw new RuntimeException("当前文件不是{[.data]}序列文件，不支持反序列化！");
            
            in = new FileInputStream(file);
            objIn = new ObjectInputStream(in);
            obj = objIn.readObject();
            
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            FileUtilbc.close(in, objIn);
        }
        
        return obj;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param filePatch
     * @return <br>
     * @List <br>
     * @methods pers.bc.utils.file.StreamUtilbc#readObjFromFiles <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-3 <br>
     * @time 20:51:40 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<Object> readObjFromFiles(String filePatch)
    {
        File file = new File(filePatch);
        // 获取最后一个.的位置
        int lastIndexOf = file.getName().lastIndexOf(".");
        // 获取文件的后缀名 .data
        String suffix = file.getName().substring(lastIndexOf);
        List<Object> objList = new ArrayList<>();
        if (PubEnvUtilbc.equals(".data", suffix))
        {
            objList.add(readObjFromFile(filePatch));
        }
        else
        {
            List<File> listFile = FileUtilbc.listFile(file.getParent());
            int i = PubEnvUtilbc._DEFAULT_VALUE, j = PubEnvUtilbc.getEncryptObjSize(listFile);
            for (; i < j; i += 7)
            {
                file = listFile.get(PubEnvUtilbc.getDecryptObjSize(i));
                // 获取最后一个.的位置
                lastIndexOf = file.getName().lastIndexOf(".");
                // 获取文件的后缀名 .data
                suffix = file.getName().substring(lastIndexOf);
                if (PubEnvUtilbc.equals(".data", suffix))
                {
                    objList.add(readObjFromFile(file.getPath()));
                }
            }
        }
        
        return objList;
    }
}
