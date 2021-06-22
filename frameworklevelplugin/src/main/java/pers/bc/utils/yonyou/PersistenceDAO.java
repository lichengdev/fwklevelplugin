//package pers.bc.utils.yonyou;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Vector;
//
//import nc.bs.dao.BaseDAO;
//import nc.bs.dao.DAOException;
//import nc.bs.logging.Logger;
//import nc.hr.utils.InSQLCreator;
//import nc.hr.utils.PubEnv;
//import nc.hr.utils.ResHelper;
//import nc.jdbc.framework.JdbcSession;
//import nc.jdbc.framework.PersistenceManager;
//import nc.jdbc.framework.SQLParameter;
//import nc.jdbc.framework.exception.DbException;
//import nc.jdbc.framework.processor.ArrayListProcessor;
//import nc.jdbc.framework.processor.BeanListProcessor;
//import nc.jdbc.framework.processor.ResultSetProcessor;
//import nc.vo.bd.tableref.RefRelationVO;
//import nc.vo.hr.frame.persistence.AggSaveResultVO;
//import nc.vo.hr.frame.persistence.IdValueObj;
//import nc.vo.hr.tools.pub.HRAggVO;
//import nc.vo.pub.BusinessException;
//import nc.vo.pub.CircularlyAccessibleValueObject;
//import nc.vo.pub.SuperVO;
//import nc.vo.pub.VOStatus;
//import nc.vo.pub.lang.UFDateTime;
//import pers.bc.utils.Bean.ReflectionUtilbc;
//import pers.bc.utils.pub.ArrayUtilbc;
//import pers.bc.utils.sql.CountVO;
//import pers.bc.utils.sql.SQLHelper;
//import pers.bc.utils.throwable.MylbcException;
//import pers.bc.utils2.pub.StringUtils;
//
///**
// * s
// * 
// * @qualiFild nc.pub.itf.tools.sql.PersistenceDAO.java<br>
// * @author：licheng<br>
// * @date Created on 2019-10-16<br>
// * @version 1.0<br>
// */
//public class PersistenceDAO implements IPersistenceDAO
//{
//    private BaseDAO baseDAO = null;// 内部的SuperDMO
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 根据条件获取对应的参照数据信息，没有值返回null<br>
//     * 
//     * @see <br>
//     * @param className
//     * @param condition
//     * @return
//     * @throws Exception <br>
//     * @SuperVO <br>
//     * @methods pers.bc.utils.yonyou.PersistenceDAO#retrieveFirstNullByClause <br>
//     * @author licheng <br>
//     * @date Created on 2019年11月7日 <br>
//     * @time 下午5:11:11 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public SuperVO retrieveFirstNullByClause(Class<?> className, String condition) throws Exception
//    {
//        return retrieveFirstByClause(className, condition, true);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 根据条件获取对应的参照数据信息,没有值返回一个属性为空的对象<br>
//     * 
//     * @see <br>
//     * @param className
//     * @param condition
//     * @return
//     * @throws Exception <br>
//     * @SuperVO <br>
//     * @methods pers.bc.utils.yonyou.PersistenceDAO#retrieveFirstNullByClause <br>
//     * @author licheng <br>
//     * @date Created on 2019年11月7日 <br>
//     * @time 下午5:11:11 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public SuperVO retrieveFirstEmptyByClause(Class<?> className, String condition) throws Exception
//    {
//        return retrieveFirstByClause(className, condition, false);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：根据条件获取对应的参照数据信息 <br>
//     * 
//     * @see <br>
//     * @param className
//     * @param condition
//     * @return
//     * @throws DAOException <br>
//     * @SuperVO <br>
//     * @methods nc.impl.pickm.dataio.DataIOServiceImpl#getRefVO <br>
//     * @author licheng <br>
//     * @date Created on 2019-10-17 <br>
//     * @time 上午12:12:42 <br>
//     * @throws IllegalAccessException
//     * @throws InstantiationException
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public SuperVO retrieveFirstByClause(Class<?> className, String condition, boolean isRetNull) throws Exception
//    {
//        Collection<?> collection = getBaseDAO().retrieveByClause(className, condition);
//        SuperVO superVO = null;
//        if (!ArrayUtilbc.isEffective(collection))
//        {
//            List<?> list = (List<?>) collection;
//            superVO = (SuperVO) list.get(0);
//            return superVO;
//        }
//        if (!isRetNull) superVO = (SuperVO) ReflectionUtilbc.getNewInstance(className);
//        return superVO;
//    }
//    
//    /*********************************************************************************************************
//     * 构造函数1，使用系统默认数据源<br>
//     * Created on 2019-11-16 09:12:16<br>
//     * 
//     * @author Li Bencheng
//     ********************************************************************************************************/
//    public PersistenceDAO()
//    {
//        super();
//        
//        baseDAO = new BaseDAO(PubEnv.getDataSource());
//    }
//    
//    /*********************************************************************************************************
//     * 构造函数1，使用指定名称数据源<br>
//     * Created on 2019-11-16 09:12:35<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     ********************************************************************************************************/
//    public PersistenceDAO(String ds)
//    {
//        super();
//        
//        if (StringUtils.isNotBlank(ds))
//        {
//            baseDAO = new BaseDAO(ds);
//        }
//        else
//        {
//            baseDAO = new BaseDAO();
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 删除值对象数组<br>
//     * Created on 2019-11-16 09:12:51<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void delete(final List vos) throws MylbcException
//    {
//        if (vos == null || vos.size() <= 0)
//        {
//            return;
//        }
//        
//        try
//        {
//            getBaseDAO().deleteVOList(vos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 删除一个值对象<br>
//     * Created on 2019-11-16 09:13:06<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void delete(final SuperVO vo) throws MylbcException
//    {
//        try
//        {
//            if (vo == null)
//            {
//                return;
//            }
//            
//            getBaseDAO().deleteVO(vo);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 删除值对象集合<br>
//     * Created on 2019-11-16 09:13:20<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void delete(final SuperVO[] vos) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.length == 0)
//            {
//                return;
//            }
//            
//            getBaseDAO().deleteVOArray(vos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 删除一个聚合ＶＯ<br>
//     * Created on 2019-11-16 09:08:40<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     * @param aggVO
//     * @return boolean
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public boolean deleteByAggVO(String ds, HRAggVO aggVO) throws MylbcException
//    {
//        if (aggVO == null)
//        {
//            return false;
//        }
//        
//        try
//        {
//            // 删除表体
//            CircularlyAccessibleValueObject[] allTableVOs = aggVO.getAllTableVOs();
//            
//            if (allTableVOs != null && allTableVOs.length > 0)
//            {
//                SuperVO superVOs[] = new SuperVO[allTableVOs.length];
//                
//                System.arraycopy(allTableVOs, 0, superVOs, 0, allTableVOs.length);
//                
//                delete(superVOs);
//                
//                // for (CircularlyAccessibleValueObject vo : allTableVOs)
//                // {
//                // delete((SuperVO) vo);
//                // }
//            }
//            
//            delete((SuperVO) aggVO.getParentVO());// 删除表头
//            
//            return true;
//        }
//        catch (Exception ex)
//        {
//            Logger.error(ex.getMessage(), ex);
//            
//            throw new MylbcException(ex.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 一个接一个的删除值对象数组中的元素，该接口用于插入少量不同类表的SuperVO数组
//     * 
//     * @param vos
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void deleteMulType(SuperVO[] vos) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.length == 0)
//            {
//                return;
//            }
//            
//            getBaseDAO().deleteVOArray(vos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 执行任意一条SQL语句<br>
//     * Created on 2019-11-16 09:14:06<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     * @param sql
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void executeNoResultSQL(String ds, String sql) throws MylbcException
//    {
//        if (StringUtils.isBlank(sql))
//        {
//            throw new MylbcException(ResHelper.getString("6001frame", "06001frame0149")
//            /* @res "传入的SQL语句为空!" */);
//        }
//        else if (!sql.trim().toLowerCase().startsWith("update") && !sql.trim().toLowerCase().startsWith("delete"))
//        {
//            throw new MylbcException(ResHelper.getString("6001frame", "06001frame0150")
//            /* @res "只能执行 DELETE 或者 UPDATE 操作的SQL语句!" */);
//        }
//        
//        // 生成新的JDBC Session
//        JdbcSession s = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            if (ds == null || ds.length() == 0)
//            {
//                sessionManager = PersistenceManager.getInstance();
//            }
//            else
//            {
//                sessionManager = PersistenceManager.getInstance(ds);
//            }
//            
//            s = sessionManager.getJdbcSession();
//            
//            s.executeUpdate(sql);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            throw new MylbcException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 执行一个查询的SQL语句<br>
//     * Created on 2019-9-4 13:00:39<br>
//     * 
//     * @author Li Bencheng
//     * @param arg0
//     * @param arg1
//     * @return Object
//     * @throws BusinessException
//     ********************************************************************************************************/
//    public Object executeQuery(String arg0, ResultSetProcessor arg1) throws BusinessException
//    {
//        return getBaseDAO().executeQuery(arg0, arg1);
//    }
//    
//    /*********************************************************************************************************
//     * 执行一个查询的SQL语句<br>
//     * Created on 2019-9-4 13:02:11<br>
//     * 
//     * @author Li Bencheng
//     * @param arg0
//     * @param arg1
//     * @param arg2
//     * @return Object
//     * @throws BusinessException
//     ********************************************************************************************************/
//    public Object executeQuery(String arg0, SQLParameter arg1, ResultSetProcessor arg2) throws BusinessException
//    {
//        return getBaseDAO().executeQuery(arg0, arg1, arg2);
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2008-4-28 14:03:44<br>
//     * 
//     * @author Li Bencheng
//     * @param strSQL
//     * @param parameter
//     * @throws BusinessException
//     ********************************************************************************************************/
//    public void executeSQL(String strSQL, SQLParameter parameter) throws BusinessException
//    {
//        executeSQL(strSQL, parameter, (Boolean) null);
//    }
//    
//    /***************************************************************************
//     * <br>
//     * Created on 2008-11-19 14:36:09<br>
//     * 
//     * @author Li Bencheng
//     * @param strSQL
//     * @param parameter
//     * @param blSQLTranslator
//     * @throws BusinessException
//     ***************************************************************************/
//    public void executeSQL(String strSQL, SQLParameter parameter, Boolean blSQLTranslator) throws BusinessException
//    {
//        JdbcSession session = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            sessionManager = PersistenceManager.getInstance();
//            session = sessionManager.getJdbcSession();
//            
//            if (blSQLTranslator != null)
//            {
//                session.setSQLTranslator(blSQLTranslator);
//            }
//            
//            if (parameter != null)
//            {
//                session.executeUpdate(strSQL, parameter);
//            }
//            else
//            {
//                session.executeUpdate(strSQL);
//            }
//        }
//        catch (DbException e)
//        {
//            throw new BusinessException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-11-24 09:27:12<br>
//     * 
//     * @author Li Bencheng
//     * @param strSQLs
//     * @throws BusinessException
//     ********************************************************************************************************/
//    public void executeSQLs(String[] strSQLs) throws BusinessException
//    {
//        if (strSQLs == null || strSQLs.length == 0)
//        {
//            return;
//        }
//        
//        JdbcSession session = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            sessionManager = PersistenceManager.getInstance();
//            session = sessionManager.getJdbcSession();
//            
//            for (int i = 0; i < strSQLs.length; i++)
//            {
//                session.addBatch(strSQLs[i]);
//            }
//            
//            session.executeBatch();
//        }
//        catch (DbException e)
//        {
//            throw new BusinessException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-9-4 13:30:12<br>
//     * 
//     * @author Li Bencheng
//     * @param strSQLs
//     * @param parameters
//     * @throws BusinessException
//     ********************************************************************************************************/
//    public void executeSQLs(String[] strSQLs, SQLParameter[] parameters) throws BusinessException
//    {
//        if (strSQLs == null || strSQLs.length == 0)
//        {
//            return;
//        }
//        
//        if (parameters == null || parameters.length == 0 || parameters.length != strSQLs.length)
//        {
//            throw new BusinessException(ResHelper.getString("6001frame", "06001frame0151")
//            /* @res "传入的参数个数与SQL语句个数不一致！" */);
//        }
//        
//        JdbcSession session = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            sessionManager = PersistenceManager.getInstance();
//            session = sessionManager.getJdbcSession();
//            
//            for (int i = 0; i < strSQLs.length; i++)
//            {
//                if (parameters[i] != null)
//                {
//                    session.addBatch(strSQLs[i], parameters[i]);
//                }
//                else
//                {
//                    session.addBatch(strSQLs[i]);
//                }
//            }
//            
//            session.executeBatch();
//        }
//        catch (DbException e)
//        {
//            throw new BusinessException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 内部使用的方法，得到SuperDMO对象
//     * 
//     * @return BaseDAO
//     ********************************************************************************************************/
//    protected BaseDAO getBaseDAO()
//    {
//        return baseDAO;
//    }
//    
//    /*********************************************************************************************************
//     * 根据表名，字段名和字段值来得到引用的次数 注意：本方法现在只能用于字符型字段
//     * 
//     * @param ds
//     * @param tabName
//     * @param tabColName
//     * @param tabColValue
//     * @return int
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public int getTabColRefCount(String ds, String tabName, String tabColName, String tabColValue) throws MylbcException
//    {
//        return getTabColRefCount(ds, tabName, tabColName, tabColValue, " = ");
//    }
//    
//    /*********************************************************************************************************
//     * 根据表名，字段名和字段值来得到引用的次数 注意：本方法现在只能用于字符型字段
//     * 
//     * @param ds
//     * @param tabName
//     * @param tabColName
//     * @param tabColValue
//     * @param strValueOperation 列和值直接的操作关系，如果是相等关系传“ = ”，如果是相似传“ like ”
//     * @return int
//     * @throws MylbcException
//     ********************************************************************************************************/
//    public int getTabColRefCount(String ds, String tabName, String tabColName, String tabColValue, String strValueOperation)
//            throws MylbcException
//    {
//        if (StringUtils.isBlank(tabName) || StringUtils.isBlank(tabColName) || StringUtils.isBlank(tabColValue))
//        {
//            throw new MylbcException(ResHelper.getString("6001frame", "06001frame0116")
//            /* @res "传入的参数不可以为空！" */);
//        }
//        
//        if (strValueOperation == null || strValueOperation.trim().length() == 0)
//        {
//            strValueOperation = " = ";
//        }
//        
//        JdbcSession s = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            if (StringUtils.isNotBlank(ds))
//            {
//                sessionManager = PersistenceManager.getInstance(ds);
//            }
//            else
//            {
//                sessionManager = PersistenceManager.getInstance();
//            }
//            
//            s = sessionManager.getJdbcSession();
//            
//            // 首先得到所有引用的表记录
//            RefRelationVO refRelationVO = new RefRelationVO();
//            SQLHelper.getSelectSQL(refRelationVO.getTableName(), refRelationVO.getAttributeNames());
//            
//            Collection collRefRelationVO =
//                new BaseDAO(ds).retrieveByClause(RefRelationVO.class, "referencedtablename='" + tabName + "' and referencedtablekey='"
//                    + tabColName + "'");
//            
//            if (collRefRelationVO == null || collRefRelationVO.size() <= 0)
//            {
//                return 0;
//            }
//            
//            // 得到数组
//            RefRelationVO[] vos = (RefRelationVO[]) collRefRelationVO.toArray(new RefRelationVO[0]);
//            int count = 0;
//            
//            // 循环得到引用次数
//            for (int i = 0; i < vos.length; i++)
//            {
//                String tabSql =
//                    "select count(" + vos[i].getReferencingtablecolumn() + ") as count from " + vos[i].getReferencingtablename()
//                        + " where " + vos[i].getReferencingtablecolumn() + strValueOperation + "'" + tabColValue + "' ";
//                
//                List l = (List) s.executeQuery(tabSql, new BeanListProcessor(CountVO.class));
//                
//                CountVO vo = (CountVO) l.get(0);
//                
//                count += vo.getCount();
//            }
//            
//            return count;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 通过Classes得到表名
//     * 
//     * @param bodyVOClasses
//     * @return String[]
//     ********************************************************************************************************/
//    private String[] getTableNamesByClasses(Class[] bodyVOClasses)
//    {
//        if (bodyVOClasses == null || bodyVOClasses.length <= 0)
//        {
//            return null;
//        }
//        
//        String[] tNames = new String[bodyVOClasses.length];
//        
//        SuperVO vo = null;
//        
//        for (int i = 0; i < tNames.length; i++)
//        {
//            try
//            {
//                vo = (SuperVO) bodyVOClasses[i].newInstance();
//            }
//            catch (Exception e)
//            {
//                continue;
//            }
//            
//            if (vo != null)
//            {
//                tNames[i] = vo.getTableName();
//            }
//        }
//        
//        return tNames;
//    }
//    
//    /*********************************************************************************************************
//     * 把一个值对象集合插入到数据库 <br>
//     * Created on 2019-11-16 09:14:49<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String[] insert(final List vos) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.size() <= 0)
//            {
//                return null;
//            }
//            
//            String[] strPks = getBaseDAO().insertVOList(vos);
//            
//            if (strPks != null)
//            {
//                for (int i = 0; i < strPks.length; i++)
//                {
//                    ((CircularlyAccessibleValueObject) vos.get(i)).setPrimaryKey(strPks[i]);
//                }
//            }
//            
//            return strPks;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 把一个值对象插入到数据库<br>
//     * Created on 2019-11-16 09:15:11<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @return String
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String insert(final SuperVO vo) throws MylbcException
//    {
//        try
//        {
//            String strPk = getBaseDAO().insertVO(vo);
//            
//            vo.setPrimaryKey(strPk);
//            
//            return strPk;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 把一个值对象数组插入到数据库 <br>
//     * Created on 2019-11-16 09:15:26<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String[] insert(final SuperVO[] vos) throws MylbcException
//    {
//        try
//        {
//            String[] strPks = getBaseDAO().insertVOArray(vos);
//            
//            if (strPks != null)
//            {
//                for (int i = 0; i < strPks.length; i++)
//                {
//                    vos[i].setPrimaryKey(strPks[i]);
//                }
//            }
//            
//            return strPks;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 一个接一个的插入值对象数组中的元素，该接口用于插入少量不同类表的SuperVO数组
//     * 
//     * @param vos
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String[] insertMulType(SuperVO[] vos) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.length <= 0)
//            {
//                throw new Exception(ResHelper.getString("6001frame", "06001frame0152")
//                /* @res "插入的值对象数组不可以为空！" */);
//            }
//            
//            return getBaseDAO().insertVOArray(vos);
//            
//            // Vector v = new Vector();
//            // for (int i = 0; i < vos.length; i++)
//            // {
//            // String strPk = getBaseDAO().insertVO(vos[i]);
//            //
//            // vos[i].setPrimaryKey(strPk);
//            //
//            // v.addElement(strPk);
//            // }
//            //
//            // String[] resAry = new String[vos.length];
//            // v.copyInto(resAry);
//            //
//            // return resAry;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 把一个值对象数组插入到数据库，并且使用VO中的PK<br>
//     * Created on 2019-11-16 09:31:03<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @return String
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String insertWithPK(final SuperVO vo) throws MylbcException
//    {
//        try
//        {
//            return getBaseDAO().insertVOWithPK(vo);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 把一个值对象数组插入到数据库，并且使用VO中的PK<br>
//     * Created on 2019-11-16 09:31:03<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String[] insertWithPK(final SuperVO[] vos) throws MylbcException
//    {
//        try
//        {
//            return getBaseDAO().insertVOArrayWithPK(vos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 根据主、子表信息查询，返回主表数据<br>
//     * Created on 2019-11-16 09:32:46<br>
//     * 
//     * @author Li Bencheng
//     * @param strDataSource
//     * @param parentClass
//     * @param childClasses
//     * @param strWhere
//     * @return SuperVO[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public SuperVO[] queryByHeadBodyCondition(String strDataSource, Class<? extends SuperVO> parentClass,
//            Class<? extends SuperVO>[] childClasses, String strWhere) throws MylbcException
//    {
//        SuperVO parentVO = null;
//        
//        String strParentTableName = null;
//        String strParentPkFieldName = null;
//        
//        JdbcSession jdbcSession = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            if (StringUtils.isNotBlank(strDataSource))
//            {
//                sessionManager = PersistenceManager.getInstance(strDataSource);
//            }
//            else
//            {
//                sessionManager = PersistenceManager.getInstance();
//            }
//            
//            jdbcSession = sessionManager.getJdbcSession();
//            
//            parentVO = parentClass.newInstance();
//            
//            strParentTableName = parentVO.getTableName();
//            strParentPkFieldName = parentVO.getPKFieldName().toLowerCase();
//            
//            // 首先根据条件查询出来所有的主表的主键，然后再根据主表主键查询出来符合条件的数据
//            StringBuffer strbufSQL =
//                new StringBuffer("select distinct ").append(strParentTableName).append(".").append(strParentPkFieldName).append(" from ")
//                    .append(strParentTableName);
//            
//            if (childClasses != null && childClasses.length > 0)
//            {
//                for (Class clazz : childClasses)
//                {
//                    SuperVO bodyVO = (SuperVO) clazz.newInstance();
//                    
//                    strbufSQL.append(" left join ").append(bodyVO.getTableName()).append(" on ").append(strParentTableName).append(".")
//                        .append(strParentPkFieldName).append("=").append(bodyVO.getTableName()).append(".")
//                        .append(bodyVO.getParentPKFieldName());
//                }
//            }
//            
//            String strOrderBy = null;
//            
//            if (strWhere != null && strWhere.trim().length() != 0)
//            {
//                int iIndex = -1;
//                if ((iIndex = strWhere.toLowerCase().indexOf("order by")) > -1)
//                {
//                    strOrderBy = strWhere.substring(iIndex);
//                    strWhere = strWhere.substring(0, iIndex);
//                }
//                
//                strbufSQL.append(" where ").append(strWhere);
//            }
//            
//            List resultList = (List) jdbcSession.executeQuery(strbufSQL.toString(), new ArrayListProcessor());
//            
//            if (resultList == null || resultList.size() == 0)
//            {
//                return null;
//            }
//            
//            String strParentPks[] = new String[resultList.size()];
//            
//            for (int i = 0; i < resultList.size(); i++)
//            {
//                strParentPks[i] = "" + ((Object[]) resultList.get(i))[0];
//            }
//            
//            return queryByPks(parentClass, strParentPkFieldName, strParentPks, strOrderBy);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 根据主表VO查询所有的子表数据 <br>
//     * Created on 2019-11-16 09:31:18<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     * @param headVO
//     * @param bodyVOClasses
//     * @param bodyTableCodes
//     * @param bodyTabFKs
//     * @param whrAppendStr
//     * @return HRAggVO
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public HRAggVO queryByHeadVO(String ds, SuperVO headVO, Class<? extends SuperVO>[] bodyVOClasses, String[] bodyTableCodes,
//            String[] bodyTabFKs, String... whrAppendStr) throws MylbcException
//    {
//        HRAggVO billVO = null;
//        
//        try
//        {
//            // 生成返回ＶＯ
//            billVO = new HRAggVO();
//            billVO.setParentVO((CircularlyAccessibleValueObject) getBaseDAO().retrieveByPK(headVO.getClass(), headVO.getPrimaryKey()));
//            
//            // 如果没有子表，则直接返回刚查询出的主表数据
//            if (bodyTableCodes == null || bodyTableCodes.length == 0)
//            {
//                return billVO;
//            }
//            
//            billVO.setTableCodes(bodyTableCodes);
//            billVO.setTableNames(getTableNamesByClasses(bodyVOClasses));
//            
//            Collection c = null;
//            SuperVO[] tvos = null;
//            
//            for (int i = 0; i < bodyTableCodes.length; i++)
//            {
//                StringBuffer sqlWhr = new StringBuffer(" ");
//                if (bodyTabFKs == null || bodyTabFKs.length != bodyTableCodes.length)
//                {
//                    sqlWhr.append(headVO.getPKFieldName());
//                }
//                else
//                {
//                    sqlWhr.append(bodyTabFKs[i]);
//                }
//                
//                sqlWhr.append("='");
//                sqlWhr.append(headVO.getPrimaryKey());
//                sqlWhr.append("' ");
//                
//                if (whrAppendStr != null && (whrAppendStr.length == 1 || whrAppendStr.length <= bodyTableCodes.length)
//                    && i < whrAppendStr.length && whrAppendStr[i] != null && sqlWhr.length() > 0)
//                {
//                    sqlWhr.append(" and ");
//                    sqlWhr.append(whrAppendStr);
//                }
//                
//                c = getBaseDAO().retrieveByClause(bodyVOClasses[i], sqlWhr.toString());
//                tvos = (SuperVO[]) Array.newInstance(bodyVOClasses[i], 0);
//                billVO.setTableVO(bodyTableCodes[i], (SuperVO[]) c.toArray(tvos));
//            }
//            
//            return billVO;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 如果界面上的数据过多生成的SQL语句会超长，这样会报SQL语句超长的错误，所以要切分成几段来检查，暂且以40个记录为一组<br>
//     * Created on 2019-1-25 09:48:17<br>
//     * 
//     * @author Li Bencheng
//     * @param className 所要查询的VO类名
//     * @param strPkFieldName 所要查询的表的主键名
//     * @param strPks 主键值数组
//     * @param strOrderBy
//     * @return SuperVO[]
//     ********************************************************************************************************/
//    public SuperVO[] queryByPks(Class<? extends SuperVO> className, String strPkFieldName, String strPks[], String strOrderBy)
//    {
//        if (strPks == null || strPks.length == 0 || strPkFieldName == null)
//        {
//            return null;
//        }
//        
//        String strSQL = "";
//        
//        InSQLCreator inSqlCreator = new InSQLCreator();
//        
//        try
//        {
//            strSQL = strPkFieldName + " in(" + inSqlCreator.getInSQL(strPks) + ")";
//            
//            if (StringUtils.isNotBlank(strOrderBy))
//            {
//                strSQL += strOrderBy;
//            }
//            
//            return retrieveByClause(className, strSQL);
//        }
//        catch (Exception ex)
//        {
//            Logger.error(ex.getMessage(), ex);
//        }
//        finally
//        {
//            try
//            {
//                inSqlCreator.clear();
//            }
//            catch (BusinessException ex)
//            {
//                Logger.error(ex.getMessage(), ex);
//            }
//        }
//        
//        return null;
//        
//        // xxx 如果界面上的数据过多生成的SQL语句会超长，这样会报SQL语句超长的错误，所以要切分成几段来检查，暂且以40个记录为一组
//        // int iGroupLength = 100;
//        // int iGroupCount = strPks.length / iGroupLength + (strPks.length %
//        // iGroupLength == 0 ? 0 : 1);
//        //
//        // ArrayList<SuperVO> arrListBlankList = new ArrayList<SuperVO>();
//        //
//        // for (int i = 0, iIndex = 0; i < iGroupCount; i++)
//        // {
//        // String strWhere = "";
//        //
//        // for (int j = 0; j < iGroupLength && iIndex < strPks.length; j++, iIndex++)
//        // {
//        // strWhere = strWhere + ",'" + strPks[iIndex] + "'";
//        // }
//        //
//        // if (strWhere.length() > 0)
//        // {
//        // strWhere = strPkFieldName + " in(" + strWhere.substring(1) + ")";
//        // }
//        //
//        // if (StringUtils.isNotBlank(strOrderBy))
//        // {
//        // strWhere += strOrderBy;
//        // }
//        //
//        // try
//        // {
//        // SuperVO superVOs[] = retrieveByClause(className, strWhere);
//        //
//        // if (superVOs != null && superVOs.length > 0)
//        // {
//        // arrListBlankList.addAll(Arrays.asList(superVOs));
//        // }
//        // }
//        // catch (MylbcException ex)
//        // {
//        // Logger.error(ex.getMessage(), ex);
//        // }
//        // }
//        //
//        // return arrListBlankList.toArray(new SuperVO[0]);
//    }
//    
//    /*********************************************************************************************************
//     * 根据VO的字段值条件查询数据 <br>
//     * Created on 2019-11-16 09:32:12<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @return SuperVO[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public SuperVO[] retrieve(SuperVO vo) throws MylbcException
//    {
//        if (vo == null)
//        {
//            return null;
//        }
//        try
//        {
//            Collection c = getBaseDAO().retrieve(vo, true);
//            SuperVO[] tvos = (SuperVO[]) Array.newInstance(vo.getClass(), 0);
//            return (SuperVO[]) c.toArray(tvos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 查询VO对应表所有数据 <br>
//     * Created on 2019-11-16 09:32:29<br>
//     * 
//     * @author Li Bencheng
//     * @param <T>
//     * @param clazz
//     * @return SuperVO[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public <T extends SuperVO> T[] retrieveAll(Class<T> clazz) throws MylbcException
//    {
//        try
//        {
//            Collection collection = getBaseDAO().retrieveAll(clazz);
//            
//            return (T[]) collection.toArray((T[]) Array.newInstance(clazz, collection.size()));
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 根据条件查询VO对应表数据<br>
//     * Created on 2019-11-16 09:34:16<br>
//     * 
//     * @author Li Bencheng
//     * @param clz
//     * @param condition
//     * @return SuperVO[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public SuperVO[] retrieveByClause(Class clz, String condition) throws MylbcException
//    {
//        try
//        {
//            Collection c = getBaseDAO().retrieveByClause(clz, condition);
//            SuperVO[] tvos = (SuperVO[]) Array.newInstance(clz, 0);
//            
//            return (SuperVO[]) c.toArray(tvos);
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-7-18 11:19:11<br>
//     * 
//     * @author Li Bencheng
//     * @param className
//     * @param strPk
//     * @return SuperVO
//     * @throws MylbcException
//     ********************************************************************************************************/
//    public SuperVO retrieveByPk(Class className, String strPk) throws MylbcException
//    {
//        try
//        {
//            return (SuperVO) getBaseDAO().retrieveByPK(className, strPk);
//        }
//        catch (DAOException ex)
//        {
//            Logger.error(ex.getMessage(), ex);
//            
//            throw new MylbcException(ex.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-10-25 10:10:36<br>
//     * 
//     * @author Li Bencheng
//     * @param className
//     * @param strPk
//     * @param strSelectedFields
//     * @return SuperVO
//     * @throws MylbcException
//     ********************************************************************************************************/
//    public SuperVO retrieveByPk(Class className, String strPk, String strSelectedFields[]) throws MylbcException
//    {
//        try
//        {
//            return (SuperVO) getBaseDAO().retrieveByPK(className, strPk, strSelectedFields);
//        }
//        catch (DAOException ex)
//        {
//            Logger.error(ex.getMessage(), ex);
//            
//            throw new MylbcException(ex.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-7-3 10:38:44<br>
//     * 
//     * @author Li Bencheng 根据指定的SQL,类对象（SuperVO）和参数对象，生成查询结果
//     * @author Wanghai
//     * @param <T>
//     * @param className
//     * @param sql
//     * @param par
//     * @return T
//     * @throws MylbcException
//     ********************************************************************************************************/
//    public <T extends SuperVO> T[] retrieveBySQL(Class<T> className, String sql, SQLParameter par) throws MylbcException
//    {
//        if (sql == null || sql.length() <= 0)
//        {
//            throw new MylbcException(ResHelper.getString("6001frame", "06001frame0149")
//            /* @res "传入的SQL语句为空!" */);
//        }
//        
//        try
//        {
//            BaseDAO dao = new BaseDAO();
//            
//            List<T> list = null;
//            
//            if (par != null)
//            {
//                list = (List<T>) dao.executeQuery(sql, par, new BeanListProcessor(className));
//            }
//            else
//            {
//                list = (List<T>) dao.executeQuery(sql, new BeanListProcessor(className));
//            }
//            
//            if (list == null)
//            {
//                return null;
//            }
//            
//            T[] result = (T[]) Array.newInstance(className, list.size());
//            list.toArray(result);
//            
//            return result;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e);
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 根据指定的SQL和类对象，生成查询结果<br>
//     * Created on 2019-11-16 09:34:30<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     * @param className
//     * @param sql
//     * @return Object[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public Object[] retrieveBySQL(String ds, Class className, String sql) throws MylbcException
//    {
//        if (sql == null || sql.length() <= 0)
//        {
//            throw new MylbcException(ResHelper.getString("6001frame", "06001frame0149")
//            /* @res "传入的SQL语句为空!" */);
//        }
//        
//        // 生成新的JDBC Session
//        JdbcSession s = null;
//        PersistenceManager sessionManager = null;
//        
//        try
//        {
//            if (ds == null || ds.length() == 0)
//            {
//                sessionManager = PersistenceManager.getInstance();
//            }
//            else
//            {
//                sessionManager = PersistenceManager.getInstance(ds);
//            }
//            
//            s = sessionManager.getJdbcSession();
//            
//            List list = (List) s.executeQuery(sql, new BeanListProcessor(className));
//            
//            if (list == null)
//            {
//                return null;
//            }
//            
//            int len = list.size();
//            
//            Object[] resObjAry = (Object[]) Array.newInstance(className, len);
//            list.toArray(resObjAry);
//            
//            return resObjAry;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//        finally
//        {
//            if (sessionManager != null)
//            {
//                sessionManager.release();
//            }
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 保存一个聚合ＶＯ的方法<br>
//     * Created on 2019-10-18 10:12:59<br>
//     * 
//     * @author Li Bencheng
//     * @param ds
//     * @param aggVO
//     * @return AggSaveResultVO
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public AggSaveResultVO saveAggVO(String ds, HRAggVO aggVO) throws MylbcException
//    {
//        if (aggVO == null)
//        {
//            return null;
//        }
//        
//        IdValueObj idObj = null;
//        String headPkFiledName = null;
//        String headPkFiledValue = null;
//        AggSaveResultVO aggResVO = new AggSaveResultVO();
//        
//        try
//        {
//            // 先处理表头
//            SuperVO headVO = (SuperVO) aggVO.getParentVO();
//            
//            if (headVO != null)
//            {
//                headPkFiledName = headVO.getPKFieldName(); // 记录新增的主键名
//                headPkFiledValue = headVO.getPrimaryKey(); // 记录新增的主键值
//                if (headVO.getStatus() == VOStatus.NEW)
//                {
//                    idObj = new IdValueObj();
//                    idObj.setTypeID(VOStatus.NEW);
//                    idObj.setTypeData(getBaseDAO().insertVO(headVO));
//                    aggResVO.setHeadVORes(idObj);
//                    
//                    // 设置表头主键的新增标记
//                    headPkFiledName = headVO.getPKFieldName(); // 记录新增的主键名
//                    headPkFiledValue = (String) idObj.getTypeData(); // 记录新增的主键值
//                    
//                    headVO.setPrimaryKey(headPkFiledValue);
//                }
//                else if (headVO.getStatus() == VOStatus.UPDATED)
//                {
//                    idObj = new IdValueObj();
//                    idObj.setTypeID(VOStatus.UPDATED);
//                    if (aggVO.getHeadVOSelFields() == null || aggVO.getHeadVOSelFields().length <= 0)
//                    {
//                        idObj.setTypeData("" + getBaseDAO().execUpdateByVoState(new SuperVO[]{headVO}).getTs());
//                    }
//                    else
//                    {
//                        idObj.setTypeData("" + getBaseDAO().execUpdateByVoState(new SuperVO[]{headVO}, aggVO.getHeadVOSelFields()).getTs());
//                    }
//                    
//                    aggResVO.setHeadVORes(idObj);
//                }
//            }
//            
//            // 再轮流处理表体
//            String[] tabCodes = aggVO.getTableCodes();
//            if (tabCodes != null && tabCodes.length > 0)
//            {
//                String[] bodyTabFKs = aggVO.getBodyTableFKs();
//                boolean isFK = bodyTabFKs != null && bodyTabFKs.length == tabCodes.length;
//                
//                SuperVO[] bodyVOs = null;
//                IdValueObj[] resVOs = null;
//                Vector<SuperVO> vNewSupVOs = null;
//                Vector<IdValueObj> vNewResObjs = null;
//                Vector<SuperVO> vDelSupVOs = null;
//                Vector<IdValueObj> vDelResObjs = null;
//                Vector<SuperVO> vUpdateSupVOs = null;
//                Vector<IdValueObj> vUpdateResObjs = null;
//                
//                for (int i = 0; i < tabCodes.length; i++)
//                {
//                    bodyVOs = (SuperVO[]) aggVO.getTableVO(tabCodes[i]);
//                    
//                    if (bodyVOs == null || bodyVOs.length <= 0)
//                    {
//                        continue;
//                    }
//                    
//                    resVOs = new IdValueObj[bodyVOs.length];
//                    vNewSupVOs = new Vector<SuperVO>();
//                    vNewResObjs = new Vector<IdValueObj>();
//                    vDelSupVOs = new Vector<SuperVO>();
//                    vDelResObjs = new Vector<IdValueObj>();
//                    vUpdateSupVOs = new Vector<SuperVO>();
//                    vUpdateResObjs = new Vector<IdValueObj>();
//                    for (int j = 0; j < resVOs.length; j++)
//                    {
//                        if (bodyVOs[j].getStatus() == VOStatus.NEW)
//                        {
//                            resVOs[j] = new IdValueObj();
//                            resVOs[j].setTypeID(VOStatus.NEW);
//                            vNewSupVOs.addElement(bodyVOs[j]);
//                            vNewResObjs.addElement(resVOs[j]);
//                            
//                            // 处理新增的表头格式
//                            bodyVOs[j].setAttributeValue(isFK && bodyTabFKs != null ? bodyTabFKs[i] : headPkFiledName, headPkFiledValue);
//                            resVOs[j].setTypeExtData(headPkFiledValue);
//                        }
//                        else if (bodyVOs[j].getStatus() == VOStatus.DELETED)
//                        {
//                            resVOs[j] = new IdValueObj();
//                            resVOs[j].setTypeID(VOStatus.DELETED);
//                            vDelSupVOs.addElement(bodyVOs[j]);
//                            vDelResObjs.addElement(resVOs[j]);
//                        }
//                        else if (bodyVOs[j].getStatus() == VOStatus.UPDATED)
//                        {
//                            resVOs[j] = new IdValueObj();
//                            resVOs[j].setTypeID(VOStatus.UPDATED);
//                            vUpdateSupVOs.addElement(bodyVOs[j]);
//                            vUpdateResObjs.addElement(resVOs[j]);
//                        }
//                        else
//                        {
//                            resVOs[j] = new IdValueObj();
//                            resVOs[j].setTypeID(VOStatus.UNCHANGED);
//                        }
//                    }
//                    
//                    // 首先处理删除的
//                    int sizeDelete = vDelSupVOs.size();
//                    if (sizeDelete > 0)
//                    {
//                        SuperVO[] deletingVOs = new SuperVO[sizeDelete];
//                        vDelSupVOs.copyInto(deletingVOs);
//                        getBaseDAO().deleteVOArray(deletingVOs);
//                    }
//                    
//                    // 然后处理修改的
//                    int sizeUpdate = vUpdateSupVOs.size();
//                    String[] tmpSelFields = null;
//                    if (sizeUpdate > 0)
//                    {
//                        tmpSelFields = aggVO.getBodyVOSelFields(tabCodes[i]);
//                        SuperVO[] updatingVOs = new SuperVO[sizeUpdate];
//                        vUpdateSupVOs.copyInto(updatingVOs);
//                        
//                        if (tmpSelFields == null || tmpSelFields.length <= 0)
//                        {
//                            getBaseDAO().execUpdateByVoState(updatingVOs);
//                        }
//                        else
//                        {
//                            getBaseDAO().execUpdateByVoState(updatingVOs, tmpSelFields);
//                        }
//                    }
//                    
//                    // 最后处理增加的
//                    int sizeNew = vNewSupVOs.size();
//                    if (sizeNew > 0)
//                    {
//                        SuperVO[] addingVOs = new SuperVO[sizeNew];
//                        vNewSupVOs.copyInto(addingVOs);
//                        String[] pks = getBaseDAO().insertVOArray(addingVOs);
//                        if (pks != null && pks.length > 0)
//                        {
//                            for (int j = 0; j < pks.length; j++)
//                            {
//                                // 设置新增的结果
//                                vNewResObjs.get(j).setTypeData(pks[j]);
//                                addingVOs[j].setPrimaryKey(pks[j]);
//                            }
//                        }
//                    }
//                    
//                    // 加入返回VO
//                    aggResVO.addBodyResVOs(tabCodes[i], resVOs);
//                }
//            }
//            
//            // 返回VO
//            return aggResVO;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 更新值对象集合<br>
//     * Created on 2019-11-16 09:34:43<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @param selFields
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void update(final List vos, String[] selFields) throws MylbcException
//    {
//        if (vos == null || vos.size() <= 0)
//        {
//            return;
//        }
//        
//        try
//        {
//            SuperVO[] sVOs = new SuperVO[vos.size()];
//            vos.toArray(sVOs);
//            
//            if (selFields != null && selFields.length > 0)
//            {
//                getBaseDAO().updateVOArray(sVOs, selFields);
//            }
//            else
//            {
//                getBaseDAO().updateVOArray(sVOs);
//            }
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 更新一个在数据库中已经存在值对象<br>
//     * Created on 2019-11-16 09:35:18<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @param selFields
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void update(final SuperVO vo, String[] selFields) throws MylbcException
//    {
//        try
//        {
//            if (vo == null)
//            {
//                return;
//            }
//            if (selFields != null && selFields.length > 0)
//            {
//                getBaseDAO().updateVO(vo, selFields);
//            }
//            else
//            {
//                getBaseDAO().updateVO(vo);
//            }
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 更新值对象数组<br>
//     * Created on 2019-11-16 09:35:33<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @param selFields
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void update(final SuperVO[] vos, String[] selFields) throws MylbcException
//    {
//        if (vos == null || vos.length <= 0)
//        {
//            return;
//        }
//        
//        try
//        {
//            if (selFields != null && selFields.length > 0)
//            {
//                getBaseDAO().updateVOArray(vos, selFields);
//            }
//            else
//            {
//                getBaseDAO().updateVOArray(vos);
//            }
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * 更新值对象数组，并且对指定的字段设置时间戳<br>
//     * Created on 2019-11-16 09:36:22<br>
//     * 
//     * @author Li Bencheng
//     * @param vos
//     * @param selFields
//     * @param tsFields
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public void updateAndSetTs(SuperVO[] vos, String[] selFields, String[] tsFields) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.length <= 0)
//            {
//                return;
//            }
//            
//            if (tsFields != null && tsFields.length > 0)
//            {
//                UFDateTime tsReal = PubEnv.getServerTime();
//                
//                for (int i = 0; i < vos.length; i++)
//                {
//                    for (int j = 0; j < tsFields.length; j++)
//                    {
//                        vos[i].setAttributeValue(tsFields[j], tsReal.toString());
//                    }
//                }
//            }
//            
//            if (selFields != null && selFields.length > 0)
//            {
//                getBaseDAO().updateVOArray(vos, selFields);
//            }
//            else
//            {
//                getBaseDAO().updateVOArray(vos);
//            }
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * @param vos
//     * @param selFields
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    @Override
//    public String[] updateVOArrayByStatus(SuperVO[] vos, String[] selFields) throws MylbcException
//    {
//        if (vos == null || vos.length <= 0)
//        {
//            return null;
//        }
//        
//        try
//        {
//            String[] pks = new String[vos.length];
//            
//            List<SuperVO> listAdd = new ArrayList<SuperVO>();
//            List<SuperVO> listUpd = new ArrayList<SuperVO>();
//            List<SuperVO> listDel = new ArrayList<SuperVO>();
//            
//            // 循环操作数据库
//            for (int i = 0; i < vos.length; i++)
//            {
//                if (VOStatus.NEW == vos[i].getStatus())
//                {
//                    listAdd.add(vos[i]);
//                }
//                else if (VOStatus.UPDATED == vos[i].getStatus())
//                {
//                    listUpd.add(vos[i]);
//                }
//                else if (VOStatus.DELETED == vos[i].getStatus())
//                {
//                    listDel.add(vos[i]);
//                }
//            }
//            
//            pks = insert(listAdd);
//            update(listUpd, selFields);
//            delete(listDel);
//            
//            return pks;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * @param vos
//     * @param selFields
//     * @return String[]
//     * @throws MylbcException
//     ********************************************************************************************************/
//    public String[] updateVOArrayByStatusWithPK(SuperVO[] vos, String[] selFields) throws MylbcException
//    {
//        try
//        {
//            if (vos == null || vos.length <= 0)
//            {
//                return null;
//            }
//            
//            String[] pks = new String[vos.length];
//            
//            // 循环操作数据库
//            for (int i = 0; i < vos.length; i++)
//            {
//                pks[i] = updateVOByStatusWithPK(vos[i], selFields);
//            }
//            
//            return pks;
//        }
//        catch (Exception e)
//        {
//            Logger.error(e.getMessage(), e);
//            throw new MylbcException(e.getMessage());
//        }
//    }
//    
//    /*********************************************************************************************************
//     * <br>
//     * Created on 2019-9-5 11:49:29<br>
//     * 
//     * @author Li Bencheng
//     * @param vo
//     * @param selFields
//     * @return pk
//     * @throws MylbcException String
//     ********************************************************************************************************/
//    private String updateVOByStatusWithPK(SuperVO vo, String[] selFields) throws MylbcException
//    {
//        String pk = null;
//        try
//        {
//            if (vo.getStatus() == VOStatus.NEW)
//            {
//                pk = getBaseDAO().insertVOWithPK(vo);
//            }
//            else if (vo.getStatus() == VOStatus.DELETED)
//            {
//                getBaseDAO().deleteVO(vo);
//            }
//            else if (vo.getStatus() == VOStatus.UPDATED)
//            {
//                if (selFields != null && selFields.length > 0)
//                {
//                    getBaseDAO().updateVO(vo, selFields);
//                }
//                else
//                {
//                    getBaseDAO().updateVO(vo);
//                }
//            }
//        }
//        catch (DAOException e)
//        {
//            Logger.error(e.getMessage(), e);
//            throw new MylbcException(e.getMessage());
//        }
//        
//        return pk;
//    }
//}
