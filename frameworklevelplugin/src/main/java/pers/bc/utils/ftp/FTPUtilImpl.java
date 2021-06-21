package pers.bc.utils.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import pers.bc.utils.cons.FTPUtilbc;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * FTP工具类的实现 需要 commons-net-3.3.jar
 * @qualiFild nc.pub.itf.tools.ftp.FTPUtilImpl.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-22<br>
 * @version 1.0<br>
 */
public class FTPUtilImpl implements FTPUtilbc
{
    private FTPClient client;
    private FTPVo vo;
    
    public FTPUtilImpl(FTPVo vo) throws IOException, MylbcException
    {
        this.vo = vo;
        client = createFTPClien(vo);
    }
    
    // 创建变连接FTP
    private FTPClient createFTPClien(FTPVo vo) throws MylbcException
    {
        FTPClient client = new FTPClient();
        int reply = -1;
        try
        {
            client.connect(vo.getHostName(), vo.getPort());
            client.login(vo.getUsername(), vo.getPassword());
            reply = client.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                client.disconnect();
                return null;
            }
            else
            {
                client.setControlEncoding(vo.getRemoteEncoding());
                client.setFileType(FTPClient.BINARY_FILE_TYPE);
                if (vo.isPassiveMode())
                {
                    client.enterLocalPassiveMode();
                }
                else
                {
                    client.enterRemotePassiveMode();
                }
                client.cwd(vo.getRemoteDir());
            }
            return client;
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
    
    // 通过FTP响应码判断是否操作成功
    public boolean reply(String operation)
    {
        int replyCode = client.getReplyCode();
        FTPLog log = new FTPLog();
        log.setHost(vo.getHostName());
        log.setOperation(operation);
        log.setLocalFile("");
        log.setRemoteFile("");
        log.setReplyCode(replyCode);
        log.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        return FTPReply.isPositiveCompletion(replyCode);
    }
    
    public boolean reply(String operation, String localFile, String remoteFile)
    {
        int replyCode = client.getReplyCode();
        FTPLog log = new FTPLog();
        log.setHost(vo.getHostName());
        log.setOperation(operation);
        log.setLocalFile(localFile);
        log.setRemoteFile(remoteFile);
        log.setReplyCode(replyCode);
        log.setReplyCodeDesc(FTPConstant.REPLYCODE.get(replyCode));
        
        return FTPReply.isPositiveCompletion(replyCode);
    }
    
    @Override
    public boolean isExists(String fileName) throws MylbcException
    {
        List<String> list = listFile(vo.getRemoteDir());
        if (list.contains(fileName))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean downLoad(String fileName) throws MylbcException
    {
        String localfileName = vo.getLocalDir() + File.separator + fileName;
        FileUtilbc.createFiles(localfileName);
        OutputStream out = null;
        try
        {
            out = new FileOutputStream(localfileName, true);
            client.retrieveFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"), out);
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (IOException e)
                {
                    throw new MylbcException(e);
                }
            }
        }
        return reply("DOWNLOAD", localfileName, fileName);
    }
    
    @Override
    public boolean downLoadDir(String directory) throws MylbcException
    {
        List<String> files = listFile(directory);
        for (String s : files)
        {
            downLoad(s);
        }
        return true;
    }
    
    @Override
    public boolean deleteFile(String fileName) throws MylbcException
    {
        if (isExists(fileName))
        {
            try
            {
                client.deleteFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
                return reply("DELETE", "", fileName);
            }
            catch (IOException e)
            {
                throw new MylbcException(e);
            }
        }
        return false;
    }
    
    @Override
    public boolean deleteDir(String directory) throws MylbcException
    {
        List<String> files = listFile(directory);
        try
        {
            for (String s : files)
            {
                deleteFile(s);
            }
            List<String> dirs = listDir(directory);
            for (int i = dirs.size() - 1; i >= 0; i--)
            {
                client.removeDirectory(new String(dirs.get(i).getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
            }
            client.removeDirectory(new String(directory.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return reply("DELETE", "", directory);
    }
    
    @Override
    public boolean putFile(String fileName, String remoteFileName, boolean isDelete) throws MylbcException
    {
        File file = new File(fileName);
        return putFile(file, remoteFileName, isDelete);
    }
    
    @Override
    public boolean putFile(File file, String remoteFileName, boolean isDelete) throws MylbcException
    {
        String fileName = remoteFileName;
        String path = "";
        if (remoteFileName.lastIndexOf("/") != -1)
        {
            path = remoteFileName.substring(0, remoteFileName.lastIndexOf("/"));
            fileName = remoteFileName.substring(remoteFileName.lastIndexOf("/") + 1);
            mkDir(path);
            changeWorkDir(path);
        }
        try
        {
            InputStream in = new FileInputStream(file);
            if (isDelete)
            {
                deleteFile(new String(file.getName().getBytes(vo.getRemoteEncoding()), "ISO-8859-1"));
            }
            client.appendFile(new String(fileName.getBytes(vo.getRemoteEncoding()), "ISO-8859-1"), in);
            return reply("UPLOAD", file.getAbsoluteFile().toString(), remoteFileName);
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        
    }
    
    @Override
    public boolean putDir(String fileName, String remoteDir) throws MylbcException
    {
        File file = new File(fileName);
        return putDir(file, remoteDir);
    }
    
    @Override
    public boolean putDir(File file, String remoteDir) throws MylbcException
    {
        List<File> list = FileUtilbc.listFile(file);
        for (File f : list)
        {
            String name = f.getAbsolutePath();
            name = name.substring(name.indexOf(file.getName())).replaceAll("\\\\", "/");
            putFile(f, remoteDir + "/" + name, true);
        }
        return true;
    }
    
    @Override
    public List<String> listFile(String directory) throws MylbcException
    {
        List<String> list = new ArrayList<String>();
        try
        {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++)
            {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isFile())
                {
                    list.add(t);
                }
                else if (files[i].isDirectory())
                {
                    list.addAll(listFile((t + "/").replaceAll("//", "/")));
                }
            }
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return list;
    }
    
    @Override
    public Map<String, FileAttr> listFileAttr(String directory) throws MylbcException
    {
        Map<String, FileAttr> map = new HashMap<String, FileAttr>();
        try
        {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isFile())
                {
                    FTPFile file = files[i];
                    String fileName = directory + file.getName();
                    FileAttr attr = new FileAttr();
                    attr.setFileName(fileName);
                    attr.setModifyTime(file.getTimestamp().getTime());
                    attr.setSize(file.getSize());
                    map.put(fileName, attr);
                }
                else if (files[i].isDirectory())
                {
                    map.putAll(listFileAttr(directory + files[i].getName() + "/"));
                }
            }
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return map;
    }
    
    @Override
    public boolean changeWorkDir(String directory) throws MylbcException
    {
        try
        {
            client.cwd(directory);
            return true;
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
    
    @Override
    public String getWorkDir() throws MylbcException
    {
        try
        {
            return client.printWorkingDirectory();
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
    }
    
    @Override
    public boolean mkDir(String directory) throws MylbcException
    {
        directory = directory.replaceAll("//", "/");
        if (directory.startsWith("/"))
        {
            directory = directory.substring(1);
        }
        if (directory.endsWith("/"))
        {
            directory = directory.substring(0, directory.length() - 1);
        }
        try
        {
            String[] str = (new String(directory.getBytes(vo.getRemoteEncoding()), "ISO-8859-1")).split("/");
            String t = "";
            String parnet = "";
            for (int i = 0; i < str.length; i++)
            {
                t += ("/" + str[i]);
                if (!isExists(t.substring(1)))
                {
                    client.makeDirectory(str[i]);
                }
                client.changeWorkingDirectory(str[i]);
                parnet += "../";
            }
            if (str.length >= 1)
            {
                client.changeWorkingDirectory(parnet);
            }
            
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return false;
    }
    
    @Override
    public boolean changName(String oldName, String newName)
    {
        return false;
    }
    
    public LinkedList<String> listDir(String directory) throws MylbcException
    {
        LinkedList<String> list = new LinkedList<String>();
        try
        {
            FTPFile[] files = client.listFiles(directory);
            for (int i = 0; i < files.length; i++)
            {
                String t = (directory + "/" + files[i].getName()).replaceAll("//", "/");
                if (files[i].isDirectory())
                {
                    list.add(t);
                    list.addAll(listDir(t + "/"));
                }
            }
        }
        catch (IOException e)
        {
            throw new MylbcException(e);
        }
        return list;
    }
    
    @Override
    public FTPClient client()
    {
        return client;
    }
    
    @Override
    public void destory() throws MylbcException
    {
        if (!ArrayUtilbc.isEmptyObj(client))
        {
            try
            {
                client.disconnect();
            }
            catch (IOException e)
            {
                throw new MylbcException(e);
            }
        }
    }
    
    public String getParentPath(String file)
    {
        if (file.indexOf("/") != -1)
        {
            String temp = null;
            Pattern p = Pattern.compile("[/]+");
            Matcher m = p.matcher(file);
            int i = 0;
            while (m.find())
            {
                temp = m.group(0);
                i += temp.length();
            }
            String parent = "";
            for (int j = 0; j < i; j++)
            {
                parent += "../";
            }
            return parent;
        }
        else
        {
            return "./";
        }
    }
    
    @Override
    public boolean downloadFile(String host, int port, String username, String password, String remotePath, String fileName,
            String localPath)
    {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try
        {
            int reply;
            ftp.connect(host, port);
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                return result;
            }
            ftp.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftp.listFiles();
            for (FTPFile ff : fs)
            {
                if (ff.getName().equals(fileName))
                {
                    File localFile = new File(localPath + "/" + ff.getName());
                    
                    OutputStream is = new FileOutputStream(localFile);
                    ftp.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }
            
            ftp.logout();
            result = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {
                }
            }
        }
        return result;
    }
    
    @Override
    public boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename,
            InputStream input)
    {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try
        {
            int reply;
            ftp.connect(host, port);// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
                return result;
            }
            // 切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath + filePath))
            {
                // 如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs)
                {
                    if (null == dir || "".equals(dir)) continue;
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath))
                    {
                        if (!ftp.makeDirectory(tempPath))
                        {
                            return result;
                        }
                        else
                        {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            // 设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            // 上传文件
            if (!ftp.storeFile(filename, input))
            {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {
                }
            }
        }
        return result;
    }
    
}
