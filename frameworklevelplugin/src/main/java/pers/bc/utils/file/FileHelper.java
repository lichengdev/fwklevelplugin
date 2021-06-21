package pers.bc.utils.file;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.util.ArrayList;
import java.util.List;

import pers.bc.utils.pub.LoggerUtilbc;

/**
 * 文件帮手
 * @qualiFild com.pub.utils.file.FileHelper.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class FileHelper
{
    
    private List<String> filePathList = new ArrayList<String>();
    
    @SuppressWarnings("resource")
    public byte[] getContent(String filePath) throws IOException
    {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE)
        {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0)
        {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length)
        {
            throw new IOException("Could not completely read file " + file.getName());
        }
        close(fi);
        return buffer;
    }
    
    /**
     * the traditional io way
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException
    {
        
        File f = new File(filename);
        if (!f.exists())
        {
            throw new FileNotFoundException(filename);
        }
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try
        {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size)))
            {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        }
        catch (IOException e)
        {
            LoggerUtilbc.getInstance("filelogs").exception("toByteArrayThw", e);
            throw e;
        }
        finally
        {
            close(in, bos);
        }
    }
    
    /**
     * NIO
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filename) throws IOException
    {
        
        File f = new File(filename);
        if (!f.exists())
        {
            throw new FileNotFoundException(filename);
        }
        
        FileChannel channel = null;
        FileInputStream fs = null;
        try
        {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0)
            {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            close(channel, fs);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     * @see
     * @param filename
     * @return
     * @throws IOException
     * @byte[]
     * @author licheng
     * @date Created on 2019-7-26
     * @time 下午5:57:17
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @SuppressWarnings("resource")
    public static byte[] toByteArray3(String filename) throws IOException
    {
        
        FileChannel fc = null;
        try
        {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0)
            {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        }
        catch (IOException e)
        {
            throw e;
        }
        finally
        {
            close(fc);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取一个路径下的所有文件(包含子文件夹)
     * @see
     * @param rootPath
     * @return
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-26
     * @time 下午5:56:56
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public List<String> getAllFilePath(String rootPath)
    {
        File file = new File(rootPath);
        File[] files = file.listFiles();
        
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                getAllFilePath(files[i].getPath());
            }
            else
            {
                filePathList.add(files[i].getPath());
            }
        }
        
        return filePathList;
    }
    
    /**
     * 获取一个路径下的所有文件(包含子文件夹) 可以指定过滤规则
     * 
     * @param rootPath
     */
    public List<String> getAllFilePath(String rootPath, FileFilter fileFilter)
    {
        File file = new File(rootPath);
        File[] files = file.listFiles(fileFilter);
        
        for (int i = 0; i < files.length; i++)
        {
            if (files[i].isDirectory())
            {
                getAllFilePath(files[i].getPath(), fileFilter);
            }
            else
            {
                filePathList.add(files[i].getPath());
            }
        }
        
        return filePathList;
    }
    
    /**
     * 删除空目录
     * @param dir 将要删除的目录路径
     */
    public static void doDeleteEmptyDir(String dir)
    {
        boolean success = (new File(dir)).delete();
        if (success)
        {
            System.out.println("Successfully deleted empty directory: " + dir);
        }
        else
        {
            System.out.println("Failed to delete empty directory: " + dir);
        }
    }
    
    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a deletion fails, the method stops
     *         attempting to delete and returns "false".
     */
    public static boolean deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++)
            {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success)
                {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }
    
    /**
     * 使用文件通道的方式复制文件
     * 
     * @param srcPath 源文件 "F:/期刊导航数据/学术之家/21世纪/54431b8d_92cf.jpg"
     * @param descPath 复制到的新文件 "F:/期刊导航数据/out
     */
    public static void copy(String srcPath, String descPath)
    {
        File srcFile = new File(srcPath);
        copy(srcFile, descPath);
    }
    
    /**
     * 使用文件通道的方式复制文件
     * @param srcFile 源文件 源文件 "F:/期刊导航数据/学术之家/21世纪/54431b8d_92cf.jpg"
     * @param descPath 目标路径 "F:/期刊导航数据/out"
     * 
     */
    public static void copy(File srcFile, String descPath)
    {
        File s = srcFile;
        File t = new File(descPath + File.separator + s.getName());
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try
        {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        }
        catch (IOException e)
        {
            LoggerUtilbc.getInstance("filelogs").exception("copyThw", e);
        }
        finally
        {
            close(fi, in, fo, out);
        }
    }
    
    /**
     * 对当前文件夹下的文件改名
     * @param oldFilePath
     * @param newName
     */
    public static void reName(String oldFilePath, String newName)
    {
        String basePath = oldFilePath.substring(0, oldFilePath.lastIndexOf(File.separator));
        String descPaht = basePath + File.separator + newName;
        reNameTo(oldFilePath, descPaht);
    }
    
    /**
     * 对当前文件夹下的文件改名
     * @param oldFile
     * @param newName
     */
    public static void reName(File oldFile, String newName)
    {
        String oldFilePath = oldFile.getPath();
        String basePath = oldFilePath.substring(0, oldFilePath.lastIndexOf("\\"));
        String descPaht = basePath + File.separator + newName;
        reNameTo(oldFile, descPaht);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 在目标目录下创建 文件夹
     * @see
     * @param descPath
     * @param dirName
     * @void
     * @author licheng
     * @date Created on 2019-7-26
     * @time 下午5:57:52
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void makeDir(String descPath, String dirName)
    {
        File file = new File(descPath);
        
        if (file != null)
        {
            new File(descPath + File.separator + dirName).mkdir();
        }
    }
    
    private static void reNameTo(File oldFile, String newFilePath)
    {
        oldFile.renameTo(new File(newFilePath));
    }
    
    private static void reNameTo(String oldFilePath, String newFilePath)
    {
        File oldFile = new File(oldFilePath);
        oldFile.renameTo(new File(newFilePath));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 关闭一个或多个流对象
     * @see
     * @param closeables
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:38:39
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void close(Closeable... closeables)
    {
        try
        {
            if (closeables != null && closeables.length > 0)
            {
                for (Closeable closeable : closeables)
                {
                    
                    if (closeable != null)
                    {
                        closeable.close();
                    }
                }
            }
        }
        catch (IOException e)
        {
            LoggerUtilbc.getInstance("filelogs").exception("close(Closeable... closeables)Thw", e);
        }
    }
}
