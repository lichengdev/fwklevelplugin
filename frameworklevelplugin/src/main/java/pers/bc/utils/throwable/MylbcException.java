package pers.bc.utils.throwable;

/**
 * 自定义异常处理类
 * @qualiFild nc.pub.itf.tools.pub.MylbcException.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class MylbcException extends Exception
{
    
    private static final long serialVersionUID = 7938972267861520994L;
    private String hint;
    private String errorCodeString = "";
    
    public MylbcException()
    {
    }
    
    public MylbcException(String s)
    {
        super(s);
        setErrorCodeString("-32000");
    }
    
    public MylbcException(String s, String errorCode)
    {
        super(s);
        setErrorCodeString(errorCode);
    }
    
    public String getHint()
    {
        return this.hint;
    }
    
    public void setHint(String newHint)
    {
        this.hint = newHint;
    }
    
    public MylbcException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public MylbcException(Throwable cause)
    {
        super(cause);
    }
    
    public String getErrorCodeString()
    {
        return this.errorCodeString;
    }
    
    public void setErrorCodeString(String errorCode)
    {
        this.errorCodeString = errorCode;
    }
    
}
