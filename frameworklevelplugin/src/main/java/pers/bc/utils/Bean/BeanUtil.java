package pers.bc.utils.Bean;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import pers.bc.utils.cons.PropertyFilter;
import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.pub.CollectionUtil;

/**
 * JavaBean相关的一些操作
 * @qualiFild nc.pub.itf.tools.Bean.BeanUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class BeanUtil
{
    /**
     * 把JSON、MAP转换为VO 功能： JSON串转List 数组转List*********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param <T>
     * @param oragin
     * @param target
     * @return <br>
     * @T <br>
     * @methods pers.bc.utils.Bean.BeanUtil#parseVO <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午4:54:37 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static <T> T parseVO(Object oragin, Class<T> target)
    {
        if (oragin instanceof Map)
        { // Map转换成对象
            Object newVO = ReflectionUtilbc.newInstance(target);
            Map temp = (Map) oragin;
            for (Object key : temp.keySet())
            {
                Object value = temp.get(key);
                ObjectUtil.set(newVO, key, value);
            }
            return (T) newVO;
        }
        else if (oragin instanceof String)
        { // Json转成Map，再转成对象
            Map temp = new Gson().fromJson(oragin.toString(), LinkedHashMap.class);
            return parseVO(temp, target);
        }
        return null;
    }
    
    private static Map<String, BeanStruct> simpleProperties(Object obj)
    {
        return Factory.BEAN_SIMPLE_PROPERTIES.get(obj.getClass().getName());
    }
    
    private static Map<String, BeanStruct> simplePropertiesIgnore(Object obj)
    {
        return Factory.BEAN_SIMPLE_PROPERTIESIGNORE.get(obj.getClass().getName());
    }
    
    private static Method getReadMethod(Object obj, String pro)
    {
        BeanStruct st = simpleProperties(obj).get(pro);
        return st.getReadMethod();
    }
    
    private static Method getWriteMethod(Object obj, String pro)
    {
        BeanStruct st = simpleProperties(obj).get(pro);
        return st.getWriteMethod();
    }
    
    private static Method getReadMethodIgnore(Object obj, String pro)
    {
        BeanStruct st = simplePropertiesIgnore(obj).get(pro);
        return st.getReadMethod();
    }
    
    private static Method getWriteMethodIgnore(Object obj, String pro)
    {
        BeanStruct st = simplePropertiesIgnore(obj).get(pro);
        return st.getWriteMethod();
    }
    
    private static Object readMethod(Object bean, Method readMethod) throws InvocationTargetException, IllegalAccessException
    {
        return readMethod.invoke(bean);
    }
    
    private static void writeMethod(Object bean, Method writeMethod, Object value) throws InvocationTargetException, IllegalAccessException
    {
        writeMethod.invoke(bean, value);
    }
    
    /**
     * 添加Bean到BeanFactory的解析范围中
     * 
     * @param obj 将目标obj加入到BeanFactory的解析范围中
     */
    public static void add(Object obj)
    {
        
    }
    
    /**
     * 添加Bean到BeanFactory的解析范围中
     * 
     * @param clazz 将目标clazz加入到BeanFactory的解析范围中
     */
    public static void add(Class<?> clazz)
    {
        
    }
    
    /**
     * 判断属性是否存在
     * 
     * @param bean 判断的目标bean
     * @param pro 判断的属性
     * @return 是否存在
     */
    public static boolean hasProperty(Object bean, String pro)
    {
        add(bean);
        Map<?, ?> map = simpleProperties(bean);
        return map.containsKey(pro);
    }
    
    /**
     * 判断自己定义的而非继承的属性pro是否存在
     * 
     * @param bean 判断的目标bean
     * @param pro 判断的属性
     * @return 是否存在
     */
    public static boolean hasDeclaredProperty(Object bean, String pro)
    {
        add(bean);
        Map<?, ?> map = simpleProperties(bean);
        BeanStruct st = (BeanStruct) map.get(pro);
        return !ArrayUtilbc.isEmptyObj(st) && st.isDeclared();
    }
    
    /**
     * 判断属性是否存在忽略大小写
     * 
     * @param bean 判断的目标bean
     * @param pro 判断的属性
     * @return 是否存在
     */
    public static boolean hasPropertyIgnoreCase(Object bean, String pro)
    {
        add(bean);
        Map<?, ?> map = simplePropertiesIgnore(bean);
        return map.containsKey(pro.toLowerCase());
    }
    
    /**
     * 使用自定义的过滤器
     * 
     * @param bean 判断的目标bean
     * @param pro 判断的属性
     * @param filter 自定义的属性过滤函数
     * @return 是否存在
     */
    public static boolean hasPropertyFilter(Object bean, String pro, PropertyFilter filter)
    {
        add(bean);
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = simpleProperties(bean);
        if (!ArrayUtilbc.isEmptyObj(map))
        {
            Set<String> set = map.keySet();
            for (String s : set)
            {
                if (pro.equals(filter.Properties(s)))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 获取对象的属性
     * 
     * @param bean 判断的目标bean
     * @param pro 判断的属性
     * @return 属性对应的值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getProperty(Object bean, String pro) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        return readMethod(bean, getReadMethod(bean, pro));
    }
    
    /**
     * 获取对象的属性
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @return 返回属性的值如果发生异常返回空
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getPropertyPeaceful(Object bean, String pro) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        return readMethod(bean, getReadMethod(bean, pro));
        
    }
    
    /**
     * 获取对象的属性(忽略属性名字大小写)
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @return 返回属性的值如果发生异常返回空
     */
    public static Object getPropertyIgnoreCase(Object bean, String pro) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        return readMethod(bean, getReadMethodIgnore(bean, pro));
    }
    
    /**
     * 获取对象的属性(忽略属性名字大小写)
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @return 返回属性的值如果发生异常返回空
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getPropertyIgnoreCasePeaceful(Object bean, String pro) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        return readMethod(bean, getReadMethodIgnore(bean, pro));
        
    }
    
    /**
     * 使用自定义的过滤器获取对象的属性获取对象的属性
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param filter 自定义的过滤函数
     * @return 返回属性的值如果发生异常返回空
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static Object getPropertyFilter(Object bean, String pro, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        Object result = null;
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = simpleProperties(bean);
        if (!ArrayUtilbc.isEmptyObj(map))
        {
            Set<String> set = map.keySet();
            for (String s : set)
            {
                if (pro.equals(filter.Properties(s)))
                {
                    result = readMethod(bean, getReadMethod(bean, s));
                }
            }
        }
        return result;
    }
    
    /**
     * 使用自定义的过滤器获取对象的属性
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param filter 自定义的过滤函数
     * @return 返回属性的值如果发生异常返回空
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static Object getPropertyFilterPeaceful(Object bean, String pro, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        Object result = null;
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = simpleProperties(bean);
        if (!ArrayUtilbc.isEmptyObj(map))
        {
            Set<String> set = map.keySet();
            
            for (String s : set)
            {
                if (pro.equals(filter.Properties(s)))
                {
                    result = readMethod(bean, getReadMethod(bean, s));
                }
            }
        }
        
        return result;
    }
    
    /**
     * 设置对象的属性
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setProperty(Object bean, String pro, Object value) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        writeMethod(bean, getWriteMethod(bean, pro), value);
    }
    
    /**
     * 设置对象的属性
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setPropertyPeaceful(Object bean, String pro, Object value) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        writeMethod(bean, getWriteMethod(bean, pro), value);
    }
    
    /**
     * 设置对象的属性忽略大小写
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setPropertyIgnoreCase(Object bean, String pro, Object value) throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
    }
    
    /**
     * 设置对象的属性忽略大小写
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setPropertyIgnoreCasePeaceful(Object bean, String pro, Object value)
            throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
    }
    
    /**
     * 使用自定义的filter进行属性设值
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @param filter 自定义的函数
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void setPropertyFilter(Object bean, String pro, Object value, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = simpleProperties(bean);
        if (!ArrayUtilbc.isEmptyObj(map))
        {
            Set<String> set = map.keySet();
            for (String s : set)
            {
                if (pro.equals(filter.Properties(s)))
                {
                    writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
                }
            }
            
        }
    }
    
    /**
     * 使用自定义的filter进行属性设值
     * 
     * @param bean 操作的Bean
     * @param pro 类型属性
     * @param value 设置属性的值
     * @param filter 自定义的函数
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void setPropertyFilterPeaceful(Object bean, String pro, Object value, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(bean);
        pro = filter.Properties(pro);
        Map<String, BeanStruct> map = simpleProperties(bean);
        if (!ArrayUtilbc.isEmptyObj(map))
        {
            Set<String> set = map.keySet();
            
            for (String s : set)
            {
                if (pro.equals(filter.Properties(s)))
                {
                    writeMethod(bean, getWriteMethodIgnore(bean, pro), value);
                }
            }
        }
    }
    
    /**
     * 拷贝对象指定的属性
     * 
     * @param srcBean 源Bean
     * @param destBean 目标Bean
     * @param pros copy的属性
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperty(Object srcBean, Object destBean, String[] pros) throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        if (!ArrayUtilbc.isEmptyObj(pros))
        {
            for (String s : pros)
            {
                Object value = readMethod(srcBean, getReadMethod(srcBean, s));
                writeMethod(destBean, getWriteMethod(destBean, s), value);
            }
        }
    }
    
    /**
     * 拷贝对象指定的属性
     * 
     * @param srcBean 源Bean
     * @param destBean 目标Bean
     * @param pros copy的属性
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyPropertyPeaceful(Object srcBean, Object destBean, String[] pros)
            throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        if (!ArrayUtilbc.isEmptyObj(pros))
        {
            for (String s : pros)
            {
                Object value = readMethod(srcBean, getReadMethod(srcBean, s));
                writeMethod(destBean, getWriteMethod(destBean, s), value);
            }
        }
    }
    
    /**
     * 复制同名属性
     * 
     * @param srcBean 源Bean
     * @param destBean 目标Bean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties(Object srcBean, Object destBean) throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simpleProperties(srcBean);
        Map<String, BeanStruct> dstMap = simpleProperties(destBean);
        
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
        for (Map.Entry<String, BeanStruct> entry : intersection.entrySet())
        {
            String key = entry.getKey();
            Object value = readMethod(srcBean, getReadMethod(srcBean, key));
            writeMethod(destBean, getWriteMethod(destBean, key), value);
        }
    }
    
    /**
     * 复制同名属性
     * 
     * @param srcBean 源Bean
     * @param destBean 目标Bean
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyPropertiesPeaceful(Object srcBean, Object destBean) throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simpleProperties(srcBean);
        Map<String, BeanStruct> dstMap = simpleProperties(destBean);
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
        for (Map.Entry<String, BeanStruct> entry : intersection.entrySet())
        {
            String key = entry.getKey();
            
            // 为什么会将try写在里面而不是foreach的外面？
            // 如果你想尽可能多的复制属性的话你可以
            Object value = readMethod(srcBean, getReadMethod(srcBean, key));
            writeMethod(destBean, getWriteMethod(destBean, key), value);
        }
    }
    
    /**
     * 复制同名属性(忽略大小写)
     * 
     * @param srcBean 原Bean
     * @param destBean 目标Bean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("rawtypes")
    public static void copyPropertiesIgnoreCase(Object srcBean, Object destBean) throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simplePropertiesIgnore(srcBean);
        Map<String, BeanStruct> dstMap = simplePropertiesIgnore(destBean);
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
        for (Map.Entry entry : intersection.entrySet())
        {
            String key = (String) entry.getKey();
            Object value = readMethod(srcBean, getReadMethodIgnore(srcBean, key));
            writeMethod(destBean, getWriteMethodIgnore(destBean, key), value);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：复制同名属性(忽略大小写) <br>
     * @see <br>
     * @param srcBean
     * @param destBean
     * @throws InvocationTargetException
     * @throws IllegalAccessException <br>
     * @void <br>
     * @methods nc.pub.itf.tools.Bean.BeanUtil#copyPropertiesIgnoreCasePeaceful <br>
     * @author licheng <br>
     * @date Created on 2019-8-22 <br>
     * @time 下午2:59:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    @SuppressWarnings("rawtypes")
    public static void copyPropertiesIgnoreCasePeaceful(Object srcBean, Object destBean)
            throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simplePropertiesIgnore(srcBean);
        Map<String, BeanStruct> dstMap = simplePropertiesIgnore(destBean);
        Map<String, BeanStruct> intersection = CollectionUtil.intersection(srcMap, dstMap);
        for (Map.Entry entry : intersection.entrySet())
        {
            String key = (String) entry.getKey();
            readMethod(srcBean, getReadMethodIgnore(srcBean, key));
        }
    }
    
    /**
     * 使用自定义的属性过滤函数
     * 
     * @param srcBean 原Bean
     * @param destBean 目标bean
     * @param filter 自定义的过滤函数
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties(Object srcBean, Object destBean, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simpleProperties(srcBean);
        Map<String, BeanStruct> dstMap = simpleProperties(destBean);
        if (!ArrayUtilbc.isEffective(srcMap, dstMap))
        {
            Map<String, String> srcMapFilter = new HashMap<String, String>();
            Map<String, String> dstMapFilter = new HashMap<String, String>();
            for (Map.Entry<String, BeanStruct> entry : srcMap.entrySet())
            {
                srcMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            for (Map.Entry<String, BeanStruct> entry : dstMap.entrySet())
            {
                dstMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            Map<String, String> intersection = CollectionUtil.intersection(srcMapFilter, dstMapFilter);
            if (!ArrayUtilbc.isEmptyObj(intersection))
            {
                for (Map.Entry<String, String> entry : intersection.entrySet())
                {
                    String key = entry.getKey();
                    String srcKey = srcMapFilter.get(key);
                    String dstKey = dstMapFilter.get(key);
                    Object value = readMethod(srcBean, getReadMethod(srcBean, srcKey));
                    writeMethod(destBean, getWriteMethod(destBean, dstKey), value);
                }
            }
        }
    }
    
    /**
     * 使用自定义的属性过滤函数
     * 
     * @param srcBean 原Bean
     * @param destBean 目标bean
     * @param filter 自定义的过滤函数
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void copyPropertiesPeaceful(Object srcBean, Object destBean, PropertyFilter filter)
            throws InvocationTargetException, IllegalAccessException
    {
        add(srcBean);
        add(destBean);
        Map<String, BeanStruct> srcMap = simpleProperties(srcBean);
        Map<String, BeanStruct> dstMap = simpleProperties(destBean);
        if (!ArrayUtilbc.isEffective(srcMap, dstMap))
        {
            Map<String, String> srcMapFilter = new HashMap<String, String>();
            Map<String, String> dstMapFilter = new HashMap<String, String>();
            for (Map.Entry<String, BeanStruct> entry : srcMap.entrySet())
            {
                srcMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            for (Map.Entry<String, BeanStruct> entry : dstMap.entrySet())
            {
                dstMapFilter.put(filter.Properties(entry.getKey()), entry.getKey());
            }
            Map<String, String> intersection = CollectionUtil.intersection(srcMapFilter, dstMapFilter);
            if (!ArrayUtilbc.isEmptyObj(intersection))
            {
                for (Map.Entry<String, String> entry : intersection.entrySet())
                {
                    String key = entry.getKey();
                    String srcKey = srcMapFilter.get(key);
                    String dstKey = dstMapFilter.get(key);
                    Object value = readMethod(srcBean, getReadMethod(srcBean, srcKey));
                    writeMethod(destBean, getWriteMethod(destBean, dstKey), value);
                }
            }
        }
    }
}
