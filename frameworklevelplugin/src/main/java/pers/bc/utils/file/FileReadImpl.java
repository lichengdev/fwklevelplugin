package pers.bc.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import pers.bc.utils.throwable.MylbcException;

/**
 * 封装了集中常用的文件读的方法
 * @qualiFild com.pub.utils.file.FileReadImpl.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class FileReadImpl
{
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 利用FileChannel直接实现文件的对拷,对于大文件速度特别明显
     * @see
     * @param source
     * @param target
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:11:10
     * @version 1.0 <br>
     * @throws MylbcException 
     ************************************************************* <br>
     */
    public static void copyFileWithChannel(File source, File target) throws MylbcException
    {
        try (FileInputStream inStream = new FileInputStream(source);
                FileOutputStream outStream = new FileOutputStream(target);
                FileChannel in = inStream.getChannel();
                FileChannel out = outStream.getChannel();)
        {
            in.transferTo(0, in.size(), out);
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 使用FileChannel+Buffer实现文件的读取拷贝是一种极佳的方案
     * @see
     * @param source
     * @param target
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:10:01
     * @version 1.0 <br>
     * @throws MylbcException 
     ************************************************************* <br>
     */
    public static void copyFileWithBuffer(File source, File target) throws MylbcException
    {
        try (FileInputStream inStream = new FileInputStream(source);
                FileOutputStream outStream = new FileOutputStream(target);
                FileChannel in = inStream.getChannel();
                FileChannel out = outStream.getChannel())
        {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            while (in.read(buffer) != -1)
            {
                buffer.flip();
                out.write(buffer);
                buffer.clear();
            }
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 利用Buffer实现文件的读取拷贝
     * @see
     * @param source
     * @param target
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:10:11
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void customBufferBufferedStreamCopy(File source, File target)
    {
        try (InputStream fis = new BufferedInputStream(new FileInputStream(source));
                OutputStream fos = new BufferedOutputStream(new FileOutputStream(target)))
        {
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 利用Buffer实现文件的读取拷贝
     * @see
     * @param source
     * @param target
     * @void
     * @author licheng
     * @date Created on 2019-7-28
     * @time 下午8:10:20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void customBufferStreamCopy(File source, File target)
    {
        try (InputStream fis = new FileInputStream(source); OutputStream fos = new FileOutputStream(target);)
        {
            byte[] buf = new byte[4096];
            int i;
            while ((i = fis.read(buf)) != -1)
            {
                fos.write(buf, 0, i);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
}
