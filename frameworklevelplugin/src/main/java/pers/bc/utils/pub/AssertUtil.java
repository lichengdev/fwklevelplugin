package pers.bc.utils.pub;

/**
 * 提供一些对象有效性校验的方法
 * @qualiFild nc.pub.itf.tools.pub.AssertUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class AssertUtil
{
    
    public static void notNull(Object object, String message)
    {
        if (object == null)
        {
            throw new IllegalArgumentException(message);
        }
    }
}
