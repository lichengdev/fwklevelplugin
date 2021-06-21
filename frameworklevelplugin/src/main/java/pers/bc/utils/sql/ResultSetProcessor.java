package pers.bc.utils.sql;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * 结果集处理接口 ,处理结果集并返回需要的数据结构
 * @qualiFild nc.pub.itf.tools.sql.ResultSetProcessor.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class ResultSetProcessor implements Serializable
{
    
    private static final long serialVersionUID = 7218140940254577234L;
    private int columnIndex = 1;
    private String columnName = null;
    private static ResultSetProcessor resultSetProcessor = null;
    
    public static ResultSetProcessor getInstance()
    {
        if (null == resultSetProcessor) resultSetProcessor = new ResultSetProcessor();
        return resultSetProcessor;
    }
    
    public ResultSetProcessor()
    {
    }
    
    public ResultSetProcessor(int columnIndex)
    {
        this.columnIndex = columnIndex;
    }
    
    public ResultSetProcessor(String columnName)
    {
        this.columnName = columnName;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:05:51
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object ArrayProcess(ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            return ProcessorUtilslbc.toArray(resultSet);
        }
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 数据库结果集
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:05:43
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object ArrayListProcess(ResultSet resultSet) throws SQLException
    {
        List<Object[]> result = new ArrayList<Object[]>();
        while (resultSet.next())
        {
            result.add(ProcessorUtilslbc.toArray(resultSet));
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 列值处理器，返回一个Java对象，结果集中只有一行数据， 该对象对应与结果集中某一列的值，该处理器通过结果集列的序号或名称来确定列
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:09:44
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object ColumnProcessor(ResultSet resultSet) throws SQLException
    {
        
        if (resultSet.next())
        {
            if (this.columnName == null)
            {
                return resultSet.getObject(this.columnIndex);
            }
            else
            {
                return resultSet.getObject(this.columnName);
            }
        }
        else
        {
            return null;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：返回一个阿ArrayList对象，结果集中有多行数据，该对象对应与结果集中某一列的值，该处理器通过结果集列的序号或名称来确定列
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:10:01
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object ColumnListProcessor(ResultSet resultSet) throws SQLException
    {
        List<Object> result = new ArrayList<Object>();
        while (resultSet.next())
        {
            if (this.columnName == null)
            {
                result.add(resultSet.getObject(this.columnIndex));
            }
            else
            {
                result.add(resultSet.getObject(this.columnName));
            }
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： HashMap处理器，返回一个HashMap, 结果集中只有一行数据，其中结果集合中每一列的列名和列值对应HashMap的一个关键字和相应的值
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:30:29
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object MapProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        while (resultSet.next())
        {
            return ProcessorUtilslbc.toMap(resultSet);
        }
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： HashMap集合处理器，返回一个ArrayList集合，集合中的每一个元素是一个HashMap，每个HashMap对应结果集中的一行数据,
     * 其中结果集合中每一列的列名和列值对应HashMap的一个关键字和相应的值
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:30:32
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object MapListProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        List<Map<?, ?>> results = new ArrayList<Map<?, ?>>();
        while (resultSet.next())
        {
            results.add(ProcessorUtilslbc.toMap(resultSet));
        }
        return results;
    }
    
    public Object tableModelProcessor(ResultSet rs) throws SQLException
    {
        DefaultTableModel model = new DefaultTableModel();
        // the table headers
        ResultSetMetaData rsMeta = rs.getMetaData();
        int cols = rsMeta.getColumnCount();
        for (int i = 1; i <= cols; i++)
        {
            model.addColumn(rsMeta.getColumnName(i));
        }
        // the table data
        while (rs.next())
        {
            Vector<Object> data = new Vector<Object>();
            for (int i = 1; i <= cols; i++)
            {
                data.add(rs.getObject(i));
            }
            model.addRow(data);
        }
        
        return model;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： JSON集合处理器，
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:15:33
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object JSONProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        while (resultSet.next())
        {
            return ProcessorUtilslbc.toJSON(resultSet);
        }
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： JSON集合处理器，返回一个ArrayList集合
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:07:53
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public Object JSONListProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        List<Object> results = new ArrayList<Object>();
        while (resultSet.next())
        {
            results.add(ProcessorUtilslbc.toJSON(resultSet));
        }
        return results;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： XML集合处理器
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:15:17
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object XMLProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        while (resultSet.next())
        {
            return PubConsUtilbc.XMLProcessor + ProcessorUtilslbc.toXML(resultSet);
        }
        
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：XML集合处理器，返回一个ArrayList集合，
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:15:04
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object XMLListProcessor(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        StringBuilder results =
            new StringBuilder(PubConsUtilbc.XMLProcessor).append(PubConsUtilbc.RN).append("<resultset>").append(PubConsUtilbc.RN);
        while (resultSet.next())
        {
            results.append(ProcessorUtilslbc.toXML(resultSet)).append(PubConsUtilbc.RN);
        }
        return results.append("</resultset>");
        // List<Object> results = new ArrayList<Object>();
        // while (resultSet.next())
        // {
        // results.add(ProcessorUtilslbc.toXML(resultSet));
        // }
        // return results;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 结果集转换成向量集合
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Vector<?>
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:22:35
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Vector<?> vectorProcess(ResultSet resultSet) throws SQLException
    {
        Vector<Vector<Object>> v = new Vector<Vector<Object>>();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int nColumnCount = rsmd.getColumnCount();
        // limit sql
        int row_ = -1;
        for (int j = 1; j <= nColumnCount; j++)
        {
            String colName = rsmd.getColumnLabel(j);
            if (colName == null)
            {
                colName = rsmd.getColumnName(j);
            }
            colName = colName.toLowerCase();
            if ("row_".equalsIgnoreCase(colName) || "rownum_".equalsIgnoreCase(colName))
            {
                row_ = j;
                break;
            }
        }
        while (resultSet.next())
        {
            Vector<Object> tmpV = new Vector<Object>();
            for (int i = 1; i <= nColumnCount; i++)
            {
                if (row_ == i)
                {
                    continue;
                }
                Object o;
                if (rsmd.getColumnType(i) == Types.CHAR || rsmd.getColumnType(i) == Types.VARCHAR)
                {
                    o = resultSet.getString(i);
                    
                }
                else
                {
                    o = resultSet.getObject(i);
                }
                
                tmpV.addElement(o);
            }
            v.addElement(tmpV);
        }
        return v;
    }
}
