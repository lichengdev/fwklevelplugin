//package pers.bc.utils.yonyou;
//
//import java.lang.reflect.Array;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.util.Collection;
//import java.util.LinkedList;
//
//import nc.jdbc.framework.processor.ResultSetProcessor;
//
//import org.apache.commons.lang.StringUtils;
//
///**
// * 
// * @qualiFild pers.bc.utils.yonyou.GeneralVOProcessor.java<br>
// * @author：LiBencheng<br>
// * @date Created on 2020-5-19<br>
// * @version 1.0<br>
// */
//public class GeneralVOProcessor<T extends GeneralVO> implements ResultSetProcessor
//{
//    /**
//     * @date 2020-5-19
//     */
//    private static final long serialVersionUID = 2218395705469345924L;
//    private Class clazzType = GeneralVO.class;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：
//     * @see
//     * @param clazzType
//     * @methods pers.bc.utils.yonyou.GeneralVOProcessor#构造方法
//     * @author LiBencheng
//     * @date Created on 2020-5-19
//     * @time 下午12:00:59
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public GeneralVOProcessor(Class<T> clazzType)
//    {
//        super();
//        
//        this.clazzType = clazzType;
//    }
//    
//    /**
//     * 
//     */
//    @SuppressWarnings("unchecked")
//    public T[] handleResultSet(ResultSet rs) throws SQLException
//    {
//        Collection<T> listResult = new LinkedList<T>();
//        
//        ResultSetMetaData metaData = rs.getMetaData();
//        
//        int iCols = metaData.getColumnCount();
//        
//        while (rs.next())
//        {
//            T generalVO = null;
//            
//            try
//            {
//                generalVO = (T) clazzType.newInstance();
//            }
//            catch (Exception ex)
//            {
//                throw new SQLException("Cannot create " + clazzType.getName() + ": " + ex.getMessage());
//            }
//            
//            for (int i = 1; i <= iCols; i++)
//            {
//                String strRealName =
//                    StringUtils.isNotEmpty(metaData.getColumnLabel(i)) ? metaData.getColumnLabel(i) : metaData.getColumnName(i);
//                
//                generalVO.setAttributeValue(strRealName.toLowerCase(), rs.getObject(i));
//            }
//            
//            listResult.add(generalVO);
//        }
//        
//        return listResult.size() > 0 ? listResult.toArray((T[]) Array.newInstance(clazzType, listResult.size())) : null;
//    }
//}
