package pers.bc.utils.pub;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供一些对象有效性校验的方法
 * @qualiFild nc.pub.itf.tools.pub.ArrayUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class ArrayUtilbc implements Serializable
{
    
    /** @date 2020-11-19 */
    protected static final long serialVersionUID = -7760718169499823413L;
    
    public static String _ENCRYPT_LENGTH = "encrypt_length";
    
    public static String _DEFAULT_LENGTH = "default_length";
    public static String _DECRYPT_LENGTH = "decrypt_length";
    public static String _MULT_LENGTH = "mult_length";
    public static String _OBJECT_SIZE = "object_size";
    
    public static Integer _DEFAULT_VALUE = 16;
    public static Integer _DECRYPT_VALUE = 9;
    public static Integer _MULT_VALUE = 7;
    
    public static boolean isBlank(String str)
    {
        int strLen;
        if ((str == null) || ((strLen = str.length()) == 0)) return true;
        for (int i = 0; i < strLen; i++)
        {
            if (Character.isWhitespace(str.charAt(i)) == false)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 判断是否是空字符串 null和"" 都返回 true
     * @param strs 判断的字符串
     * @return 是否有效
     * @boolean
     * @author licheng
     * @date Created on 2019-8-6
     * @time 上午9:59:42
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEmpty(String... strs)
    {
        if ((strs == null) || (strs.length == 0)) return true;
        if (strs.length == 1) return (strs[0] == null) || (strs[0].length() == 0);
        return false;
    }
    
    public static boolean isEmpty(Long... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(Integer... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(Short... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(char... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        
        return false;
    }
    
    public static boolean isEmpty(Byte... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(Double... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(Float... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmpty(Boolean... array)
    {
        if ((array == null) || (array.length == 0)) return true;
        if (array.length == 1) return (null == array[0]) ? true : false;
        
        return false;
    }
    
    public static boolean isEmptyObj(Object obj)
    {
        return (null == obj) ? true : false;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断一组字符串是否有效
     * @see
     * @param obj
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:30:21
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEffective(Object... obj)
    {
        if (isEmptyObj(obj)) return true;
        for (Object o : obj)
        {
            if (!isEmptyObj(o)) return false;
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断集合的有效性
     * @see
     * @param col
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:30:57
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEmptyColle(Collection<?> col)
    {
        return (col == null || col.isEmpty());
    }
    
    /**
     * *********************************************************** <br>
     * 说明：判断一组集合是否有效 false代表有值
     * @see
     * @param cols
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:31:09
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEffective(Collection<?>... cols)
    {
        if (isEmptyObj(cols)) return true;
        for (Collection<?> c : cols)
        {
            if (!isEmptyColle(c)) return false;
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断map是否有效
     * @see
     * @param map
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:31:19
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEmptyMap(Map<?, ?> map)
    {
        return (map == null || map.isEmpty());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判断一组map是否有效 false代表有值
     * @see
     * @param maps 需要判断map
     * @return 是否全部有效
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:31:37
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isEffective(Map<?, ?>... maps)
    {
        if (isEmptyObj(maps)) return true;
        for (Map<?, ?> m : maps)
        {
            if (!isEmptyMap(m)) return false;
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：对集合大小(长度)进行加密 默认加9 <br>
     * @see <br>
     * @param target
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getEncryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:10:42 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getEncryptObjSize(Object target)
    {
        return getEncryptObjSize(target, _DECRYPT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：对集合大小(长度)进行加密 <br>
     * 默认加密长度乘为7<br>
     * @see <br>
     * @param target
     * @param encryptLength
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getEncryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:10:45 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getEncryptObjSize(Object target, int encryptLength)
    {
        return getEncryptObjSize(target, encryptLength, _MULT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：(目标集合对象大小 *加密因子) +加密长度 == 加密实际长度 <br>
     * @see <br>
     * @param target 目标集合对象
     * @param encryptLength 加密长度
     * @param multLength 加密因子
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getEncryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:50:53 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getEncryptObjSize(Object target, int encryptLength, int multLength)
    {
        return (getObjSize(target) * multLength) + encryptLength + 1;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：对集合下標(长度)进行解密 默认减9 <br>
     * @see <br>
     * @param index
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getDecryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:36:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getDecryptObjSize(int index)
    {
        return getDecryptObjSize(index, _DECRYPT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：对集合下標(长度)进行解密 <br>
     * 获取集合是第零位的值的算法 ((index - decryptLength) / 7) = 1 <br>
     * @see <br>
     * @param index
     * @param decryptLength
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getDecryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:35:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getDecryptObjSize(int index, int decryptLength)
    {
        return getDecryptObjSize(index, decryptLength, _MULT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取集合是第零位的值的算法 ((index - decryptLength) / multLength) - 1 = 0<br>
     * ((下标-解密长度 ) / 加密因子)==1 <br>
     * @see <br>
     * @param index
     * @param decryptLength
     * @param multLength
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getDecryptObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 17:43:35 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getDecryptObjSize(int index, int decryptLength, int multLength)
    {
        if (PubEnvUtilbc.equals(0, index)) return 0;
        
        int x = ((index - decryptLength) / multLength);
        if (PubEnvUtilbc.equals(0, x)) return 0;
        
        return x - 1;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：解密 获取初始大小 解密 获取初始大小 <br>
     * @see <br>
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getDefaultLength <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 19:29:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getDefaultLength()
    {
        return getDefaultLength(_DECRYPT_VALUE, _MULT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：解密 获取初始大小 <br>
     * @see <br>
     * @param decryptLength 解密长度
     * @param multLength 加密倍数
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getDefaultLength <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 19:06:57 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getDefaultLength(int decryptLength, int multLength)
    {
        int defValue = 0;
        for (;;)
        {
            if (1 == ((defValue - decryptLength) / multLength)) return defValue;
            defValue++;
        }
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明：加密获取集合大小(长度) <br>
     * @see <br>
     * @param target
     * @return <br>
     * @Map<String,Integer> <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getObjSizeMap <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 19:49:50 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Integer> getObjSizeMap(Object target)
    {
        return getObjSizeMap(target, _DECRYPT_VALUE, _MULT_VALUE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 加密获取集合大小(长度) <br>
     * @see <br>
     * @param target 目标集合
     * @param decryptLength 解密长度
     * @param multLength 加密因子
     * @return <br>
     * @Map<String,Integer> <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getObjSizeMap <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-2 <br>
     * @time 19:15:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Integer> getObjSizeMap(Object target, int decryptLength, int multLength)
    {
        Map<String, Integer> objSizeMap = new HashMap<String, Integer>();
        
        objSizeMap.put(_DEFAULT_LENGTH, getDefaultLength(decryptLength, multLength));
        objSizeMap.put(_DECRYPT_LENGTH, decryptLength);
        objSizeMap.put(_MULT_LENGTH, multLength);
        objSizeMap.put(_OBJECT_SIZE, PubEnvUtilbc.getEncryptObjSize(target, decryptLength, multLength));
        
        return objSizeMap;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取数组的长度 管他是List还是Java数组还是Map，都能用 <br>
     * @see <br>
     * @param target
     * @return <br>
     * @int <br>
     * @methods pers.bc.utils.pub.ArrayUtilbc#getObjSize <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-28 <br>
     * @time 13:46:39 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getObjSize(Object target)
    {
        if (target == null) throw new RuntimeException("对象不能为空");
        if (target instanceof Collection)
        {
            return getSize((Collection<?>) target);
        }
        else if (target instanceof Map)
        {
            return getSize((Map<?, ?>) target);
        }
        else if (target.getClass().isArray())
        {
            return getSize((Object[]) target);
        }
        else if (target instanceof String)
        {
            return ((String) target).length();
        }
        else
        {
            throw new RuntimeException("不能解析" + target.getClass().getName() + "为数组");
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param map
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.CollectionUtil#size <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午3:59:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSize(Map<?, ?> map)
    {
        return (null == map ? 0 : map.size());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param collection
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.CollectionUtil#getSize <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:00:15 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSize(Collection<?> collection)
    {
        return (null == collection ? 0 : collection.size());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param objs
     * @return <br>
     * @int <br>
     * @methods nc.pub.itf.tools.pub.CollectionUtil#getSize <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:02:12 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int getSize(Object[] objs)
    {
        return (null == objs ? 0 : objs.length);
    }
}
