/**
 * <a href="http://www.cpupk.com/decompiler">Eclipse Class Decompiler</a> plugin, Copyright (c) 2017 Chen
 * Chao.
 */
package pers.bc.utils.sql;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pers.bc.utils.Bean.BeanHelper;
import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.pub.ArrayUtilbc;
import pers.bc.utils.pub.CollectionUtil;
import pers.bc.utils.pub.StringUtilbc;

/**
 * 处理和SQL语句相关的工具类
 * @qualiFild nc.pub.itf.tools.sql.SQLHelper.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class SQLHelper implements PubConsUtilbc
{
    
    private int iCount = 200;
    @SuppressWarnings("rawtypes")
    public static final HashMap<Class, String> hashFieldSQL = new HashMap<Class, String>()
    {
        private static final long serialVersionUID = -2518812495109758605L;
        
        {
            put(String.class, " varchar(200) null");
            
            put(int.class, " smallint null");
            put(Integer.class, " smallint null");
            
            put(boolean.class, " char(1) null");
            put(Boolean.class, " char(1) null");
            
            put(Date.class, " char(10) null");
            
            put(double.class, " decimal(16,4) null");
            put(Double.class, " decimal(16,4) null");
        }
    };
    
    public int getiCount()
    {
        return iCount;
    }
    
    public void setiCount(int iCount)
    {
        this.iCount = iCount;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：拼接两个SQL语句中的条件表达式
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:07:58
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String appendExtraCond(String cond, String extraCond)
    {
        if (StringUtilbc.isEmpty(cond) && StringUtilbc.isEmpty(extraCond))
        {
            return null;
        }
        
        if (StringUtilbc.isEmpty(extraCond))
        {
            return cond;
        }
        
        if (StringUtilbc.isEmpty(cond))
        {
            return extraCond;
        }
        
        if (extraCond.trim().startsWith("(") && extraCond.trim().endsWith(")"))
        {
            return cond + " and " + extraCond;
        }
        
        return cond + " and (" + extraCond + ")";
    }
    
    /***************************************************************************
     * 逻辑型字段的空值判断SQL片断<br>
     * Created on 2011-3-4 15:56:25<br>
     * @param strFieldCode
     * @return isnull(field,'N')='N'
     * @author zc
     ***************************************************************************/
    public static String getBoolNullSql(String strFieldCode)
    {
        return MessageFormat.format(" isnull({0},''{1}'')=''{1}'' ", strFieldCode, '~');
    }
    
    /***************************************************************************
     * <br>
     * Created on 2011-3-4 15:02:05<br>
     * @param strFieldCode
     * @return strFieldCode='~'
     * @author zc
     ***************************************************************************/
    public static String getEqualsWaveSql(String strFieldCode)
    {
        return " " + strFieldCode + "='~' ";
    }
    
    /***************************************************************************
     * 文本型字段的空值判断SQL片断<br>
     * Created on 2011-3-4 15:56:25<br>
     * @param strFieldCode
     * @return isnull(strFieldCode,'~')='~'
     * @author zc
     ***************************************************************************/
    public static String getNullSql(String strFieldCode)
    {
        return MessageFormat.format(" isnull({0},''{1}'')=''{1}'' ", strFieldCode, '~');
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：数值型字段的空值判断SQL片断
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:40:12
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getNumberNullSql(String strFieldCode)
    {
        return MessageFormat.format(" isnull(cast({0} as char),''{1}'')=''{1}'' ", strFieldCode, '~');
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param strPk_corp 本级公司主键
     * @param blIncludeParents 是否包含上级公司
     * @param blIncludeChirldren 是否包含下级公司
     * @return String 返回一个形如“select pk_corp from bd_corp”的sql语句
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:40:44
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getPkCorpSQL(String strPk_corp, boolean blIncludeParents, boolean blIncludeChirldren)
    {
        String strSQL = "";
        
        String strIncludeParents = "(a.pk_corp='" + strPk_corp + "' and a.innercode=substring(t1.innercode,1,len(a.innercode)))";
        String strIncludeChirldren = "(a.pk_corp='" + strPk_corp + "' and t1.innercode=substring(a.innercode,1,len(t1.innercode)))";
        
        if (blIncludeParents)
        {
            strSQL = strIncludeParents;
        }
        
        if (blIncludeChirldren)
        {
            strSQL = strSQL.length() > 0 ? strSQL + " or " + strIncludeChirldren : strIncludeChirldren;
        }
        
        strSQL = "select a.pk_corp from bd_corp a, bd_corp t1" + (strSQL.length() > 0 ? " where (" + strSQL + ")" : "");
        
        return strSQL;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param strValues
     * @return 把strValues数组中的合并成一个或者多个可以用于in语句的字符串
     * @String[] @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:43:08
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    protected static String[] joinToInSql(String... strValues)
    {
        if (strValues == null || strValues.length == 0)
        {
            return null;
        }
        
        int iCount = 100;// 每组只有100个值用来拼in的语句
        
        List<String> arrListInSQL = new ArrayList<String>();
        List<String> arrListExistValue = new ArrayList<String>();// 已经存在的值，以保证不重复
        
        StringBuilder strbdrInSQL = new StringBuilder();
        
        for (int i = 0, iIndex = 0; i < strValues.length; i++)
        {
            if (StringUtilbc.isBlank(strValues[i]) || arrListExistValue.contains(strValues[i].trim()))
            {
                continue;
            }
            
            iIndex++;
            arrListExistValue.add(strValues[i].trim());
            
            strbdrInSQL.append(",'").append(strValues[i]).append("'");
            
            if (iIndex == iCount || i == strValues.length - 1)
            {
                if (strbdrInSQL.toString().trim().length() > 0)
                {
                    arrListInSQL.add(strbdrInSQL.substring(1));
                }
                
                iCount = 0;
                strbdrInSQL = new StringBuilder();
            }
        }
        
        if (strbdrInSQL.length() > 0 && !arrListInSQL.contains(strbdrInSQL.toString()))// 保证最后一个也在in语句的列表中
        {
            arrListInSQL.add(strbdrInSQL.substring(1));
        }
        
        return arrListInSQL.toArray(new String[0]);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param strValues
     * @param iCount 每组只有 iCount 个值用来拼in的语句，如果为 -1，则不限长度
     * @return 把strValues数组中的合并成一个或者多个可以用于in语句的字符串，返回值形式：
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:43:55
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String joinToInSql(String strValues[], int iCount)
    {
        if (strValues == null || strValues.length == 0)
        {
            return null;
        }
        
        List<String> arrListInSQL = new ArrayList<String>();
        List<String> arrListExistValue = new ArrayList<String>();// 已经存在的值，以保证不重复
        
        StringBuilder strbdrInSQL = new StringBuilder();
        
        for (int i = 0, iIndex = 0; i < strValues.length; i++)
        {
            if (StringUtilbc.isBlank(strValues[i]) || arrListExistValue.contains(strValues[i].trim()))
            {
                continue;
            }
            
            iIndex++;
            arrListExistValue.add(strValues[i].trim());
            
            strbdrInSQL.append(",'").append(transfer(strValues[i])).append("'");
            
            if (iCount == -1 && i == strValues.length - 1)
            {
                arrListInSQL.add(strbdrInSQL.substring(1));
                continue;
            }
            
            if (iIndex == iCount || i == strValues.length - 1)
            {
                if (strbdrInSQL.toString().trim().length() > 0)
                {
                    arrListInSQL.add(strbdrInSQL.substring(1));
                }
                
                iCount = 0;
                strbdrInSQL = new StringBuilder();
            }
        }
        
        if (strbdrInSQL.length() > 0 && !arrListInSQL.contains(strbdrInSQL.toString()))// 保证最后一个也在in语句的列表中
        {
            arrListInSQL.add(strbdrInSQL.substring(1));
        }
        
        return arrListInSQL.isEmpty() ? null : arrListInSQL.get(0);
    }
    
    /**
     * s *********************************************************** <br>
     * 说明：
     * @see
     * @String[] @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:06:23
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    protected static String[] joinToInSqls(String strValues[], int iCount)
    {
        if (strValues == null || strValues.length == 0)
        {
            return null;
        }
        
        List<String> arrListInSQL = new ArrayList<String>();
        List<String> arrListExistValue = new ArrayList<String>();// 已经存在的值，以保证不重复
        
        StringBuilder strbdrInSQL = new StringBuilder();
        
        for (int i = 0, iIndex = 0; i < strValues.length; i++)
        {
            
            if ((StringUtilbc.isBlank(strValues[i])) || arrListExistValue.contains(strValues[i].trim()))
            {
                continue;
            }
            
            iIndex++;
            arrListExistValue.add(strValues[i].trim());
            
            strbdrInSQL.append(",'").append(strValues[i]).append("'");
            
            if (iCount == -1 && i == strValues.length - 1)
            {
                arrListInSQL.add(strbdrInSQL.substring(1));
                continue;
            }
            
            if (iIndex == iCount || i == strValues.length - 1)
            {
                if (strbdrInSQL.toString().trim().length() > 0)
                {
                    arrListInSQL.add(strbdrInSQL.substring(1));
                }
                
                iIndex = 0;
                strbdrInSQL = new StringBuilder();
            }
        }
        
        if (strbdrInSQL.length() > 0 && !arrListInSQL.contains(strbdrInSQL.toString()))// 保证最后一个也在in语句的列表中
        {
            arrListInSQL.add(strbdrInSQL.substring(1));
        }
        
        return arrListInSQL.toArray(new String[0]);
    }
    
    /**************************************************************
     * <br>
     * Created on 2012-5-30 19:36:48<br>
     * @param strFieldCode
     * @param iCount
     * @param strValues 每个里面根据需要加上“%”
     * @return String[] (strFieldCode like 'strValue[0]' or ... or strFieldCode like 'strValue[n]'
     * @author Rocex Wang
     **************************************************************/
    public static String[] joinToLikeSqls(String strFieldCode, int iCount, String... strValues)
    {
        if (strValues == null || strValues.length == 0)
        {
            return null;
        }
        
        List<String> listLikeSQL = new ArrayList<String>();
        List<String> listExistValue = new ArrayList<String>();// 已经存在的值，以保证不重复
        
        StringBuilder strbdrInSQL = new StringBuilder();
        
        for (int i = 0, iIndex = 0; i < strValues.length; i++)
        {
            if (StringUtilbc.isBlank(strValues[i]) || listExistValue.contains(strValues[i].trim()))
            {
                continue;
            }
            iIndex++;
            listExistValue.add(strValues[i].trim());
            
            strbdrInSQL.append(" or ").append(strFieldCode).append(" like '").append(strValues[i]).append("'");
            
            if (iCount == -1 && i == strValues.length - 1)
            {
                listLikeSQL.add(strbdrInSQL.substring(4));
                continue;
            }
            
            if (iIndex == iCount || i == strValues.length - 1)
            {
                if (strbdrInSQL.toString().trim().length() > 0)
                {
                    listLikeSQL.add(strbdrInSQL.substring(4));
                }
                
                iIndex = 0;
                strbdrInSQL = new StringBuilder();
            }
        }
        
        if (strbdrInSQL.length() > 0 && !listLikeSQL.contains(strbdrInSQL.toString()))// 保证最后一个也在in语句的列表中
        {
            listLikeSQL.add(strbdrInSQL.substring(4));
        }
        
        return listLikeSQL.toArray(new String[0]);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:06:06
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    protected static String joinToString(Object[] objArray)
    {
        if (objArray == null || objArray.length == 0)
        {
            return null;
        }
        
        List<String> listExist = new ArrayList<String>();
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < objArray.length; i++)
        {
            // 判断重复
            if (listExist.contains(objArray[i].toString()))
            {
                continue;
            }
            
            listExist.add(objArray[i].toString());
            
            sb.append("'");
            sb.append(objArray[i].toString());
            sb.append("',");
        }
        
        if (sb.length() > 0)
        {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        listExist.clear();
        
        return sb.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 用在拼接sql语句中，把一个单引号转译成两个单引号，以防止SQL注入，调用这个方法不用区分前后台代码<br>
     *  需要commons-lang-2.1.jar
     * @String
     * @author licheng
     * @date Created on 2019年8月5日
     * @time 上午12:05:11
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String transfer(String strValue)
    {
        return StringUtilbc.replace(strValue, "'", "''");
    }
    
    /**
     * 拼装Insert SQL
     * @param obj
     * @return
     * @throws Exception
     */
    @Deprecated
    public String getInsertSql(Object obj) throws Exception
    {
        Map<String, Object> map = BeanHelper.transBean2Map(obj);
        if ((null == map) || (map.isEmpty())) return null;
        StringBuffer into = new StringBuffer();
        StringBuffer column = new StringBuffer(" (");
        StringBuffer value = new StringBuffer(" VALUES(");
        for (@SuppressWarnings("unused")
        Object key : map.keySet())
        {
            // if (SuperBaseVO.TABLENAME.equals(key))
            // {
            // into.append("INSERT INTO ").append(map.get(key));
            // continue;
            // }
            // if (!SuperBaseVO.ID.equals(key) && map.get(key) != null)
            // {
            // column.append(key).append(",");
            // value.append("'").append(String.valueOf(map.get(key)).replaceAll("'", "''")).append("',");
            // }
            // if (SuperBaseVO.ID.equals(key))
            // {
            // column.append(key).append(",");
            // value.append(map.get(SuperBaseVO.TABLENAME)).append("_seq.Nextval,");
            // }
        }
        column.setLength(column.length() - 1);
        column.append(")");
        value.setLength(value.length() - 1);
        value.append(")");
        
        // System.out.println(String.valueOf(into.append(column).append(value)));
        return String.valueOf(into.append(column).append(value));// + String.valueOf(column) +
                                                                 // String.valueOf(value);
    }
    
    /***
     * 
     * *********************************************************** <br>
     * 说明： 根据表名和列名称得到插入语句
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:05:55
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getInsertSQL(String table, String names[])
    {
        return getInsertSQL(table, names, null);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param table
     * @param names
     * @param pkName
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.sql.SQLHelper#getInsertSQL <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:22:39 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getInsertSQL(String table, String[] names, String pkName)
    {
        StringBuffer into = new StringBuffer("INSERT INTO ").append(table).append(" (");
        StringBuffer value = new StringBuffer(" VALUES(");
        for (int i = 0; i < ArrayUtilbc.getSize(names); i++)
        {
            into.append(names[i]).append(",");
            value.append("?,");
        }
        if (!ArrayUtilbc.isEmpty(pkName)) into.append(pkName).append(",");
        
        into.setLength(into.length() - 1);
        into.append(")");
        value.setLength(value.length() - 1);
        value.append(")");
        
        return into.append(value).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param table
     * @param map
     * @param pkName
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.sql.SQLHelper#getInsertSQL <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:22:35 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getInsertSQL(String table, Map<?, ?> map, String pkName)
    {
        StringBuffer into = new StringBuffer("INSERT INTO ").append(table).append(" (");
        StringBuffer value = new StringBuffer(" VALUES(");
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            into.append(entry.getKey()).append(",");
            value.append("'").append(map.get(entry.getKey())).append("',");
        }
        if (!ArrayUtilbc.isEmpty(pkName))
        {
            into.append(pkName).append(",");
            value.append("'").append("xxxxxxxxxxxxxxxxxxxx").append("',");
        }
        
        into.setLength(into.length() - 1);
        into.append(")");
        value.setLength(value.length() - 1);
        value.append(")");
        
        return into.append(value).toString();
    }
    
    /***
     * 
     * *********************************************************** <br>
     * 说明：根据表名和列名称得到更新语句
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:21
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getUpdateSQLByPK(String tableName, String[] names, String pkName)
    {
        return getUpdateSQLByCont(tableName, names, pkName);
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 根据表名和列名称得到更新语句
     * @see
     * @param tableName
     * @param names
     * @param conts
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-7-31
     * @time 下午1:38:52
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getUpdateSQLByCont(String tableName, String[] names, String... conts)
    {
        StringBuffer sql = new StringBuffer("UPDATE ").append(tableName).append(" SET  ");
        for (int i = 0; i < ArrayUtilbc.getSize(names); i++)
        {
            if (names[i].equalsIgnoreCase("ts")) continue;
            sql.append(names[i]).append("=?,");
        }
        sql.setLength(sql.length() - 1);
        sql.append(" WHERE ");// .append(cont).append("=?");
        for (int i = 0; i < ArrayUtilbc.getSize(conts); i++)
        {
            sql.append(conts[i]).append("=?,");
        }
        sql.setLength(sql.length() - 1);
        return sql.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:14
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getUpdateSQL(String tableName, String[] names)
    {
        StringBuffer sql = new StringBuffer("UPDATE ").append(tableName).append(" SET  ");
        for (int i = 0; i < ArrayUtilbc.getSize(names); i++)
        {
            if (names[i].equalsIgnoreCase("ts")) continue;
            sql.append(names[i]).append("=?,");
        }
        sql.setLength(sql.length() - 1);
        return sql.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 根据表名和列名称得到更新语句<br>
     * @see <br>
     * @param tableName
     * @param map key=value
     * @param conts 条件列
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.sql.SQLHelper#getUpdateSQLByCont <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:16:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getUpdateSQLByCont(String tableName, Map<?, ?> map, String... conts)
    {
        StringBuffer sql = new StringBuffer("UPDATE ").append(tableName).append(" SET  ");
        List<String> values = new ArrayList<String>();
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            String key = String.valueOf(entry.getKey());
            
            if (key.equalsIgnoreCase("ts")) continue;
            sql.append(key).append("='").append(null == entry.getValue() ? "" : entry.getValue()).append("'");
            if (!ArrayUtilbc.isEmpty(conts) && CollectionUtil.contains(key, conts))
            {
                values.add(null == entry.getValue() ? "" : entry.getValue().toString());
            }
        }
        sql.setLength(sql.length() - 1);
        if (!ArrayUtilbc.isEmpty(conts))
        {
            sql.append(" WHERE ");
            for (int i = 0; i < ArrayUtilbc.getSize(conts); i++)
            {
                sql.append(conts[i]).append("='").append(values.get(i)).append("'");
            }
            sql.setLength(sql.length() - 1);
        }
        return sql.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:33
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getDeleteByPKSQL(String tableName, String pkName)
    {
        return String.valueOf(new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ").append(pkName).append("=?"));
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:39
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getDeleteSQL(String tableName, String[] names)
    {
        StringBuffer sql = new StringBuffer("DELETE FROM ").append(tableName).append(" WHERE ");
        for (int i = 0; i < names.length; i++)
        {
            sql.append(names[i]).append("=? AND ");
        }
        sql.setLength(sql.length() - 4);
        return sql.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： map 条件 key=value<br>
     * @see <br>
     * @param tableName
     * @param map
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.sql.SQLHelper#getDeleteSQL <br>
     * @author licheng <br>
     * @date Created on 2019-9-20 <br>
     * @time 下午4:30:50 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getDeleteSQL(String tableName, Map<?, ?> map)
    {
        StringBuffer sql = new StringBuffer(" DELETE FROM ").append(tableName).append(" WHERE ");
        for (Map.Entry<?, ?> entry : map.entrySet())
        {
            sql.append(entry.getKey()).append("='").append(entry.getValue()).append("' AND ");
        }
        sql.setLength(sql.length() - 4);
        return sql.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:48
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getSelectSQL(String tableName, String[] names, boolean isAnd, String[] fields)
    {
        StringBuffer sql = new StringBuffer();
        if (fields == null) sql.append("SELECT * FROM " + tableName);
        else
        {
            sql.append("SELECT ");
            for (int i = 0; i < fields.length; i++)
            {
                sql.append(fields[i]).append(",");
                
            }
            sql.setLength(sql.length() - 1);
            sql.append(" FROM " + tableName);
        }
        String append = "AND ";
        if (!isAnd) append = "OR ";
        if (names == null || names.length == 0) return sql.toString();
        sql.append(" WHERE ");
        for (int i = 0; i < names.length; i++)
        {
            String name = names[i];
            sql.append(name).append("=? ");
            if (i != names.length - 1) sql.append(append);
        }
        
        return sql.toString();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:04:56
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getSelectSQL(String tableName, String[] fields)
    {
        StringBuffer sql = new StringBuffer();
        if (fields == null) sql.append("SELECT * FROM " + tableName);
        else
        {
            sql.append("SELECT ");
            for (int i = 0; i < fields.length; i++)
            {
                sql.append(fields[i]).append(",");
                
            }
            sql.setLength(sql.length() - 1);
            sql.append(" FROM ").append(tableName);
        }
        
        return sql.toString();
        
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see
     * @String
     * @author licheng
     * @date Created on 2019-7-19
     * @time 上午11:05:01
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getSelectSQL(String tableName, String[] fields, String[] names)
    {
        StringBuffer sql = new StringBuffer();
        if (fields == null) sql.append("SELECT * FROM " + tableName);
        else
        {
            sql.append("SELECT ");
            for (int i = 0; i < fields.length; i++)
            {
                sql.append(fields[i]).append(",");
            }
            sql.setLength(sql.length() - 1);
            sql.append(" FROM ").append(tableName);
        }
        String append = "AND ";
        
        if (names == null || names.length == 0) return sql.toString();
        sql.append(" WHERE ");
        for (int i = 0; i < names.length; i++)
        {
            String name = names[i];
            sql.append(name).append("=? ");
            if (i != names.length - 1) sql.append(append);
        }
        
        return sql.toString();
    }
}
