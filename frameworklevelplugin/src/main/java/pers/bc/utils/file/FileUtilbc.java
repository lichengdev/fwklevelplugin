/**
 * <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen
 * Chao.
 */
package pers.bc.utils.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;
import pers.bc.utils.pub.RegUtil;
import pers.bc.utils.pub.SysUtil;
import pers.bc.utils.throwable.ExceptionUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 封装了些文件相关的操作
 *
 * @qualiFild com.pub.utils.file.FileUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class FileUtilbc
{
    private static LoggerUtilbc log = LoggerUtilbc.getInstance("filelogs");

    /**
     * *********************************************************** <br>
     * 说明：根据文件的修改日期排序 <br>
     * @see <br>
     * @param fliePath <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#orderByDate <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-29 <br>
     * @time 23:52:50 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void orderByDate(String fliePath)
    {
        File file = new File(fliePath);
        File[] files = file.listFiles();
        Arrays.sort(files, new Comparator<File>()
        {
            public int compare(File file1, File file2)
            {
                long diff = file1.lastModified() - file2.lastModified();
                if (diff > 0) return 1;
                else if (diff == 0) return 0;
                else
                    return -1;
            }

            public boolean equals(Object obj)
            {
                return true;
            }

        });
        for (int i = files.length - 1; i > -1; i--)
        {
            System.out.println(files[i].getName() + ":" + new Date(files[i].lastModified()));
            log.info(files[i].getName() + ":" + new Date(files[i].lastModified()));
        }
    }

    /**
     * *********************************************************** <br>
     * *说明：遍历目录，将文件从GBK转换成UTF-8 <br>
     * @see <br>
     * @param targetFileUrl
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#GBKToUTF8 <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-19 <br>
     * @time 15:12:15 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void GBKToUTF8(String targetFileUrl) throws IOException
    {
        codeConvertFileList(new File(targetFileUrl), PubConsUtilbc.GBK, PubConsUtilbc.UTF_8);
    }

    /**
     * *********************************************************** <br>
     * * 说明：遍历目录，将文件从UTF-8转换成 GBK <br>
     * @see <br>
     * @param targetFileUrl
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#UTF8ToGBK <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-19 <br>
     * @time 15:13:03 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void UTF8ToGBK(String targetFileUrl) throws IOException
    {
        codeConvertFileList(new File(targetFileUrl), PubConsUtilbc.UTF_8, PubConsUtilbc.GBK);
    }

    /**
     * *********************************************************** <br>
     * 说明： 遍历目录，将文件从原来的编码转换成指定的编码<br>
     * @see <br>
     * @param targetFile
     * @param sourceCharset 来源编码<br>
     * @param targetCharset 目录编码<br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#fileList <br>
     * @author LiBencheng <br>
     * @throws IOException
     * @date Created on 2020-11-19 <br>
     * @time 15:04:22 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void codeConvertFileList(File targetFile, String sourceCharset, String targetCharset) throws IOException
    {
        File rootFile = targetFile;
        File[] files = rootFile.listFiles();
        BufferedReader br = null;
        BufferedWriter bw = null;
        try
        {
            if (files != null)
            {
                for (File f : files)
                {

                    if (!f.isDirectory())
                    {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(f), Charset.forName(sourceCharset)));
                        StringBuilder sb = new StringBuilder();
                        String str = "";
                        while ((str = br.readLine()) != null)
                        {
                            sb.append(str);
                            sb.append(PubConsUtilbc.NEWLINE);
                        }
                        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), Charset.forName(targetCharset)));
                        bw.write(sb.toString());
                        bw.flush();
                        // bw.close();
                        // br.close();
                    }
                    codeConvertFileList(f, sourceCharset, targetCharset);// 递归调用子文件夹下的文件
                }
            }
        }
        catch (IOException e)
        {
            log.consoleout("遍历目录，将文件从原来的编码转换成指定的编码" + e.getMessage());

            throw new IOException(e);
        }
        finally
        {
            close(br, bw);
        }
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param relativePosition
     * @param fileName
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.file.FileUtilbc#findPath <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-19 <br>
     * @time 15:19:02 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String findPath(Class<?> relativePosition, String fileName)
    {
        return relativePosition.getResource(fileName).getPath();
    }

    /** * Buffer的大小 */
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

    public static MessageDigest MD5 = null;

    static
    {
        try
        {
            MD5 = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException ne)
        {
            log.consoleout("MD5Thw" + ne.getMessage());

        }
    }

    /**
     * *********************************************************** <br>
     * 说明： 备份文件，在原来文件上加{0}<br>
     *
     * @see <br>
     * @param logPatch
     * @param fileName
     * @throws IOException <br>
     * @void <br>
     * @methods com.pub.utils.file.FileUtil#backToFile <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午11:08:42 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void backToFile(String logPatch, String fileName) throws IOException
    {
        backToFile(logPatch, fileName, null);
    }

    /**
     * *********************************************************** <br>
     * 说明： 备份文件，在原来文件上加{0}_suffix<br>
     * @see <br>
     * @param logPatch
     * @param fileName
     * @param _suffix
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#backToFile <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-25 <br>
     * @time 19:43:31 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void backToFile(String logPatch, String fileName, String _suffix) throws IOException
    {
        int i = 0;
        String oldFilePath = null;
        String newFilePath = null;
        File newFile = null;
        if (PubEnvUtilbc.isEmpty(fileName) || (fileName.contains("\\."))) throw new IOException("不是有效的文件名！！");
        String[] split = fileName.split("\\.");
        String prefix = split[0];
        String suffix = split[1];

        do
        {
            oldFilePath = logPatch.substring(0, logPatch.lastIndexOf(File.separator));
            newFilePath = oldFilePath + File.separator + prefix + "{" + i + "}." + suffix;
            if (!PubEnvUtilbc.isEmpty(_suffix)) newFilePath += _suffix;
            newFile = new File(newFilePath);
            i++;
        }
        while (newFile.exists());
        String filePatch = oldFilePath + File.separator + prefix + "." + suffix;
        // if (!PubEnvUtilbc.isEmpty(_suffix)) filePatch += _suffix;
        File oldFile = new File(filePatch);
        // 改名
        oldFile.renameTo(newFile);

    }

    /**
     * *********************************************************** <br>
     * 说明： 备份文件,校验文件是否存在，不存在将改名改名<br>
     *
     * @see <br>
     * @param logPatch
     * @param fileName
     * @throws IOException <br>
     * @void <br>
     * @methods com.pub.utils.file.FileUtil#checkFile <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午11:12:22 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void checkFile(String logPatch, String fileName) throws IOException
    {
        int i = 0;
        for (;;)
        {
            String oldFilePath = logPatch.substring(0, logPatch.lastIndexOf(File.separator));
            String newFilePath = oldFilePath + File.separator + fileName + "{" + i + "}.log";
            File newFile = new File(newFilePath);
            i++;
            // 文件存在
            if (newFile.exists()) continue;
            File oldFile = new File(oldFilePath + File.separator + fileName + ".log");
            // 改名
            oldFile.renameTo(newFile);
            break;
        }
    }

    /**
     * *********************************************************** <br>
     * 说明： 关闭一个或多个流对象
     *
     * @see
     * @param closeables
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午6:38:39
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void close(Closeable... closeables)
    {
        try
        {
            if (PubEnvUtilbc.getSize(closeables) > 0)
            {
                for (Closeable closeable : closeables)
                {
                    if (!PubEnvUtilbc.isEmptyObj(closeable)) closeable.close();
                }
            }
        }
        catch (IOException ioe)
        {
        }
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取文件的md5
     *
     * @see
     * @param file
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:06:34
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String fileMD5(File file)
    {
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1)
            {
                MD5.update(buffer, 0, length);
            }

            return new BigInteger(1, MD5.digest()).toString(16);
        }
        catch (IOException e)
        {
            log.consoleout("MD5Thw" + e.getMessage());

            return null;
        }
        finally
        {
            close(fileInputStream);
        }
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取文件的行数
     *
     * @param file 统计的文件
     * @return 文件行数
     * @int
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:06:14
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static int countLines(File file)
    {
        try (LineNumberReader rf = new LineNumberReader(new FileReader(file)))
        {
            long fileLength = file.length();
            rf.skip(fileLength);
            int lineNumber = rf.getLineNumber();
            close(rf);
            return lineNumber;
        }
        catch (IOException e)
        {
            log.consoleout("countLinesThw" + e.getMessage());
        }
        return 0;
    }

    /**
     * *********************************************************** <br>
     * 说明： 以列表的方式获取文件的所有行
     *
     * @param file 需要出来的文件
     * @return 包含所有行的list
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:06:04
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<String> lines(File file)
    {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
            }
            close(reader);
        }
        catch (IOException e)
        {
            log.consoleout("lines(File file)" + e.getMessage());
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 以列表的方式获取文件的所有行
     *
     * @param file 需要处理的文件
     * @param encoding 指定读取文件的编码
     * @return 包含所有行的list
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:05:21
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<String> lines(File file, String encoding)
    {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
            }
            close(reader);
        }
        catch (IOException e)
        {
            log.consoleout("lines(File file, String encoding)" + e.getMessage());
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 以列表的方式获取文件的指定的行数数据
     *
     * @param file 处理的文件
     * @param lines 需要读取的行数
     * @return 包含制定行的list
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:05:51
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<String> lines(File file, int lines)
    {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
                if (list.size() == lines)
                {
                    break;
                }
            }
            close(reader);
        }
        catch (IOException e)
        {
            log.consoleout("lines(File file, int lines)" + e.getMessage());

        }
        return list;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 以列表的方式获取文件的指定的行数数据
     *
     * @param file 需要处理的函数
     * @param lines 需要处理的行还俗
     * @param encoding 指定读取文件的编码
     * @return 包含制定行的list
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:35:43
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<String> lines(File file, int lines, String encoding)
    {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                list.add(line);
                if (list.size() == lines)
                {
                    break;
                }
            }
            close(reader);
        }
        catch (IOException e)
        {
            log.consoleout("lines(File file, int lines, String encoding)" + e.getMessage());

        }
        return list;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明：在文件末尾追加一行
     *
     * @param file 需要处理的函数
     * @param str 添加的子字符串
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:37:33
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean appendLine(File file, String str)
    {
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(SysUtil.FILE_SEPARATOR + str);
            close(randomFile);
            return true;
        }
        catch (IOException e)
        {
            log.consoleout("appendLine(File file, String str)" + e.getMessage());

        }
        return false;
    }

    /**
     * *********************************************************** <br>
     * 说明： 在文件末尾追加一行
     *
     * @param file 需要处理的文件
     * @param str 添加的字符串
     * @param encoding 指定写入的编码
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:37:50
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean appendLine(File file, String str, String encoding)
    {
        String lineSeparator = System.getProperty("line.separator", "\n");
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((lineSeparator + str).getBytes(encoding));
            close(randomFile);
        }
        catch (IOException e)
        {
            log.consoleout("appendLine(File file, String str, String encoding)" + e.getMessage());

        }
        return false;
    }

    /**
     * *********************************************************** <br>
     * 说明： 将字符串写入到文件中
     *
     * @see
     * @param file
     * @param str
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:38:21
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean write(File file, String str)
    {
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            randomFile.writeBytes(str);
            close(randomFile);
            return true;
        }
        catch (IOException e)
        {
            log.consoleout("write(File file, String str)" + e.getMessage());

        }
        return false;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 将字符串以追加的方式写入到文件中
     *
     * @see
     * @param file
     * @param str
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:07:53
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean writeAppend(File file, String str)
    {
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(str);
            close(randomFile);
            return true;
        }
        catch (IOException e)
        {
            log.consoleout("writeAppend(File file, String str)" + e.getMessage());

        }
        return false;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 将字符串以制定的编码写入到文件中
     *
     * @see
     * @param file
     * @param str
     * @param encoding
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:07:36
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean write(File file, String str, String encoding)
    {
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            randomFile.write(str.getBytes(encoding));
            close(randomFile);
            return true;
        }
        catch (IOException e)
        {
            log.consoleout("write(File file, String str, String encoding)" + e.getMessage());

            // ExceptionUtilbc.throwException(e);
        }
        return false;
    }

    /**
     * *********************************************************** <br>
     * 说明： 将字符串以追加的方式以制定的编码写入到文件中
     *
     * @see
     * @param file
     * @param str
     * @param encoding
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:07:25
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean writeAppend(File file, String str, String encoding)
    {
        try (RandomAccessFile randomFile = new RandomAccessFile(file, "rw"))
        {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write(str.getBytes(encoding));
            close(randomFile);
            return true;
        }
        catch (IOException e)
        {
            ExceptionUtilbc.throwException(e);
        }
        return false;
    }

    /**
     * *********************************************************** <br>
     * 说明： 快速清空一个超大的文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:38:49
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean cleanFile(File file)
    {
        try (FileWriter fw = new FileWriter(file))
        {
            fw.write("");
            close(fw);
            return true;
        }
        catch (IOException e)
        {
            log.exception("cleanFileThw", e);
        }
        return false;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 获取文件的Mime类型
     *
     * @param file 需要处理的文件
     * @return 返回文件的mime类型
     * @throws IOException
     * @String
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:53:18
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String mimeType(String file) throws IOException
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 获取文件的类型
     * <p/>
     * Summary:只利用文件头做判断故不全
     *
     * @param file 需要处理的文件
     * @return 文件类型
     * @String
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:53:45
     * @version 1.0 <br>
     * @throws MylbcException <br>
     */
    public final static String fileType(File file) throws MylbcException
    {
        return FileTypeImpl.getFileType(file);
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取文件最后的修改时间
     *
     * @param file 需要处理的文件
     * @return 返回文件的修改时间
     * @Date
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:54:04
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static Date modifyTime(File file)
    {
        return new Date(file.lastModified());
    }

    /**
     *
     * *********************************************************** <br>
     * 说明：复制文件
     *
     * @param resourcePath 源文件
     * @param targetPath 目标文件
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:54:22
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean copy(String resourcePath, String targetPath)
    {
        File file = new File(resourcePath);
        return copy(file, targetPath);
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 复制文件 通过该方式复制文件文件越大速度越是明显
     *
     * @param file 需要处理的文件
     * @param targetFile 目标文件
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:54:39
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean copy(File file, String targetFile)
    {
        try (FileInputStream fin = new FileInputStream(file); FileOutputStream fout = new FileOutputStream(new File(targetFile)))
        {
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            // 设定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1)
            {
                // 准备写入，防止其他读取，锁住文件
                buffer.flip();
                out.write(buffer);
                // 准备读取。将缓冲区清理完毕，移动文件内部指针
                buffer.clear();
            }
            close(in, out);
        }
        catch (IOException e)
        {
            log.consoleout("copy(File file, String targetFile)" + e.getMessage());
        }
        return false;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 创建多级目录
     *
     * @param paths 需要创建的目录
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午10:55:38
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean createPaths(String paths)
    {
        File dir = new File(paths);
        return !dir.exists() && dir.mkdir();
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 创建文件支持多级目录
     *
     * @param filePath 需要创建的文件
     * @return 是否成功+
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 上午11:06:15
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean createFiles(String filePath)
    {
        File file = new File(filePath);
        if (file.isDirectory())
        {
            if (!file.exists())
            {
                return file.mkdirs();
            }
        }
        else
        {
            File dir = file.getParentFile();
            if (!dir.exists())
            {
                if (dir.mkdirs())
                {
                    try
                    {
                        return file.createNewFile();
                    }
                    catch (IOException e)
                    {
                        ExceptionUtilbc.throwException(e);
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 删除一个文件
     *
     * @see
     * @param file
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:00:48
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean deleteFile(File file)
    {
        return file.delete();
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 删除一个目录
     *
     * @param file 需要处理的文件
     * @return 是否成功
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:01:04
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean deleteDir(File file)
    {
        List<File> files = listFileAll(file);
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File f : files)
            {
                if (f.isDirectory())
                {
                    deleteDir(f);
                }
                else
                {
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 快速的删除超大的文件
     *
     * @boolean
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:01:22
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static boolean deleteBigFile(File file)
    {
        return cleanFile(file) && file.delete();
    }

    /**
     * *********************************************************** <br>
     * 说明： 复制目录
     *
     * @param filePath 需要处理的文件
     * @param targetPath 目标文件
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:01:38
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static void copyDir(String filePath, String targetPath)
    {
        File file = new File(filePath);
        copyDir(file, targetPath);
    }

    /**
     * *********************************************************** <br>
     * 说明： 复制目录
     *
     * @param filePath 需要处理的文件
     * @param targetPath 目标文件
     * @void
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:01:55
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static void copyDir(File filePath, String targetPath)
    {
        File targetFile = new File(targetPath);
        if (!targetFile.exists())
        {
            createPaths(targetPath);
        }
        File[] files = filePath.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                String path = file.getName();
                if (file.isDirectory())
                {
                    copyDir(file, targetPath + "/" + path);
                }
                else
                {
                    copy(file, targetPath + "/" + path);
                }
            }
        }
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件
     *
     * @see
     * @param path
     * @return
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:02:22
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFile(String path)
    {
        File file = new File(path);
        return listFile(file);
    }

    /**
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @param child 是否罗列子文件
     * @return 包含所有文件的的list
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:04:53
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFile(String path, boolean child)
    {
        return listFile(new File(path), child);
    }

    /**
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:02:39
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFile(File path)
    {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    list.addAll(listFile(file));
                }
                else
                {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件
     *
     * @param path 指定的路径
     * @param child 是否罗列子目录
     * @return
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:04:37
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFile(File path, boolean child)
    {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (child && file.isDirectory())
                {
                    list.addAll(listFile(file));
                }
                else
                {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件包括文件夹
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:04:22
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFileAll(File path)
    {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                list.add(file);
                if (file.isDirectory())
                {
                    list.addAll(listFileAll(file));
                }
            }
        }
        return list;
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 罗列指定路径下的全部文件包括文件夹
     *
     * @param path 需要处理的文件
     * @param filter 处理文件的filter
     * @return 返回文件列表
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:04:12
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFileFilter(File path, FilenameFilter filter)
    {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    list.addAll(listFileFilter(file, filter));
                }
                else
                {
                    if (filter.accept(file.getParentFile(), file.getName()))
                    {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取指定目录下的特点文件,通过后缀名过滤
     *
     * @param dirPath 需要处理的文件
     * @param postfixs 文件后缀
     * @return 返回文件列表
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:03:56
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> listFileFilter(File dirPath, final String postfixs)
    {
        /*
         * 如果在当前目录中使用Filter讲只罗列当前目录下的文件不会罗列孙子目录下的文件 FilenameFilter filefilter = new FilenameFilter() { public
         * boolean accept(File dir, String name) { return name.endsWith(postfixs); } };
         */
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    list.addAll(listFileFilter(file, postfixs));
                }
                else
                {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(postfixs.toLowerCase()))
                    {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 在指定的目录下搜寻文个文件
     *
     * @param dirPath 搜索的目录
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     * @List<File>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:03:39
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> searchFile(File dirPath, String fileName)
    {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    list.addAll(searchFile(file, fileName));
                }
                else
                {
                    String Name = file.getName();
                    if (Name.equals(fileName))
                    {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 查找符合正则表达式reg的的文件 <br>
     * @see <br>
     * @param dirPath
     * @param reg
     * @return <br>
     * @List<File> <br>
     * @methods pers.bc.utils.file.FileUtilbc#searchFileReg <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-17 <br>
     * @time 下午7:07:25 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static List<File> searchFileReg(File dirPath, String reg)
    {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (!ArrayUtilbc.isEmptyObj(files))
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    list.addAll(searchFile(file, reg));
                }
                else
                {
                    String Name = file.getName();
                    if (RegUtil.isMatche(Name, reg))
                    {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取文件后缀名 <br>
     * @see <br>
     * @param file
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.file.FileUtilbc#suffix <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-17 <br>
     * @time 下午7:07:16 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String suffix(File file)
    {
        String fileName = file.getName();
        return fileName.substring(fileName.indexOf(".") + 1);
    }

    /**
     * *********************************************************** <br>
     * 说明： 比较原始的一种实现方法，首先将文件一次性读入内存， <br>
     * 然后通过MessageDigest进行MD5加密， <br>
     * 最后再手动将其转换为16进制的MD5值。<br>
     *
     * @see <br>
     * @param path
     * @return
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     * @throws IOException <br>
     * @String <br>
     * @methods nc.pub.itf.tools.encrypt.SecurityHelper#getMD5One <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 上午11:25:08 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getFileMD5One(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {

        String[] strHex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        StringBuffer sb = new StringBuffer();
        byte[] b = MD5.digest(StreamUtilbc.readFileToByteArray(path));
        for (int i = 0; i < b.length; i++)
        {
            int d = b[i];
            if (d < 0)
            {
                d += 256;
            }
            int d1 = d / 16;
            int d2 = d % 16;
            sb.append(strHex[d1] + strHex[d2]);
        }

        return sb.toString();
    }

    /**
     * *********************************************************** <br>
     * 说明： 这里借助了Integer类的方法实现16进制的转换，比方法一更简洁一些。 <br>
     * PS：JAVA中byte是有负数的，代码中&0xff的操作与计算机中数据存储的原理有关，即负数存储的是二进制的补码。<br>
     *
     * @see 获取文件的MD5值<br>
     * @param path
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws NoSuchAlgorithmException <br>
     * @String <br>
     * @methods nc.pub.itf.tools.encrypt.SecurityHelper#getMD5Two <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 上午11:24:44 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getFileMD5Two(String path) throws FileNotFoundException, IOException, NoSuchAlgorithmException
    {
        StringBuffer sb = new StringBuffer();
        MD5.update(StreamUtilbc.readFileToByteArray(path));
        byte b[] = MD5.digest();
        int d;
        for (int i = 0; i < b.length; i++)
        {
            d = b[i];
            if (d < 0)
            {
                d = b[i] & 0xff;
                // 与上一行效果等同
                // i += 256;
            }
            if (d < 16) sb.append("0");
            sb.append(Integer.toHexString(d));
        }

        return sb.toString();
    }

    /**
     * *********************************************************** <br>
     * 说明：在读入文件信息上有点不同。 这里是分多次将一个文件读入，<br>
     * 对于大型文件而言，比较推荐这种方式，占用内存比较少。步骤三则是通过BigInteger类提供的方法进行16进制的转换 <br>
     *
     * @see <br>
     * @param path
     * @return
     * @throws NoSuchAlgorithmException
     * @throws IOException <br>
     * @String <br>
     * @methods nc.pub.itf.tools.encrypt.SecurityHelper#getMD5Three <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 上午11:25:47 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getFileMD5Three(String path) throws NoSuchAlgorithmException, IOException
    {
        BigInteger bi = null;

        byte[] buffer = new byte[8192];
        int len = 0;
        FileInputStream fis = new FileInputStream(new File(path));
        while ((len = fis.read(buffer, 0, buffer.length)) != -1)
        {
            MD5.update(buffer, 0, len);
        }
        fis.close();
        byte[] b = MD5.digest();
        bi = new BigInteger(1, b);

        return bi.toString(16);
    }

    /**
     * *********************************************************** <br>
     * 说明：获取目录下的所有图片文件，包括子目录 <br>
     * @see <br>
     * @param psnPhotoFileMap
     * @param allNames
     * @param path <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#getFileForDirectory <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-17 <br>
     * @time 下午7:06:35 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void getFileForDirectory(Map<String, File> psnPhotoFileMap, String[] allNames, String path)
    {
        for (int i = 0; i < allNames.length; i++)
        {
            File tempFile = new File(path + "/" + allNames[i]);
            if (tempFile.isDirectory())
            {// 处理目录
                path = tempFile.getPath();
                File file = new File(path);
                allNames = file.list();
                getFileForDirectory(psnPhotoFileMap, allNames, path);
            }
            else
            {
                // 处理文件
                String fileName = tempFile.getName();
                // 这里文件后缀不能转化为小写。
                String suffix = fileName.substring(fileName.length() - 4)/* .toLowerCase() */;
                String[] psncodes = fileName.split(suffix);
                String[] psncode = psncodes[0].split("-");
                psnPhotoFileMap.put(psncode[0], tempFile);
            }
        }
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param flushables <br>
     * @void <br>
     * @methods pers.bc.utils.file.FileUtilbc#flush <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:44:08 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static void flush(Flushable... flushables)
    {

        try
        {
            if (PubEnvUtilbc.getSize(flushables) > 0)
            {
                for (Flushable flushable : flushables)
                {
                    if (!PubEnvUtilbc.isEmptyObj(flushable)) flushable.flush();
                }
            }
        }
        catch (IOException ioe)
        {
        }

    }

}
