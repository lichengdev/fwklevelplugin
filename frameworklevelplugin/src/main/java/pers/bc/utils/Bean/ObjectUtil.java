package pers.bc.utils.Bean;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

/**
 * 万能对象操作
 * @qualiFild pers.bc.utils.Bean.ObjectUtil.java<br>
 * @author：李本城<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public class ObjectUtil
{
    
    public static final int ASC = 1;
    public static final int DESC = -1;
    
    /**
     * *********************************************************** <br>
     * 说明：从一个对象中获取值 如果对象是Map，则相当于调用get(Object) 如果对象是VO，则相当于执行对应的getter方法 如果name是整数，则相当于获取数组下标对应的对象 <br>
     * @see <br>
     * @param target
     * @param name
     * @return <br>
     * @Object <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#get <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:13:14 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object get(Object target, Object name)
    {
        // 如果是Map，用name作为key返回对应的value
        if ((target instanceof Map) && (name instanceof String))
        {
            return ((Map) target).get(name);
        }
        // 如果是List，用name作为索引，返回下标对应的对象
        if ((target instanceof List) && (name instanceof Integer))
        {
            return ((List) target).get((Integer) name);
        }
        if ((target instanceof List) && (name instanceof String))
        {
            throw new RuntimeException("List对象不可用String作为key进行访问");
        }
        // 如果目标对象是数组
        if (target.getClass().isArray() && (name instanceof Integer))
        {
            return Array.get(target, (Integer) name);
        }
        if (target.getClass().isArray() && (name instanceof String))
        {
            throw new RuntimeException("数组不可用String作为key进行访问");
        }
        // 如果是Json格式的键值对表，则将其转换为Map，再获取值
        if ((target instanceof String) && (target.toString().startsWith("{")) && (target.toString().endsWith("}")))
        {
            Map jsonObject = new Gson().fromJson(target.toString(), LinkedHashMap.class);
            return get(jsonObject, name);
        }
        // 如果是Json格式的数组，则将其转换为List，再获取值
        if ((target instanceof String) && (target.toString().startsWith("[")) && (target.toString().endsWith("]")))
        {
            List jsonObject = new Gson().fromJson(target.toString(), ArrayList.class);
            return get(jsonObject, name);
        }
        // 如果目标对象是CircularlyAccessibleValueObject（NC的VO）
        // if (target instanceof CircularlyAccessibleValueObject)
        // {
        // return ((CircularlyAccessibleValueObject) target).getAttributeValue((String) name);
        // }
        // 如果以上情况都未命中，则认为这个对象是VO
        if (name instanceof String)
        {
            String getName = NamingUtil.getNaming(name.toString());
            String getNameWithBoolean = NamingUtil.getNaming(name.toString(), true);
            
            if (ReflectionUtilbc.containsMethod(target, getName))
            {
                return ReflectionUtilbc.invoke(target, getName);
            }
            else if (ReflectionUtilbc.containsMethod(target, getNameWithBoolean))
            {
                return ReflectionUtilbc.invoke(target, getNameWithBoolean);
            }
            else
            {
                throw new RuntimeException(target.getClass().getName() + "不存在" + name + "属性对应的Getter");
            }
        }
        throw new RuntimeException("无法获取" + target.getClass().getName() + "对象的" + name + "字段");
    }
    
    /**
     * *********************************************************** <br>
     * 说明：往一个对象中设置值 <br>
     * @see <br>
     * @param target 目标对象
     * @param name 名称或数组下标
     * @param value 值<br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#set <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:14:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void set(Object target, Object name, Object value)
    {
        // 如果是Map，用name作为key返回对应的value
        if ((target instanceof Map) && (name instanceof String))
        {
            ((Map) target).put(name, value);
            return;
        }
        // 如果是List，用name作为索引，返回下标对应的对象
        if ((target instanceof List) && (name instanceof Integer))
        {
            ((List) target).set((Integer) name, value);
            return;
        }
        if ((target instanceof List) && (name instanceof String))
        {
            throw new RuntimeException("List对象不可用String作为key进行写入");
        }
        // 如果目标对象是数组
        if (target.getClass().isArray() && (name instanceof Integer))
        {
            Array.set(target, (Integer) name, value);
            return;
        }
        if (target.getClass().isArray() && (name instanceof String))
        {
            throw new RuntimeException("数组不可用String作为key进行写入");
        }
        // 如果目标对象是CircularlyAccessibleValueObject（NC的VO）
        // if (target instanceof CircularlyAccessibleValueObject)
        // {
        // ((CircularlyAccessibleValueObject) target).setAttributeValue((String) name, value);
        // return;
        // }
        // 如果以上情况都未命中，则认为这个对象是VO
        if (name instanceof String)
        {
            String setName = NamingUtil.setNaming(name.toString());
            String setNameWithBoolean = NamingUtil.setNaming(name.toString(), true);
            
            if (ReflectionUtilbc.containsMethod(target, setName, value))
            {
                ReflectionUtilbc.invoke(target, setName, value);
                return;
            }
            else if (ReflectionUtilbc.containsMethod(target, setNameWithBoolean, value))
            {
                ReflectionUtilbc.invoke(target, setNameWithBoolean, value);
                return;
            }
            else
            {
                throw new RuntimeException(target.getClass().getName() + "不存在" + name + "属性对应的Setter");
            }
        }
        throw new RuntimeException("无法写入" + target.getClass().getName() + "对象的" + name + "字段");
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 万能相等<br>
     * @see <br>
     * @param obj1 A对象
     * @param obj2 B对象
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#equals <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:26:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean equals(Object obj1, Object obj2)
    {
        return PubEnvUtilbc.equals(obj1, obj2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取数组的长度 管他是List还是Java数组还是Map，都能用 <br>
     * @see <br>
     * @param target
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#getSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:13:33 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSize(Object target)
    {
        return PubEnvUtilbc.getObjSize(target);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：找最大的 <br>
     * @see <br>
     * @param vos
     * @param fieldname
     * @return <br>
     * @Object <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#max <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:11:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object max(Object[] vos, String fieldname)
    {
        if ((vos == null) || (vos.length == 0)) return null;
        int maxindex = 0;
        for (int i = 1; i < vos.length; ++i)
        {
            Object o = BeanHelper.getProperty(vos[i], fieldname);
            if (o == null) continue;
            String c = o.toString();
            if (PubEnvUtilbc.isEmptyObj(BeanHelper.getProperty(vos[maxindex], fieldname)))
            {
                maxindex = i;
            }
            else
            {
                String s = "" + BeanHelper.getProperty(vos[maxindex], fieldname);
                Double dc = new Double(c);
                Double ds = new Double(s);
                
                if (dc.compareTo(ds) > 0) maxindex = i;
            }
        }
        return vos[maxindex];
    }
    
    /**
     * *********************************************************** <br>
     * 说明：将ObjectVO数组以某一字段排序 <br>
     * @see <br>
     * @param vos 数组
     * @param attributeName 排序字段名
     * @param isAscend 是否升序<br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#sortByAttributeName <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:11:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void sortByAttributeName(Object[] vos, final String attributeName, final boolean isAscend)
    {
        if (vos == null || attributeName == null) return;
        Comparator<Object> c = new Comparator<Object>()
        {
            public int compare(Object o1, Object o2)
            {
                
                Object v1 = BeanHelper.getProperty(o1, attributeName);
                Object v2 = BeanHelper.getProperty(o2, attributeName);
                if (v1 == null && v2 == null)
                {
                    return 0;
                }
                else if (v1 == null)
                {
                    // 默认null比所有值小
                    return -1;
                }
                else if (v2 == null)
                {
                    return 1;
                }
                int r = ((Comparable) v1).compareTo(v2);
                // int r = compareAttribute(v1, v2);
                if (isAscend) return r;
                if (r > 0) return -1;
                if (r < 0) return 1;
                
                return 0;
            }
        };
        Arrays.sort(vos, c);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param vos
     * @param fields 排序字段名数组<br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#ascSort <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:14:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void ascSort(Object[] vos, String[] fields)
    {
        if (vos == null) return;
        if ((fields == null) || (fields.length == 0)) return;
        int[] ascFlags = new int[fields.length];
        Arrays.fill(ascFlags, ASC);
        sort(vos, fields, ascFlags);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param vos
     * @param fields 排序字段名数组<br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#descSort <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:14:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void descSort(Object[] vos, String[] fields)
    {
        if (vos == null) return;
        if ((fields == null) || (fields.length == 0)) return;
        int[] ascFlags = new int[fields.length];
        Arrays.fill(ascFlags, DESC);
        sort(vos, fields, ascFlags);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param vos
     * @param fields
     * @param ascFlags <br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#sort <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:15:03 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void sort(Object[] vos, String[] fields, int[] ascFlags)
    {
        sort(vos, fields, ascFlags, false);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param vos
     * @param fields
     * @param ascFlags
     * @param nullLast <br>
     * @void <br>
     * @methods pers.bc.utils.Bean.ObjectUtil#sort <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-18 <br>
     * @time 下午3:15:09 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void sort(Object[] vos, String[] fields, int[] ascFlags, boolean nullLast)
    {
        if (vos == null) return;
        if ((fields == null) || (fields.length == 0)) return;
        if (ascFlags == null) throw new IllegalArgumentException("ObjectUtil.sort ascFlags cann't be null");
        if (fields.length != ascFlags.length)
        {
            throw new IllegalArgumentException("ObjectUtil.sort length of fields not equal with that of ascFlags");
        }
        for (int i = 0; i < ascFlags.length; ++i)
        {
            if ((ascFlags[i] != 1) && (ascFlags[i] != -1))
            {
                throw new IllegalArgumentException("ObjectUtil.sort Illegal Value of ascFlag i=" + i + " value= " + ascFlags[i]);
            }
        }
        Comparator<Object> c = new Comparator<Object>()
        {
            public int compare(Object vo1, Object vo2)
            {
                
                int Greater = 1;
                int Less = -1;
                int Equal = 0;
                
                for (int i = 0; i < fields.length; ++i)
                {
                    Object v1 = BeanHelper.getProperty(vo1, fields[i]);
                    Object v2 = BeanHelper.getProperty(vo2, fields[i]);
                    
                    if ((v1 == null) && (v2 == null)) continue;
                    if ((v1 == null) && (v2 != null))
                    {
                        if ((ascFlags[i] == 1) && (nullLast))
                        {
                            return (ascFlags[i] * Greater);
                        }
                        return (ascFlags[i] * Less);
                    }
                    if ((v1 != null) && (v2 == null))
                    {
                        if ((ascFlags[i] == 1) && (nullLast))
                        {
                            return (ascFlags[i] * Less);
                        }
                        return (ascFlags[i] * Greater);
                        
                    }
                    
                    Comparable c1 = null;
                    Comparable c2 = null;
                    
                    if ((v1 instanceof Comparable) && (v2 instanceof Comparable))
                    {
                        c1 = (Comparable) v1;
                        c2 = (Comparable) v2;
                    }
                    else
                    {
                        if ((v1 instanceof Double) && (v2 instanceof Double))
                        {
                            Double u1 = (Double) v1;
                            Double u2 = (Double) v2;
                            if (u1.compareTo(u2) == 0)
                            {
                                continue;
                            }
                            return (u1.compareTo(u2) * ascFlags[i]);
                        }
                        
                        c1 = "" + v1;
                        c2 = "" + v2;
                    }
                    
                    if (c1.compareTo(c2) != 0)
                    
                    {
                        return (c1.compareTo(c2) * ascFlags[i]);
                    }
                }
                return Equal;
            }
        };
        Arrays.sort(vos, c);
        
    }
}
