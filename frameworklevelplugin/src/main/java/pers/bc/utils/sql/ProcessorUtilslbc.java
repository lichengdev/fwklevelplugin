package pers.bc.utils.sql;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.helpers.AttributesImpl;

import pers.bc.utils.Bean.BeanHelper;
import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.encrypt.Base64;
import pers.bc.utils.file.InOutUtil;
import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.pub.XMLUtilbc;

/**
 * @qualiFild nc.pub.itf.tools.sql.ProcessorUtilslbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class ProcessorUtilslbc
{
    
    /**
     * *********************************************************** <br>
     * 说明： 结果集转换成数组
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Object[] @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:12:13
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public static Object[] toArray(ResultSet resultSet) throws SQLException
    {
        ResultSetMetaData meta = resultSet.getMetaData();
        int cols = meta.getColumnCount();
        Object[] result = new Object[cols];
        for (int i = 0; i < cols; i++)
        {
            result[i] = resultSet.getObject(i + 1);
        }
        return result;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 结果集转换成HashMap
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @Map<String,Object>
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:12:05
     * @veresultSetion 1.0 <br>
     * @throws IOException
     * @throws ClassNotFoundException <br>
     */
    public static Map<String, Object> toMap(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();
        Map<String, Object> resultSetValues = new HashMap<String, Object>();
        for (int i = 1; i <= cols; i++)
        {
            Object value = getColumnValue(metaData.getColumnType(i), resultSet, i);
            String propName = metaData.getColumnLabel(i).toLowerCase();
            if (propName == null) propName = metaData.getColumnName(i).toLowerCase();
            resultSetValues.put(propName, value);
        }
        return resultSetValues;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 结果集转换成JSON
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:10:54
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Object toJSON(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();
        StringBuilder strb = new StringBuilder("{");
        for (int i = 1; i <= cols; i++)
        {
            Object value = getColumnValue(metaData.getColumnType(i), resultSet, i);
            String propName = metaData.getColumnLabel(i).toLowerCase();
            if (propName == null) propName = metaData.getColumnName(i).toLowerCase();
            strb.append("\"").append(propName).append("\":\"");
            if (null != value) strb.append(value);
            strb.append("\",").append(PubConsUtilbc.RN);
        }
        return strb.delete(strb.length() - 3, strb.length()).append("}").append(PubConsUtilbc.RN);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 结果集转换成XML
     * @see
     * @param resultSet
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws IOException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:11:06
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Object toXML(ResultSet resultSet) throws SQLException, ClassNotFoundException, IOException
    {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();
        StringBuilder strb = new StringBuilder("<result>").append(PubConsUtilbc.RN);
        for (int i = 1; i <= cols; i++)
        {
            Object value = getColumnValue(metaData.getColumnType(i), resultSet, i);
            String propName = metaData.getColumnLabel(i).toLowerCase();
            if (propName == null) propName = metaData.getColumnName(i).toLowerCase();
            strb.append("<").append(propName).append(">");
            if (null != value) strb.append(value);
            strb.append("</").append(propName).append(">").append(PubConsUtilbc.RN);
        }
        return strb.append("</result>");
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 果集合转换成java bean
     * @see
     * @param resultSet
     * @param type
     * @return
     * @throws SQLException
     * @Object
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午9:51:06
     * @version 1.0 <br>
     *************************************************************          <br>
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object toBeanInner(ResultSet resultSet, Class<?> type) throws SQLException, ClassNotFoundException, IOException
    {
        Object target = null;
        MethodAndTypes methodAndTypes = getBeanInvokeAndTypes(type, resultSet, null);
        if (resultSet.next())
        {
            target = toBeanOfCurrentRow(resultSet, type, methodAndTypes);
        } // end if
        return target;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 需要 granite.jar
     * @see
     * @Object
     * @author licheng
     * @date Created on 2019年8月4日
     * @time 下午11:41:42
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Object toBeanOfCurrentRow(ResultSet resultSet, Class<?> type, MethodAndTypes methodAndTypes)
            throws SQLException, ClassNotFoundException, IOException
    {
        Object target = null;
        
        target = newInstance(type);
        for (int i = 1; i <= methodAndTypes.types.length; i++)
        {
            Object value = getColumnValue(methodAndTypes.types[i - 1], resultSet, i);
            if (value == null) continue;
            Method invoke = methodAndTypes.invokes[i - 1];
            if (invoke == null)
            {
                // 如果是null则不赋值
                continue;
            }
            Converter converter = methodAndTypes.converters[i - 1];
            if (converter != null) value = converter.convert(value, invoke.getParameterTypes()[0]);
            BeanHelper.invokeMethod(target, invoke, value);
        }
        return target;
    }
    
    /**
     * 结果集转换成java bean 数组
     * 
     * @param resultSet
     * @param type
     * @return
     * @throws SQLException
     */
    public static List<?> toBeanListInner(ResultSet resultSet, Class<?> type, String columns[])
            throws SQLException, ClassNotFoundException, IOException
    {
        MethodAndTypes methodAndTypes = getBeanInvokeAndTypes(type, resultSet, columns);
        List<Object> list = new ArrayList<Object>();
        while (resultSet.next())
        {
            Object target = newInstance(type);
            for (int i = 1; i <= methodAndTypes.types.length; i++)
            {
                if (methodAndTypes.propNames[i - 1] == null)
                {
                    continue;
                }
                Object value = getColumnValue(methodAndTypes.types[i - 1], resultSet, methodAndTypes.colIdx[i - 1]);
                if (value == null) continue;
                Method invoke = methodAndTypes.invokes[i - 1];
                
                if (invoke == null) continue;
                Converter converter = methodAndTypes.converters[i - 1];
                if (converter != null) value = converter.convert(value, invoke.getParameterTypes()[0]);
                BeanHelper.invokeMethod(target, invoke, value);
            }
            list.add(target);
        }
        return list;
    }
    
    public static List<?> toBeanList(ResultSet resultSet, Class<?> type, int skip, int size)
            throws SQLException, ClassNotFoundException, IOException
    {
        MethodAndTypes methodAndTypes = getBeanInvokeAndTypes(type, resultSet, null);
        List<Object> list = new ArrayList<Object>();
        int index = -1;
        while (index < skip && resultSet.next())
        {
            index++;
        }
        if (index < skip) return list;
        int offset = 0;
        do
        {
            offset++;
            Object target = newInstance(type);
            for (int i = 1; i <= methodAndTypes.types.length; i++)
            {
                Object value = getColumnValue(methodAndTypes.types[i - 1], resultSet, i);
                if (value == null) continue;
                Method invoke = methodAndTypes.invokes[i - 1];
                if (invoke == null)
                {
                    // 如果是null则不赋值
                    continue;
                }
                Converter converter = methodAndTypes.converters[i - 1];
                if (converter != null) value = converter.convert(value, invoke.getParameterTypes()[0]);
                BeanHelper.invokeMethod(target, invoke, value);
                // populate(target, value, methodAndTypes.invokes[i - 1]);
            }
            list.add(target);
        }
        while (resultSet.next() && offset < size);
        
        return list;
    }
    
    public static MethodAndTypes getBeanInvokeAndTypes(Class<?> type, ResultSet resultSet, String[] columns) throws SQLException
    {
        MethodAndTypes retResult = new MethodAndTypes();
        
        Object bean = newInstance(type);
        ResultSetMetaData metaData = resultSet.getMetaData();
        int cols = metaData.getColumnCount();
        int len = columns == null ? 0 : columns.length;
        if (len == 0)
        {
            len = cols;
        }
        Method[] invokes = new Method[len];
        // granite.jar
        Converter[] converters = new Converter[len];
        String[] propNames = new String[len];
        int[] idxs = new int[len];
        int[] types = new int[len];
        int j = 0;
        for (int i = 0; i < cols && j < len; i++)
        {
            int t = metaData.getColumnType(i + 1);
            String propName = metaData.getColumnLabel(i + 1).toLowerCase();
            if (propName == null) propName = metaData.getColumnName(i + 1).toLowerCase();
            if (columns != null) if (!columns[j].toLowerCase().equals(propName))
            {
                continue;
            }
            
            idxs[j] = i + 1;
            propNames[j] = propName;
            types[j] = t;
            j++;
        }
        
        Method[] temps = BeanHelper.getMethods(bean.getClass(), propNames);
        for (int i = 0; i < len; i++)
        {
            invokes[i] = temps[i];
        }
        
        retResult.propNames = propNames;
        retResult.invokes = invokes;
        retResult.types = types;
        retResult.converters = converters;
        retResult.colIdx = idxs;
        return retResult;
    }
    
    private static Object newInstance(Class<?> c) throws SQLException
    {
        try
        {
            return c.newInstance();
        }
        catch (InstantiationException e)
        {
            throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
        }
        catch (IllegalAccessException e)
        {
            throw new SQLException("Cannot create " + c.getName() + ": " + e.getMessage());
        }
    }
    
    public static Object getColumnValue(int type, ResultSet resultSet, int i) throws SQLException, ClassNotFoundException, IOException
    {
        Object value;
        switch (type)
        {
            case Types.VARCHAR :
            case Types.CHAR :
                value = resultSet.getString(i);
                break;
            // case Types.INTEGER:
            // case Types.DECIMAL:
            // value = new Integer(resultSet.getInt(i));
            // break;
            case Types.BLOB :
            case Types.LONGVARBINARY :
            case Types.VARBINARY :
            case Types.BINARY :
                value = InOutUtil.deserialize(resultSet.getBytes(i));
                break;
            case Types.CLOB :
                value = getClob(resultSet, i);
                break;
            default :
                value = resultSet.getObject(i);
                break;
        }
        return value;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019年8月4日
     * @time 下午11:58:45
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getClob(ResultSet resultSet, int columnIndex) throws SQLException
    {
        Reader resultSetReader = null;
        BufferedReader reader = null;
        StringBuffer buffer = null;
        try
        {
            resultSetReader = resultSet.getCharacterStream(columnIndex);
            if (resultSetReader == null) return null;
            reader = new BufferedReader(resultSetReader);
            buffer = new StringBuffer();
            while (true)
            {
                String c = reader.readLine();
                if (c == null)
                {
                    break;
                }
                buffer.append(c);
                buffer.append("\n");
            }
        }
        catch (IOException e)
        {
            
        }
        finally
        {
            try
            {
                if (resultSetReader != null) resultSetReader.close();
                if (reader != null) reader.close();
            }
            catch (Exception e)
            {
                
            }
        }
        
        return buffer.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param resultSet
     * @param columnIndex
     * @return
     * @throws SQLException
     * @byte[] @author licheng
     * @date Created on 2019-7-24
     * @time 下午8:21:00
     * @veresultSetion 1.0 <br>
     *************************************************************                 <br>
     */
    public static byte[] getBlob(ResultSet resultSet, int columnIndex) throws SQLException
    {
        return resultSet.getBytes(columnIndex);
    }
    
    static class MethodAndTypes
    {
        
        public String[] propNames = null;
        
        public Method[] invokes = null;
        
        public int[] types = null;
        /** 需要 granite.jar */
        public Converter[] converters = null;
        
        public int[] colIdx = null;
        
    }
    
    static final class JSONSerializer implements Serializable
    {
        
        /**
         * 
         */
        private static final long serialVersionUID = 226339274312310277L;
        private OutputStream os;
        
        public JSONSerializer(OutputStream os)
        {
            this.os = os;
        }
        
        private final void writeChar(char c) throws IOException
        {
            this.os.write((int) c);
        }
        
        public void startDocument() throws IOException
        {
            if (this.os == null)
            {
                this.os = System.out;
            }
        }
        
        public void endDocument() throws IOException
        {
            this.os.flush();
        }
        
        public void startObject() throws IOException
        {
            this.writeChar('{');
        }
        
        public void endObject() throws IOException
        {
            this.writeChar('}');
        }
        
        public void startElement(String key) throws IOException
        {
            this.writeChar('"');
            this.os.write(key.getBytes());
            this.writeChar('"');
            this.writeChar(':');
        }
        
        public void element(Object value) throws IOException
        {
            byte[] valueString = String.valueOf(value).getBytes();
            if (value == null || value instanceof Number || value instanceof Boolean)
            {
                this.os.write(valueString);
            }
            else
            {
                this.writeChar('"');
                this.os.write(valueString);
                this.writeChar('"');
            }
        }
        
        public void separeElement() throws IOException
        {
            this.writeChar(',');
        }
        
        public void startArray() throws IOException
        {
            this.writeChar('[');
        }
        
        public void endArray() throws IOException
        {
            this.writeChar(']');
        }
        
    }
    
    static class JSONProcessor implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 8878498854207512359L;
        
        private final static String DEFAULT_ROOT = "result-set";
        
        private JSONSerializer serializer;
        
        public void setOutputStream(OutputStream outputStreamPtr)
        {
            setOutputStream(outputStreamPtr);
            this.serializer = new JSONSerializer(outputStreamPtr);
        }
        
        public Object handleRS(ResultSet rs) throws SQLException
        {
            try
            {
                ResultSetMetaData rsm = rs.getMetaData();
                this.serializer.startDocument();
                this.serializer.startObject();
                this.serializer.startElement(DEFAULT_ROOT);
                this.serializer.startArray();
                boolean hasNext = rs.next();
                while (hasNext)
                {
                    this.serializer.startObject();
                    for (int i = 1; i <= rsm.getColumnCount(); i++)
                    {
                        this.serializer.startElement(rsm.getColumnName(i));
                        this.serializer.element(rs.getObject(i));
                        if (i < rsm.getColumnCount())
                        {
                            this.serializer.separeElement();
                        }
                    }
                    this.serializer.endObject();
                    hasNext = rs.next();
                    if (hasNext)
                    {
                        this.serializer.separeElement();
                    }
                }
                this.serializer.endArray();
                this.serializer.endObject();
                this.serializer.endDocument();
            }
            catch (IOException e)
            {
                throw new SQLException(e.getMessage());
            }
            return null;
        }
        
    }
    
    static class CSVProcessor implements Serializable
    {
        
        private static final long serialVersionUID = 2360860001162805563L;
        
        protected OutputStream outputStreamPtr;
        
        public void setOutputStream(OutputStream outputStreamPtr)
        {
            this.outputStreamPtr = outputStreamPtr;
        }
        
        private final static char COMMA = ';';
        
        private char separator;
        
        public CSVProcessor()
        {
            this.separator = COMMA;
        }
        
        public CSVProcessor(char separator)
        {
            this.separator = separator;
        }
        
        public Object handleRS(ResultSet rs) throws SQLException
        {
            ResultSetMetaData rsMeta = rs.getMetaData();
            int cols = rsMeta.getColumnCount();
            while (rs.next())
            {
                try
                {
                    for (int i = 1; i <= cols; i++)
                    {
                        this.outputStreamPtr.write(rs.getBytes(i));
                        if (i < cols)
                        {
                            this.outputStreamPtr.write((int) separator);
                        }
                    }
                    this.outputStreamPtr.write((int) '\n');
                }
                catch (Exception e)
                {
                    LoggerUtilbc.getInstance("httplogs").exception("handleRSThw", e);
                }
            }
            return null;
        }
    }
    
    static class XMLProcessor implements Serializable
    {
        private static final long serialVersionUID = -30873584088551805L;
        
        private final static String EMPTY_URI = "", DEFAULT_ENCODING = "UTF-8", DEFAULT_ROOT = "result-set", DEFAULT_ROW = "row";
        
        private TransformerHandler th;
        
        private Templates template;
        
        private String rootElement, rowElement;
        
        private Map<?, ?> xslParams;
        
        /**
         * 
         * @param rootElement
         * @param rowElement
         */
        public void SetRootRowNames(String rootElement, String rowElement)
        {
            this.rootElement = rootElement;
            this.rowElement = rowElement;
        }
        
        protected OutputStream outputStreamPtr;
        
        /**
         * 
         * @param outputStreamPtr
         */
        public void setOutputStream(OutputStream outputStreamPtr)
        {
            this.outputStreamPtr = outputStreamPtr;
        }
        
        /**
         * 
         * @param f
         * @throws TransformerConfigurationException
         */
        public void setXSL(File f) throws TransformerConfigurationException
        {
            
            this.template = XMLUtilbc.createTemplate(f);
        }
        
        /**
         * 
         * @param is
         * @throws TransformerConfigurationException
         */
        public void setXSL(InputStream is) throws TransformerConfigurationException
        {
            this.template = XMLUtilbc.createTemplate(is);
        }
        
        /**
         * 
         * @param xslParams
         */
        public void setXslParams(Map<?, ?> xslParams)
        {
            this.xslParams = xslParams;
        }
        
        /**
         * 
         */
        public Object handleRS(ResultSet rs) throws SQLException
        {
            try
            {
                if (this.rootElement == null || this.rowElement == null)
                {
                    this.rootElement = DEFAULT_ROOT;
                    this.rowElement = DEFAULT_ROW;
                }
                
                if (this.template == null)
                {
                    this.th = XMLUtilbc.createTransformerHandler();
                    Transformer serializer = this.th.getTransformer();
                    serializer.setOutputProperty(OutputKeys.ENCODING, DEFAULT_ENCODING);
                }
                else
                {
                    this.th = XMLUtilbc.createTransformerHandler(this.template);
                    if (this.xslParams != null)
                    {
                        Transformer serializer = this.th.getTransformer();
                        Object[] keys = this.xslParams.keySet().toArray();
                        for (int i = 0; i < keys.length; i++)
                        {
                            String key = keys[i].toString();
                            Object value = this.xslParams.get(keys[i]);
                            
                            serializer.setParameter(key, value);
                        }
                    }
                }
                this.th.setResult(new StreamResult(this.outputStreamPtr));
                
                AttributesImpl atts = new AttributesImpl();
                this.th.startDocument();
                this.th.startElement(EMPTY_URI, this.rootElement, this.rootElement, atts);
                
                ResultSetMetaData rsMeta = rs.getMetaData();
                while (rs.next())
                {
                    this.th.startElement(EMPTY_URI, this.rowElement, this.rowElement, atts);
                    
                    for (int i = 1; i <= rsMeta.getColumnCount(); i++)
                    {
                        if (rs.getObject(i) != null)
                        {
                            String colname = rsMeta.getColumnName(i);
                            String colValue = null;
                            if (rsMeta.getColumnType(i) == Types.BINARY || rsMeta.getColumnType(i) == Types.LONGVARBINARY)
                            {
                                colValue = Base64.decodeToString(rs.getBytes(i));
                            }
                            else
                            {
                                colValue = new String(rs.getString(i).getBytes(DEFAULT_ENCODING));
                            }
                            
                            this.th.startElement(EMPTY_URI, colname, colname, atts);
                            this.th.characters(colValue.toCharArray(), 0, colValue.length());
                            this.th.endElement(EMPTY_URI, colname, colname);
                        }
                    }
                    this.th.endElement(EMPTY_URI, this.rowElement, this.rowElement);
                }
                
                this.th.endElement(EMPTY_URI, this.rootElement, this.rootElement);
                this.th.endDocument();
            }
            catch (Exception e)
            {
                
            }
            return null;
        }
    }
}
