package pers.bc.utils.pub;

import java.lang.management.ManagementFactory;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import com.sun.management.OperatingSystemMXBean;

/**
 * 提供些获取系统信息相关的工具方法
 * @qualiFild nc.pub.itf.tools.pub.SysUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class SysUtil
{
    
    public static HashSet<String> clientHosts = new HashSet<String>();
    /**
     * JVM的版本
     */
    public static final String JVM_VERSION = PropertiesUtilbc.key("java.version");
    /**
     * JVM的编码
     */
    public static final String JVM_ENCODING = PropertiesUtilbc.key("file.encoding");
    /**
     * JVM默认的临时目录
     */
    public static final String JVM_TEMPDIR = PropertiesUtilbc.key("java.io.tmpdir");
    public static final String HTTP_PROXY_HOST = "http.proxyHost";
    public static final String HTTP_PROXY_PORT = "http.proxyPort";;
    public static final String HTTP_PROXY_USER = "http.proxyUser";;
    public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    /**
     * 主机IP
     */
    public static String HOST_IP;
    /**
     * 主机名
     */
    public static String HOST_NAME;
    
    /**
     * 主机架构
     */
    public static String OS_ARCH = PropertiesUtilbc.key("os.arch");
    /**
     * 主机类型
     */
    public static String OS_NAME = PropertiesUtilbc.key("os.name");
    /**
     * 主机类型版本
     */
    public static String OS_VERSION = PropertiesUtilbc.key("os.version");
    /**
     * 操作系统类型
     */
    public static String SUN_DESKTOP = PropertiesUtilbc.key("sun.desktop");
    /**
     * 当前用户
     */
    public static String CURRENT_USER = PropertiesUtilbc.key("user.name");
    /**
     * 当前用户的家目录
     */
    public static String CURRENT_USER_HOME = PropertiesUtilbc.key("user.home");
    /**
     * 当用用户的工作目录
     */
    public static String CURRENT_USER_DIR = PropertiesUtilbc.key("user.dir");
    public static String FILE_SEPARATOR = PropertiesUtilbc.key("file.separator");
    public static String PATH_SEPARATOR = PropertiesUtilbc.key("path.separator");
    public static String LINE_SEPARATOR = PropertiesUtilbc.key("line.separator");
    /**
     * 总的物理内存
     */
    public static long TotalMemorySize;
    private static OperatingSystemMXBean osmxb;
    private static int kb = 1024;
    
    static
    {
        try
        {
            InetAddress addr = InetAddress.getLocalHost();
            HOST_NAME = addr.getHostName();
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            
            for (NetworkInterface netint : Collections.list(nets))
            {
                if (null != netint.getHardwareAddress())
                {
                    List<InterfaceAddress> list = netint.getInterfaceAddresses();
                    for (int i = 0; i < PubEnvUtilbc.getSize(list); i++)
                    {
                        InterfaceAddress interfaceAddress = list.get(i);
                        InetAddress ip = interfaceAddress.getAddress();
                        if (ip instanceof Inet4Address)
                        {
                            HOST_IP += (interfaceAddress.getAddress() + "");
                            clientHosts.add(interfaceAddress.getAddress().getHostAddress());
                        }
                    }
                }
            }
            HOST_IP = HOST_IP.replaceAll("null", "");
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("publogs").exception("获取服务器IP出错:" + e.getMessage(), e);
            LoggerUtilbc.getInstance("publogs").error("获取服务器IP出错:" + e.getMessage());
        }
        
        try
        {
            osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            TotalMemorySize = osmxb.getTotalPhysicalMemorySize() / kb;
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("publogs").exception("获取系统信息失败:" + e.getMessage(), e);
            LoggerUtilbc.getInstance("publogs").error("获取系统信息失败:" + e.getMessage());
        }
        
    }
    
    /**
     * 已使用的物理内存
     */
    public final static long usedMemory()
    {
        if (ArrayUtilbc.isEmptyObj(osmxb))
        {
            return (osmxb.getTotalPhysicalMemorySize() - osmxb.getFreePhysicalMemorySize()) / kb;
        }
        return 0;
    }
    
    /**
     * 获取JVM内存总量
     */
    public final static long JVMtotalMem()
    {
        return Runtime.getRuntime().totalMemory() / kb;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：虚拟机空闲内存量 <br>
     * @see <br>
     * @return <br>
     * @long <br>
     * @methods nc.pub.itf.tools.pub.SysUtil#JVMfreeMem <br>
     * @author licheng <br>
     * @date Created on 2019-8-15 <br>
     * @time 下午3:43:57 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static long JVMfreeMem()
    {
        return Runtime.getRuntime().freeMemory() / kb;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 虚拟机使用最大内存量<br>
     * @see <br>
     * @return <br>
     * @long <br>
     * @methods nc.pub.itf.tools.pub.SysUtil#JVMmaxMem <br>
     * @author licheng <br>
     * @date Created on 2019-8-15 <br>
     * @time 下午3:43:45 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static long JVMmaxMem()
    {
        return Runtime.getRuntime().maxMemory() / kb;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Sets HTTP proxy settings.<br>
     * @see <br>
     * @param host
     * @param port
     * @param username
     * @param password <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.SysUtil#setHttpProxy <br>
     * @author licheng <br>
     * @date Created on 2019-8-15 <br>
     * @time 下午3:43:29 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static void setHttpProxy(String host, String port, String username, String password)
    {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
        System.getProperties().put(HTTP_PROXY_USER, username);
        System.getProperties().put(HTTP_PROXY_PASSWORD, password);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Sets HTTP proxy settings.<br>
     * @see <br>
     * @param host
     * @param port <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.SysUtil#setHttpProxy <br>
     * @author licheng <br>
     * @date Created on 2019-8-15 <br>
     * @time 下午3:43:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static void setHttpProxy(String host, String port)
    {
        System.getProperties().put(HTTP_PROXY_HOST, host);
        System.getProperties().put(HTTP_PROXY_PORT, port);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：java环境 <br>
     * @see <br>
     * <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.SysUtil#getJVMEnv <br>
     * @author licheng <br>
     * @date Created on 2019-8-15 <br>
     * @time 下午3:48:02 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getJVMEnv()
    {
        Properties props = System.getProperties();
        StringBuffer javaStr = new StringBuffer();
        javaStr.append("\r\nJava的运行环境版本：" + props.getProperty("java.version"));
        javaStr.append("\r\nJava的运行环境供应商：" + props.getProperty("java.vendor"));
        javaStr.append("\r\nJava供应商的URL：" + props.getProperty("java.vendor.url"));
        javaStr.append("\r\nJava的安装路径：" + props.getProperty("java.home"));
        javaStr.append("\r\nJava的虚拟机规范版本：" + props.getProperty("java.vm.specification.version"));
        javaStr.append("\r\nJava的虚拟机规范供应商：" + props.getProperty("java.vm.specification.vendor"));
        javaStr.append("\r\nJava的虚拟机规范名称：" + props.getProperty("java.vm.specification.name"));
        javaStr.append("\r\nJava的虚拟机实现版本：" + props.getProperty("java.vm.version"));
        javaStr.append("\r\nJava的虚拟机实现供应商：" + props.getProperty("java.vm.vendor"));
        javaStr.append("\r\nJava的虚拟机实现名称：" + props.getProperty("java.vm.name"));
        javaStr.append("\r\nJava运行时环境规范版本：" + props.getProperty("java.specification.version"));
        javaStr.append("\r\nJava运行时环境规范供应商：" + props.getProperty("java.specification.vender"));
        javaStr.append("\r\nJava运行时环境规范名称：" + props.getProperty("java.specification.name"));
        javaStr.append("\r\nJava的类格式版本号：" + props.getProperty("java.class.version"));
        // javaStr.append("\r\nJava的类路径：" + props.getProperty("java.class.path"));
        // javaStr.append("\r\n加载库时搜索的路径列表：" + props.getProperty("java.library.path"));
        javaStr.append("\r\n默认的临时文件路径：" + props.getProperty("java.io.tmpdir"));
        javaStr.append("\r\n一个或多个扩展目录的路径：" + props.getProperty("java.ext.dirs"));
        javaStr.append("\r\n操作系统的名称：" + props.getProperty("os.name"));
        javaStr.append("\r\n操作系统的构架：" + props.getProperty("os.arch"));
        javaStr.append("\r\n操作系统的版本：" + props.getProperty("os.version"));
        javaStr.append("\r\n文件分隔符：" + props.getProperty("file.separator"));// 在 unix 系统中是＂／＂
                                                                           // System.out.println("路径分隔符："+props.getProperty("path.separator"));
                                                                           // unix 系统中是＂:＂
                                                                           // System.out.println("行分隔符："+props.getProperty("line.separator"));
        // 在 unix 系统中是＂/n＂
        // System.out.println("用户的账户名称："+props.getProperty("user.name"));
        javaStr.append("\r\n用户的主目录：" + props.getProperty("user.home"));
        javaStr.append("\r\n用户的当前工作目录：" + props.getProperty("user.dir"));
        
        return javaStr.toString();
    }
}
