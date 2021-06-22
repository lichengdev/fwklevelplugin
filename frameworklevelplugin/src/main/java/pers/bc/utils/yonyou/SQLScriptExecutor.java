//package pers.bc.utils.yonyou;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.google.gson.Gson;
//
//import nc.bs.dao.BaseDAO;
//import nc.bs.dao.DAOException;
//import nc.jdbc.framework.processor.MapListProcessor;
//import pers.bc.utils.file.FilePathUtil;
//import pers.bc.utils2.quote.tuple.Pair;
//
///**
// * SQL脚本文件执行器
// * @qualiFild pers.bc.utils.yonyou.SQLScriptExecutor.java<br>
// * @author：licheng<br>
// * @date Created on 2020年3月24日<br>
// * @version 1.0<br>
// */
//public class SQLScriptExecutor
//{
//    private Class relativePosition;
//    
//    /**
//     * 构造方法
//     * 
//     * @param relativePosition 传入相对位置的类
//     */
//    public SQLScriptExecutor(Class relativePosition)
//    {
//        this.relativePosition = relativePosition;
//    }
//    
//    /**
//     * 单独查询一个值
//     * 
//     * @param file 文件相对位置，比如./FindAll.sql
//     * @param valueType 返回值类型
//     * @param params 参数列表，这里用Apache Commons的Pair组件 用Pair.of(LeftObject,RightObject)方法可以快速创建键值对
//     * @return
//     */
//    public <T> T executeQueryValue(String file, Class<T> valueType, Pair<String, String>... params)
//    {
//        T valueResult = null;
//        
//        List<Map> result = executeQueryScript(file, Map.class, params);
//        if (result.size() > 0)
//        {
//            Map row = result.get(0);
//            for (Object key : row.keySet())
//            {
//                if (row.get(key) != null) valueResult = (T) row.get(key);
//                break;
//            }
//        }
//        return valueResult;
//    }
//    
//    /**
//     * 执行更新SQL脚本
//     * 
//     * @param file 文件位置
//     * @param params 参数列表
//     */
//    public void executeUpdateScript(String file, Pair<String, String>... params)
//    {
//        String script = FilePathUtil.readFilePatch(relativePosition, file);
//        String[] sqls = script.split(";");
//        try
//        {
//            for (String sql : sqls)
//            {
//                // 参数替换
//                for (Pair<String, String> param : params)
//                {
//                    sql = sql.replace("{" + param.getLeft() + "}", param.getRight());
//                }
//                new BaseDAO().executeUpdate(sql);
//            }
//        }
//        catch (DAOException e)
//        {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//    
//    /**
//     * 执行查询SQL脚本
//     * 
//     * @param file 脚本文件位置
//     * @param target 返回值类型（会给构造成列表）
//     * @return
//     */
//    
//    public <T> List<T> executeQueryScript(String file, Class<T> target, Pair<String, String>... params)
//    {
//        String sql = FilePathUtil.readFilePatch(relativePosition, file);
//        // 参数替换
//        for (Pair<String, String> param : params)
//        {
//            sql = sql.replace("{" + param.getLeft() + "}", param.getRight().replace("'", "''"));
//        }
//        // 执行查询
//        try
//        {
//            List<T> result = new ArrayList<T>();
//            List<Map> resultSet = (List<Map>) new BaseDAO().executeQuery(sql, new MapListProcessor());
//            for (int i = 0; i < resultSet.size(); i++)
//            {
//                Map row = resultSet.get(i);
//                result.add(new Gson().fromJson(new Gson().toJson(row), target));
//            }
//            return result;
//        }
//        catch (DAOException e)
//        {
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//}
