package pers.bc.utils.sql;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import pers.bc.utils.enums.DataBaseType;
import pers.bc.utils.pub.LoggerUtilbc;

/**
 * 类:DBToVO 功能:根据数据库表生成VO--Value Object
 * @author sam
 * @e-mail:sanlai_lee@lisanlai.cn
 */
public class DBToJavaVO
{
    private static final String TARGET_DIR = "C:/tempVO/";							// 类文件存放的路径
    private static final String DIVER_NAME = "oracle.jdbc.OracleDriver";// 驱动类名
    private static final String URL = "jdbc:oracle:thin:@10.16.1.137:1521/orcl";
    // private static final String PASSWORD="nc650118";
    private static final String E_MAIL = "changrh@yonyou.com";					// 作者邮箱
    private static final String PACKAGE_NAME = "nc.vo.casic";						// 包名
    private static final String extendClass = "extends nc.vo.pub.SuperVO";// 继承类
    private Connection conn;
    private Statement stmt;
    private String sql;
    private ResultSet rs;
    private String[] fields;	// 属性
    private String[] dataTypes;	// 数据类型
    private String[] comments;	// 属性的注释
    private String[] dataPrecisions;	// 数据精度
    private String[] dataScales;	// 数据精度范围
    private String pkField;	// 表主键
    
    /**
     * 方法:根据数据库表生成VO--Value Object
     * @param className
     * @param tableName
     * @param tableName
     */
    private void tableToVo(String tableName, String className)
    {
        
        // String tableName;
        // String className;
        try
        {
            InputStreamReader reader = new InputStreamReader(System.in);
            BufferedReader bf = new BufferedReader(reader);
            System.out.println("输入要转换成VO的表名称:");
            // tableName = bf.readLine().trim();
            // tableName = "bd_psndoc";
            System.out.println("输入生成的类名,不输入的时候默认跟表名相同:");
            // className = bf.readLine().trim();
            // className = "BdPsndocVO";
            
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);	// 创建可滚动的,只读的结果集
            sql =
                "select tab.COLUMN_NAME,tab.DATA_TYPE,col.comments,cu.column_name,tab.DATA_PRECISION,tab.DATA_SCALE "
                    + " from user_tab_columns tab"
                    + " left join user_col_comments col on col.table_name = tab.TABLE_NAME and col.column_name = tab.COLUMN_NAME"
                    + " inner join user_constraints au on au.table_name = tab.TABLE_NAME"
                    + " inner join user_cons_columns cu on cu.constraint_name = au.constraint_name and au.constraint_type = 'P'"
                    + " where tab.table_name = '" + tableName.toUpperCase() + "'";		// 表名
            // +" AND table_schema = '"+DATABASE_NAME+"'"; //数据库名
            
            PreparedStatement ps = JdbcSession.getConnection(DataBaseType.ORACLE).prepareStatement(sql);
            rs = ps.executeQuery();
            rs = stmt.executeQuery(sql);
            rs.last();										// 把指针指向结果集的最后
            int fieldNum = rs.getRow();						// 取得最后一条结果的行号,作为类的属性个数
            int n = fieldNum;
            if (n > 0)
            {									// 判断数据表中是否存在字段
                fields = new String[n];
                dataTypes = new String[n];
                comments = new String[n];
                dataPrecisions = new String[n];
                dataScales = new String[n];
                fields[--n] = rs.getString(1);
                dataTypes[n] = rs.getString(2);
                comments[n] = rs.getString(3);
                // 表主键
                if (rs.getString(4) != null)
                {
                    pkField = rs.getString(4);
                }
                
                dataPrecisions[n] = rs.getString(5);
                dataScales[n] = rs.getString(6);
                
                while (rs.previous())
                {
                    fields[--n] = rs.getString(1);			// 取得结果集的第一列数据,对应的列名:Field
                    dataTypes[n] = rs.getString(2);
                    comments[n] = rs.getString(3);
                    // 表主键
                    if (rs.getString(4) != null)
                    {
                        pkField = rs.getString(4);
                    }
                    dataPrecisions[n] = rs.getString(5);
                    dataScales[n] = rs.getString(6);
                }
                // 打印相关信息
                System.out.println("你要转换的表是:" + tableName);
                System.out.println("你要转换的表主键字段是:" + pkField);
                System.out.println("该表中共有" + fieldNum + "个字段,信息如下:");
                for (int i = 0, j = fields.length; i < j; i++)
                {
                    System.out.println("----------------------------------------");
                    String field = fields[i];
                    System.out.println("字段名称:" + field);
                    // 把字段名称格式化成java命名规则形式
                    field = formatField(field);
                    fields[i] = field;						// 把格式化后的字段放入属性数组中
                    System.out.println("数据类型:" + dataTypes[i]);
                    System.out.println("数据精度1:" + dataPrecisions[i]);
                    System.out.println("数据精度2:" + dataScales[i]);
                    // 把数据库字段类型转换成java数据类型
                    String dataType = dataTypes[i].toLowerCase();
                    String dataPrecision = dataPrecisions[i];
                    String dataScale = dataScales[i];
                    dataType = formatDataType(dataType, dataPrecision, dataScale, field, tableName);
                    dataTypes[i] = dataType;
                    
                    System.out.println("字段注释:" + comments[i]);
                    if ("".equals(comments[i]) || comments[i] == null)
                    {
                        comments[i] = fields[i];
                    }
                    System.out.println("----------------------------------------");
                }
                // 格式化类名称
                foramtClassName(className, tableName);
                // 生成类文件,写入到磁盘中
                writeObjectToFile(className, tableName);
            }
            else
            {
                System.out.println("该表不存在或者表中没有字段");
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            // e.printStackTrace();
            // } catch (IOException e) {
            // System.out.println(e.getMessage());
            // e.printStackTrace();
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
                if (conn != null) conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }
    
    /**
     * 把数据库字段格式成java变量名
     * @param field
     * @return
     */
    private String formatField(String field)
    {
        
        field = field.toLowerCase();
        return field;
    }
    
    private String formatDataType(String dataType, String dataPrecision, String dataScale, String field, String tableName)
    {
        if (dataType.contains("char"))
        {
            dataType = "java.lang.String";
        }
        else if (dataType.contains("int"))
        {
            dataType = "java.lang.Integer";
        }
        else if (dataType.contains("float"))
        {
            // dataType="java.lang.Float";
            dataType = "java.lang.String";
        }
        else if (dataType.contains("double"))
        {
            dataType = "nc.vo.pub.lang.Double";
        }
        else if (dataType.contains("number"))
        {
            dataType = "java.lang.Integer";
            // dataType = convertType(dataType, field,tableName);
            if ((dataPrecision != null || !"".equals(dataPrecision)) && (dataScale != null && !"".equals(dataScale)))
            {
                if (!"0".equals(dataScale))
                {
                    dataType = "java.lang.Double";
                }
            }
            
        }
        else if (dataType.contains("decimal"))
        {
            dataType = "nc.vo.pub.lang.Double";
        }
        else if (dataType.contains("date"))
        {
            dataType = "nc.vo.pub.lang.UFLiteralDate";
        }
        else if (dataType.contains("time"))
        {
            dataType = "jnc.vo.pub.lang.UFDateTime";
        }
        else if (dataType.contains("clob"))
        {
            dataType = "java.sql.Clob";
        }
        else if (dataType.contains("blob"))
        {// 照片
            dataType = "byte[]";
        }
        else
        {
            dataType = "java.lang.String";
        }
        return dataType;
    }
    
    /**
     * 格式化类名
     * @param className
     * @param tableName
     * @return
     */
    private String foramtClassName(String className, String tableName)
    {
        // 如果类名不是自己定义的,那么根据表名格式化类名
        if ("".equals(className) || className == null)
        {
            className = "";
            String[] tempArr = tableName.split("_");
            for (int m = 0, length = tempArr.length; m < length; m++)
            {
                className += tempArr[m].substring(0, 1).toUpperCase() + tempArr[m].substring(1, tempArr[m].length()).toLowerCase();
            }
        }
        else
        {
            // 如果类名已经输入,那么不管三七二十一就把类名的第一个字母大写
            className = className.substring(0, 1).toUpperCase() + className.substring(1, className.length());
        }
        return className;
    }
    
    /**
     * 生成类并写到文件
     * @param className
     */
    @SuppressWarnings("deprecation")
    private void writeObjectToFile(String className, String tablename)
    {
        PrintWriter writer = null;
        try
        {
            File dir = new File(TARGET_DIR);
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            // vo 名称首字母大写
            File file = new File(TARGET_DIR + className + ".java");
            if (!file.exists())
            {
                file.createNewFile();
            }
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            
            // 拼文件内容
            StringBuffer content = new StringBuffer();
            content.append("package " + PACKAGE_NAME + ";\n\n");
            content.append("/**\n");
            content.append(" *@类:" + className + "\n");
            content.append(" *@作者: \n");
            // content.append(" *@E-mail:"+E_MAIL+"\n");
            content.append(" *@日期:" + new Date().toLocaleString().substring(0, 10) + "\n");
            content.append(" */\n\n");
            content.append("public class " + className + " " + extendClass + "{\n\n");
            for (int i = 0, j = fields.length; i < j; i++)
            {
                content.append("	/**" + comments[i] + "*/\n");
                content.append("	private " + dataTypes[i] + " " + fields[i] + ";\n\n");
            }
            // get set 方法
            for (int i = 0, j = fields.length; i < j; i++)
            {
                content.append("	/**\n");
                content.append("	 *方法: 取得" + fields[i] + "\n");
                content.append("	 *@return: " + dataTypes[i] + "  " + fields[i] + "\n");
                content.append("	 */\n");
                content.append("	public " + dataTypes[i] + " get" + fields[i].substring(0, 1).toUpperCase()
                    + fields[i].substring(1, fields[i].length()) + "(){\n");
                content.append("		return this." + fields[i] + ";\n");
                content.append("	}\n\n");
                content.append("	/**\n");
                content.append("	 *方法: 设置" + fields[i] + "\n");
                content.append("	 *@param: " + dataTypes[i] + "  " + fields[i] + "\n");
                content.append("	 */\n");
                content.append("	public void set" + fields[i].substring(0, 1).toUpperCase() + fields[i].substring(1, fields[i].length())
                    + "(" + dataTypes[i] + " " + fields[i] + "){\n");
                content.append("		this." + fields[i] + " = " + fields[i] + ";\n");
                content.append("	}\n\n");
            }
            // 表主键
            content.append("	public String getPKFieldName(){\n");
            content.append("		return \"" + pkField.toLowerCase() + "\";\n");
            content.append("	}\n\n");
            // 表名
            content.append("	public String getTableName(){\n");
            content.append("		return \"" + tablename + "\";\n");
            content.append("	}\n\n");
            
            content.append("}");
            writer.write(content.toString());
            writer.flush();
            System.out.println("类生成成功，存放路径：" + TARGET_DIR);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (writer != null)
            {
                writer.close();
            }
        }
    }
    
    public static void main(String[] args)
    {
        try
        {
            String[] tableNames = {"hi_psndoc_glbdef17"};
            for (int i = 0; i < tableNames.length; i++)
            {
                String tableName = tableNames[i];
                String className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length()) + "VO";
                new DBToJavaVO().tableToVo(tableName, className);
            }
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("jdbclogs").exception(e);
        }
        
    }
}
