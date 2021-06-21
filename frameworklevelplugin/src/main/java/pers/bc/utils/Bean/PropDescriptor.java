package pers.bc.utils.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import pers.bc.utils.pub.AppDebug;
import pers.bc.utils.pub.StringUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 
 * @qualiFild nc.pub.itf.tools.Bean.PropDescriptor.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class PropDescriptor  // extends PropertyDescriptor
{
    private final Class<?> beanType;
    
    private Class<?> propType;
    
    private final String name;
    
    private String baseName;
    
    public PropDescriptor(Class<?> beanType, Class<?> propType, String propName)
    {
        if (beanType == null)
        {
            throw new IllegalArgumentException("Bean Class can not be null!");
        }
        if (propName == null)
        {
            throw new IllegalArgumentException("Bean Property name can not be null!");
        }
        this.propType = propType;
        // in which this property is declared.
        this.beanType = beanType;
        this.name = propName;
        if (this.name.startsWith("m_") && this.name.length() > 2)
        {
            this.baseName = StringUtilbc.capitalize(this.name.substring(2));
        }
        else
        {
            this.baseName = StringUtilbc.capitalize(propName);
        }
    }
    
    /**
     * currBean my override get and set.
     */
    public synchronized Method getReadMethod(Class<?> currBean)
    {
        Method readMethod;
        String readMethodName = null;
        if (propType == boolean.class || propType == null)
        {
            readMethodName = "is" + baseName;
        }
        else
        {
            readMethodName = "get" + baseName;
        }
        Class<?> classStart = currBean;
        if (classStart == null)
        {
            classStart = beanType;
        }
        readMethod = findMemberMethod(classStart, readMethodName, 0, null);
        if (readMethod == null && readMethodName.startsWith("is"))
        {
            readMethodName = "get" + baseName;
            readMethod = findMemberMethod(classStart, readMethodName, 0, null);
        }
        if (readMethod != null)
        {
            int mf = readMethod.getModifiers();
            if (!Modifier.isPublic(mf))
            {
                return null;
            }
            Class<?> retType = readMethod.getReturnType();
            if (!propType.isAssignableFrom(retType))
            {
                AppDebug.debug("return type unmatch for get Method and property! : " + classStart.getName() + "." + name);
            }
        }
        return readMethod;
    }
    
    public synchronized Method getWriteMethod(Class<?> currBean)
    {
        Method writeMethod;
        String writeMethodName = null;
        if (propType == null)
        {
            propType = findPropertyType(getReadMethod(currBean), null);
        }
        if (writeMethodName == null)
        {
            writeMethodName = "set" + baseName;
        }
        Class<?> classStart = currBean;
        if (classStart == null)
        {
            classStart = beanType;
        }
        writeMethod = findMemberMethod(classStart, writeMethodName, 1, (propType == null) ? null : new Class[]{propType});
        if (writeMethod != null)
        {
            int mf = writeMethod.getModifiers();
            if (!Modifier.isPublic(mf) || Modifier.isStatic(mf))
            {
                writeMethod = null;
            }
        }
        return writeMethod;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param clazz
     * @param propertyName
     * @return
     * @Method
     * @author licheng
     * @date Created on 2019-8-8
     * @time 下午4:49:34
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Method getReadMethod(Class<?> clazz, String propertyName)
    {
        Method method = null;
        BeanInfo bi = null;
        try
        {
            bi = Introspector.getBeanInfo(clazz);
        }
        catch (IntrospectionException ex)
        {
            if (ex != null) new MylbcException(ex);
        }
        PropertyDescriptor[] props = null;
        if (bi != null) props = bi.getPropertyDescriptors();
        if (props != null)
        {
            for (int i = 0; i < props.length; i++)
                if (propertyName.toLowerCase().equals(props[i].getName().toLowerCase()))
                {
                    method = props[i].getReadMethod();
                    break;
                }
        }
        return method;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param clazz
     * @param propertyName
     * @return
     * @Method
     * @author licheng
     * @date Created on 2019-8-8
     * @time 下午4:49:41
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Method getWriteMethod(Class<?> clazz, String propertyName)
    {
        Method method = null;
        BeanInfo bi = null;
        try
        {
            bi = Introspector.getBeanInfo(clazz);
        }
        catch (IntrospectionException ex)
        {
            if (ex != null) new MylbcException(ex);
        }
        PropertyDescriptor[] props = null;
        if (bi != null) props = bi.getPropertyDescriptors();
        if (props != null)
        {
            for (int i = 0; i < props.length; i++)
                if (propertyName.toLowerCase().equals(props[i].getName().toLowerCase()))
                {
                    method = props[i].getWriteMethod();
                    break;
                }
        }
        return method;
    }
    
    private Class<?> findPropertyType(Method readMethod, Method writeMethod)
    {
        Class<?> propertyType = null;
        if (readMethod != null)
        {
            propertyType = readMethod.getReturnType();
        }
        if (propertyType == null && writeMethod != null)
        {
            Class<?> params[] = writeMethod.getParameterTypes();
            propertyType = params[0];
        }
        return propertyType;
    }
    
    private Method findMemberMethod(Class<?> beanClass, String mName, int num, Class<?>[] args)
    {
        Method foundM = null;
        Method[] methods = beanClass.getDeclaredMethods();
        if (methods.length < 0)
        {
            return null;
        }
        for (Method method : methods)
        {
            if (method.getName().equalsIgnoreCase(mName))
            {
                Class<?>[] paramTypes = method.getParameterTypes();
                if (paramTypes.length == num)
                {
                    boolean match = true;
                    for (int i = 0; i < num; i++)
                    {
                        // parameter should be compatible with prop type
                        if (!args[i].isAssignableFrom(paramTypes[i]))
                        {
                            match = false;
                            break;
                        }
                    }
                    if (match)
                    {
                        foundM = method;
                        break;
                    }
                }
            }
        }
        // recursively find super
        if (foundM == null)
        {
            if (beanClass.getSuperclass() != null)
            {
                foundM = findMemberMethod(beanClass.getSuperclass(), mName, num, args);
            }
        }
        return foundM;
    }
    
    public String getName()
    {
        return name;
    }
}
