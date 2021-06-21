package pers.bc.utils.Bean;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 
 * @qualiFild pers.bc.utils.Bean.ClassUtils.java<br>
 * @author：licheng<br>
 * @date Created on 2019-12-19<br>
 * @version 1.0<br>
 */
public class ClassUtils
{
    
    public ClassUtils()
    {
        super();
    }
    
    // 包分割符
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    // 字符串类型的包分隔符
    public static final String PACKAGE_SEPARATOR = String.valueOf(PACKAGE_SEPARATOR_CHAR);
    // 内部类分割符
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    // 字符串类型内部类分隔符
    public static final String INNER_CLASS_SEPARATOR = String.valueOf(INNER_CLASS_SEPARATOR_CHAR);
    
    // 基本类型的Map集合
    private static Map primitiveWrapperMap = new HashMap();
    static
    {
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
    }
    
    // 用于交换的Map类型集合
    private static Map wrapperPrimitiveMap = new HashMap();
    static
    {
        for (Iterator it = primitiveWrapperMap.keySet().iterator(); it.hasNext();)
        {
            Class primitiveClass = (Class) it.next();
            Class wrapperClass = (Class) primitiveWrapperMap.get(primitiveClass);
            if (!primitiveClass.equals(wrapperClass))
            {
                wrapperPrimitiveMap.put(wrapperClass, primitiveClass);
            }
        }
    }
    // 用于存储类型名称简写的Map
    private static Map abbreviationMap = new HashMap();
    // 类名缩写的替换Map集合
    private static Map reverseAbbreviationMap = new HashMap();
    
    /**
     * 功能描述: 〈基本类型的类名缩写添加到Map当中〉
     * 
     * @params : [primitive, abbreviation]
     * @return : void
     * @author : cwl
     * @date : 2019/5/31 15:22
     */
    private static void addAbbreviation(String primitive, String abbreviation)
    {
        abbreviationMap.put(primitive, abbreviation);
        reverseAbbreviationMap.put(abbreviation, primitive);
    }
    
    /**
     * 功能描述: 〈将类型缩写对应的添加到Map当中〉
     * 
     * @params :
     * @return :
     * @author : cwl
     * @date : 2019/5/31 15:23
     */
    static
    {
        addAbbreviation("int", "I");
        addAbbreviation("boolean", "Z");
        addAbbreviation("float", "F");
        addAbbreviation("long", "J");
        addAbbreviation("short", "S");
        addAbbreviation("byte", "B");
        addAbbreviation("double", "D");
        addAbbreviation("char", "C");
    }
    
    /**
     * 功能描述: 〈获取object的名臣简写,valueIfNull为当object==null时返回的默认参数〉
     * 
     * @params : [object, valueIfNull]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:27
     */
    public static String getShortClassName(Object object, String valueIfNull)
    {
        if (object == null)
        {
            return valueIfNull;
        }
        return getShortClassName(object.getClass().getName());
    }
    
    /**
     * 功能描述: 〈根据cls对象获取它的简单类型名称〉
     * 
     * @params : [cls]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:32
     */
    public static String getShortClassName(Class cls)
    {
        if (cls == null)
        {
            return "";
        }
        return getShortClassName(cls.getName());
    }
    
    /**
     * 功能描述: 〈依据字符串className名称获取它的简写字符名称〉
     * 
     * @params : [className]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:33
     */
    private static String getShortClassName(String className)
    {
        if (className == null)
        {
            return "";
        }
        if (className.length() == 0)
        {
            return "";
        }
        
        int lastDotIdx = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        int innerIdx = className.indexOf(INNER_CLASS_SEPARATOR_CHAR, lastDotIdx == -1 ? 0 : lastDotIdx + 1);
        String out = className.substring(lastDotIdx + 1);
        if (innerIdx != -1)
        {
            out = out.replace(INNER_CLASS_SEPARATOR_CHAR, PACKAGE_SEPARATOR_CHAR);
        }
        return out;
    }
    
    /**
     * 功能描述: 〈获取object对象所在的包名称,valueIfNull是当object为null时返回的默认值〉
     * 
     * @params : [object, valueIfNull]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:34
     */
    public static String getPackageName(Object object, String valueIfNull)
    {
        if (object == null)
        {
            return valueIfNull;
        }
        return getPackageName(object.getClass().getName());
    }
    
    /**
     * 功能描述: 〈根据字节码对象cls获取该对象所在包名称〉
     * 
     * @params : [cls]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:35
     */
    public static String getPackageName(Class cls)
    {
        if (cls == null)
        {
            return "";
        }
        return getPackageName(cls.getName());
    }
    
    /**
     * 功能描述: 〈根据className获得它所在的报名〉
     * 
     * @params : [className]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 15:36
     */
    private static String getPackageName(String className)
    {
        if (className == null)
        {
            return "";
        }
        int i = className.lastIndexOf(PACKAGE_SEPARATOR_CHAR);
        if (i == -1)
        {
            return "";
        }
        return className.substring(0, i);
    }
    
    /**
     * 功能描述: 〈获取cls所有的父类对象〉
     * 
     * @params : [cls]
     * @return : java.util.List
     * @author : cwl
     * @date : 2019/5/31 15:38
     */
    public static List getAllSuperclasses(Class cls)
    {
        if (cls == null)
        {
            return null;
        }
        List classes = new ArrayList();
        Class superclass = cls.getSuperclass();
        while (superclass != null)
        {
            classes.add(superclass);
            superclass = superclass.getSuperclass();
        }
        return classes;
    }
    
    /**
     * 功能描述: 〈获取cls对象所有实现的接口〉
     * 
     * @params : [cls]
     * @return : java.util.List
     * @author : cwl
     * @date : 2019/5/31 15:39
     */
    public static List getAllInterfaces(Class cls)
    {
        if (cls == null)
        {
            return null;
        }
        List list = new ArrayList();
        while (cls != null)
        {
            Class[] interfaces = cls.getInterfaces();
            for (int i = 0; i < interfaces.length; i++)
            {
                if (list.contains(interfaces[i]) == false)
                {
                    list.add(interfaces[i]);
                }
                List superInterfaces = getAllInterfaces(interfaces[i]);
                for (Iterator it = superInterfaces.iterator(); it.hasNext();)
                {
                    Class intface = (Class) it.next();
                    if (list.contains(intface) == false)
                    {
                        list.add(intface);
                    }
                }
            }
            cls = cls.getSuperclass();
        }
        return list;
    }
    
    /**
     * 功能描述: 〈将类名转换成为Class对象,貌似也没啥用处〉
     * 
     * @params : [classNames]
     * @return : java.util.List<java.lang.Class>
     * @author : cwl
     * @date : 2019/5/31 15:52
     */
    public static List convertClassNamesToClasses(List<String> classNames)
    {
        if (classNames == null)
        {
            return null;
        }
        List classes = new ArrayList(classNames.size());
        for (Iterator it = classNames.iterator(); it.hasNext();)
        {
            String className = (String) it.next();
            try
            {
                classes.add(Class.forName(className));
            }
            catch (Exception ex)
            {
                classes.add(null);
            }
        }
        return classes;
    }
    
    /**
     * 功能描述: 〈classes集合转换成为全限定类名的集合〉
     * 
     * @params : [classes]
     * @return : java.util.List
     * @author : cwl
     * @date : 2019/5/31 16:01
     */
    public static List convertClassesToClassNames(List classes)
    {
        if (classes == null)
        {
            return null;
        }
        List classNames = new ArrayList(classes.size());
        for (Iterator it = classes.iterator(); it.hasNext();)
        {
            Class cls = (Class) it.next();
            if (cls == null)
            {
                classNames.add(null);
            }
            else
            {
                classNames.add(cls.getName());
            }
        }
        return classNames;
    }
    
    /**
     * 功能描述: 〈判断classArray数组能否转换为toClassArray〉
     * 
     * @params : [classArray, toClassArray]
     * @return : boolean
     * @author : cwl
     * @date : 2019/5/31 16:58
     */
    public static boolean isAssignable(Class[] classArray, Class[] toClassArray)
    {
        if ((classArray == null && toClassArray != null && toClassArray.length > 0)
            || (toClassArray == null && classArray != null && classArray.length > 0)
            || (classArray != null && toClassArray != null && classArray.length != toClassArray.length))
        {
            return false;
        }
        if (classArray == null)
        {
            classArray = new Class[0];
        }
        if (toClassArray == null)
        {
            toClassArray = new Class[0];
        }
        for (int i = 0; i < classArray.length; i++)
        {
            if (isAssignable(classArray[i], toClassArray[i]) == false)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 功能描述: 〈判断cls能否转换为toClass〉
     * 
     * @params : [cls, toClass]
     * @return : boolean
     * @author : cwl
     * @date : 2019/5/31 16:59
     */
    public static boolean isAssignable(Class cls, Class toClass)
    {
        if (toClass == null)
        {
            return false;
        }
        // have to check for null, as isAssignableFrom doesn't
        if (cls == null)
        {
            return !(toClass.isPrimitive());
        }
        if (cls.equals(toClass))
        {
            return true;
        }
        if (cls.isPrimitive())
        {
            if (toClass.isPrimitive() == false)
            {
                return false;
            }
            if (Integer.TYPE.equals(cls))
            {
                return Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Long.TYPE.equals(cls))
            {
                return Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            if (Boolean.TYPE.equals(cls))
            {
                return false;
            }
            if (Double.TYPE.equals(cls))
            {
                return false;
            }
            if (Float.TYPE.equals(cls))
            {
                return Double.TYPE.equals(toClass);
            }
            if (Character.TYPE.equals(cls))
            {
                return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass)
                    || Double.TYPE.equals(toClass);
            }
            if (Short.TYPE.equals(cls))
            {
                return Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass) || Float.TYPE.equals(toClass)
                    || Double.TYPE.equals(toClass);
            }
            if (Byte.TYPE.equals(cls))
            {
                return Short.TYPE.equals(toClass) || Integer.TYPE.equals(toClass) || Long.TYPE.equals(toClass)
                    || Float.TYPE.equals(toClass) || Double.TYPE.equals(toClass);
            }
            // should never get here
            return false;
        }
        return toClass.isAssignableFrom(cls);
    }
    
    /**
     * 功能描述: 〈类名转全限定类名〉
     * 
     * @params : [cls]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:07
     */
    public static Class primitiveToWrapper(Class cls)
    {
        Class convertedClass = cls;
        if (cls != null && cls.isPrimitive())
        {
            convertedClass = (Class) primitiveWrapperMap.get(cls);
        }
        return convertedClass;
    }
    
    /**
     * 功能描述: 〈类名数组转全限定类名数组〉
     * 
     * @params : [classes]
     * @return : java.lang.Class[]
     * @author : cwl
     * @date : 2019/5/31 17:08
     */
    public static Class[] primitivesToWrappers(Class[] classes)
    {
        if (classes == null)
        {
            return null;
        }
        
        if (classes.length == 0)
        {
            return classes;
        }
        
        Class[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; i++)
        {
            convertedClasses[i] = primitiveToWrapper(classes[i]);
        }
        return convertedClasses;
    }
    
    /**
     * 功能描述: 〈全限定类名转类名〉
     * 
     * @params : [cls]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:45
     */
    public static Class wrapperToPrimitive(Class cls)
    {
        return (Class) wrapperPrimitiveMap.get(cls);
    }
    
    /**
     * 功能描述: 〈全限定类名数组转类名数组〉
     * 
     * @params : [classes]
     * @return : java.lang.Class[]
     * @author : cwl
     * @date : 2019/5/31 17:45
     */
    public static Class[] wrappersToPrimitives(Class[] classes)
    {
        if (classes == null)
        {
            return null;
        }
        
        if (classes.length == 0)
        {
            return classes;
        }
        
        Class[] convertedClasses = new Class[classes.length];
        for (int i = 0; i < classes.length; i++)
        {
            convertedClasses[i] = wrapperToPrimitive(classes[i]);
        }
        return convertedClasses;
    }
    
    /**
     * 功能描述: 〈是否是内部类〉
     * 
     * @params : [cls]
     * @return : boolean
     * @author : cwl
     * @date : 2019/5/31 17:45
     */
    public static boolean isInnerClass(Class cls)
    {
        if (cls == null)
        {
            return false;
        }
        return cls.getName().indexOf(INNER_CLASS_SEPARATOR_CHAR) >= 0;
    }
    
    /**
     * 功能描述: 〈从类加载器当中获取字节码对象〉
     * 
     * @params : [classLoader, className, initialize]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:46
     */
    public static Class getClass(ClassLoader classLoader, String className, boolean initialize) throws ClassNotFoundException
    {
        Class clazz;
        if (abbreviationMap.containsKey(className))
        {
            String clsName = "[" + abbreviationMap.get(className);
            clazz = Class.forName(clsName, initialize, classLoader).getComponentType();
        }
        else
        {
            clazz = Class.forName(toCanonicalName(className), initialize, classLoader);
        }
        return clazz;
    }
    
    /**
     * 功能描述: 〈从类加载器当中获取字节码对象〉
     * 
     * @params : [classLoader, className]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:47
     */
    public static Class getClass(ClassLoader classLoader, String className) throws ClassNotFoundException
    {
        return getClass(classLoader, className, true);
    }
    
    /**
     * 功能描述: 〈根据className获取字节码对象〉
     * 
     * @params : [className]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:47
     */
    private static Class getClass(String className) throws ClassNotFoundException
    {
        return getClass(className, true);
    }
    
    /**
     * 功能描述: 〈根据className获取字节码对象〉
     * 
     * @params : [className, initialize]
     * @return : java.lang.Class
     * @author : cwl
     * @date : 2019/5/31 17:48
     */
    private static Class getClass(String className, boolean initialize) throws ClassNotFoundException
    {
        ClassLoader contextCL = Thread.currentThread().getContextClassLoader();
        ClassLoader loader = contextCL == null ? ClassUtils.class.getClassLoader() : contextCL;
        return getClass(loader, className, initialize);
    }
    
    /**
     * 功能描述: 〈获取cls的公共方法〉
     * 
     * @params : [cls, methodName, parameterTypes]
     * @return : java.lang.reflect.Method
     * @author : cwl
     * @date : 2019/5/31 17:49
     */
    public static Method getPublicMethod(Class cls, String methodName, Class parameterTypes[]) throws SecurityException,
            NoSuchMethodException
    {
        
        Method declaredMethod = cls.getMethod(methodName, parameterTypes);
        if (Modifier.isPublic(declaredMethod.getDeclaringClass().getModifiers()))
        {
            return declaredMethod;
        }
        
        List candidateClasses = new ArrayList();
        candidateClasses.addAll(getAllInterfaces(cls));
        candidateClasses.addAll(getAllSuperclasses(cls));
        
        for (Iterator it = candidateClasses.iterator(); it.hasNext();)
        {
            Class candidateClass = (Class) it.next();
            if (!Modifier.isPublic(candidateClass.getModifiers()))
            {
                continue;
            }
            Method candidateMethod;
            try
            {
                candidateMethod = candidateClass.getMethod(methodName, parameterTypes);
            }
            catch (NoSuchMethodException ex)
            {
                continue;
            }
            if (Modifier.isPublic(candidateMethod.getDeclaringClass().getModifiers()))
            {
                return candidateMethod;
            }
        }
        
        throw new NoSuchMethodException("Can't find a public method for " + methodName);
    }
    
    /**
     * 功能描述: 〈转换成JSL规范的类名〉
     * 
     * @params : [className]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 17:52
     */
    private static String toCanonicalName(String className)
    {
        className = StringUtils.deleteWhitespace(className);
        if (className == null)
        {
            throw new NullPointerException("className is null");
        }
        else if (className.endsWith("[]"))
        {
            StringBuffer classNameBuffer = new StringBuffer();
            while (className.endsWith("[]"))
            {
                className = className.substring(0, className.length() - 2);
                classNameBuffer.append("[");
            }
            String abbreviation = (String) abbreviationMap.get(className);
            if (abbreviation != null)
            {
                classNameBuffer.append(abbreviation);
            }
            else
            {
                classNameBuffer.append("L").append(className).append(";");
            }
            className = classNameBuffer.toString();
        }
        return className;
    }
    
    /**
     * 功能描述: 〈Object类型的数组array转换成它对应的字节码对象〉
     * 
     * @params : [array]
     * @return : java.lang.Class[]
     * @author : cwl
     * @date : 2019/5/31 17:53
     */
    public static Class[] toClass(Object[] array)
    {
        if (array == null)
        {
            return null;
        }
        else if (array.length == 0)
        {
            return new Class[0];
        }
        Class[] classes = new Class[array.length];
        for (int i = 0; i < array.length; i++)
        {
            classes[i] = array[i].getClass();
        }
        return classes;
    }
    
    /**
     * 功能描述: 〈获取object对象的简称,valueIfNull为当object为null时的返回值〉
     * 
     * @params : [object, valueIfNull]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 17:53
     */
    public static String getShortCanonicalName(Object object, String valueIfNull)
    {
        if (object == null)
        {
            return valueIfNull;
        }
        return getShortCanonicalName(object.getClass().getName());
    }
    
    /**
     * 功能描述: 〈获取cls字节码对象的简称〉
     * 
     * @params : [cls]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 17:55
     */
    public static String getShortCanonicalName(Class cls)
    {
        if (cls == null)
        {
            return "";
        }
        return getShortCanonicalName(cls.getName());
    }
    
    /**
     * 功能描述: 〈获取canonicalName的简称〉
     * 
     * @params : [canonicalName]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 17:55
     */
    public static String getShortCanonicalName(String canonicalName)
    {
        return ClassUtils.getShortClassName(getCanonicalName(canonicalName));
    }
    
    /**
     * 功能描述: 〈获取canonicalName的简称〉
     * 
     * @params : [className]
     * @return : java.lang.String
     * @author : cwl
     * @date : 2019/5/31 17:58
     */
    private static String getCanonicalName(String className)
    {
        className = StringUtils.deleteWhitespace(className);
        if (className == null)
        {
            return null;
        }
        else
        {
            int dim = 0;
            while (className.startsWith("["))
            {
                dim++;
                className = className.substring(1);
            }
            if (dim < 1)
            {
                return className;
            }
            else
            {
                if (className.startsWith("L"))
                {
                    className = className.substring(1, className.endsWith(";") ? className.length() - 1 : className.length());
                }
                else
                {
                    if (className.length() > 0)
                    {
                        className = (String) reverseAbbreviationMap.get(className.substring(0, 1));
                    }
                }
                StringBuffer canonicalClassNameBuffer = new StringBuffer(className);
                for (int i = 0; i < dim; i++)
                {
                    canonicalClassNameBuffer.append("[]");
                }
                return canonicalClassNameBuffer.toString();
            }
        }
    }
}
