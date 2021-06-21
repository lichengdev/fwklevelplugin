package pers.bc.utils.pub;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.file.FileUtilbc;

/**
 * 提供一些常用的属性文件相关方法<br>
 * java的properties文件中的换行书写<br>
 * test.properties文件: name=Hello world \ <br>
 * My Name is ferreousbox<br>
 * 读取name属性的时其值就变成了:Hello world My Name is ferreousbox <br>
 * 注意加"\"符号 换行
 * @qualiFild pers.bc.utils.pub.PropertiesUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月7日<br>
 * @version 1.0<br>
 */
public final class PropertiesUtilbc
{
    // load(Reader reader)
    // load(InputStream inStream)
    // loadFromXML(InputStream in)
    
    // 对对象的操作
    // prop.clear(); // 清空
    // prop.containsKey("key"); // 是否包含key
    // prop.containsValue("value"); // 是否包含value
    // prop.entrySet(); // prop的Map.Entry集合
    // prop.getProperty("key"); // 通过key获取value
    // prop.put("key", "value"); // 添加属性
    // prop.list(new PrintStream(new File(""))); // 将prop保存到文件
    // prop.store(new FileOutputStream(new File("")), "注释"); // 和上面类似
    
    private String properiesName = "";
    
    public PropertiesUtilbc(String fileName)
    {
        this.properiesName = fileName;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取系统参数<br>
     * @see <br>
     * @param key
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#key <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:48:21 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String key(String key)
    {
        return getSystemPro(key);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取系统参数 <br>
     * @see <br>
     * @param key
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#getSystemKey <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-27 <br>
     * @time 下午9:18:38 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String getSystemPro(String key)
    {
        return System.getProperty(key);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据key，获取指定配置文件的值 <br>
     * @see <br>
     * @param filePath 配置文件路径
     * @param key
     * @return
     * @throws IOException <br>
     * @String <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#getValueByKey <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:48:40 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static String getValueByKey(String filePath, String key) throws IOException
    {
        Properties pps = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, PubConsUtilbc.UTF_8);
        pps.load(inputStreamReader);
        String value = pps.getProperty(key);
        
        FileUtilbc.close(inputStreamReader, fileInputStream);
        
        return value;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 读取配置文件所有信息<br>
     * @see <br>
     * @param in 指定的文件流
     * @return
     * @throws IOException <br>
     * @Map<String,String> <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#properties <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:49:54 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static Map<String, String> properties(InputStreamReader in) throws IOException
    {
        Properties prop = new Properties();
        prop.load(in);
        
        Map<String, String> map = new HashMap<String, String>();
        prop.load(in);
        Enumeration<?> en = prop.propertyNames();
        while (en.hasMoreElements())
        {
            String strKey = (String) en.nextElement();
            String strValue = prop.getProperty(strKey);
            map.put(strKey, strValue);
        }
        
        FileUtilbc.close(in);
        
        return map;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据指定配置文件，读取配置文件所有信息 <br>
     * @see <br>
     * @param filePath 配置文件路径
     * @return
     * @throws IOException <br>
     * @Map<String,String> <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#getAllProperties <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:54:42 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static Map<String, String> getAllProperties(String filePath) throws IOException
    {
        return properties(new InputStreamReader(new FileInputStream(filePath), PubConsUtilbc.UTF_8));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：向指定配置文件，写入信息key，value <br>
     * @see <br>
     * @param filePath 配置文件路径
     * @param pKey
     * @param pValue
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#WriteProperties <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:55:34 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public final static void WriteProperties(String filePath, String pKey, String pValue) throws IOException
    {
        synchronized (pValue)
        {
            Properties props = new Properties();
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, PubConsUtilbc.UTF_8);
            props.load(inputStreamReader);
            // 使用 Hashtable 的方法put， 使用 getProperty 提供并行性
            // 强制要求key和value使用字符串。返回hashtable 调用put结果
            OutputStream fos = new FileOutputStream(filePath);
            props.setProperty(pKey, pValue);
            // 以适合 load 方法加载到 Properties 表中
            // 將此 Properties表中的属性列表（key和value）写入输入流
            props.store(fos, "Update operation of development tools from Licheng! --> "+ "Update '" + pKey + "' value = " + pValue );
            
            FileUtilbc.close(fos, inputStreamReader, fileInputStream);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据key，获取指定配置文件的值<br>
     * 需要构造对象 <br>
     * @see <br>
     * @param key
     * @return
     * @throws IOException <br>
     * @String <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#getProperty <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:57:04 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public String getProperty(String key) throws IOException
    {
        String value = "";
        if ("".equals(properiesName)) return value;
        FileInputStream fileInputStream = new FileInputStream(properiesName);
        InputStreamReader is = new InputStreamReader(fileInputStream, PubConsUtilbc.UTF_8);
        // PropertiesUtilbc.class.getClassLoader().getResourceAsStream(properiesName);
        Properties p = new Properties();
        p.load(is);
        value = p.getProperty(key);
        
        FileUtilbc.close(is, fileInputStream, fileInputStream);
        
        return value;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据指定配置文件路径，读取配置文件所有信息 <br>
     * 需要构造对象<br>
     * @see <br>
     * @return
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException <br>
     * @Properties <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#getProperties <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:57:18 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Properties getProperties() throws UnsupportedEncodingException, FileNotFoundException
    {
        if ("".equals(properiesName)) return null;
        Properties p = new Properties();
        FileInputStream inputStream = new FileInputStream(properiesName);
        
        InputStreamReader is = new InputStreamReader(inputStream, PubConsUtilbc.UTF_8);
        // PropertiesUtilbc.class.getClassLoader().getResourceAsStream(properiesName);
        
        FileUtilbc.close(is, inputStream);
        
        return p;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 向指定配置文件，写入信息key，value<br>
     * 需要构造对象<br>
     * @see <br>
     * @param key
     * @param value
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.pub.PropertiesUtilbc#writeProperty <br>
     * @author licheng <br>
     * @date Created on 2020年4月7日 <br>
     * @time 上午8:57:43 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void writeProperty(String key, String value) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(properiesName);
        Properties p = new Properties();
        InputStreamReader is = new InputStreamReader(fileInputStream, PubConsUtilbc.UTF_8);
        p.load(is);
        OutputStream os = new FileOutputStream(properiesName);
        p.setProperty(key, value);
        p.store(os, key);
        
        FileUtilbc.flush(os);
        FileUtilbc.close(os, is, fileInputStream);
    }
    
}
