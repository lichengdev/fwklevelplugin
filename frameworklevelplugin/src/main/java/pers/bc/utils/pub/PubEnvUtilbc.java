package pers.bc.utils.pub;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公共常用的
 * @qualiFild nc.puobj2.itf.tools.puobj2.PubEnvUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-10-31<br>
 * @version 1.0<br>
 */
public class PubEnvUtilbc extends ArrayUtilbc
{
    
    private static final long serialVersionUID = -8496983165267782313L;
    
    /**
     * *********************************************************** <br>
     * 说明：三元表达式,比较操作 <br>
     * @see <br>
     * @param object
     * @return <br>
     * @Object <br>
     * @methods pers.bc.utils.pub.PubEnvUtilbc#ternaryExpression <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-21 <br>
     * @time 22:03:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Object ternaryExpression(Object object)
    {
        return isEmptyObj(object) ? null : object;
    }
    
    public static Object nullOfDefValue(Object object, Object defaultValue)
    {
        return ((object != null) ? object : defaultValue);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 往一个对象中设置值
     * @param targetObj 目标对象
     * @param name 名称或数组下标
     * @param value 值 <br>
     * @see <br>
     * @void <br>
     * @methods pers.bc.utils.pub.PubEnvUtilbc#setObjValue <br>
     * @author licheng <br>
     * @date Created on 2019年3月24日 <br>
     * @time 下午7:58:17 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    @SuppressWarnings("unchecked")
    public static void setObjValue(Object targetObj, Object name, Object value)
    {
        // 如果是Map，用name作为key返回对应的value
        if ((targetObj instanceof Map) && (name instanceof String))
        {
            ((Map<Object, Object>) targetObj).put(name, value);
            return;
        }
        // 如果是List，用name作为索引，返回下标对应的对象
        if ((targetObj instanceof List) && (name instanceof Integer))
        {
            ((List<Object>) targetObj).set((Integer) name, value);
            return;
        }
        if ((targetObj instanceof List) && (name instanceof String))
        {
            throw new RuntimeException("List对象不可用String作为key进行写入");
        }
        // 如果目标对象是数组
        if (targetObj.getClass().isArray() && (name instanceof Integer))
        {
            Array.set(targetObj, (Integer) name, value);
            return;
        }
        if (targetObj.getClass().isArray() && (name instanceof String))
        {
            throw new RuntimeException("数组不可用String作为key进行写入");
        }
        
        throw new RuntimeException("无法写入" + targetObj.getClass().getName() + "对象的" + name + "字段");
    }
    
    /**
     * *********************************************************** <br>
     * 说明：“==”比较两个变量本身的值，即两个对象在内存中的首地址<br>
     * 当这两个地址相等时，判断结果为true，否则结果为false<br>
     * 两对象为null返回false<br>
     * 两个变量的内存地址不一样，也就是说它们指向的对象不 一样，<br>
     * @see <br>
     * @param obj1
     * @param obj2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.puobj2.PubEnvUtilbc#BeEqualTo <br>
     * @author licheng <br>
     * @date Created on 2019年11月23日 <br>
     * @time 下午10:15:18 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean equality(Object obj1, Object obj2)
    {
        if (isEmptyObj(obj1) || isEmptyObj(obj2)) return false;
        
        return obj1 == obj2;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： String 不区分大小写<br>
     * @see <br>
     * @param obj1
     * @param obj2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.PubEnvUtilbc#equalsIgnoreCase <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-14 <br>
     * @time 下午5:14:31 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean equalsIgnoreCase(String obj1, String obj2)
    {
        if (isEmptyObj(obj1)||isEmptyObj(obj2)) return false;
        return equals(obj1.toLowerCase(), obj2.toLowerCase());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 万能相等 equals，支持深度比较<br>
     * 判断某个特征值来判断两个对象是否“等价”，<br>
     * 当这两个对象等价时，判断结果为true，否则结果为false<br>
     * 两对象为null返回false<br>
     * <br>
     * 注意: (Object类中的equals方法是用来比较“地址”的，所以等于false;<br>
     * equals方法对于字符串来说是比较内容的，而对于非字符串来说是比较，其指向的对象是否相同的)<br>
     * @see <br>
     * @param obj1
     * @param obj2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.puobj2.PubEnvUtilbc#equals <br>
     * @author licheng <br>
     * @date Created on 2019年11月23日 <br>
     * @time 下午10:11:27 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean equals(Object obj1, Object obj2)
    {
        try
        {
            boolean eq;
            
            if (obj1 == obj2) //
            eq = true;
            else if (obj1 == null && obj2 != null) //
            eq = false;
            else if (obj2 == null && obj1 != null) //
            eq = false;
            else if ((obj1 instanceof Integer || obj2 instanceof Integer)
                && Integer.parseInt(obj1.toString()) == Integer.parseInt(obj2.toString())) //
            eq = true;
            else if ((obj1 instanceof Double || obj2 instanceof Double)
                && Double.parseDouble(obj1.toString()) == Double.parseDouble(obj2.toString())) //
            eq = true;
            else if ((obj1 instanceof Long || obj2 instanceof Long) && Long.parseLong(obj1.toString()) == Long.parseLong(obj2.toString())) //
            eq = true;
            else if ((obj1 instanceof Short || obj2 instanceof Short)
                && Short.parseShort(obj1.toString()) == Short.parseShort(obj2.toString())) //
            eq = true;
            else if ((obj1 instanceof Float || obj2 instanceof Float)
                && Float.parseFloat(obj1.toString()) == Float.parseFloat(obj2.toString())) //
            eq = true;
            else if ((obj1 instanceof String || obj2 instanceof String) && obj1.toString().equals(obj2.toString())) //
            eq = true;
            else if (obj1 instanceof Object[] && obj2 instanceof Object[]) //
            eq = Arrays.deepEquals((Object[]) obj1, (Object[]) obj2);
            else if (obj1 instanceof byte[] && obj2 instanceof byte[]) //
            eq = Arrays.equals((byte[]) obj1, (byte[]) obj2);
            else if (obj1 instanceof short[] && obj2 instanceof short[]) //
            eq = Arrays.equals((short[]) obj1, (short[]) obj2);
            else if (obj1 instanceof int[] && obj2 instanceof int[]) //
            eq = Arrays.equals((int[]) obj1, (int[]) obj2);
            else if (obj1 instanceof long[] && obj2 instanceof long[]) //
            eq = Arrays.equals((long[]) obj1, (long[]) obj2);
            else if (obj1 instanceof char[] && obj2 instanceof char[]) //
            eq = Arrays.equals((char[]) obj1, (char[]) obj2);
            else if (obj1 instanceof float[] && obj2 instanceof float[]) //
            eq = Arrays.equals((float[]) obj1, (float[]) obj2);
            else if (obj1 instanceof double[] && obj2 instanceof double[]) //
            eq = Arrays.equals((double[]) obj1, (double[]) obj2);
            else if (obj1 instanceof boolean[] && obj2 instanceof boolean[]) //
            eq = Arrays.equals((boolean[]) obj1, (boolean[]) obj2);
            else
                eq = false;
            
            return eq;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 包含某一个元素
     * @see
     * @param element
     * @param t
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:21:38
     * @version 1.0 <br>
     * @param <T> <br>
     */
    @SafeVarargs
    public static <T> boolean contains(Object element, T... t)
    {
        return Arrays.asList(t).contains(element);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 包含某一个子元素
     * @see
     * @param element
     * @param t
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-8-2
     * @time 上午10:21:38
     * @version 1.0 <br>
     * @param <T> <br>
     */
    @SafeVarargs
    public static <T> boolean containStr(String element, T... t)
    {
        List<T> ls = Arrays.asList(t);
        for (int i = 0, j = PubEnvUtilbc.getSize(ls); i < j; i++)
        {
            if (element.contains(StringUtilbc.valueOfNull(ls.get(i)))) return true;
        }
        
        return false;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： java 类型判断方法<br>
     * 和instanceof一样的功能<br>
     * @see
     * @param zlass
     * @param object
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.PubEnvUtilbc#isInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 下午11:23:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isInstance(Class<?> zlass, Object object)
    {
        return zlass.isInstance(object);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：java 类型判断方法: 自身类.class.isAssignableFrom(自身类或子类.class)<br>
     * 和instanceof一样的功能 <br>
     * @see
     * @param C1
     * @param C2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.PubEnvUtilbc#isAssignableFrom <br>
     * @author LiBencheng <br>
     * @date Created on 2020-4-28 <br>
     * @time 下午11:24:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isAssignableFrom(Class<?> C1, Class<?> C2)
    {
        return C1.isAssignableFrom(C2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 检查是否是数字<br>
     * @see <br>
     * @param value
     * @return <br>
     * @boolean <br>
     * @methods nc.puobj2.itf.tools.puobj2.PubOftenUtilbc#isNumber <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:30:47 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isNumber(String value)
    {
        String patternStr = "^\\d+$";
        Pattern p = Pattern.compile(patternStr, Pattern.CASE_INSENSITIVE); // 忽略大小写;
        Matcher m = p.matcher(value);
        return m.find();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断字符串是否是符合指定格式的时间
     * @see
     * @param date 时间字符串
     * @param format 时间格式
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:32:19
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isDate(String date, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            sdf.parse(date);
            return true;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取一个double类型的数字的小数位有多长 <br>
     * @see <br>
     * @param dd
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.puobj2.PubEnvUtilbc#doueleBitCount <br>
     * @author licheng <br>
     * @date Created on 2019年11月23日 <br>
     * @time 下午10:11:15 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int doueleBitCount(double dd)
    {
        String temp = String.valueOf(dd);
        int i = temp.indexOf(".");
        if (i > -1)
        {
            return temp.length() - i - 1;
        }
        return 0;
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param arr
     * @return <br>
     * @Integer[] <br>
     * @methods pers.bc.utils.puobj2.PubEnvUtilbc#doubleBitCount <br>
     * @author licheng <br>
     * @date Created on 2019年11月23日 <br>
     * @time 下午10:11:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Integer[] doubleBitCount(double[] arr)
    {
        Integer[] len = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            len[i] = doueleBitCount(arr[i]);
        }
        return len;
    }
    
}
