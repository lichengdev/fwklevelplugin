package pers.bc.utils.Bean;

/**
 * 命名工具
 * @qualiFild pers.bc.utils.Bean.NamingUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public class NamingUtil
{
    /**
     * 首字母小写
     * 
     * @param function 名称
     * @return
     */
    public static String firstCharLowerCase(String function)
    {
        if (function.length() >= 1) function = function.substring(0, 1).toLowerCase() + function.substring(1);
        return function;
    }
    
    /**
     * 首字母大写
     * 
     * @param function 名称
     * @return
     */
    public static String firstCharUpperCase(String function)
    {
        if (function.length() >= 1) function = function.substring(0, 1).toUpperCase() + function.substring(1);
        return function;
    }
    
    /**
     * 获取Setter方法名
     * @param name 字段名称
     * @return
     */
    public static String setNaming(String name)
    {
        return setNaming(name, false);
    }
    
    /**
     * 获取Getter方法名
     * @param name 字段名称
     * @return
     */
    public static String getNaming(String name)
    {
        return getNaming(name, false);
    }
    
    /**
     * 获取Setter方法名
     * @param name 字段名称
     * @param useBoolean 是否是布尔类型
     * @return
     */
    public static String setNaming(String name, boolean useBoolean)
    {
        int length = name.length();
        if (length >= 2 && Character.isLowerCase(name.charAt(0)) && Character.isUpperCase(name.charAt(1))
            && (!name.toLowerCase().startsWith("is")))
        {
            return "set" + name;
        }
        if (length >= 3 && (name.startsWith("is")) && Character.isUpperCase(name.charAt(2)) && useBoolean)
        {
            return "set" + firstCharUpperCase(name.substring(2));
        }
        if (useBoolean)
        {
            return "set" + firstCharUpperCase(name);
        }
        return "set" + firstCharUpperCase(name);
    }
    
    /**
     * 获取Getter方法名
     * @param name 属性
     * @param useBoolean 属性是否是布尔类型
     * @return
     */
    public static String getNaming(String name, boolean useBoolean)
    {
        int length = name.length();
        if (length >= 2 && Character.isLowerCase(name.charAt(0)) && Character.isUpperCase(name.charAt(1))
            && (!name.toLowerCase().startsWith("is")))
        {
            return "get" + name;
        }
        if (length >= 3 && (name.startsWith("is")) && Character.isUpperCase(name.charAt(2)) && useBoolean)
        {
            return name;
        }
        if (useBoolean)
        {
            return "is" + firstCharUpperCase(name);
        }
        return "get" + firstCharUpperCase(name);
    }
}
