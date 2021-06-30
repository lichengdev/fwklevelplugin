package pers.bc.utils.pub;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.file.FileUtilbc;
import pers.bc.utils.throwable.ExceptionUtilbc;

/**
 * 简单的日志保存操作
 * @qualiFild nc.pub.itf.tools.pub.LoggerUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-26<br>
 * @version 1.0<br>
 */
public final class LoggerUtilbc implements PubConsUtilbc, Serializable
{

    private static final long serialVersionUID = -4291438837102849653L;
    public final int STACKTRACE = 1;
    private volatile Boolean IS_LOGS = true;
    private volatile String folderName = null;
    private volatile String fileUrl = null;
    private volatile String FileDirectory;
    public static String className = "pers.bc.utils.pub.LoggerUtilbc";

    public String getFileDirectory()
    {
        return FileDirectory;
    }

    public void setFileDirectory(String fileDirectory)
    {
        FileDirectory = fileDirectory;
    }

    // 默认值必须为false，中间件刚启动时，尚未配置System.getProperty("debug")，导致异常信息输出到控制台
    public static Boolean SYS_DEBUG = false;

    static
    {
        strDocTempletDirPath = getWorkPath() + File.separator + "temp" + File.separator;
        System.setProperty("logger", className);
        String strDebug = System.getProperty("debug");
        if (!ArrayUtilbc.isEmpty(strDebug))
        {
            SYS_DEBUG = Boolean.valueOf(strDebug).booleanValue();
        }
    }

    /**
     * 堆栈信息
     */
    public void consoleout(Object msg)
    {
        String str = null;
        try
        {
            str = StringUtilbc.toString(msg);
        }
        catch (Exception e)
        {
        }
        System.err.println(str);
    }

    /**
     * *********************************************************** <br>
     * 说明：指定文件前缀，debug输出日志 <br>
     * @see <br>
     * @param msg
     * @param filePrefix <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#debugAppointPrefix <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-18 <br>
     * @time 下午4:12:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void debugAppointPrefix(String msg, String filePrefix)
    {
        exeSavaLogger(msg, false, filePrefix, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * *********************************************************** <br>
     * 说明： debug保存日志信息<br>
     * @see <br>
     * @param msg <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.LoggerUtils#debug <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 上午10:18:10 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void debug(String msg)
    {
        exeSavaLogger(msg, false, "dbug", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param msg <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtils#debug <br>
     * @author licheng <br>
     * @date Created on 2019年11月18日 <br>
     * @time 下午11:18:48 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void debug(String msg, String str)
    {
        debug(msg + RN + str);
    }

    /**
     * *********************************************************** <br>
     * 说明：指定文件前缀，info输出日志 <br>
     * @see <br>
     * @param msg
     * @param filePrefix <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#debugAppointPrefix <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-18 <br>
     * @time 下午4:12:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void infoAppointPrefix(String msg, String filePrefix)
    {
        exeSavaLogger(msg, false, filePrefix, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
    }

    /**
     * *********************************************************** <br>
     * 说明：info保存日志信息 <br>
     * @see <br>
     * @param msg <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtils#info <br>
     * @author LiBencheng <br>
     * @date Created on 2020-6-2 <br>
     * @time 下午2:43:02 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void info(String msg)
    {
        exeSavaLogger(msg, false, "info", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
    }

    public final void info(String msg, String str)
    {
        info(msg + RN + str);
    }

    /**
     * *********************************************************** <br>
     * 说明：指定文件前缀，error输出日志 <br>
     * @see <br>
     * @param msg
     * @param filePrefix <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#debugAppointPrefix <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-18 <br>
     * @time 下午4:12:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void errorAppointPrefix(String msg, String filePrefix)
    {
        exeSavaLogger(msg, false, filePrefix, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
    }

    /**
     * *********************************************************** <br>
     * 说明：错误日志保存 <br>
     * @see <br>
     * @param msg <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.LoggerUtils#error <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 上午10:17:16 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void error(String msg)
    {
        consoleout(msg);
        exeSavaLogger(msg, false, "error", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
    }

    /**
     * *********************************************************** <br>
     * 说明：错误日志保存 <br>
     * @see <br>
     * @param msg <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.LoggerUtils#error <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 上午10:17:16 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void error(String msg, String str)
    {
        error(msg + RN + str);
    }

    /**
     * *********************************************************** <br>
     * 说明：异常日志保存 <br>
     * @see <br>
     * @param th <br>
     * @void <br>
     * @methods nc.pub.itf.tools.pub.LoggerUtils#errExpLogger <br>
     * @author licheng <br>
     * @date Created on 2019-9-10 <br>
     * @time 下午12:00:27 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void exception(Throwable th)
    {
        savaLogErr(getInvokMethod(2), String.valueOf(th) + "{[" + th.getMessage() + "]}");
        consoleout(getInvokMethod(2) + String.valueOf(th) + "{[" + th.getMessage() + "]}");
        th.getStackTrace();
        savaLogger(getInvokMethod(2), th);
    }

    /**
     * *********************************************************** <br>
     * 说明：debug异常日志保存 <br>
     * @see <br>
     * @param msg
     * @param th <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#exception <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-8 <br>
     * @time 上午1:37:35 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void exception(String msg, Throwable th)
    {
        debug("程序异常结束{[" + msg + "]}");
        debug(getSplitLine());
        savaLogErr(getInvokMethod(2), String.valueOf(th) + "{[" + th.getMessage() + "]}");
        consoleout(getInvokMethod(2) + String.valueOf(th) + "{[" + th.getMessage() + "]}");
        th.getStackTrace();
        savaLogger(getInvokMethod(2), th);
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param msg
     * @param isErrExp
     * @param fileFix
     * @param isInfo <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#exeSavaLogger <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-10 <br>
     * @time 下午4:20:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private final void exeSavaLogger(String msg, boolean isErrExp, String fileFix, Boolean isDbug, Boolean isInfo, Boolean isErr)
    {
        try
        {
            if (!IS_LOGS) return;
            String fileName = new SimpleDateFormat(PubConsUtilbc.DATE_FORMAT).format(new Date());
            String format = new SimpleDateFormat(PubConsUtilbc.DATETIME_FORMAT2).format(new Date());
            String filePath = getLogsFilePatch(isErrExp, fileFix, fileName);
            setFileDirectory(filePath);
            File file = new File(filePath);
            fileName = file.getName();

            StringBuffer msgStr = getSystemInfoStr();
            if(!PubEnvUtilbc.isEmptyObj(System.getProperty("nc.server.location"))&&!PubEnvUtilbc.equals("develop", System.getProperty("nc.runMode")) ) msgStr = getYonYouStr();

            // if (isDbug) msgStr.append(getBeautyWomanStr());
            if (isInfo) msgStr.append(getKeyBoardStr());
            if (isErr) msgStr.append(getDangerStr());

            msgStr.append("Runing time：").append(format).append("-->>> ").append(msg).append(CRLF);
            if (!file.exists())
            {
                FileUtilbc.createFiles(filePath);
                FileUtilbc.write(file, msgStr.toString(), UTF_8);
            }
            else
            {
                // 文件大于 6.49MB新建文件，
                // 可以自己修改比较文件大小参数11475968f=10.94MB,
                // 6808055f =6.49MB ；
                // 5013547f =4.78MB；
                // 12623539f =12.04MB
                if (5013547f < file.length())
                {
                    // checkFile(filePath, fileName);
                    FileUtilbc.backToFile(filePath, fileName);
                    FileUtilbc.write(file, msgStr.toString(), UTF_8);
                    return;
                }
                msgStr = new StringBuffer();
                msgStr.append("Runing time：").append(format).append("-->>> ").append(msg).append(CRLF);
                FileUtilbc.writeAppend(file, msgStr.toString(), UTF_8);
            }
        }
        catch (IOException e)
        {
            System.err.println(StringUtilbc.toString(e));
            e.printStackTrace();
            ExceptionUtilbc.throwException(e);

        }
    }

    /**
     * *********************************************************** <br>
     * 说明： 执行日志保存， 支持linux客服端<br>
     * @see <br>
     * @param method
     * @param json
     * @param err
     * @param fileFix
     * @param isThrow
     * @param isErr
     * @param isInfo <br>
     * @void <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#exeSavaLogger <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-10 <br>
     * @time 下午4:17:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private final void exeSavaLogger(String method, String json, String err, String fileFix, Boolean isThrow, Boolean isErr, Boolean isInfo)
    {
        try
        {
            if (!IS_LOGS) return;
            String fileName = new SimpleDateFormat(PubConsUtilbc.DATE_FORMAT).format(new Date());
            String format = new SimpleDateFormat(PubConsUtilbc.DATETIME_FORMAT2).format(new Date());
            String filePath = getLogsFilePatch(true, fileFix, fileName);
            setFileDirectory(filePath);
            File file = new File(filePath);
            fileName = file.getName();

            StringBuffer msgStr = getSystemInfoStr();
            if(!PubEnvUtilbc.isEmptyObj(System.getProperty("nc.server.location"))&&!PubEnvUtilbc.equals("develop", System.getProperty("nc.runMode")) ) msgStr = getYonYouStr();

            if (isThrow) msgStr.append(getThrowStr());
            if (isErr) msgStr.append(getDangerStr());
            if (isInfo) msgStr.append(getOfficeStr());

            msgStr.append("VM memory usage details: ").append(CRLF).append("        ※ ").append(printMemory()).append(CRLF);
            msgStr.append("Runing time：").append(format).append(CRLF);
            msgStr.append("Runing method：").append(method).append(CRLF);
            msgStr.append("Receive  details：").append(json).append(CRLF);
            msgStr.append("Other details：").append(err).append(CRLF);
            msgStr.append(getSplitLine()).append(CRLF);
            if (!file.exists())
            {
                FileUtilbc.createFiles(filePath);
                FileUtilbc.write(file, msgStr.toString(), UTF_8);
            }
            else
            {
                // 文件大于 6.49MB新建文件，
                // 可以自己修改比较文件大小参数11475968f=10.94MB,
                // 6808055f =6.49MB ；
                // 5013547f =4.78MB；
                // 12623539f =12.04MB
                if (5013547f < file.length())
                {
                    // checkFile(filePath, fileName);
                    FileUtilbc.backToFile(filePath, fileName);
                    FileUtilbc.write(file, msgStr.toString(), UTF_8);
                    return;
                }
                msgStr = new StringBuffer();
                msgStr.append("VM memory usage details: ").append(CRLF).append("        ※ ").append(printMemory()).append(CRLF);
                msgStr.append("Runing time：").append(format).append(CRLF);
                msgStr.append("Runing method：").append(method).append(CRLF);
                msgStr.append("Receive  details：").append(json).append(CRLF);
                msgStr.append("Other details：").append(err).append(CRLF);
                msgStr.append(getSplitLine()).append(RN);
                FileUtilbc.writeAppend(file, msgStr.toString(), UTF_8);
            }
        }
        catch (IOException e)
        {
            System.err.println(StringUtilbc.toString(e));
            e.printStackTrace();
            ExceptionUtilbc.throwException(e);

        }
    }

    /**
     * *********************************************************** <br>
     * 说明：保存错误信息日志
     * @see
     * @param method
     * @param err
     * @void
     * @author licheng
     * @date Created on 2019-8-1
     * @time 上午10:36:23
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void savaLogErr(String method, String err)
    {
        exeSavaLogger(method, null, err, "logerror", Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
    }

    /**
     *
     * *********************************************************** <br>
     * 说明：保存详细日志
     * @see
     * @param method
     * @param info
     * @void
     * @author licheng
     * @date Created on 2019-8-1
     * @time 上午10:36:12
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void savaLogInfo(String method, String info)
    {
        exeSavaLogger(method, info, null, "loginfo", Boolean.FALSE, Boolean.FALSE, Boolean.TRUE);
    }

    /**
     *
     * *********************************************************** <br>
     * 说明：保存错误信息日志，和详细日志
     * @see
     * @param method
     * @param json
     * @param err
     * @void
     * @author licheng
     * @date Created on 2019-8-1
     * @time 上午10:36:16
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void savaLogger(String method, String json, String err)
    {
        exeSavaLogger(method, json, err, "", Boolean.FALSE, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     *
     * *********************************************************** <br>
     * 说明： 打印堆栈信息的日志
     * @see
     * @param method
     * @param th
     * @void
     * @author licheng
     * @date Created on 2019-8-1
     * @time 上午10:37:37
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void savaLogger(String method, Throwable th)
    {
        consoleout(th);
        String msg = "";
        if (null != th) msg = th.getMessage();
        th.getStackTrace();
        exeSavaLogger(method, msg, ExceptionUtilbc.getStackTrace(th), "thw", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * *********************************************************** <br>
     * 说明： 打印堆栈信息的日志 ，和接受到的日志
     * @see
     * @param method
     * @param receiveStr
     * @param th
     * @void
     * @author licheng
     * @date Created on 2019-8-1
     * @time 上午10:36:30
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final void savaLogger(String method, String receiveStr, Throwable th)
    {
        th.getStackTrace();
        exeSavaLogger(method, receiveStr, ExceptionUtilbc.stackTraceToString(th), "thw", Boolean.TRUE, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * *********************************************************** <br>
     * 说明： 打印内存信息
     * @see
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午3:36:16
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public String printMemory()
    {
        final Runtime rt = Runtime.getRuntime();
        final long freeMemory = rt.freeMemory();
        final long totalMemory = rt.totalMemory();
        final StringBuilder buf = new StringBuilder(64);

        buf.append("FREE_MEMORY: ");
        buf.append(freeMemory / 1024);
        buf.append("KB(");
        buf.append(freeMemory / 1024 / 1024);
        buf.append("M)  TOTAL_MEMORY: ");
        buf.append(totalMemory / 1024);
        buf.append("KB(");
        buf.append(totalMemory / 1024 / 1024);
        buf.append("M)  FREE_RATE：");

        long hundredths = (freeMemory * 10000) / totalMemory;

        buf.append(hundredths / 100);
        hundredths %= 100;
        if (hundredths >= 10)
        {
            buf.append('.');
        }
        else
        {
            buf.append(".0");
        }
        buf.append(hundredths);
        buf.append('%');
        String log = buf.toString();
        return log;
    }

    /**
     * *********************************************************** <br>
     * 说明：日志存储目录 <br>
     * @see <br>
     * @param isErrExp
     * @param fileFix
     * @param fileName
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#getLogsFilePatch <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-13 <br>
     * @time 上午9:58:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public String getLogsFilePatch(boolean isErrExp, String fileFix, String fileName)
    {
        if (null == folderName)
        {
            if (!isErrExp) folderName = "buglogs";
            if (isErrExp) folderName = "errlogs";
        }

        StringBuffer logFilePath = new StringBuffer();
        logFilePath.append(getWorkPath());
        logFilePath.append(File.separator);
        if (!ArrayUtilbc.isEmpty(getFileUrl()))
        {
            logFilePath.append(getFileUrl());
            logFilePath.append(File.separator);
        }
        logFilePath.append(folderName);
        logFilePath.append(File.separator);
        logFilePath.append(fileFix);
        logFilePath.append(fileName);
        logFilePath.append(".log");

        return logFilePath.toString();
    }

    /**
     * *********************************************************** <br>
     * 说明： 获取当前項目工程工作路径<br>
     * @see <br>
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#getWorkPath <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-13 <br>
     * @time 上午9:57:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getWorkPath()
    {

        String patch = System.getProperty("client.code.dir");
        // client.code.dir
        if (!PubEnvUtilbc.isEmptyObj(System.getProperty("client.code.dir"))) patch = System.getProperty("client.code.dir");
        // catalina.base
        if (!PubEnvUtilbc.isEmptyObj(System.getProperty("catalina.base"))) patch = System.getProperty("catalina.base");
        // catalina.home
        if (!PubEnvUtilbc.isEmptyObj(System.getProperty("catalina.home"))) patch = System.getProperty("catalina.home");
        // user.dir
        if (!PubEnvUtilbc.isEmptyObj(System.getProperty("user.dir"))) patch = System.getProperty("user.dir");

        patch  += File.separator + "mylogs";
        // nc.server.location
        if (!PubEnvUtilbc.isEmptyObj(System.getProperty("nc.server.location")))
            patch = System.getProperty("nc.server.location") + File.separator + "nclogs";

        return patch;
    }

    public static String strDocTempletDirPath;

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param strFileName 文件名
     * @param suffix 文件扩展名
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#getFullFileName <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午11:47:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getFullFileName(String strFileName, String suffix)
    {
        return getFullFileName(strFileName + suffix);
    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param strFileName 文件名
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#getFullFileName <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午11:47:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getFullFileName(String strFileName)
    {

        if (StringUtil.isBlank(strFileName))
        {
            strFileName = String.valueOf(System.currentTimeMillis());
        }

        FileUtilbc.createFiles(strDocTempletDirPath);

        return strDocTempletDirPath + "templet_" + strFileName + ".png";

    }

    /**
     * *********************************************************** <br>
     * 说明：获取当前调用方法
     * @see
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-2
     * @time 下午3:47:53
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final String getInvokMethod()
    {
        return getInvokMethod(2);
    }

    /**
     * *********************************************************** <br>
     * 说明：获取当前调用方法
     * @see
     * @param i
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-7-25
     * @time 下午10:31:59
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public final static String getInvokMethod(int i)
    {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[i];
        String className = stackTraceElement.getClassName();

        return className + "." + stackTraceElement.getMethodName();
    }

    public String getFolderName()
    {
        return folderName;
    }

    public void setFolderName(String folderName)
    {
        this.folderName = folderName;
    }

    public void setIS_LOGS(boolean isflag)
    {
        IS_LOGS = isflag;
    }

    public String getFileUrl()
    {
        return fileUrl;
    }

    public static StringBuffer getOfficeStr()
    {
        StringBuffer bookStr = new StringBuffer();

        bookStr.append("                 ,----------------,              ,---------,").append(CRLF);
        bookStr.append("            ,-----------------------,          ,\"        ,\"|").append(CRLF);
        bookStr.append("          ,\"                      ,\"|        ,\"        ,\"  |").append(CRLF);
        bookStr.append("         +-----------------------+  |      ,\"        ,\"    |").append(CRLF);
        bookStr.append("         |  .-----------------.  |  |     +---------+      |").append(CRLF);
        bookStr.append("         |  |                 |  |  |     | -==----'|      |").append(CRLF);
        bookStr.append("         |  |  I LOVE DOS!    |  |  |     |         |      |").append(CRLF);
        bookStr.append("         |  |  Bad command or |  |  |/----|`---=    |      |").append(CRLF);
        bookStr.append("         |  |  C:\\>_          |  |  |   ,/|==== ooo |      ;").append(CRLF);
        bookStr.append("         |  |                 |  |  |  // |(((( [33]|    ,\"").append(CRLF);
        bookStr.append("         |  `-----------------'  |,\" .;'| |((((     |  ,\"").append(CRLF);
        bookStr.append("         +-----------------------+  ;;  | |         |,\"").append(CRLF);
        bookStr.append("            /_)______________(_/  //'   | +---------+").append(CRLF);
        bookStr.append("       ___________________________/___  `,").append(CRLF);
        bookStr.append("      /  oooooooooooooooo  .o.  oooo /,   \\,\"-----------").append(CRLF);
        bookStr.append("     / ==ooooooooooooooo==.o.  ooo= //   ,`\\--{)B     ,\"").append(CRLF);
        bookStr.append("    /_==__==========__==_ooo__ooo=_/'   /___________,\"").append(CRLF);
        bookStr.append("   ").append(CRLF);
        bookStr.append("   ").append(CRLF);
        bookStr.append("                    .-~~~~~~~~~-._       _.-~~~~~~~~~-.").append(CRLF);
        bookStr.append("                __.'              ~.   .~              `.__").append(CRLF);
        bookStr.append("              .'//                 \\./                  \\\\`.").append(CRLF);
        bookStr.append("            .'//                     |                     \\\\`.").append(CRLF);
        bookStr.append("          .'// .-~\"\"\"\"\"\"\"~~~~-._     |     _,-~~~~\"\"\"\"\"\"\"~-. \\\\`.").append(CRLF);
        bookStr.append("        .'//.-\"                 `-.  |  .-'                 \"-.\\\\`.").append(CRLF);
        bookStr.append("      .'//______.============-..   \\ | /   ..-============.______\\\\`.").append(CRLF);
        bookStr.append("    .'______________________________\\|/______________________________`.").append(CRLF);
        bookStr.append("").append(CRLF);
        bookStr.append("").append(CRLF);

        return bookStr;
    }

    public static StringBuffer getKeyBoardStr()
    {
        StringBuffer keyBoardStr = new StringBuffer();

        keyBoardStr.append("   ").append(CRLF);
        keyBoardStr.append("   ┌───┐   ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┬───┐ ┌───┬───┬───┐").append(CRLF);
        keyBoardStr.append("   │Esc│   │ F1│ F2│ F3│ F4│ │ F5│ F6│ F7│ F8│ │ F9│F10│F11│F12│ │P/S│S L│P/B│  ┌┐    ┌┐    ┌┐").append(CRLF);
        keyBoardStr.append("   └───┘   └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┴───┘ └───┴───┴───┘  └┘    └┘    └┘").append(CRLF);
        keyBoardStr.append("   ┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───────┐ ┌───┬───┬───┐ ┌───┬───┬───┬───┐").append(CRLF);
        keyBoardStr.append("   │~ `│! 1│@ 2│# 3│$ 4│% 5│^ 6│& 7│* 8│( 9│) 0│_ -│+ =│ BacSp │ │Ins│Hom│PUp│ │N L│ / │ * │ - │").append(CRLF);
        keyBoardStr.append("   ├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─────┤ ├───┼───┼───┤ ├───┼───┼───┼───┤").append(CRLF);
        keyBoardStr.append("   │ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{ [│} ]│ | \\ │ │Del│End│PDn│ │ 7 │ 8 │ 9 │   │")
                .append(CRLF);
        keyBoardStr.append("   ├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤ └───┴───┴───┘ ├───┼───┼───┤ + │").append(CRLF);
        keyBoardStr.append("   │ Caps │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│\" '│ Enter  │               │ 4 │ 5 │ 6 │   │")
                .append(CRLF);
        keyBoardStr.append("   ├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────────┤     ┌───┐     ├───┼───┼───┼───┤").append(CRLF);
        keyBoardStr.append("   │ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│  Shift   │     │ ↑ │     │ 1 │ 2 │ 3 │   │").append(CRLF);
        keyBoardStr.append("   ├─────┬──┴─┬─┴──┬┴───┴───┴───┴───┴───┴──┬┴───┼───┴┬────┬────┤ ┌───┼───┼───┐ ├───┴───┼───┤ E││").append(CRLF);
        keyBoardStr.append("   │ Ctrl│    │Alt │         Space         │ Alt│    │    │Ctrl│ │ ← │ ↓ │ → │ │   0   │ . │←─┘│").append(CRLF);
        keyBoardStr.append("   └─────┴────┴────┴───────────────────────┴────┴────┴────┴────┘ └───┴───┴───┘ └───────┴───┴───┘").append(CRLF);
        keyBoardStr.append("   ").append(CRLF);
        keyBoardStr.append("").append(CRLF);
        keyBoardStr.append("").append(CRLF);

        return keyBoardStr;
    }

    public static StringBuffer getDangerStr()
    {
        StringBuffer dangerStr = new StringBuffer();
        dangerStr.append("").append(CRLF);
        dangerStr.append("  **************************************************************").append(CRLF);
        dangerStr.append("  *                                                            *").append(CRLF);
        dangerStr.append("  *    .=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.      *").append(CRLF);
        dangerStr.append("  *    |                     ______                     |      *").append(CRLF);
        dangerStr.append("  *    |                  .-\"      \"-.                  |      *").append(CRLF);
        dangerStr.append("  *    |                 /            \\                 |      *").append(CRLF);
        dangerStr.append("  *    |     _          |              |          _     |      *").append(CRLF);
        dangerStr.append("  *    |    ( \\         |,  .-.  .-.  ,|         / )    |      *").append(CRLF);
        dangerStr.append("  *    |     > \"=._     | )(__/  \\__)( |     _.=\" <     |      *").append(CRLF);
        dangerStr.append("  *    |    (_/\"=._\"=._ |/     /\\     \\| _.=\\\"_.=\"\\_)   |      *").append(CRLF);
        dangerStr.append("  *    |           \"=._\"(_     ^^     _)\"_.=\"           |      *").append(CRLF);
        dangerStr.append("  *    |               \"=\\__|IIIIII|__/=\"               |      *").append(CRLF);
        dangerStr.append("  *    |              _.=\"| \\IIIIII/ |\"=._              |      *").append(CRLF);
        dangerStr.append("  *    |    _     _.=\"_.=\"\\          /\"=._\"=._     _    |      *").append(CRLF);
        dangerStr.append("  *    |   ( \\_.=\"_.=\"     `--------`     \"=._\"=._/ )   |      *").append(CRLF);
        dangerStr.append("  *    |    > _.=\"                            \"=._ <    |      *").append(CRLF);
        dangerStr.append("  *    |   (_/                                    \\_)   |      *").append(CRLF);
        dangerStr.append("  *    |                                                |      *").append(CRLF);
        dangerStr.append("  *    '-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-='      *").append(CRLF);
        dangerStr.append("  *                                                            *").append(CRLF);
        dangerStr.append("  *           LASCIATE OGNI SPERANZA, VOI CH'ENTRATE           *").append(CRLF);
        dangerStr.append("  **************************************************************").append(CRLF);
        dangerStr.append("").append(CRLF);

        return dangerStr;
    }

    public static StringBuffer getWomanStr()
    {
        StringBuffer womanStr = new StringBuffer();
        womanStr.append("").append(CRLF);
        womanStr.append("                  _.._        ,------------.").append(CRLF);
        womanStr.append("               ,'      `.    (  I want you! )").append(CRLF);
        womanStr.append("              /  __) __` \\    `-,----------'").append(CRLF);
        womanStr.append("             (  (`-`(-')  ) _.-'").append(CRLF);
        womanStr.append("             /)  \\  = /  (").append(CRLF);
        womanStr.append("            /'    |--' . \\ \\").append(CRLF);
        womanStr.append("           (  ,---|  `-.)__`").append(CRLF);
        womanStr.append("            )(  `-.,--'   _`-.").append(CRLF);
        womanStr.append("           '/,'          (  Uu\",").append(CRLF);
        womanStr.append("            (_       ,    `/,-' )").append(CRLF);
        womanStr.append("            `.__,  : `-'/  /`--'").append(CRLF);
        womanStr.append("              |     `--'  |").append(CRLF);
        womanStr.append("              `   `-._   /").append(CRLF);
        womanStr.append("               \\        (\\").append(CRLF);
        womanStr.append("               /\\ .      \\.  ").append(CRLF);
        womanStr.append("              / |` \\     ,-\\").append(CRLF);
        womanStr.append("             /  \\| .)   /   \\").append(CRLF);
        womanStr.append("            ( ,'|\\    ,'     :").append(CRLF);
        womanStr.append("            | \\,`.`--\"/      }").append(CRLF);
        womanStr.append("            `,'    \\  |,'    /").append(CRLF);
        womanStr.append("           / \"-._   `-/      |").append(CRLF);
        womanStr.append("           \"-.   \"-.,'|     ;").append(CRLF);
        womanStr.append("          /        _/[\"---'\"\"]").append(CRLF);
        womanStr.append("         :        /  |\"-     '").append(CRLF);
        womanStr.append("         '           |      /").append(CRLF);
        womanStr.append("                     `      |").append(CRLF);
        womanStr.append("").append(CRLF);

        return womanStr;
    }

    public static StringBuffer getBeautyWomanStr()
    {
        StringBuffer womanStr = new StringBuffer();
        womanStr.append(CRLF);
        womanStr.append(CRLF);
        womanStr.append("                       .::::.                                      ").append(CRLF);
        womanStr.append("                     .::::::::.                                    ").append(CRLF);
        womanStr.append("                    :::::::::::                                    ").append(CRLF);
        womanStr.append("                 ..:::::::::::'                                    ").append(CRLF);
        womanStr.append("              '::::::::::::'                                       ").append(CRLF);
        womanStr.append("                .::::::::::                                        ").append(CRLF);
        womanStr.append("           '::::::::::::::..                                       ").append(CRLF);
        womanStr.append("                ..::::::::::::.                                    ").append(CRLF);
        womanStr.append("              ``::::::::::::::::                                   ").append(CRLF);
        womanStr.append("               ::::``:::::::::'        .:::.                       ").append(CRLF);
        womanStr.append("              ::::'   ':::::'       .::::::::.                     ").append(CRLF);
        womanStr.append("            .::::'      ::::     .:::::::'::::.                    ").append(CRLF);
        womanStr.append("           .:::'       :::::  .:::::::::' ':::::.                  ").append(CRLF);
        womanStr.append("          .::'        :::::.:::::::::'      ':::::.                ").append(CRLF);
        womanStr.append("         .::'         ::::::::::::::'         ``::::.              ").append(CRLF);
        womanStr.append("     ...:::           ::::::::::::'              ``::.             ").append(CRLF);
        womanStr.append("    ```` ':.          ':::::::::'                  ::::..          ").append(CRLF);
        womanStr.append("                       '.:::::'                    ':'````..       ").append(CRLF);
        womanStr.append(CRLF);
        womanStr.append(CRLF);

        return womanStr;
    }

    public static StringBuffer getThrowStr()
    {
        StringBuffer throwStr = new StringBuffer();
        throwStr.append(CRLF);
        throwStr.append(CRLF);
        throwStr.append(" ...........................我佛慈悲................................ ").append(CRLF);
        throwStr.append("//                          _ooOoo_                               //").append(CRLF);
        throwStr.append("//                         o8888888o                              //").append(CRLF);
        throwStr.append("//                         88\" . \"88                              //").append(CRLF);
        throwStr.append("//                         (| ^_^ |)                              //").append(CRLF);
        throwStr.append("//                         O\\  =  /O                              //").append(CRLF);
        throwStr.append("//                      ____/`---'\\____                           //").append(CRLF);
        throwStr.append("//                    .'  \\\\|     |//  `.                         //").append(CRLF);
        throwStr.append("//                   /  \\\\|||  :  |||//  \\                        //").append(CRLF);
        throwStr.append("//                  /  _||||| -卍-|||||-  \\                       //").append(CRLF);
        throwStr.append("//                  |   | \\\\\\  -  /// |   |                       //").append(CRLF);
        throwStr.append("//                  | \\_|  ''\\---/''  |   |                       //").append(CRLF);
        throwStr.append("//                  \\  .-\\__  `-`  ___/-. /                       //").append(CRLF);
        throwStr.append("//                ___`. .'  /--.--\\  `. . ___                     //").append(CRLF);
        throwStr.append("//              .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                  //").append(CRLF);
        throwStr.append("//            | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |                 //").append(CRLF);
        throwStr.append("//            \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /                 //").append(CRLF);
        throwStr.append("//      ========`-.____`-.___\\_____/___.-`____.-'========         //").append(CRLF);
        throwStr.append("//                           `=---='                              //").append(CRLF);
        throwStr.append("//      ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^        //").append(CRLF);
        throwStr.append("//                佛祖保佑       永无BUG     永不修改             //").append(CRLF);
        throwStr.append(CRLF);
        throwStr.append(CRLF);

        return throwStr;
    }
    public static StringBuffer getYonYouStr() throws UnknownHostException
    {
        StringBuffer yonyouStr = new StringBuffer();
        yonyouStr.append(CRLF);
        yonyouStr.append(CRLF);
        yonyouStr.append("  ").append(CRLF);
        yonyouStr.append("  ").append("host_ip：")            .append(InetAddress.getLocalHost().getHostAddress()).append(CRLF);
        yonyouStr.append("  ").append("host_name：")            .append(InetAddress.getLocalHost().getHostName()).append(CRLF);
        yonyouStr.append(" __     ______  _   ___     ______  _    _   ").append("file.encoding：")            .append(PropertiesUtilbc.key("file.encoding")).append(CRLF);
        yonyouStr.append(" \\ \\   / / __ \\| \\ | \\ \\   / / __ \\| |  | |  ").append("java.version：")            .append(PropertiesUtilbc.key("java.version")).append(CRLF);
        yonyouStr.append("  \\ \\_/ / |  | |  \\| |\\ \\_/ / |  | | |  | |  ").append("java.Memory：")            .append(Runtime.getRuntime().totalMemory() / 1024).append(CRLF);
        yonyouStr.append("   \\   /| |  | | . ` | \\   /| |  | | |  | |  ").append("java.io.tmpdir：")            .append(PropertiesUtilbc.key("java.io.tmpdir")).append(CRLF);
        yonyouStr.append("    | | | |__| | |\\  |  | | | |__| | |__| |  ").append("os.arch：")            .append(PropertiesUtilbc.key("os.arch")).append(CRLF);
        yonyouStr.append("    |_|  \\____/|_| \\_|  |_|  \\____/ \\____/   ").append("os.name：")            .append(PropertiesUtilbc.key("os.name")).append(CRLF);
        yonyouStr.append("                                             ").append("os.version：")            .append(PropertiesUtilbc.key("os.version")).append(CRLF);
        yonyouStr.append("                                             ").append("sun.desktop：")            .append(PropertiesUtilbc.key("sun.desktop")).append(CRLF);
        yonyouStr.append("  ").append("user.name：")            .append(PropertiesUtilbc.key("user.name")).append(CRLF);
        yonyouStr.append("  ").append("totalMemory：")            .append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append(" MB").append(CRLF);
        yonyouStr.append("  ").append(CRLF);
        yonyouStr.append("　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　 ").append(LEFT_CHEV)            .append(DateUtil.currentDateTime()).append(RIGHT_CHEV).append(CRLF);
        yonyouStr.append(CRLF);
        yonyouStr.append(CRLF);
        yonyouStr.append(CRLF).append(getHXWSplitLine());
        yonyouStr.append(CRLF);

        return yonyouStr;
    } 
    public static StringBuffer getMsgStr() throws UnknownHostException
    {

        StringBuffer msgStr = new StringBuffer();
        msgStr.append(CRLF);
        msgStr.append(CRLF);
        msgStr.append("                _._").append(CRLF);
        msgStr.append("           _.-``__ ''-._").append(CRLF);
        msgStr.append("      _.-``    `.  `_.  ''-._           ").append("host_ip：").append(InetAddress.getLocalHost().getHostAddress())            .append(CRLF);
        msgStr.append("  .-`` .-```.  ```\\/    _.,_ ''-._     ").append("java.version：").append(PropertiesUtilbc.key("java.version"))            .append(CRLF);
        msgStr.append(" (    '      ,       .-`     | `, )     ").append("file.encoding：").append(PropertiesUtilbc.key("file.encoding"))            .append(CRLF);
        msgStr.append(" |`-._`-...-` __...-.``-._|'` _.-'|     ").append("java.Memory：").append(Runtime.getRuntime().totalMemory() / 1024)            .append(CRLF);
        msgStr.append(" |    `-._   `._     /     _.-'   |     ").append("java.io.tmpdir：").append(PropertiesUtilbc.key("java.io.tmpdir"))            .append(CRLF);
        msgStr.append("  `-._    `-._  `-./  _.-'    _.-'      ").append("host_name：").append(InetAddress.getLocalHost().getHostName())            .append(CRLF);
        msgStr.append(" |`-._`-._    `-.__.-'    _.-'_.-'|     ").append("os.arch：").append(PropertiesUtilbc.key("os.arch")).append(CRLF);
        msgStr.append(" |    `-._`-._        _.-'_.-'    |     ").append("os.name：").append(PropertiesUtilbc.key("os.name")).append(CRLF);
        msgStr.append("  `-._    `-._`-.__.-'_.-'    _.-'      ").append("os.version：").append(PropertiesUtilbc.key("os.version"))            .append(CRLF);
        msgStr.append(" |`-._`-._    `-.__.-'    _.-'_.-'|     ").append("sun.desktop：").append(PropertiesUtilbc.key("sun.desktop"))            .append(CRLF);
        msgStr.append(" |    `-._`-._        _.-'_.-'    |     ").append("user.name：").append(PropertiesUtilbc.key("user.name"))            .append(CRLF);
        msgStr.append("  `-._    `-._`-.__.-'_.-'    _.-'      ").append("totalMemory：")            .append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append(" MB").append(CRLF);
        msgStr.append("      `-._    `-.__.-'    _.-'          ").append(LEFT_CHEV).append(CRLF);
        msgStr.append("          `-._        _.-'").append(CRLF);
        msgStr.append("              `-.__.-'").append(CRLF);

        msgStr.append(CRLF).append(getHXWSplitLine());
        msgStr.append(CRLF);
        msgStr.append(CRLF);

        return msgStr;
    }

    public static StringBuffer getSystemInfoStr() throws UnknownHostException
    {
        StringBuffer womanStr = new StringBuffer();
        womanStr.append("").append(CRLF);
        womanStr.append("                       .::::.                                      ").append(CRLF);
        womanStr.append("                     .::::::::.                                    ").append(CRLF);
        womanStr.append("                    :::::::::::                                    ").append("host_ip：")            .append(InetAddress.getLocalHost().getHostAddress()).append(CRLF);
        womanStr.append("                 ..:::::::::::'                                    ").append("host_name：")            .append(InetAddress.getLocalHost().getHostName()).append(CRLF);
        womanStr.append("              '::::::::::::'                                       ").append("file.encoding：")            .append(PropertiesUtilbc.key("file.encoding")).append(CRLF);
        womanStr.append("                .::::::::::                                        ").append("java.version：")            .append(PropertiesUtilbc.key("java.version")).append(CRLF);
        womanStr.append("           '::::::::::::::..                                       ").append("java.Memory：")            .append(Runtime.getRuntime().totalMemory() / 1024).append(CRLF);
        womanStr.append("                ..::::::::::::.                                    ").append("java.io.tmpdir：")            .append(PropertiesUtilbc.key("java.io.tmpdir")).append(CRLF);
        womanStr.append("              ``::::::::::::::::                                   ").append("os.arch：")            .append(PropertiesUtilbc.key("os.arch")).append(CRLF);
        womanStr.append("               ::::``:::::::::'        .:::.                       ").append("os.name：")            .append(PropertiesUtilbc.key("os.name")).append(CRLF);
        womanStr.append("              ::::'   ':::::'       .::::::::.                     ").append("os.version：")            .append(PropertiesUtilbc.key("os.version")).append(CRLF);
        womanStr.append("            .::::'      ::::     .:::::::'::::.                    ").append("sun.desktop：")            .append(PropertiesUtilbc.key("sun.desktop")).append(CRLF);
        womanStr.append("           .:::'       :::::  .:::::::::' ':::::.                  ").append("user.name：")            .append(PropertiesUtilbc.key("user.name")).append(CRLF);
        womanStr.append("          .::'        :::::.:::::::::'      ':::::.                ").append("totalMemory：")            .append(Runtime.getRuntime().totalMemory() / 1024 / 1024).append(" MB").append(CRLF);
        womanStr.append("         .::'         ::::::::::::::'         ``::::.              ").append(CRLF);
        womanStr.append("     ...:::           ::::::::::::'              ``::.             ").append(LEFT_CHEV)            .append(DateUtil.currentDateTime()).append(RIGHT_CHEV).append(CRLF);
        womanStr.append("    ```` ':.          ':::::::::'                  ::::..          ").append(CRLF);
        womanStr.append("                       '.:::::'                    ':'````..       ").append(CRLF);
        womanStr.append(CRLF);
        womanStr.append(CRLF);
        womanStr.append(CRLF).append(getHXWSplitLine())            .append(CRLF);
        womanStr.append(CRLF);

        return womanStr;
    }
    public static String getHXWSplitLine()
    {
        // 🀙🀚🀛🀜🀝🀞🀟🀠🀡   🀏🀎🀍🀌🀋🀊🀉🀇  🀐🀔🀕🀖🀗🀘  🀅🀁🀆🀢🀣 👨🏻‍🍳🪑
        // ꕥ᭄ঞ এ⁵²ºꕥꦿ       💞ꦿ 🕊️   ༺o༻
        // ༊ღ᭄夣醒ꦿ꯭心亦碎꧔ꦿ᭄💞 ⁵²º⅓¼
        // ▏▎▍▌▋▊▉🎼♪♬♩▉▊▋▌▍▎
        // 💥 🚀🌏 🌕😍🥰😘
        // 🌲🚔🚺🏃🏼‍♂️
        // 🖥⌨️🖱☎💺👨🏻‍🍳  真 皮 网 咖 ∧＿∧
        // ´¯`•´`•.¸¸.•´´༺o༻`••.¸¸.•´´¯`•´
        // 🎹🎼♩♩♪     ♬♩   ♪♫  ♫  ♬♪
        // 📱❌💻❌🖐🏻👴🏻🖐🏻💵❌🍜❌▏
        String msg=   "👉🏻————ꕥ😍🥰😘(◡‿◡✿)༺ཌ༈魑魅魍魉༈ད༻ ꧁ღ❦ꦿ勇敢的少年啊快去创造奇迹এ⁵²ºஐఌ꧂ 🌲🚔🚺🏃🏼‍♂️❤————"+CRLF//
                + "☞∴ღ ঞ✲❈✿✲❈☀☂❁☁ 🚀🌏💥   🀙🀚🀛🀜🀝🀞🀟🀠🀡 🀢🀣🀥 🀗🀐 🀏🀎🀍🀌🀋🀊🀉  📱❌💻🖐🏻👴🏻💵🍜  🎹🎼♩♩♪  ♬♩  ♪♫ ♫ ♬♪"+CRLF//
                + "🖥⌨️🖱☎💺👨🏻‍🍳✦💞ꦿ兲檤絒懄এ⁵²º❣🕊️--༊夲峸⁵²º⅓¼ "+CRLF//
                + "👇🏻´¯`•´`•.¸¸.•´´༺o༻`••.¸¸.•´´¯`•´ꕥꦿ";
        return msg;
    }
    public static String getSplitLine()
    {
        return "✄—————————————————————————(◕‿◕✿)❤完美分割线❀split line❤—————————————————————————————";
    }

    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }

    public boolean isIS_LOGS()
    {
        return IS_LOGS;
    }

    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @methods nc.pub.itf.tools.pub.LoggerUtils#构造方法
     * @author licheng
     * @date Created on 2019-8-12
     * @time 上午10:20:09
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @Deprecated
    private LoggerUtilbc()
    {

    }

    /**
     * *********************************************************** <br>
     * 说明：
     * @see @param fileUrl
     * @see @param folderName
     * @methods nc.pub.itf.tools.pub.LoggerUtils#构造方法
     * @author licheng
     * @date Created on 2019-8-12
     * @time 上午10:01:40
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @Deprecated
    private LoggerUtilbc(String fileUrl, String folderName)
    {
        this.fileUrl = fileUrl;
        this.folderName = folderName;
    }

    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param folderName 文件名
     * @methods nc.pub.itf.tools.pub.LoggerUtils#构造方法
     * @author licheng
     * @date Created on 2019-8-12
     * @time 上午11:13:34
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @Deprecated
    private LoggerUtilbc(String folderName)
    {
        this.folderName = folderName;
    }

    private static class InnerInstance
    {
        // private static final LoggerUtilbc logger = new LoggerUtilbc();
        private static LoggerUtilbc logger = new LoggerUtilbc();
        //懶漢式
        public static LoggerUtilbc getInstance()
        {
            if (null == logger)
            {
                synchronized (InnerInstance.class)
                {
                    if (null == logger)
                    {
                        logger = new LoggerUtilbc();
                    }
                }
            }
            return logger;
        }

        public static LoggerUtilbc getInstance(String fileUrl, String folderName)
        {
            return new LoggerUtilbc(fileUrl,folderName);
        }

        public static LoggerUtilbc getInstance(String folderName)
        {
            return new LoggerUtilbc(folderName);
        }

    }

    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @return <br>
     * @LoggerUtilbc <br>
     * @methods pers.bc.utils.pub.LoggerUtilbc#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-29 <br>
     * @time 23:13:50 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static LoggerUtilbc getInstance()
    {
        return InnerInstance.logger;
    }

    public static LoggerUtilbc getInstance(String folderName)
    {
        return InnerInstance.getInstance(folderName);
    }

    public static LoggerUtilbc getInstance(String fileUrl, String folderName)
    {
        return InnerInstance.getInstance(fileUrl, folderName);
    }
    public static void main(String[] args) throws UnknownHostException
    {

        // System.err.println(LoggerUtilbc.getThrowStr());
        // System.err.println(LoggerUtilbc.getBeautyWomanStr());
        // System.err.println(LoggerUtilbc.getWomanStr());
        // System.err.println(LoggerUtilbc.getDangerStr());
        // System.err.println(LoggerUtilbc.getKeyBoardStr());
        // System.err.println(LoggerUtilbc.getOfficeStr());
        // System.err.println(LoggerUtilbc.getYONStr());
        // System.err.println(LoggerUtilbc.getYonYouStr());
        // System.err.println(LoggerUtilbc.getMsgStr());
        // System.err.println(LoggerUtilbc.getSystemInfoStr());

    }
}
