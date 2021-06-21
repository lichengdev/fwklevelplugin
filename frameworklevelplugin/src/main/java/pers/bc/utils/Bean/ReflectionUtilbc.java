package pers.bc.utils.Bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import pers.bc.utils.pub.StringUtilbc;

/**
 * 反射工具类 <br />
 * <br />
 * 提供了一系列的获取某一个类的信息的方法<br />
 * 包括获取全类名，实现的接口，接口的泛型等<br />
 * 并且提供了根据Class类型获取对应的实例对象的方法，以及修改属性和调用对象的方法等
 * @qualiFild nc.pub.itf.tools.Bean.ReflectionUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class ReflectionUtilbc
{
    
    /**
     * 批量获取对象的类型
     * @param objs
     * @return
     */
    private static Class[] getClz(Object... objs)
    {
        Class[] paramsClz = new Class[objs.length];
        for (int i = 0; i < objs.length; i++)
            paramsClz[i] = objs[i].getClass();
        return paramsClz;
    }
    
    public static Object newInstance(String clz, Object... params)
    {
        try
        {
            return newInstance(Class.forName(clz), params);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException("无法实例化类" + clz);
        }
    }
    
    /**
     * 新建对象
     * @param clz 类
     * @param params 构造方法参数
     * @return
     */
    public static <T> T newInstance(Class<T> clz, Object... params)
    {
        Class[] paramsClz = getClz(params);
        try
        {
            Constructor constructor = clz.getConstructor(paramsClz);
            return (T) constructor.newInstance(params);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 判断一个方法是否存在
     * @param target 目标对象
     * @param methodName 方法名称
     * @param params 方法参数列表
     * @return
     */
    public static boolean containsMethod(Object target, String methodName, Object... params)
    {
        Class targetClz = target.getClass();
        Class[] paramsClz = getClz(params);
        try
        {
            Method method = targetClz.getMethod(methodName, paramsClz);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /**
     * 执行方法
     * @param target 目标对象
     * @param method 方法名
     * @param params 参数列表
     * @return
     */
    public static Object invoke(Object target, String method, Object... params)
    {
        Class targetClz = target.getClass();
        Class[] paramsClz = getClz(params);
        try
        {
            Method targetMethod = targetClz.getMethod(method, paramsClz);
            return targetMethod.invoke(target, params);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 获取包名
     * 
     * @return 包名【String类型】
     */
    public static String getPackage(Class<?> clazz)
    {
        Package pck = clazz.getPackage();
        if (null != pck)
        {
            return pck.getName();
        }
        else
        {
            return "没有包！";
        }
    }
    
    /**
     * 获取继承的父类的全类名
     * 
     * @return 继承的父类的全类名【String类型】
     */
    public static String getSuperClassName(Class<?> clazz)
    {
        Class<?> superClass = clazz.getSuperclass();
        if (null != superClass)
        {
            return superClass.getName();
        }
        else
        {
            return "没有父类！";
        }
    }
    
    /**
     * 获取全类名
     * 
     * @return 全类名【String类型】
     */
    public static String getClassName(Class<?> clazz)
    {
        return clazz.getName();
    }
    
    /**
     * 获取实现的接口名
     * 
     * @return 所有的接口名【每一个接口名的类型为String，最后保存到一个List集合中】
     */
    public static List<String> getInterfaces(Class<?> clazz)
    {
        Class<?>[] interfaces = clazz.getInterfaces();
        int len = interfaces.length;
        
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < len; i++)
        {
            Class<?> itfc = interfaces[i];
            
            // 接口名
            String interfaceName = itfc.getSimpleName();
            
            list.add(interfaceName);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有属性
     * 
     * @return 所有的属性【每一个属性添加到StringBuilder中，最后保存到一个List集合中】
     * @List<StringBuilder>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:31:41
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<StringBuilder> getFields(Class<?> clazz)
    {
        Field[] fields = clazz.getDeclaredFields();
        int len = fields.length;
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder sb = null;
        for (int i = 0; i < len; i++)
        {
            Field field = fields[i];
            sb = new StringBuilder();
            
            // 修饰符
            String modifier = Modifier.toString(field.getModifiers());
            sb.append(modifier + " ");
            
            // 数据类型
            Class<?> type = field.getType();
            String typeName = type.getSimpleName();
            sb.append(typeName + " ");
            
            // 属性名
            String fieldName = field.getName();
            sb.append(fieldName + ";");
            
            list.add(sb);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有公共的属性
     * 
     * @return 所有公共的属性【每一个属性添加到StringBuilder中，最后保存到一个List集合中】
     * @List<StringBuilder>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:31:51
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<StringBuilder> getPublicFields(Class<?> clazz)
    {
        Field[] fields = clazz.getFields();
        int len = fields.length;
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder sb = null;
        for (int i = 0; i < len; i++)
        {
            Field field = fields[i];
            sb = new StringBuilder();
            
            // 修饰符
            String modifier = Modifier.toString(field.getModifiers());
            sb.append(modifier + " ");
            
            // 数据类型
            Class<?> type = field.getType();
            String typeName = type.getSimpleName();
            sb.append(typeName + " ");
            
            // 属性名
            String fieldName = field.getName();
            sb.append(fieldName + ";");
            
            list.add(sb);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有构造方法
     * 
     * @return 所有的构造方法【每一个构造方法添加到StringBuilder中，最后保存到一个List集合中】
     * @List<StringBuilder>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:32:02
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<StringBuilder> getConstructors(Class<?> clazz)
    {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        int len = constructors.length;
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder sb = null;
        for (int i = 0; i < len; i++)
        {
            Constructor<?> constructor = constructors[i];
            sb = new StringBuilder();
            
            // 修饰符
            String modifier = Modifier.toString(constructor.getModifiers());
            sb.append(modifier + " ");
            
            // 方法名（类名）
            String constructorName = clazz.getSimpleName();
            sb.append(constructorName + " (");
            
            // 形参列表
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            int length = parameterTypes.length;
            for (int j = 0; j < length; j++)
            {
                Class<?> parameterType = parameterTypes[j];
                
                String parameterTypeName = parameterType.getSimpleName();
                
                if (j < length - 1)
                {
                    sb.append(parameterTypeName + ", ");
                }
                else
                {
                    sb.append(parameterTypeName);
                }
                
            }
            
            sb.append(") {}");
            
            list.add(sb);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有自身的方法
     * 
     * @return 所有自身的方法【每一个方法添加到StringBuilder中，最后保存到一个List集合中】
     * @List<StringBuilder>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:32:16
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<StringBuilder> getMethods(Class<?> clazz)
    {
        Method[] methods = clazz.getDeclaredMethods();
        int len = methods.length;
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder sb = null;
        for (int i = 0; i < len; i++)
        {
            Method method = methods[i];
            sb = new StringBuilder();
            
            // 修饰符
            String modifier = Modifier.toString(method.getModifiers());
            sb.append(modifier + " ");
            
            // 返回值类型
            Class<?> returnClass = method.getReturnType();
            String returnType = returnClass.getSimpleName();
            sb.append(returnType + " ");
            
            // 方法名
            String methodName = method.getName();
            sb.append(methodName + " (");
            
            // 形参列表
            Class<?>[] parameterTypes = method.getParameterTypes();
            int length = parameterTypes.length;
            
            for (int j = 0; j < length; j++)
            {
                Class<?> parameterType = parameterTypes[j];
                
                // 形参类型
                String parameterTypeName = parameterType.getSimpleName();
                
                if (j < length - 1)
                {
                    sb.append(parameterTypeName + ", ");
                }
                else
                {
                    sb.append(parameterTypeName);
                }
                
            }
            
            sb.append(") {}");
            
            list.add(sb);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有公共的方法
     * 
     * @return 所有公共的方法【每一个方法添加到StringBuilder中，最后保存到一个List集合中】
     * @List<StringBuilder>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:32:29
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<StringBuilder> getPublicMethods(Class<?> clazz)
    {
        Method[] methods = clazz.getMethods();
        int len = methods.length;
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        StringBuilder sb = null;
        for (int i = 0; i < len; i++)
        {
            Method method = methods[i];
            sb = new StringBuilder();
            
            // 修饰符
            String modifier = Modifier.toString(method.getModifiers());
            sb.append(modifier + " ");
            
            // 返回值类型
            Class<?> returnClass = method.getReturnType();
            String returnType = returnClass.getSimpleName();
            sb.append(returnType + " ");
            
            // 方法名
            String methodName = method.getName();
            sb.append(methodName + " (");
            
            // 形参列表
            Class<?>[] parameterTypes = method.getParameterTypes();
            int length = parameterTypes.length;
            
            for (int j = 0; j < length; j++)
            {
                Class<?> parameterType = parameterTypes[j];
                
                // 形参类型
                String parameterTypeName = parameterType.getSimpleName();
                
                if (j < length - 1)
                {
                    sb.append(parameterTypeName + ", ");
                }
                else
                {
                    sb.append(parameterTypeName);
                }
                
            }
            
            sb.append(") {}");
            
            list.add(sb);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取所有的注解名
     * 
     * @return 所有的注解名【每一个注解名的类型为String，最后保存到一个List集合中】
     * @List<String>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:32:38
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static List<String> getAnnotations(Class<?> clazz)
    {
        Annotation[] annotations = clazz.getAnnotations();
        int len = annotations.length;
        
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < len; i++)
        {
            Annotation annotation = annotations[i];
            
            String annotationName = annotation.annotationType().getSimpleName();
            list.add(annotationName);
        }
        
        return list;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 获取父类的泛型
     * 
     * @return 父类的泛型【Class类型】
     * @Class<?>
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午3:32:47
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Class<?> getSuperClassGenericParameterizedType(Class<?> clazz)
    {
        Type genericSuperClass = clazz.getGenericSuperclass();
        
        Class<?> superClassGenericParameterizedType = null;
        
        // 判断父类是否有泛型
        if (genericSuperClass instanceof ParameterizedType)
        {
            // 向下转型，以便调用方法
            ParameterizedType pt = (ParameterizedType) genericSuperClass;
            // 只取第一个，因为一个类只能继承一个父类
            Type superClazz = pt.getActualTypeArguments()[0];
            // 转换为Class类型
            superClassGenericParameterizedType = (Class<?>) superClazz;
        }
        
        return superClassGenericParameterizedType;
    }
    
    /**
     * 获取接口的所有泛型
     * 
     * @return 所有的泛型接口【每一个泛型接口的类型为Class，最后保存到一个List集合中】
     */
    public static List<Class<?>> getInterfaceGenericParameterizedTypes(Class<?> clazz)
    {
        Type[] genericInterfaces = clazz.getGenericInterfaces();
        int len = genericInterfaces.length;
        
        List<Class<?>> list = new ArrayList<Class<?>>();
        for (int i = 0; i < len; i++)
        {
            Type genericInterface = genericInterfaces[i];
            
            // 判断接口是否有泛型
            if (genericInterface instanceof ParameterizedType)
            {
                ParameterizedType pt = (ParameterizedType) genericInterface;
                
                // 得到所有的泛型【Type类型的数组】
                Type[] interfaceTypes = pt.getActualTypeArguments();
                
                int length = interfaceTypes.length;
                
                for (int j = 0; j < length; j++)
                {
                    // 获取对应的泛型【Type类型】
                    Type interfaceType = interfaceTypes[j];
                    // 转换为Class类型
                    Class<?> interfaceClass = (Class<?>) interfaceType;
                    list.add(interfaceClass);
                }
                
            }
            
        }
        
        return list;
    }
    
    /**
     * 打印包名
     */
    public static void printPackage(Class<?> clazz)
    {
        System.out.println(getPackage(clazz));
    }
    
    /**
     * 打印继承的父类的全类名
     */
    public static void printSuperClassName(Class<?> clazz)
    {
        System.out.println(getSuperClassName(clazz));
    }
    
    /**
     * 打印全类名
     */
    public static void printClassName(Class<?> clazz)
    {
        System.out.println(getClassName(clazz));
    }
    
    /**
     * 打印实现的接口
     * @throws Exception
     */
    public static String printInterfaces(Class<?> clazz) throws Exception
    {
        List<String> list = getInterfaces(clazz);
        
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有实现接口！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有属性
     * @throws Exception
     */
    public static String printFields(Class<?> clazz) throws Exception
    {
        List<StringBuilder> list = getFields(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有属性！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有公共的属性
     * @throws Exception
     */
    public static String printPublicFields(Class<?> clazz) throws Exception
    {
        List<StringBuilder> list = getPublicFields(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有属性！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有构造方法
     * @throws Exception
     */
    public static String printConstructors(Class<?> clazz) throws Exception
    {
        List<StringBuilder> list = getConstructors(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有构造方法！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有方法
     * @throws Exception
     */
    public static String printMethods(Class<?> clazz) throws Exception
    {
        List<StringBuilder> list = getMethods(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有方法！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有公共的方法
     * @throws Exception
     */
    public static String printPublicMethods(Class<?> clazz) throws Exception
    {
        List<StringBuilder> list = getPublicMethods(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有方法！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印所有的注解
     * @throws Exception
     */
    public static String printAnnotations(Class<?> clazz) throws Exception
    {
        List<String> list = getAnnotations(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i));
            }
        }
        else
        {
            System.out.println("没有注解！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * 打印父类的泛型名
     */
    public static String printSuperClassGenericParameterizedType(Class<?> clazz)
    {
        Class<?> superClassGenericParameterizedType = getSuperClassGenericParameterizedType(clazz);
        if (null != superClassGenericParameterizedType)
        {
            System.out.println(superClassGenericParameterizedType.getSimpleName());
            return superClassGenericParameterizedType.getSimpleName();
        }
        else
        {
            System.out.println("父类没有泛型！");
        }
        
        return null;
    }
    
    /**
     * 打印接口的泛型
     * @throws Exception
     */
    public static String printInterfaceGenericParameterizedTypes(Class<?> clazz) throws Exception
    {
        List<Class<?>> list = getInterfaceGenericParameterizedTypes(clazz);
        int size = list.size();
        if (0 < size)
        {
            for (int i = 0; i < size; i++)
            {
                System.out.println(list.get(i).getSimpleName());
            }
        }
        else
        {
            System.out.println("没有泛型接口！");
        }
        
        return StringUtilbc.toString(list);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取Bean所有信息 <br>
     * @see <br>
     * @param obj
     * @return
     * @throws Exception <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.Bean.ReflectionUtilbc#getBeanInfo <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-8 <br>
     * @time 上午12:45:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Map<String, Object> getBeanInfo(Object obj) throws Exception
    {
        Map<String, Object> beanMap = new LinkedHashMap();
        Class<?> clazz =obj.getClass();
        
        beanMap.put("对象引用地址", obj);
        beanMap.put("包名", getPackage(clazz));
        beanMap.put("类名", clazz.getSimpleName());
        beanMap.put("父类全类名", getSuperClassName(clazz));
        beanMap.put("全类名", getClassName(clazz));
        beanMap.put("所有已实现的接口", printInterfaces(clazz));
        beanMap.put("属性", printFields(clazz));
        beanMap.put("构造方法", printConstructors(clazz));
        beanMap.put("方法", printMethods(clazz));
        beanMap.put("公共的属性", printPublicFields(clazz));
        beanMap.put("公共的方法", printPublicMethods(clazz));
        beanMap.put("父类的泛型名", printSuperClassGenericParameterizedType(clazz));
        beanMap.put("泛型接口", printInterfaceGenericParameterizedTypes(clazz));
        
        return beanMap;
    }
    
    /**
     * 打印一个类的相关信息
     * 
     * @param clazz
     * @throws Exception
     */
    public static void printAll(Class<?> clazz) throws Exception
    {
        
        System.out.print("【包名】  ");
        printPackage(clazz);
        
        System.out.print("【类名】  ");
        System.out.println(clazz.getSimpleName());
        
        System.out.println("\n【父类全类名】");
        printSuperClassName(clazz);
        System.out.println("【全类名】");
        printClassName(clazz);
        System.out.println("\n【所有已实现的接口】");
        printInterfaces(clazz);
        
        System.out.println("\n【属性】");
        printFields(clazz);
        System.out.println("\n【构造方法】");
        printConstructors(clazz);
        System.out.println("\n【方法】");
        printMethods(clazz);
        
        System.out.println("\n【公共的属性】");
        printPublicFields(clazz);
        System.out.println("\n【公共的方法】");
        printPublicMethods(clazz);
        
    }
    
    /**
     * 根据Class类型，获取对应的实例【要求必须有无参的构造器】
     * 
     * @return 对应的实例【Object类型】
     */
    public static Object getNewInstance(Class<?> clazz) throws InstantiationException, IllegalAccessException
    {
        return clazz.newInstance();
    }
    
    /**
     * 根据传入的类的Class对象，以及构造方法的形参的Class对象，获取对应的构造方法对象
     * 
     * @param clazz 类的Class对象
     * @param parameterTypes 构造方法的形参的Class对象【可以不写】
     * @return 构造方法对象【Constructor类型】
     */
    public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException
    {
        return clazz.getDeclaredConstructor(parameterTypes);
    }
    
    /**
     * 根据传入的构造方法对象，以及，获取对应的实例
     * 
     * @param constructor 构造方法对象
     * @param initargs 传入构造方法的实参【可以不写】
     * @return 对应的实例【Object类型】
     */
    public static Object getNewInstance(Constructor<?> constructor, Object... initargs) throws InstantiationException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        constructor.setAccessible(true);
        return constructor.newInstance(initargs);
    }
    
    /**
     * 根据传入的属性名字符串，修改对应的属性值
     * 
     * @param clazz 类的Class对象
     * @param name 属性名
     * @param obj 要修改的实例对象
     * @param value 修改后的新值
     */
    public static void setField(Class<?> clazz, String name, Object obj, Object value) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException
    {
        Field field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        field.set(obj, value);
    }
    
    /**
     * 根据传入的方法名字符串，获取对应的方法
     * 
     * @param clazz 类的Class对象
     * @param name 方法名
     * @param parameterTypes 方法的形参对应的Class类型【可以不写】
     * @return 方法对象【Method类型】
     */
    public static Method getMethod(Class<?> clazz, String name, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException
    {
        return clazz.getDeclaredMethod(name, parameterTypes);
    }
    
    /**
     * 根据传入的方法对象，调用对应的方法
     * 
     * @param method 方法对象
     * @param obj 要调用的实例对象【如果是调用静态方法，则可以传入null】
     * @param args 传入方法的实参【可以不写】
     * @return 方法的返回值【没有返回值，则返回null】
     */
    public static Object invokeMethod(Method method, Object obj, Object... args) throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException
    {
        method.setAccessible(true);
        return method.invoke(obj, args);
    }
    
}
