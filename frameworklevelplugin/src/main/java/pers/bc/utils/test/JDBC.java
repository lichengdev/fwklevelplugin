package pers.bc.utils.test;

import java.sql.ResultSet;

import pers.bc.utils.enums.DataBaseType;
import pers.bc.utils.sql.DBUtilbc;
import pers.bc.utils.sql.ProcessorUtilslbc;

public class JDBC
{
    public static void main(String[] args)
    {
        
        try
        {
            // ResultSet resultSet = new DBUtilbc(DataBaseType.ORACLE)
            // .executeQuery("select code,name,id,pk_org from bd_psndoc where code in('2000000001','2000000007')",
            // null);
            // while (resultSet.next())
            // { <?xml version="1.0" encoding="gb2312"?>
            // Map<String, Object> map = ProcessorUtilslbc.toMap(resultSet);
            // System.out.println(map);
            // }
            System.err.println("--------------------------------------");
            // Object object = new ResultSetProcessor("pk_org").ColumnListProcessor(resultSet);
            // Object object = ProcessorUtilslbc.toBeanInner(resultSet, PsndocVO.class);
            // System.out.println(object);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
