package pers.bc.utils.sql;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.file.StreamUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;
import pers.bc.utils.pub.StringUtilbc;

/**
 * MyBatisUtilbc操作工具
 * @qualiFild pers.bc.utils.sql.MyBatisUtilbc.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2021-4-24<br>
 * @version 1.0<br>
 */
public class MyBatisUtilbc
{
    /**
     * *********************************************************** <br>
     * 说明：MyBatis逆向工程<br>
     * @see <br>
     * @param file new File("generator.xml")
     * @throws Exception
     * @throws InterruptedException <br>
     * @void <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#MyBatisGenerator <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午10:53:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void myBatisGenerator(File file) throws Exception, InterruptedException
    {
        buildMyBatisGenerator(file);
    }
    
    public static void myBatisGenerator(String generatorXmlFile) throws Exception, InterruptedException
    {
        buildMyBatisGenerator(new File(generatorXmlFile));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：构建MyBatis逆向工程 <br>
     * @see <br>
     * @param file
     * @throws Exception
     * @throws InterruptedException <br>
     * @void <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#buildMyBatisGenerator <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:29:29 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static void buildMyBatisGenerator(File file) throws Exception, InterruptedException
    {
        
        // 存放预警信息的
        List<String> warings = new ArrayList<>();
        
        ConfigurationParser configParser = new ConfigurationParser(warings);
        Configuration configuration = configParser.parseConfiguration(file);
        
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warings);
        myBatisGenerator.generate(null);
        
        LoggerUtilbc.getInstance("mybatislog").debug(StringUtilbc.toString(warings));
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明：关闭 MyBatisSqlSession 使用 getOpenSession()后必须使用此关闭方法<br>
     * @see <br>
     * @throws UnsupportedEncodingException
     * @throws IOException <br>
     * @void <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#closeOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:11:43 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void closeOpenSession() throws UnsupportedEncodingException, IOException
    {
        if (null != openSession)
        {
            openSession.close();
            openSession = null;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：数据源配置在myBatisConfigure.Xml里面<br>
     * 或者已经引用 dataSourceProp.Properties文件 <br>
     * @see <br>
     * @param myBatisConfigureXml myBatis配置文件
     * @return
     * @SqlSession <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#getOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午10:59:03 <br>
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    public static SqlSession getOpenSession(String myBatisConfigureXml) throws Exception
    {
        return obtainOpenSession(myBatisConfigureXml, null, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：得到 MyBatis的 SqlSession <br>
     * @see <br>
     * @param myBatisConfigureXml
     * @param dataSourceProp 数据源配置文件 dataSourceProp.Properties
     * @return
     * @SqlSession <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#getOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:00:30 <br>
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    public static SqlSession getOpenSession(String myBatisConfigureXml, Properties dataSourceProp) throws Exception
    {
        return obtainOpenSession(myBatisConfigureXml, null, dataSourceProp);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：得到 MyBatis的 SqlSession <br>
     * @see <br>
     * @param myBatisConfigureXml
     * @param environment
     * @return
     * @SqlSession <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#getOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:10:25 <br>
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    public static SqlSession getOpenSession(String myBatisConfigureXml, String environment) throws Exception
    {
        return obtainOpenSession(myBatisConfigureXml, environment, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 得到 MyBatis的 SqlSession<br>
     * @see <br>
     * @param myBatisConfigureXml
     * @param environment
     * @param dataSourceProp 数据源配置文件 dataSourceProp.Properties
     * @return
     * @SqlSession <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#getOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:09:30 <br>
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    public static SqlSession getOpenSession(String myBatisConfigureXml, String environment, Properties dataSourceProp) throws Exception
    {
        return obtainOpenSession(myBatisConfigureXml, environment, dataSourceProp);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：构建 MyBatis的 SqlSession<br>
     * new SqlSessionFactory().Builder()->SqlSessionFactory.openSession()->SqlSession<br>
     * @see <br>
     * @param myBatisConfigureXml
     * @param environment
     * @param dataSourceProp
     * @return
     * @SqlSession <br>
     * @methods pers.bc.utils.sql.MyBatisUtilbc#obtainOpenSession <br>
     * @author LiBencheng <br>
     * @date Created on 2021-4-24 <br>
     * @time 下午11:05:50 <br>
     * @version 1.0 <br>
     * @throws Exception <br>
     */
    private static SqlSession obtainOpenSession(String myBatisConfigureXml, String environment, Properties dataSourceProp) throws Exception
    {
        SqlSessionFactory sqlSessionFactory = null;
        // 懶漢式
        if (null == openSession)
        {
            synchronized (MyBatisUtilbc.class)
            {
                if (null == openSession)
                {
                    // Reader myBatisConfigure = Resources.getResourceAsReader(myBatisConfigureXml);
                    
                    byte[] readFileToByteArray = StreamUtilbc.readFileToByteArray(myBatisConfigureXml);
                    InputStream byte2InputStream = StreamUtilbc.byte2InputStream(readFileToByteArray);
                    Reader myBatisConfigure = new InputStreamReader(byte2InputStream, PubConsUtilbc.UTF_8);
                    
                    if (!PubEnvUtilbc.isEmptyObj(environment) && !PubEnvUtilbc.isEmptyObj(dataSourceProp)) sqlSessionFactory =
                        new SqlSessionFactoryBuilder().build(myBatisConfigure, environment, dataSourceProp);
                    else if (!PubEnvUtilbc.isEmptyObj(environment) && PubEnvUtilbc.isEmptyObj(dataSourceProp)) sqlSessionFactory =
                        new SqlSessionFactoryBuilder().build(myBatisConfigure, environment);
                    else if (PubEnvUtilbc.isEmptyObj(environment) && !PubEnvUtilbc.isEmptyObj(dataSourceProp)) sqlSessionFactory =
                        new SqlSessionFactoryBuilder().build(myBatisConfigure, dataSourceProp);
                    else
                        sqlSessionFactory = new SqlSessionFactoryBuilder().build(myBatisConfigure);
                    
                    openSession = sqlSessionFactory.openSession();
                }
            }
        }
        
        return openSession;
        
    }
    
    private static SqlSession openSession = null;
}
