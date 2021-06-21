package pers.bc.utils.Bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 方法级上的注解
 * @qualiFild nc.pub.itf.tools.Bean.MethodInfo.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodInfo
{
    // 方法类型
    public String type() default "";
    
    // 方法标识
    public String name() default "";
}
