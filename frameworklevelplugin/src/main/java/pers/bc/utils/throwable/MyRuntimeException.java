package pers.bc.utils.throwable;

public class MyRuntimeException extends RuntimeException
{
    private static final long serialVersionUID = 3700318870246955673L;
    
    public MyRuntimeException(String message)
    {
        super(message);
    }
    
    public MyRuntimeException(MylbcException ex)
    {
        super(ex.getMessage(), ex);
    }
}
