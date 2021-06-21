package pers.bc.utils.throwable;
/**
 * 密钥异常类
 * @qualiFild pers.bc.utils.throwb.CryptException.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public class CryptException extends Exception
{
    
    private static final long serialVersionUID = 2843021655596315128L;
    
    public CryptException(String message)
    {
        super(message);
    }
    
    public CryptException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public CryptException(Throwable cause)
    {
        super(cause);
    }
}
