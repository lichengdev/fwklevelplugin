package pers.bc.utils.sql;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 值对象处理器
 * @qualiFild nc.pub.itf.tools.sql.BeanResultSetProcessor.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class BeanResultSetProcessor implements Serializable
{
    public BeanResultSetProcessor()
    {
    }
    
    private static final long serialVersionUID = -3278015023629556594L;
    private Class<?> type = null;
    
    public BeanResultSetProcessor(Class<?> type)
    {
        this.type = type;
    }
    
    public Object processResultSet(ResultSet rs, int skip, int size) throws SQLException, ClassNotFoundException, IOException
    {
        return ProcessorUtilslbc.toBeanList(rs, this.type, skip, size);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 值对象处理器，根据映射信息返回一个JavaBean，结果集中只有一行数据，<br>
     * 该处理器能自动把结果集中的值按列的名称映射到javaBean中，如结果集中有名称为”name”的字段， 那么只要该java对象中有getName()方法就能把结果集合中”name”对应的值映射到对象中
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:58:08
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object beanMappingProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        return ProcessorUtilslbc.toBeanInner(resultSet, this.type);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 值对象集合处理器，根据映射信息返回一个ArrayList集合，集合中的每一个元素是一个javaBean,每个javaBean对应结果集合中一行数据，
     * 其中每个JavaBean中的数据映射关系和BeanMappingProcess同理
     * @see
     * @param resultSet
     * @param columns
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:57:47
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object beanMappingListProcessor(ResultSet resultSet, String columns[]) throws SQLException, ClassNotFoundException, IOException
    {
        return ProcessorUtilslbc.toBeanListInner(resultSet, this.type, columns);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 值对象处理器，返回一个JavaBean，结果集中只有一行数据，该处理器能自动把结果集中的值按列的名称映射到javaBean中，<br>
     * 如结果集中有名称为”name”的字段， 那么只要该java对象中有getName()方法就能把结果集合中”name”对应的值映射到对象中
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:58:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object beanProcessResultSet(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        return ProcessorUtilslbc.toBeanInner(resultSet, type);
    }

    /**
     * *********************************************************** <br>
     * 说明： 值对象集合处理器，返回一个ArrayList集合，集合中的每一个元素是一个javaBean,每个javaBean对应结果集合中一行数据，
     * 其中每个JavaBean中的数据映射关系和BeanProcess同理
     * @see
     * @param rs
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:31:27
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public Object beanListProcessResultSet(ResultSet rs) throws SQLException, ClassNotFoundException, IOException
    {
        return ProcessorUtilslbc.toBeanListInner(rs, this.type, null);
    }
    
}
