package pers.bc.utils.pub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印调试信息和日志记录
 * @qualiFild nc.pub.itf.tools.pub.AppDebug.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class AppDebug
{
    private AppDebug()
    {
    }
    
    public static final Logger log = LoggerFactory.getLogger(AppDebug.class);
    public static final LoggerUtilbc loggr = LoggerUtilbc.getInstance();
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#debug <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:31:19 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void debug(Object obj)
    {
        String msg = StringUtilbc.toString(obj);
        loggr.debug(msg);
        log.debug(msg);
        System.err.println(msg);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#info <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:29:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void info(Object obj)
    {
        String msg = StringUtilbc.toString(obj);
        loggr.info(msg);
        log.info(msg);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#error <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:31:39 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void error(Object obj)
    {
        String msg = StringUtilbc.toString(obj);
        loggr.error(msg);
        log.error(msg);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 打印异常信息及异常轨迹<br>
     * @see <br>
     * @param th <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#exception <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:28:14 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void exception(Throwable th)
    {
        String msgStr = LoggerUtilbc.getInvokMethod(0);
        
        loggr.exception(th);
        log.trace(msgStr);
        th.printStackTrace();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：打印整数 <br>
     * @see <br>
     * @param num <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#debug <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:32:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void debug(int num)
    {
        debug(num + "");
    }
    
    /**
     * *********************************************************** <br>
     * 说明：打印小数 <br>
     * @see <br>
     * @param num <br>
     * @void <br>
     * @methods pers.bc.utils.pub.AppDebug#debug <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:32:13 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void debug(double num)
    {
        debug(num + "");
    }
    
    public static void debug(boolean b)
    {
        debug(b + "");
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param folderName
     * @return <br>
     * @LoggerUtilbc <br>
     * @methods pers.bc.utils.pub.AppDebug#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2021-6-16 <br>
     * @time 下午6:32:35 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static LoggerUtilbc getInstance(String folderName)
    {
        if (null == folderName || folderName.length() < 1)
        {
            return LoggerUtilbc.getInstance();
        }
        return LoggerUtilbc.getInstance(folderName);
    }
    
}
