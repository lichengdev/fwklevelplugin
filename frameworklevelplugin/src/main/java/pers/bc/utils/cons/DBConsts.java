package pers.bc.utils.cons;

/**
 * 数据库类型
 */
public interface DBConsts
{
    String DB_DRIVER = "driverClass";
    String DB_URL = "url";
    String USER_NAME = "username";
    String PASS = "password";
    
    // 数据库类型
    int DB2 = 0;
    int ORACLE = 1;
    int SQLSERVER = 2;
    int SYBASE = 3;
    int INFORMIX = 4;
    int HSQL = 5;
    int OSCAR = 6;
    int POSTGRESQL = 7;
    int GBASE = 8;
    int ALCEDO = 9;
    int UNKOWNDATABASE = -1;
    String DEFAULT_DATABASE_ID = "default_database";
    
    String ORACLE_NAME = "ORACLE";
    String MSSQL_NAME = "MSSQL";
    String MYSQL_NAME = "MYSQL";
    String SQLSERVER_NAME = "SQLSERVER";
    String DB2_NAME = "DB2";
    String HSQL_NAME = "HSQL";
    String SYBASE_NAME = "SYBASE";
    String INFORMIX_NAME = "INFORMIX";
    String OSCAR_NAME = "OSCAR";
    String POSTGRESQL_NAME = "POSTGRESQL";
    String ALCEDO_NAME = "ALCEDO";
    String GBASE_NAME = "GBASE";
    String UNKOWN_NAME = "UNKOWN";
    
    // JDBC驱动
    
    String JDBC_ODBC = "sun.jdbc.odbc.JdbcOdbcDriver";
    String JDBC_DB2 = "com.ibm.db2.jcc.DB2Driver";
    String JDBC_DB2_NET = "COM.ibm.db2.jdbc.net.DB2Driver";
    String JDBC_DB2_APP = "COM.ibm.db2.jdbc.app.DB2Driver";
    String JDBC_ORACLE = "oracle.jdbc.driver.OracleDriver";
    String JDBC_MYSQL = "com.mysql.cj.jdbc.Driver";
    String JDBC_SYBASE = "com.sybase.jdbc.SybDriver";
    String JDBC_GBASE = "com.gbase.jdbc.Driver";
    String JDBC_OSCAR = "com.oscar.Driver";
    
    // ConnectionDriver
    final String URL_PREFIX = "jdbc:ufsoft:jdbcDriver";
    final int MAJOR_VERSION = 1;
    final int MINOR_VERSION = 0;
    
    final String JdbcOdbcBridgeName = "JDBC-ODBC Bridge";
    
    String JDBC_INFORMIX = "com.informix.jdbc.IfxDriver";
    String JDBC_SQLSERVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
    
    /*
     * @@author libchc
     * @project 航天科工项目
     * @date 2018年10月30日 注释Notes
     * @project 目的：适配昆仑数据库==100
     * @author licheng
     * @2018年10月30日 条件数据库类型
     */
    int KUNLUN = 100;// @2018年10月30日 libchc
    String KUNLUN_NAME = "KUNLUN";// @2018年10月30日 libchc
    String JDBC_KUNLUN = "com.kunlun.jdbc.Driver";// @2018年10月30日 libchc
    
    // ------------------------------------------------------------------------
    // Sql语句类型
    // ------------------------------------------------------------------------
    
    int SQL_SELECT = 1;
    int SQL_INSERT = 2;
    int SQL_CREATE = 3;
    int SQL_DROP = 4;
    int SQL_DELETE = 5;
    int SQL_UPDATE = 6;
    int SQL_EXPLAIN = 7;
    
    // ------------------------------------------------------------------------
    // 函数列表,在SQL语句中只能用这些函数
    // ------------------------------------------------------------------------
    String[] functions = {
        "coalesce",
        "len",
        "left",
        "right",
        "substring",
        "lower",
        "upper",
        "ltrim",
        "rtrim",
        "sqrt",
        "abs",
        "square",
        "sign",
        "count",
        "max",
        "min",
        "sum",
        "avg",
        "cast"};
    
    String NULL_WAVE = "~";
}
