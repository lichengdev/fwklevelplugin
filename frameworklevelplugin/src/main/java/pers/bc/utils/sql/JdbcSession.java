package pers.bc.utils.sql;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

import pers.bc.utils.cons.DBConsts;
import pers.bc.utils.enums.DataBaseType;
import pers.bc.utils.pub.JsonUtilbc;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PropertiesUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;

/**
 * 
 * @qualiFild nc.pub.itf.tools.sql.JdbcSession.java<br>
 * @author：licheng<br>
 * @date Created on 2019-9-25<br>
 * @version 1.0<br>
 */
public class JdbcSession implements Serializable, DBConsts
{
    private static final long serialVersionUID = -419054544554479527L;
    
    public JdbcSession(String driver, String url, String user, String password)
    {
        setDriver(driver);
        setUrl(url);
        // username为登陆oracle数据库的用户名
        setUser(user);
        // password为用户名username的密码
        setPassword(password);
    }
    
    private String driver;
    private String url;
    private String user;
    private String password;
    
    public String getHome()
    {
        return (null == System.getProperty("nc.server.location") ? System.getProperty("user.dir") : System
            .getProperty("nc.server.location"));
    }
    
    /**
     * *********************************************************** <br>
     * 说明：连接数据库的方法,需要构造对象使用 <br>
     * @see <br>
     * @return
     * @throws SQLException <br>
     * @Connection <br>
     * @methods pers.bc.utils.sql.JdbcSession#getConnection <br>
     * @author licheng <br>
     * @date Created on 2020年4月4日 <br>
     * @time 下午8:21:23 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Connection getConnection()
    {
        try
        {
            return DriverManager.getConnection(getUrl(), getUser(), getPassword());
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("httplogs").exception("JDBCgetConnectionThw", e);
            return null;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：连接数据库的方法 <br>
     * @see <br>
     * @param dbType
     * @return
     * @throws SQLException
     * @throws IOException
     * @throws ClassNotFoundException <br>
     * @Connection <br>
     * @methods pers.bc.utils.sql.JdbcSession#getConnection <br>
     * @author licheng <br>
     * @date Created on 2020年4月4日 <br>
     * @time 下午8:21:12 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Connection getConnection(DataBaseType dbType)
    {
        return getConnection(dbType, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：连接数据库的方法
     * @see
     * @return
     * @Connection
     * @author licheng
     * @throws IOException
     * @throws SQLException
     * @throws ClassNotFoundException
     * @date Created on 2019-7-25
     * @time 下午11:07:49
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Connection getConnection(DataBaseType dbType, java.net.URL filePath)
    {
        try
        {
            if (PubEnvUtilbc.isEmptyObj(dbType) && PubEnvUtilbc.isEmptyObj(filePath)) return null;
            if (null == filePath) filePath = JdbcSession.class.getResource("jdbc.properties");
            Map<String, String> dataSourceMap = PropertiesUtilbc.getAllProperties(filePath.getPath());
            // Map<String, String> propertys = new LinkedHashMap();
            String key = null;
            String driverClass = null;
    
            if (DataBaseType.ORACLE == dbType)
            {
                key = ORACLE_NAME;
                driverClass = JDBC_ORACLE;
            }
            if (DataBaseType.MYSQL == dbType)
            {
                key = MYSQL_NAME;
                driverClass = JDBC_MYSQL;
            }
            if (DataBaseType.MSSQL == dbType)
            {
                key = MSSQL_NAME;
                driverClass = JDBC_MYSQL;
            }
            if (DataBaseType.DB2 == dbType)
            {
                key = DB2_NAME;
                driverClass = JDBC_DB2;
            }
            if (DataBaseType.SQLSERVER == dbType)
            {
                key = SQLSERVER_NAME;
                driverClass = JDBC_SQLSERVER;
            }
            if (DataBaseType.KUNLUN == dbType)
            {
                key = KUNLUN_NAME;
                driverClass = JDBC_KUNLUN;
            }
            // JSONObject jsonObj = JSON.parseObject(dataSourceMap.get(key));
            // propertys = new Gson().fromJson(dataSourceMap.get(key), LinkedHashMap.class);
            Map<String, Object> propertys = JsonUtilbc.toMapHelp(dataSourceMap.get(key));
            Class.forName(driverClass);// 注册数据库驱动
            // DriverManager.getConnection(propertys.get(DB_URL) + "",
            // propertys.get(USER_NAME) + "", propertys.get(PASS) + "");
            // 定义Properties对象
            Properties prop = new Properties();
            // 设置Properties对象属性
            prop.setProperty("user", propertys.get(USER_NAME).toString());
            prop.setProperty("password", propertys.get(PASS).toString());
            
            return DriverManager.getConnection(propertys.get(DB_URL).toString(), prop);
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            LoggerUtilbc.getInstance("httplogs").exception("JDBCgetConnectionThw", e);
            return null;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 释放资源<br>
     * @see <br>
     * @param autoCloseables <br>
     * @void <br>
     * @methods nc.pub.itf.tools.sql.JDBCUtils#colseResource <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午11:00:19 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static void close(AutoCloseable... autoCloseables)
    {
        try
        {
            if (autoCloseables != null)
            {
                for (AutoCloseable autoCloseable : autoCloseables)
                {
                    autoCloseable.close();
                    autoCloseable = null;
                }
            }
        }
        catch (Exception e)
        {
            try
            {
                
                LoggerUtilbc.getInstance("httplogs").exception("JDBCcloseThw", e);
            }
            catch (Exception e1)
            {
            }
        }
        // 等待垃圾回收
    }
    
    public String getDriver()
    {
        return driver;
    }
    
    public void setDriver(String driver)
    {
        try
        {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e)
        {
            LoggerUtilbc.getInstance("httplogs").exception("setDriverThw", e);
        }
        this.driver = driver;
    }
    
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getUser()
    {
        return user;
    }
    
    public void setUser(String user)
    {
        this.user = user;
    }
    
    public String getPassword()
    {
        return password;
    }
    
    public void setPassword(String password)
    {
        this.password = password;
    }
    
}
