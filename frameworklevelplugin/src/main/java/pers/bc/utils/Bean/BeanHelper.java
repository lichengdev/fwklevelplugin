/**
 * <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen
 * Chao.
 */
package pers.bc.utils.Bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import pers.bc.utils.pub.PubEnvUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 
 * @qualiFild nc.pub.itf.tools.Bean.BeanHelper.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class BeanHelper
{
    protected static final Object[] NULL_ARGUMENTS = {};
    
    private static Map<String, ReflectionInfo> cache = new ConcurrentHashMap<String, ReflectionInfo>();
    
    private static BeanHelper bhelp = null;
    
    public BeanHelper()
    {
    }
    
    public static BeanHelper getInstance()
    {
        if (null == bhelp)
        {
            bhelp = new BeanHelper();
        }
        return bhelp;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 检测对象的有没有属性名<br>
     * @see <br>
     * @param bean
     * @param field
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.Bean.BeanHelper#checkfield <br>
     * @author licheng <br>
     * @date Created on 2019年11月27日 <br>
     * @time 上午9:31:48 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean checkfield(Object bean, String field)
    {
        return Arrays.asList(getInstance().getPropertiesAry(bean.getClass())).contains(field);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 返回对象的所有属性名<br>
     * @see <br>
     * @param beanCls
     * @return <br>
     * @List<String> <br>
     * @methods nc.pub.itf.tools.Bean.BeanHelper#getPropertys <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 下午2:59:59 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<String> getPropertys(Class<?> beanCls)
    {
        return Arrays.asList(getInstance().getPropertiesAry(beanCls));
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 返回对象的所有属性名<br>
     * @see <br>
     * @param beanCls
     * @return <br>
     * @String[] <br>
     * @methods nc.pub.itf.tools.Bean.BeanHelper#getPropertiesAry <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 下午2:57:06 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String[] getPropertiesAry(Class<?> beanCls)
    {
        
        ReflectionInfo reflectionInfo = null;
        reflectionInfo = cachedReflectionInfo(beanCls);
        Set<String> propertys = new HashSet<String>();
        for (String key : reflectionInfo.writeMap.keySet())
        {
            if (reflectionInfo.writeMap.get(key) != null)
            {
                propertys.add(key);
            }
        }
        return propertys.toArray(new String[0]);
    }
    
    public static void setProperty(Object bean, String propertyName, Object value)
    {
        try
        {
            Method method = getInstance().getMethod(bean.getClass(), propertyName, true);
            if (propertyName != null && method == null)
            {
                // Logger.error(String.format("No set method found! : [%s].[%s]",
                // bean.getClass().getName(), propertyName));
                return;
            }
            else if (method == null)
            {
                return;
            }
            method.invoke(bean, value);
        }
        catch (IllegalArgumentException e)
        {
            String errStr =
                "Failed to set property: " + propertyName + " at bean: " + bean.getClass().getName() + " with value:" + value + " type:"
                    + (value == null ? "null" : value.getClass().getName());
            // Logger.error(errStr, e);
            throw new IllegalArgumentException(errStr, e);
        }
        catch (Exception e)
        {
            String errStr = "Failed to set property: " + propertyName + " at bean: " + bean.getClass().getName() + " with value:" + value;
            // Logger.error(errStr, e);
            throw new RuntimeException(errStr, e);
        }
    }
    
    /**
     * 获取bean对象的属性值
     * 
     * @param bean
     * @param propertyName
     * @return
     */
    public static Object getProperty(Object bean, String propertyName)
    {
        
        try
        {
            Method method = getInstance().getMethod(bean.getClass(), propertyName, false);
            if (propertyName != null && method == null)
            {
                return null;
            }
            else if (method == null)
            {
                return null;
            }
            return method.invoke(bean, NULL_ARGUMENTS);
        }
        catch (Exception e)
        {
            String errStr = "Failed to get property: " + propertyName;
            // Logger.warn(errStr, e);
            throw new RuntimeException(errStr, e);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 批量返回bean对象的属性值<br>
     * @see <br>
     * @param beanCls
     * @param propertys
     * @return <br>
     * @Object[] <br>
     * @methods nc.pub.itf.tools.Bean.BeanHelper#getPropertyValues <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 下午4:04:40 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object[] getPropertyValues(Class<?> beanCls, String[] propertys)
    {
        Object[] result = new Object[propertys.length];
        try
        {
            Method[] methods = getInstance().getMethods(beanCls, propertys, false);
            for (int i = 0; i < propertys.length; i++)
            {
                if (propertys[i] == null || methods[i] == null)
                {
                    result[i] = null;
                }
                else
                {
                    result[i] = methods[i].invoke(beanCls.newInstance(), NULL_ARGUMENTS);
                }
            }
        }
        catch (Exception e)
        {
            String errStr = "Failed to get getPropertys from " + beanCls;
            throw new RuntimeException(errStr, e);
        }
        return result;
    }
    
    /**
     * 返回属性的set方法
     * 
     * @param beanCls
     * @param propertyName
     * @return
     */
    public static Method getMethod(Class<?> beanCls, String propertyName)
    {
        return getInstance().getMethod(beanCls, propertyName, true);
    }
    
    public static Method getGetMethod(Class<?> beanCls, String propertyName)
    {
        return getInstance().getMethod(beanCls, propertyName, false);
    }
    
    public static Method getSetMethod(Class<?> beanCls, String propertyName)
    {
        return getInstance().getMethod(beanCls, propertyName, true);
    }
    
    /**
     * 批量返回属性的set方法
     * 
     * @param beanCls
     * @param propertys
     * @return
     */
    public static Method[] getMethods(Class<?> beanCls, String[] propertys)
    {
        return getInstance().getMethods(beanCls, propertys, true);
    }
    
    private Method[] getMethods(Class<?> beanCls, String[] propertys, boolean isSetMethod)
    {
        Method[] methods = new Method[propertys.length];
        ReflectionInfo reflectionInfo = null;
        
        reflectionInfo = cachedReflectionInfo(beanCls);
        for (int i = 0, j = propertys.length; i < j; i++)
        {
            Method method = null;
            if (isSetMethod)
            {
                method = reflectionInfo.getWriteMethod(propertys[i]);
            }
            else
            {
                method = reflectionInfo.getReadMethod(propertys[i]);
            }
            methods[i] = method;
        }
        return methods;
    }
    
    private Method getMethod(Class<?> beanCls, String propertyName, boolean isSetMethod)
    {
        return getMethods(beanCls, new String[]{propertyName}, isSetMethod)[0];
    }
    
    private static ReflectionInfo cachedReflectionInfo(Class<?> beanCls)
    {
        return cacheReflectionInfo(beanCls, null);
    }
    
    private static ReflectionInfo cacheReflectionInfo(Class<?> beanCls, List<PropDescriptor> pdescriptor)
    {
        String key = beanCls.getName();
        ReflectionInfo reflectionInfo = cache.get(key);
        if (reflectionInfo == null)
        {
            reflectionInfo = cache.get(key);
            if (reflectionInfo == null)
            {
                reflectionInfo = new ReflectionInfo();
                List<PropDescriptor> propDesc = new ArrayList<PropDescriptor>();
                if (pdescriptor != null)
                {
                    propDesc.addAll(pdescriptor);
                }
                else
                {
                    propDesc = getPropertyDescriptors(beanCls);
                }
                for (PropDescriptor pd : propDesc)
                {
                    Method readMethod = pd.getReadMethod(beanCls, pd.getName());
                    Method writeMethod = pd.getWriteMethod(beanCls, pd.getName());
                    if (readMethod != null) reflectionInfo.readMap.put(pd.getName().toLowerCase(), readMethod); // store
                    if (writeMethod != null) reflectionInfo.writeMap.put(pd.getName().toLowerCase(), writeMethod);
                }
                cache.put(key, reflectionInfo);
            }
        }
        return reflectionInfo;
        
    }
    
    public static void invokeMethod(Object bean, Method method, Object value)
    {
        try
        {
            if (method == null) return;
            Object[] arguments = {value};
            method.invoke(bean, arguments);
        }
        catch (Exception e)
        {
            String errStr = "Failed to set property: " + method.getName();
            throw new RuntimeException(errStr, e);
        }
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
    public static Method getReadMethod(Class<?> clazz, String propertyName)
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
    public static Method getWriteMethod(Class<?> clazz, String propertyName)
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
    
    /**
     * *********************************************************** <br>
     * 说明：返回所有get的方法 <br>
     * @see <br>
     * @param beanCls
     * @param fieldNames
     * @return <br>
     * @Method[] <br>
     * @methods nc.pub.itf.tools.Bean.BeanHelper#getAllGetMethod <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 下午2:52:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Method[] getAllGetMethod(Class<?> beanCls, String[] fieldNames)
    {
        
        Method[] methods = null;
        ReflectionInfo reflectionInfo = null;
        List<Method> al = new ArrayList<Method>();
        reflectionInfo = cachedReflectionInfo(beanCls);
        for (String str : fieldNames)
        {
            al.add(reflectionInfo.getReadMethod(str));
        }
        methods = al.toArray(new Method[al.size()]);
        return methods;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取Bean所有信息 <br>
     * @see <br>
     * @param obj
     * @return
     * @throws Exception <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.Bean.BeanHelper#getBeanInfo <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-8 <br>
     * @time 上午12:45:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Object> getBeanInfo(Object obj) throws Exception
    {
        return ReflectionUtilbc.getBeanInfo(obj);
    }
    
    private static List<PropDescriptor> getPropertyDescriptors(Class<?> clazz)
    {
        List<PropDescriptor> descList = new ArrayList<PropDescriptor>();
        List<String> propsList = new ArrayList<String>();
        Class<?> propType = null;
        for (Method method : clazz.getDeclaredMethods())
        {
            if (method.getName().length() < 4)
            {
                continue;
            }
            if (method.getName().charAt(3) < 'A' || method.getName().charAt(3) > 'Z')
            {
                continue;
            }
            if (method.getName().startsWith("set"))
            {
                if (method.getParameterTypes().length != 1)
                {
                    continue;
                }
                if (method.getReturnType() != void.class)
                {
                    continue;
                }
                propType = method.getParameterTypes()[0];
            }
            else if (method.getName().startsWith("get"))
            {
                if (method.getParameterTypes().length != 0)
                {
                    continue;
                }
                propType = method.getReturnType();
            }
            else
            {
                continue;
            }
            String propname = method.getName().substring(3, 4).toLowerCase();
            if (method.getName().length() > 4)
            {
                propname = propname + method.getName().substring(4);
            }
            if (propname.equals("class"))
            {
                continue;
            }
            if (propsList.contains(propname))
            {
                continue;
            }
            else
            {
                propsList.add(propname);
            }
            descList.add(new PropDescriptor(clazz, propType, propname));
        }
        
        // Class<?> superClazz = clazz.getSuperclass();
        // if (superClazz != null)
        // {
        // superDescList = getPropertyDescriptors(superClazz);
        // descList.addAll(superDescList);
        // if (!isBeanCached(superClazz))
        // {
        // cacheReflectionInfo(superClazz, superDescList);
        // }
        // }
        return descList;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：是否缓存了Bean <br>
     * @see <br>
     * @param bean
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.Bean.BeanHelper#isBeanCached <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-7 <br>
     * @time 上午9:28:24 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public boolean isBeanCached(Class<?> bean)
    {
        String key = bean.getName();
        ReflectionInfo cMethod = cache.get(key);
        if (cMethod == null)
        {
            cMethod = cache.get(key);
            if (cMethod == null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：判断是不是Bean Class <br>
     * @see <br>
     * @param bean
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.Bean.BeanHelper#isBeanClass <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-8 <br>
     * @time 上午12:10:13 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isBeanClass(Class<?> bean)
    {
        String[] propertiesAry = getPropertiesAry(bean);
        if (PubEnvUtilbc.getSize(propertiesAry) > 1)
        {
            Method[] setMethods = getInstance().getMethods(bean, propertiesAry, true);
            Method[] getMethods = getInstance().getAllGetMethod(bean, propertiesAry);
            if (PubEnvUtilbc.getSize(setMethods) > 1 && PubEnvUtilbc.getSize(getMethods) > 1) //
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将一个 JavaBean 对象转化为一个Map <br>
     * @see <br>
     * @param bean
     * @return
     * @throws Exception <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.Bean.BeanHelper#transBean2Map2 <br>
     * @author LiBencheng <br>
     * @date Created on 2021-1-29 <br>
     * @time 上午11:56:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Object> transBean2Map2(Object bean) throws Exception
    {
        if (null == bean) return null;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        
        String[] propertiesAry = getPropertiesAry(bean.getClass());
        for (int i = 0, j = PubEnvUtilbc.getSize(propertiesAry); i < j; i++)
        {
            String key = propertiesAry[i];
            if (!key.equals("class"))
            {
                Object value = getProperty(bean, key);
                map.put(key, value);
            }
        }
        
        return map;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 用Introspector和PropertyDescriptor 将Bean --> Map<br>
     * @see <br>
     * @param obj
     * @return
     * @throws Exception <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.Bean.BeanHelper#transBean2Map <br>
     * @author LiBencheng <br>
     * @date Created on 2021-1-29 <br>
     * @time 上午11:42:01 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Object> transBean2Map(Object obj) throws Exception
    {
        if (null == obj) return null;
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors)
        {
            String key = property.getName();
            // 过滤class属性
            if (!key.equals("class"))
            {
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);
                map.put(key, value);
            }
        }
        return map;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 将一个 Map 对象转化为一个 JavaBean<br>
     * 
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws IntrospectionException <br>
     * @Object <br>
     * @methods pers.bc.utils.Bean.BeanHelper#transMap2Bean <br>
     * @author LiBencheng <br>
     * @date Created on 2021-1-29 <br>
     * @time 上午11:39:21 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object transMap2Bean(Class<?> type, Map<?, ?> map) throws InvocationTargetException, IllegalAccessException,
            InstantiationException, IntrospectionException
    {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象
        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++)
        {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (map.containsKey(propertyName))
            {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);
                Object[] args = new Object[1];
                args[0] = value;
                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }
    
    static class ReflectionInfo
    {
        
        Map<String, Method> readMap = new HashMap<String, Method>();
        
        Map<String, Method> writeMap = new HashMap<String, Method>();
        
        Method getReadMethod(String prop)
        {
            return prop == null ? null : this.readMap.get(prop.toLowerCase());
        }
        
        Method getWriteMethod(String prop)
        {
            return prop == null ? null : this.writeMap.get(prop.toLowerCase());
        }
    }
    
}
