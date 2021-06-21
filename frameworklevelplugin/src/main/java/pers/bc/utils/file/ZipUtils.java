package pers.bc.utils.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩解压缩
 * @qualiFild com.pub.utils.file.ZipUtils.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class ZipUtils
{
    
    public static final String SUFFIX_NAME = "zip";
    
    public ZipUtils()
    {
        super();
    }
    
    /**
     * 压缩文件
     * @param zipFile 压缩文件名路径
     * @param fileList 待压缩的文件集合
     * @param isDeleteRes 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     * @return 压缩后的压缩包File
     */
    
    public static File compress(String zipFile, List<File> fileList, boolean isDelete)
    {
        
        File f = isCheckFileNameByZip(zipFile);
        
        return compress(f.getName(), f.getParent(), getFilePathArray(fileList), false, isDelete);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： * 压缩文件
     * @param zipFile 压缩文件名路径
     * @param fileList 待压缩的文件集合
     * @return 压缩后的压缩包File
     * @File
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:37:03
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static File compress(String zipFile, List<File> fileList)
    {
        
        return compress(zipFile, fileList, true);
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 校验目标文件名称是否是zip格式
     * @see
     * @param zipFile
     * @return
     * @File
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:37:20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static File isCheckFileNameByZip(String zipFile)
    {
        
        return new File(zipFile.substring(0, isCheckFileNameInZip(zipFile)));
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 校验目标文件名称是否是zip格式
     * @see
     * @param zipFile
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:37:27
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static int isCheckFileNameInZip(String zipFile)
    {
        
        int las = zipFile.lastIndexOf(".");
        
        if (las == -1)
        {
            
            throw new RuntimeException(zipFile + " is not zip format! this format = ??? ");
            
        }
        
        String format = zipFile.substring(las + 1);
        
        if (!SUFFIX_NAME.equalsIgnoreCase(format))
        {
            
            throw new RuntimeException(zipFile + " is not zip format! this format = " + format);
            
        }
        
        return las;
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param list
     * @return
     * @String[]
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:37:35
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static String[] getFilePathArray(List<File> list)
    {
        
        String[] strs = new String[list.size()];
        
        for (int index = 0; index < strs.length; index++)
        {
            
            strs[index] = list.get(index).getPath();
            
        }
        
        return strs;
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： * 压缩文件
     * @param zipName 压缩文件名, 无需加后缀
     * @param zipFilePath 压缩路径
     * @param filePaths 待压缩的文件路径
     * 
     * @param isNewFolder 是否在压缩包新建同名文件夹
     * @param isDeleteRes 如果出现同名压缩包, 是否删除原本的压缩包, 如果false,且出现同名则抛异常
     * @return 压缩后的压缩包File
     * @File
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:37:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static File compress(String zipName, String zipFilePath, String[] filePaths, boolean isNewFolder, boolean isDeleteRes)
    {
        
        File target = null;
        
        File source = new File(zipFilePath);
        
        if (source.exists())
        {
            
            String base = isNewFolder ? zipName + File.separator : "";
            
            zipName = zipName + "." + SUFFIX_NAME;// 压缩文件名=源文件名.zip
            
            target = new File(source.getPath(), zipName);
            
            if (target.exists())
            {
                
                if (!isDeleteRes)
                {
                    
                    throw new RuntimeException("Compression package name repetition !");
                    
                }
                
                target.delete(); // 删除旧的文件
                
            }
            
            FileOutputStream fos = null;
            
            ZipOutputStream zos = null;
            
            try
            {
                
                fos = new FileOutputStream(target);
                
                zos = new ZipOutputStream(new BufferedOutputStream(fos));
                
                // 添加对应的文件Entry
                
                for (String fip : filePaths)
                {
                    
                    addEntry(base, new File(fip), zos);
                    
                }
                
            }
            catch (IOException e)
            {
                
                throw new RuntimeException(e);
                
            }
            finally
            {
                
                closeQuietly(zos, fos);
                
            }
            
        }
        
        return target;
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 解压文件 filePath所代表文件系统不能与targetStr一致
     * @param filePath 压缩文件路径
     * @param targetStr 解压至所在文件目录
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:38:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void decompression(String filePath, String targetStr)
    {
        
        isCheckFileNameInZip(filePath);
        
        File source = new File(filePath);
        
        if (source.exists())
        {
            
            ZipInputStream zis = null;
            
            BufferedOutputStream bos = null;
            
            try
            {
                
                zis = new ZipInputStream(new FileInputStream(source));
                
                ZipEntry entry = null;
                
                while ((entry = zis.getNextEntry()) != null && !entry.isDirectory())
                {
                    
                    File target = new File(targetStr, entry.getName());
                    
                    if (!target.getParentFile().exists())
                    {
                        
                        target.getParentFile().mkdirs();// 创建文件父目录
                        
                    }
                    
                    // 写入文件
                    
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    
                    int read = 0;
                    
                    byte[] buffer = new byte[1024 * 10];
                    
                    while ((read = zis.read(buffer, 0, buffer.length)) != -1)
                    {
                        
                        bos.write(buffer, 0, read);
                        
                    }
                    
                    bos.flush();
                    
                }
                
                zis.closeEntry();
                
            }
            catch (Exception e)
            {
                
                throw new RuntimeException(e);
                
            }
            finally
            {
                
                closeQuietly(zis, bos);
                
            }
            
        }
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 扫描添加文件Entry
     * @param base 基路径
     * @param source 源文件
     * @param zos Zip文件输出流
     * @throws IOException
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:38:25
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static void addEntry(String base, File source, ZipOutputStream zos) throws IOException
    {
        
        // 按目录分级，形如：/aaa/bbb.txt
        
        String entry = base.concat(source.getName());
        
        if (source.isDirectory())
        {
            
            for (File file : source.listFiles())
            {
                
                // 递归列出目录下的所有文件，添加文件Entry
                
                addEntry(entry + File.separator, file, zos);
                
            }
            
        }
        else
        {
            
            FileInputStream fis = null;
            
            BufferedInputStream bis = null;
            
            try
            {
                
                byte[] buffer = new byte[1024 * 10];
                
                fis = new FileInputStream(source);
                
                bis = new BufferedInputStream(fis, buffer.length);
                
                int read = 0;
                
                zos.putNextEntry(new ZipEntry(entry));
                
                while ((read = bis.read(buffer, 0, buffer.length)) != -1)
                {
                    
                    zos.write(buffer, 0, read);
                    
                }
                
                zos.closeEntry();
                
            }
            finally
            {
                
                closeQuietly(bis, fis);
                
            }
            
        }
        
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
    public static void closeQuietly(Closeable... closeables)
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
            
        }
    }
    
    /**
     * 文档压缩
     * 
     * @param file 需要压缩的文件或目录
     * @param dest 压缩后的文件名称
     * @throws Exception
     */
    public static void deCompress(File file, String dest) throws IOException
    {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dest)))
        {
            String dir = "";
            if (file.isDirectory())
            {
                dir = file.getName();
            }
            zipFile(file, zos, dir);
        }
        catch (IOException e)
        {
            throw e;
        }
    }
    
    public static void zipFile(File inFile, ZipOutputStream zos, String dir) throws IOException
    {
        if (inFile.isDirectory())
        {
            File[] files = inFile.listFiles();
            if (files == null || files.length == 0)
            {
                String entryName = dir + "/";
                zos.putNextEntry(new ZipEntry(entryName));
                return;
            }
            for (File file : files)
            {
                String entryName = dir + "/" + file.getName();
                if (file.isDirectory())
                {
                    zipFile(file, zos, entryName);
                }
                else
                {
                    ZipEntry entry = new ZipEntry(entryName);
                    zos.putNextEntry(entry);
                    try (InputStream is = new FileInputStream(file))
                    {
                        int len = 0;
                        while ((len = is.read()) != -1)
                        {
                            zos.write(len);
                        }
                    }
                    catch (IOException e)
                    {
                        throw e;
                    }
                }
            }
        }
        else
        {
            String entryName = dir + "/" + inFile.getName();
            ZipEntry entry = new ZipEntry(entryName);
            zos.putNextEntry(entry);
            try (InputStream is = new FileInputStream(inFile))
            {
                int len = 0;
                while ((len = is.read()) != -1)
                {
                    zos.write(len);
                }
            }
            catch (IOException e)
            {
                throw e;
            }
        }
    }
    
    /**
     * 文档解压
     * 
     * @param source 需要解压缩的文档名称
     * @param path 需要解压缩的路径
     */
    public static void unCompress(File source, String path) throws IOException
    {
        ZipEntry zipEntry = null;
        FileUtilbc.createPaths(path);
        // 实例化ZipFile，每一个zip压缩文件都可以表示为一个ZipFile
        // 实例化一个Zip压缩文件的ZipInputStream对象，可以利用该类的getNextEntry()方法依次拿到每一个ZipEntry对象
        try (ZipFile zipFile = new ZipFile(source); ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(source)))
        {
            while ((zipEntry = zipInputStream.getNextEntry()) != null)
            {
                String fileName = zipEntry.getName();
                String filePath = path + "/" + fileName;
                if (zipEntry.isDirectory())
                {
                    File temp = new File(filePath);
                    if (!temp.exists())
                    {
                        temp.mkdirs();
                    }
                }
                else
                {
                    File temp = new File(filePath);
                    if (!temp.getParentFile().exists())
                    {
                        temp.getParentFile().mkdirs();
                    }
                    try (OutputStream os = new FileOutputStream(temp);
                    // 通过ZipFile的getInputStream方法拿到具体的ZipEntry的输入流
                            InputStream is = zipFile.getInputStream(zipEntry))
                    {
                        int len = 0;
                        while ((len = is.read()) != -1)
                        {
                            os.write(len);
                        }
                    }
                    catch (IOException e)
                    {
                        throw e;
                    }
                }
                
            }
        }
        catch (IOException e)
        {
            throw e;
        }
    }
}
