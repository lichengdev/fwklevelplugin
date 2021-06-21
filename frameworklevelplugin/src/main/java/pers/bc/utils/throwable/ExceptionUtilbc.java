package pers.bc.utils.throwable;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 异常处理的工具类
 * @qualiFild nc.pub.itf.tools.pub.ExceptionUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class ExceptionUtilbc
{
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param s
     * @return <br>
     * @Throwable <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#throwException <br>
     * @author licheng <br>
     * @date Created on 2019-9-9 <br>
     * @time 下午4:35:16 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Throwable throwException(String message)
    {
        
        MylbcException ex = new MylbcException(message);
        
        throw new MyRuntimeException(ex);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param th
     * @return <br>
     * @Throwable <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#throwException <br>
     * @author licheng <br>
     * @date Created on 2019-9-10 <br>
     * @time 上午11:45:08 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Throwable throwException(Throwable th)
    {
        return throwException(th.toString());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param s
     * @param errorCode
     * @return <br>
     * @Throwable <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#throwException <br>
     * @author licheng <br>
     * @date Created on 2019-9-9 <br>
     * @time 下午4:35:13 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Throwable throwException(String s, String errorCode)
    {
        MylbcException ex = new MylbcException(s, errorCode);
        
        throw new MyRuntimeException(ex);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param th
     * @param errorCode
     * @return <br>
     * @Throwable <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#throwException <br>
     * @author licheng <br>
     * @date Created on 2019-9-10 <br>
     * @time 上午11:45:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Throwable throwException(Throwable th, String errorCode)
    {
        return throwException(th.toString(), errorCode);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取异常信息
     * 
     * @param e 异常信息
     * @return string
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#stackTraceToString <br>
     * @author licheng <br>
     * @date Created on 2019-9-9 <br>
     * @time 下午4:35:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String stackTraceToString(Throwable th)
    {
        StringWriter sw = new StringWriter();
        th.printStackTrace(new PrintWriter(sw, true));
        return sw.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 只返回指定包中的异常堆栈信息
     * @param e 异常信息
     * @param packageName 只转换某个包下的信息
     * @return string
     * @String <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#stackTraceToString <br>
     * @author licheng <br>
     * @date Created on 2019-9-9 <br>
     * @time 下午4:35:27 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String stackTraceToString(Throwable th, String packageName)
    {
        String str = stackTraceToString(th);
        if (packageName == null)
        {
            return str;
        }
        String[] arrs = str.split("\n");
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(arrs[0] + "\n");
        for (int i = 0; i < arrs.length; i++)
        {
            String temp = arrs[i];
            if (temp != null && temp.indexOf(packageName) > 0)
            {
                sbuf.append(temp + "\n");
            }
        }
        return sbuf.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取堆栈信息<br>
     * @see <br>
     * @param e
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.ExceptionUtil#getStackTrace <br>
     * @author licheng <br>
     * @date Created on 2019-9-9 <br>
     * @time 下午4:36:08 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final String getStackTrace(Throwable th)
    {
        if (th == null) return "Throwable{[null]}没有堆栈信息！";
        
        StringBuffer msg = new StringBuffer(th.toString() + "\r\n");
        StackTraceElement[] stackTrace = th.getStackTrace();
        for (int i = 0, j = stackTrace.length; i < j; i++)
        {
            msg.append("        at " + stackTrace[i] + "\r\n");
        }
        
        return msg.toString();
    }
    
}
