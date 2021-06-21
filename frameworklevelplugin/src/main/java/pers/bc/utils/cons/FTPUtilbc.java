package pers.bc.utils.cons;

import java.io.File;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;

import pers.bc.utils.ftp.FileAttr;
import pers.bc.utils.throwable.MylbcException;

/**
 * FTP工具类
 * 
 * @qualiFild nc.pub.itf.tools.ftp.FTPUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public interface FTPUtilbc
{
    
    /**
     * *********************************************************** <br>
     * Description:从FTP服务器下载文件 <br>
     * 
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     * @boolean <br>
     * @methods pers.bc.utils.cons.FTPUtil#downloadFile <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午5:53:44 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean downloadFile(String host, int port, String username, String password, String remotePath, String fileName, String localPath);
    
    /**
     * *********************************************************** <br>
     * 说明：向FTP服务器上传文件 <br>
     * 
     * @see <br>
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     * @boolean <br>
     * @methods pers.bc.utils.cons.FTPUtil#uploadFile <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午5:51:32 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean uploadFile(String host, int port, String username, String password, String basePath, String filePath, String filename,
                       InputStream input);
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 判断远程文件是否存在
     * 
     * @see
     * @param fileName
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:16:30
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean isExists(String fileName) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 下载远程文件
     * 
     * @see
     * @param fileName
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:16:47
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean downLoad(String fileName) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 下载远程目录
     * 
     * @see
     * @param directory
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:16:57
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean downLoadDir(String directory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 删除远程文件
     * 
     * @see
     * @param fileName
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:17:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean deleteFile(String fileName) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 删除远程目录
     * 
     * @see
     * @param directory
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:17:13
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean deleteDir(String directory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 上传本地文件到远程目录
     * 
     * @see
     * @param fileName
     * @param remoteFileName
     * @param isDelete
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:17:20
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean putFile(String fileName, String remoteFileName, boolean isDelete) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 上传本地文件到远程目录
     * 
     * @see
     * @param file
     * @param remoteFileName
     * @param isDelete
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:18:57
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean putFile(File file, String remoteFileName, boolean isDelete) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 上传本地目录到远程
     * 
     * @see
     * @param fileName
     * @param remoteDir
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:05
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean putDir(String fileName, String remoteDir) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 上传本地目录到远程
     * 
     * @see
     * @param file
     * @param remoteDir
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:13
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean putDir(File file, String remoteDir) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 创建文件夹
     * 
     * @see
     * @param destory
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:22
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean mkDir(String destory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取远程文件列表
     * 
     * @see
     * @param directory
     * @return
     * @List<String>
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    List<String> listFile(String directory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取远程文件夹的目录结构
     * 
     * @see
     * @param direcotyr
     * @return
     * @LinkedList<String>
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:39
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    LinkedList<String> listDir(String direcotyr) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取远程文件属性以Map形式返回
     * 
     * @see
     * @param directory
     * @return
     * @Map<String,FileAttr>
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:19:46
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    Map<String, FileAttr> listFileAttr(String directory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 改变FTP连接的工作目录
     * 
     * @see
     * @param directory
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:18:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean changeWorkDir(String directory) throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取当前连接的工作目录
     * 
     * @see
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:18:15
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    String getWorkDir() throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 重命名文件
     * 
     * @see
     * @param oldName
     * @param newName
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:18:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    boolean changName(String oldName, String newName) throws MylbcException;
    
    /**
     ************************************************************ <br>
     * 说明： 返回FTPCliend对象(已经打开连接) 需要 commons-net-3.3.jar
     * 
     * @see
     * @return
     * @FTPClient
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:17:39
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    FTPClient client() throws MylbcException;
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 释放所有的资源
     * 
     * @see
     * @void
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午9:17:32
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    void destory() throws MylbcException;
}
