package pers.bc.utils.sql;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pers.bc.utils.Bean.BeanHelper;
import pers.bc.utils.enums.DataBaseType;
import pers.bc.utils.enums.DataSetType;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.PubEnvUtilbc;
import pers.bc.utils.pub.StringUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 数据库使用工具
 * @qualiFild nc.pub.itf.tools.sql.DBUtils.java<br>
 * @author：李本城<br>
 * @date Created on 2019-9-25<br>
 * @version 1.0<br>
 */
public class DBUtilbc
{
    // 默认游标数 300
    // show parameter open_cursors;
    // alter system set open_cursors=1000;
    public static int DEFAULT_CURSOR = 290;
    public static int EXECUTE_NUMBER = 0;// 執行次數
    
    public Connection connection = null;
    private Boolean isclose = true;
    
    public Boolean isclose()
    {
        return isclose;
    }
    
    private DataBaseType dbType;
    
    private java.net.URL filePath;
    
    public DataBaseType getDbType()
    {
        return dbType;
    }
    
    public void setDbType(DataBaseType dbType)
    {
        this.dbType = dbType;
    }
    
    public java.net.URL getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(java.net.URL filePath)
    {
        this.filePath = filePath;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：设置了false，记得手动关闭连接池 <br>
     * @see <br>
     * @param isclose <br>
     * @void <br>
     * @methods pers.bc.utils.sql.DBUtilbc#setIsclose <br>
     * @author licheng <br>
     * @date Created on 2020-4-22 <br>
     * @time 下午7:34:41 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public void setIsclose(Boolean isclose)
    {
        this.isclose = isclose;
    }
    
    public Connection getConnection()
    {
        if (PubEnvUtilbc.isEmptyObj(connection))
        {
            synchronized (this)
            {
                if (PubEnvUtilbc.isEmptyObj(connection))
                {
                    connection = JdbcSession.getConnection(getDbType(), getFilePath());
                }
            }
        }
        return connection;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：检测SQL注入，UNION查询关键字的正则表达式 ：
     *   检测MS SQL Server SQL注入攻击的正则表达式：
     *   SQL 注入攻击的正则表达式 ：
     * @see
     * @param sql
     * @throws SQLException
     * @void
     * @author licheng
     * @date Created on 2019-7-25
     * @time 下午10:56:14
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private void checkSQL(String sql) throws SQLException
    {
        if (sql.contains(SQLHelper.SQL_CHARACTERS) || sql.contains(SQLHelper.MSSQLServerSQL) || sql.contains(SQLHelper.UNION))
        {
            throw new SQLException("ERROR SQL语句有注入攻击风险：" + sql);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：慎用，用後自己关闭连接，不仅阿姨使用 <br>
     * @see <br>
     * @param sql
     * @param prams
     * @return
     * @throws SQLException <br>
     * @ResultSet <br>
     * @methods pers.bc.utils.sql.DBUtilbc#exeQueryRs <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 上午11:28:36 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ResultSet exeQueryRs(String sql, Object[] prams) throws SQLException
    {
        return executeQuery(sql, prams);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param SetType BEAN, BEANLIST, BEANMAP, BEANMAPLIST
     * @param clzss
     * @param columns 可以為null
     * @param sql
     * @param prams 可以為null
     * @return
     * @throws Exception <br>
     * @Object <br>
     * @methods pers.bc.utils.sql.DBUtilbc#exeQueryBean <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 上午11:46:48 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object exeQueryBean(DataSetType SetType, Class<?> clzss, String columns[], String sql, Object[] prams) throws MylbcException
    {
        
        Object obj = null;
        ResultSet resultSet = null;
        try
        {
            
            resultSet = executeQuery(sql, prams);
            
            BeanResultSetProcessor processor = new BeanResultSetProcessor(clzss);
            if (PubEnvUtilbc.getSize(columns) < 1) columns = BeanHelper.getPropertiesAry(clzss);
            if (SetType == DataSetType.BEAN) obj = processor.beanProcessResultSet(resultSet);
            if (SetType == DataSetType.BEANLIST) obj = processor.beanListProcessResultSet(resultSet);
            if (SetType == DataSetType.BEANMAP) obj = processor.beanMappingProcessor(resultSet);
            if (SetType == DataSetType.BEANMAPLIST) obj = processor.beanMappingListProcessor(resultSet, columns);
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            throw new MylbcException(e);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(resultSet);
        }
        return obj;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：* 根据开始时间和结束时间 获取数据,<br>
     * 注意：数据获取完毕后 关闭连接 <br>
     * @see <br>
     * @param sql
     * @param prams
     * @return
     * @throws SQLException <br>
     * @ResultSet <br>
     * @methods pers.bc.utils.sql.DBUtilbc#executeQuery <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 上午10:49:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private ResultSet executeQuery(String sql, Object[] prams) throws SQLException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            checkSQL(sql);
            ps = getConnection().prepareStatement(sql);
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                ps.setObject(i + 1, prams[i]);
            }
            
            rs = ps.executeQuery();
        }
        catch (SQLException e)
        {
            String msg = null;
            try
            {
                msg =
                    "SQL:" + sql + LoggerUtilbc.RN + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN + " SQLException:"
                        + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            ps = null;
        }
        
        return rs;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：查询方法 <br>
     * @see <br>
     * @param SetType 数据处理类型
     * @param sql
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException <br>
     * @Object <br>
     * @methods pers.bc.utils.sql.DBUtilbc#queryProcessor <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 上午10:33:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object executeQuery(DataSetType SetType, String sql) throws MylbcException
    {
        return executeQuery(SetType, sql, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：帶參數的查詢方法 <br>
     * @see <br>
     * @param SetType 数据处理类型
     * @param sql
     * @param prams 參數
     * @return
     * @throws MylbcException <br>
     * @Object <br>
     * @methods pers.bc.utils.sql.DBUtilbc#executeQuery <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 上午11:15:54 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object executeQuery(DataSetType SetType, String sql, Object[] prams) throws MylbcException
    {
        Object obj = null;
        ResultSet resultSet = null;
        try
        {
            resultSet = executeQuery(sql, prams);
            
            if (SetType == DataSetType.COLUMN) obj = ResultSetProcessor.getInstance().ColumnProcessor(resultSet);
            if (SetType == DataSetType.COLUMNLIST) obj = ResultSetProcessor.getInstance().ColumnListProcessor(resultSet);
            if (SetType == DataSetType.MAP) obj = ResultSetProcessor.getInstance().MapProcessor(resultSet);
            if (SetType == DataSetType.MAPLIST) obj = ResultSetProcessor.getInstance().MapListProcessor(resultSet);
            if (SetType == DataSetType.JSON) obj = ResultSetProcessor.getInstance().JSONProcessor(resultSet);
            if (SetType == DataSetType.JSONLIST) obj = ResultSetProcessor.getInstance().JSONListProcessor(resultSet);
            if (SetType == DataSetType.XML) obj = ResultSetProcessor.getInstance().XMLProcessor(resultSet);
            if (SetType == DataSetType.XMLIST) obj = ResultSetProcessor.getInstance().XMLListProcessor(resultSet);
            if (SetType == DataSetType.ARRAY) obj = ResultSetProcessor.getInstance().ArrayProcess(resultSet);
            if (SetType == DataSetType.ARRAYLIST) obj = ResultSetProcessor.getInstance().ArrayListProcess(resultSet);
            if (SetType == DataSetType.COLLECTION) obj = ResultSetProcessor.getInstance().vectorProcess(resultSet);
            if (SetType == DataSetType.TABLEMODEL) obj = ResultSetProcessor.getInstance().tableModelProcessor(resultSet);
            
        }
        catch (SQLException | ClassNotFoundException | IOException e)
        {
            throw new MylbcException(e);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            resultSet = null;
        }
        return obj;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 针对于不同的表的通用的查询操作，返回表中的一条记录<br>
     * @see <br>
     * @param clazz
     * @param sql
     * @param prams
     * @return <br>
     * @T <br>
     * @methods nc.pub.itf.tools.sql.JdbcSession#queryDateById <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午10:17:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public <T> T queryDateByPram(Class<T> clazz, String sql, Object[] prams) throws SQLException, InstantiationException,
            IllegalAccessException, NoSuchFieldException, SecurityException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = getConnection().prepareStatement(sql);
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                ps.setObject(i + 1, prams[i]);
            }
            
            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if (rs.next())
            {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++)
                {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);
                    
                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        }
        catch (Exception e)
        {
            String msg = null;
            try
            {
                msg =
                    "SQL:" + StringUtilbc.toString(sql) + LoggerUtilbc.RN + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN
                        + " SQLException:" + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(ps, rs);
        }
        
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param clazz
     * @param sql
     * @param prams
     * @return <br>
     * @List<T> <br>
     * @methods nc.pub.itf.tools.sql.DBUtils#getForList <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午10:31:10 <br>
     * @version 1.0 <br>
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws SecurityException
     * @throws NoSuchFieldException <br>
     */
    public <T> List<T> queryDateByPrams(Class<T> clazz, String sql, Object[] prams) throws SQLException, InstantiationException,
            IllegalAccessException, NoSuchFieldException, SecurityException
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            ps = getConnection().prepareStatement(sql);
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                ps.setObject(i + 1, prams[i]);
            }
            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            // 创建集合对象
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next())
            {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列:给t对象指定的属性赋值
                for (int i = 0; i < columnCount; i++)
                {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);
                    
                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                list.add(t);
            }
            
            return list;
        }
        catch (Exception e)
        {
            String msg = null;
            try
            {
                msg =
                    "SQL:" + sql + LoggerUtilbc.RN + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN + " SQLException:"
                        + e.getMessage();
            }
            catch (Exception e1)
            {
                e1.printStackTrace();
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(rs, ps);
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 保存或跟新<br>
     * @see <br>
     * @param sql
     * @return
     * @throws SQLException <br>
     * @int <br>
     * @methods pers.bc.utils.sql.DBUtils#executeUpdate <br>
     * @author licheng <br>
     * @date Created on 2020年4月4日 <br>
     * @time 下午6:30:24 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public int executeUpdate(String sql) throws SQLException
    {
        List<String> sqls = new ArrayList<String>();
        sqls.add(sql);
        
        return executeUpdates(sqls);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：批量 保存或跟新 <br>
     * @see <br>
     * @param sqls
     * @return
     * @throws Exception <br>
     * @int <br>
     * @methods nc.pub.itf.tools.sql.DBUtils#executeUpdates <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午10:41:19 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public int executeUpdates(List<String> sqls) throws SQLException
    {
        int[] executeUpdate = new int[]{};
        if (sqls == null || sqls.size() == 0) return 0;
        // 使用PreparedStatement 可以防止1=1的sql 比较安全
        // PreparedStatement ps = null;
        // 禁用自动提交
        // 用conn创建Statement对象类实例
        Statement statement = null;
        try
        {
            statement = getConnection().createStatement();
            for (String sql : sqls)
            {
                checkSQL(sql);
                statement.addBatch(sql);
            }
            // 同时提交所有的sql语句
            // ps.executeBatch();
            executeUpdate = statement.executeBatch();
            // 清空缓存
            statement.clearBatch();
            // 提交事务
            connection.commit();
        }
        catch (SQLException e)
        {
            if (connection != null)
            {
                // 事务回滚
                connection.rollback();
            }
            String msg = null;
            try
            {
                msg = "SQL:" + StringUtilbc.toString(sqls) + LoggerUtilbc.RN + " SQLException:" + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(statement);
        }
        
        return executeUpdate.length;
    }
    
    public int executeUpdates(List<String> sqls, List<String[]> prams) throws SQLException
    {
        PreparedStatement pstatement = null;
        int[] executeUpdate = new int[]{};
        if (sqls == null || sqls.size() == 0) return 0;
        // 使用PreparedStatement 可以防止1=1的sql 比较安全
        // PreparedStatement ps = null;
        // 禁用自动提交
        // 用conn创建Statement对象类实例
        try
        {
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                checkSQL(sqls.get(i));
                pstatement = getConnection().prepareStatement(sqls.get(i));
                String[] strPrams = prams.get(i);
                for (int x = 0, y = PubEnvUtilbc.getSize(prams); x < y; x++)
                {
                    pstatement.setObject(x + 1, strPrams[x]);
                }
                pstatement.addBatch(sqls.get(i));
                if (i % 1000 == 0)
                {
                    // 2.执行batch
                    pstatement.executeBatch();
                    // 3.清空batch
                    pstatement.clearBatch();
                }
            }
            // 同时提交所有的sql语句
            // ps.executeBatch();
            executeUpdate = pstatement.executeBatch();
            // 清空缓存
            pstatement.clearBatch();
            // 提交事务
            getConnection().commit();
        }
        catch (SQLException e)
        {
            if (connection != null)
            {
                // 事务回滚
                connection.rollback();
            }
            String msg = null;
            try
            {
                msg =
                    "SQL:" + StringUtilbc.toString(sqls) + LoggerUtilbc.RN + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN
                        + " SQLException:" + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(pstatement);
        }
        
        return executeUpdate.length;
    }
    
    public int executeUpdates(String sql, List<String[]> prams) throws SQLException
    {
        PreparedStatement pstatement = null;
        int[] executeUpdate = new int[]{};
        
        // 使用PreparedStatement 可以防止1=1的sql 比较安全
        // PreparedStatement ps = null;
        // 禁用自动提交
        // 用conn创建Statement对象类实例
        try
        {
            checkSQL(sql);
            pstatement = getConnection().prepareStatement(sql);
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                String[] strPrams = prams.get(i);
                for (int x = 0, y = PubEnvUtilbc.getSize(prams); x < y; x++)
                {
                    pstatement.setObject(x + 1, strPrams[x]);
                }
                pstatement.addBatch(sql);
                if (i % 1000 == 0)
                {
                    // 2.执行batch
                    executeUpdate = pstatement.executeBatch();
                    // 3.清空batch
                    pstatement.clearBatch();
                }
            }
            // 同时提交所有的sql语句
            // ps.executeBatch();
            executeUpdate = pstatement.executeBatch();
            // 清空缓存
            pstatement.clearBatch();
            // 提交事务
            connection.commit();
        }
        catch (SQLException e)
        {
            if (connection != null)
            {
                // 事务回滚
                connection.rollback();
            }
            String msg = null;
            try
            {
                msg =
                    "SQL:" + sql + LoggerUtilbc.RN + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN + " SQLException:"
                        + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(pstatement);
        }
        
        return executeUpdate.length;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 通用的增删改操作<br>
     * @see <br>
     * @param sql
     * @param prams <br>
     * @void <br>
     * @methods nc.pub.itf.tools.sql.DBUtils#executeUpdates <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午10:41:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public void executeUpdates(String sql, Object[] prams) throws SQLException
    {
        // sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try
        {
            // 1.获取数据库的连接
            // 2.预编译sql语句，返回PreparedStatement的实例
            ps = getConnection().prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0, j = PubEnvUtilbc.getSize(prams); i < j; i++)
            {
                ps.setObject(i + 1, prams[i]);// 小心参数声明错误！！
            }
            // 4.执行
            ps.execute();
        }
        catch (SQLException e)
        {
            String msg = null;
            try
            {
                msg =
                    "SQL:" + sql + LoggerUtilbc.CRLF + " prams:" + StringUtilbc.toString(prams) + LoggerUtilbc.RN + " SQLException:"
                        + e.getMessage();
            }
            catch (Exception e1)
            {
            }
            throw new SQLException(msg);
        }
        finally
        {
            // 5.资源的关闭
            if (isclose()) JdbcSession.close(connection);
            JdbcSession.close(ps);
        }
    }
    
    private static DBUtilbc bDUtil = null;
    
    /**
     * *********************************************************** <br>
     * 说明：自己初始化连接后，使用此类的CRUD方法 <br>
     * @see <br>
     * @param connection
     * @return <br>
     * @DBUtilbc <br>
     * @methods pers.bc.utils.sql.DBUtilbc#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-21 <br>
     * @time 下午2:34:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static DBUtilbc getInstance(Connection connection)
    {
        return InnerInstance.getInstance(connection);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：传入数据库必须的参数创建 并使用CRUD <br>
     * @see <br>
     * @param driver
     * @param url
     * @param user
     * @param password
     * @return <br>
     * @DBUtilbc <br>
     * @methods pers.bc.utils.sql.DBUtilbc#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-21 <br>
     * @time 下午2:35:29 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static DBUtilbc getInstance(String driver, String url, String user, String password)
    {
        return InnerInstance.getInstance(driver, url, user, password);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据默认jdbc.properties配置文件的信息使用CRUD操作 <br>
     * @see <br>
     * @param dbType 数据库类型
     * @return <br>
     * @DBUtilbc <br>
     * @methods pers.bc.utils.sql.DBUtilbc#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-21 <br>
     * @time 下午2:34:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static DBUtilbc getInstance(DataBaseType dbType)
    {
        return InnerInstance.getInstance(dbType);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据传入的配置文件地址，获取配置文件的信息使用CRUD操作 <br>
     * @see <br>
     * @param dbType 数据库类型
     * @param filePath jdbc.properties配置文件
     * @return <br>
     * @DBUtilbc <br>
     * @methods pers.bc.utils.sql.DBUtilbc#getInstance <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-21 <br>
     * @time 下午2:36:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static DBUtilbc getInstance(DataBaseType dbType, java.net.URL filePath)
    {
        return InnerInstance.getInstance(dbType, filePath);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：自己初始化连接
     * @see
     * @param connection
     * @methods pers.bc.utils.sql.DBUtilbc#构造方法
     * @author licheng
     * @date Created on 2020年4月5日
     * @time 上午12:15:52
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private DBUtilbc(Connection connection)
    {
        this.connection = connection;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param driver 驱动路径
     * @param url 地址
     * @param user 用户名
     * @param password 密码
     * @methods pers.bc.utils.sql.DBUtilbc#构造方法
     * @author licheng
     * @date Created on 2020年4月5日
     * @time 上午12:08:45
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private DBUtilbc(String driver, String url, String user, String password)
    {
        this.connection = new JdbcSession(driver, url, user, password).getConnection();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： ORACLE, MYSQL, MSSQL, DB2, SQLSERVER, HSQL...
     * @see
     * @param dbType
     * @methods pers.bc.utils.sql.DBUtilbc#构造方法
     * @author licheng
     * @date Created on 2020年4月5日
     * @time 上午12:08:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private DBUtilbc(DataBaseType dbType)
    {
        this.connection = JdbcSession.getConnection(dbType);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：可以根据自己的配置文件，獲取数据库链接<br>
     * @see
     * @param dbType 數據庫类型，pers.bc.utils.enums.DataBaseType
     * @param filePath jdbc.properties配置文件路徑
     * @methods pers.bc.utils.sql.DBUtilbc#构造方法
     * @author licheng
     * @date Created on 2020年4月5日
     * @time 上午12:06:59
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private DBUtilbc(DataBaseType dbType, java.net.URL filePath)
    {
        setDbType(dbType);
        setFilePath(filePath);
        this.connection = JdbcSession.getConnection(dbType, filePath);
    }
    
    private static class InnerInstance
    {
        
        public static DBUtilbc getInstance(Connection connection)
        {
            return new DBUtilbc(connection);
        }
        
        public static DBUtilbc getInstance(DataBaseType dbType)
        {
            return new DBUtilbc(dbType);
        }
        
        public static DBUtilbc getInstance(DataBaseType dbType, java.net.URL filePath)
        {
            EXECUTE_NUMBER++;
            // 超出執行數后，重慶獲取链接，解决超出打开游标的最大数异常
            if (EXECUTE_NUMBER == DEFAULT_CURSOR)
            {
                bDUtil = null;
                EXECUTE_NUMBER = 0;
            }
            if (PubEnvUtilbc.isEmptyObj(bDUtil))
            {
                bDUtil = new DBUtilbc(dbType, filePath);
                // 设置了false记得关闭连接池
                bDUtil.setIsclose(false);
            }
            
            return bDUtil;
        }
        
        public static DBUtilbc getInstance(String driver, String url, String user, String password)
        {
            EXECUTE_NUMBER++;
            // 超出執行數后，重慶獲取链接，解决超出打开游标的最大数异常
            if (EXECUTE_NUMBER == DEFAULT_CURSOR)
            {
                bDUtil = null;
                EXECUTE_NUMBER = 0;
            }
            if (PubEnvUtilbc.isEmptyObj(bDUtil))
            {
                bDUtil = new DBUtilbc(driver, url, user, password);
                // 设置了false记得关闭连接池
                bDUtil.setIsclose(false);
            }
            
            return bDUtil;
        }
        
    }
}
