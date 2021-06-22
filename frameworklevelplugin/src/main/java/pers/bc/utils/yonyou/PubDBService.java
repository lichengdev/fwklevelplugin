//package pers.bc.utils.yonyou;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import nc.bs.dao.DAOException;
//import nc.jdbc.framework.processor.BeanListProcessor;
//import nc.jdbc.framework.processor.ColumnListProcessor;
//import nc.jdbc.framework.processor.ColumnProcessor;
//import nc.ui.querytemplate.querytree.IQueryScheme;
//import nc.vo.pub.AggregatedValueObject;
//import nc.vo.pub.BusinessException;
//import nc.vo.pub.SuperVO;
//import nc.vo.pub.lang.UFDateTime;
//import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
//import nc.vo.trade.pub.IExAggVO;
//import nc.vo.uif2.LoginContext;
//import pers.bc.utils.Bean.ReflectionUtilbc;
//import pers.bc.utils.pub.PubEnvUtilbc;
//import pers.bc.utils.pub.StringUtilbc;
//import pers.bc.utils.throwable.MylbcException;
//
//public class PubDBService
//{
//    private int inCount = 250;
//    
//    public void checkTS(SuperVO superVO) throws BusinessException
//    {
//        SuperVO obj = (SuperVO) YonYouUtilbc.getBaseDAOQueryBSyC().retrieveByPK(superVO.getClass(), superVO.getPrimaryKey());
//        
//        if (((UFDateTime) (superVO.getAttributeValue("ts"))).before((UFDateTime) (obj.getAttributeValue("ts"))))
//        {
//            throw new BusinessException("操作失败！当前单据已被他人操作，请刷新后操作！！");
//        }
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：执行插入操作 <br>
//     * @see <br>
//     * @param obj
//     * @return
//     * @throws DAOException <br>
//     * @Object <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#exeInsert <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:28:49 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public Object insert(Object insertObj) throws DAOException
//    {
//        return exeUpdata(insertObj, true);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：执行更新操作 <br>
//     * @see <br>
//     * @param obj
//     * @return
//     * @throws DAOException <br>
//     * @Object <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#exeUpdate <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:28:29 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public Object update(Object updataObj) throws DAOException
//    {
//        return exeUpdata(updataObj, false);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：执行删除 <br>
//     * @see <br>
//     * @param obj
//     * @throws MylbcException <br>
//     * @void <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#delete <br>
//     * @author LiBencheng <br>
//     * @throws DAOException
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:57 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public void delete(Object obj) throws DAOException
//    {
//        if (PubEnvUtilbc.isEmptyObj(obj)) return;
//        List<SuperVO> deleteVOs = new ArrayList<>();
//        
//        if (obj instanceof SuperVO)
//        {
//            SuperVO pVO = (SuperVO) obj;
//            deleteVOs.add(pVO);
//        }
//        if (obj instanceof IExAggVO)
//        {
//            IExAggVO aggVO = (IExAggVO) obj;
//            SuperVO pVO = (SuperVO) aggVO.getParentVO();
//            
//            deleteVOs.add(pVO);
//            String[] tableCodes = aggVO.getTableCodes();
//            for (int i = 0, j = PubEnvUtilbc.getSize(tableCodes); i < j; i++)
//            {
//                String tableCode = tableCodes[i];
//                SuperVO[] childrenVOs = (SuperVO[]) aggVO.getTableVO(tableCode);
//                deleteVOs.addAll(Arrays.asList(childrenVOs));
//            }
//        }
//        if (obj instanceof AggregatedValueObject)
//        {
//            AggregatedValueObject aggVO = (AggregatedValueObject) obj;
//            SuperVO pVO = (SuperVO) aggVO.getParentVO();
//            SuperVO[] childrenVOs = (SuperVO[]) aggVO.getChildrenVO();
//            
//            deleteVOs.add(pVO);
//            deleteVOs.addAll(Arrays.asList(childrenVOs));
//        }
//        
//        if (PubEnvUtilbc.getSize(deleteVOs) > 0) YonYouUtilbc.getBaseDAO().deleteVOList(deleteVOs);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @param context
//     * @param superVO
//     * @param qScheme
//     * @return
//     * @throws DAOException <br>
//     * @String[] <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#queryByPageQueryScheme <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:50 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    @SuppressWarnings({"unchecked", "deprecation"})
//    public String[] queryByPageQueryScheme(LoginContext context, SuperVO superVO, IQueryScheme qScheme) throws DAOException
//    {
//        if (PubEnvUtilbc.isEmptyObj(superVO)) return null;
//        QuerySchemeProcessor processor = new QuerySchemeProcessor(qScheme);
//        StringBuffer qrySQL = new StringBuffer();
//        qrySQL.append("SELECT ").append(superVO.getPKFieldName());
//        qrySQL.append(" ").append(processor.getFinalFromWhere());
//        List<String> pks = (List<String>) YonYouUtilbc.getBaseDAO().executeQuery(qrySQL.toString(), new ColumnListProcessor());
//        
//        return PubEnvUtilbc.getSize(pks) > 0 ? pks.toArray(new String[0]) : null;
//    }
//    
//    public String[] queryPKSByPagination(LoginContext context, SuperVO superVO) throws DAOException
//    {
//        return queryPKSByPagination(context, superVO, null, null);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @param context
//     * @param superVO
//     * @param treePkName
//     * @param treePk
//     * @return
//     * @throws DAOException <br>
//     * @String[] <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#queryPKSByPagination <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:43 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    @SuppressWarnings({"unchecked", "deprecation"})
//    public String[] queryPKSByPagination(LoginContext context, SuperVO superVO, String treePkName, String treePk) throws DAOException
//    {
//        if (PubEnvUtilbc.isEmptyObj(superVO)) return null;
//        StringBuffer qrySQL = new StringBuffer();
//        qrySQL.append("SELECT ").append(superVO.getPKFieldName());
//        qrySQL.append(" FROM ").append(superVO.getTableName());
//        qrySQL.append(" WHERE ").append("dr='0'");
//        if (!PubEnvUtilbc.isEmptyObj(treePkName))
//        {
//            qrySQL.append(" AND ").append(treePkName);
//            qrySQL.append(" ='").append(treePk).append("'");
//        }
//        List<String> pks = (List<String>) YonYouUtilbc.getBaseDAO().executeQuery(qrySQL.toString(), new ColumnListProcessor());
//        
//        return PubEnvUtilbc.getSize(pks) > 0 ? pks.toArray(new String[0]) : null;
//        
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 执行查询信息，返回Object<br>
//     * @see <br>
//     * @param context
//     * @param obj
//     * @param pk_values
//     * @param qScheme
//     * @return
//     * @throws InstantiationException
//     * @throws IllegalAccessException
//     * @throws BusinessException <br>
//     * @Object[] <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#exeQueryBycondition <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:22:56 <br>
//     * @throws ClassNotFoundException
//     * @throws NegativeArraySizeException
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public Object[] exeQueryBycondition(LoginContext context, Object obj, String[] pk_values, IQueryScheme qScheme)
//            throws InstantiationException, IllegalAccessException, BusinessException, NegativeArraySizeException, ClassNotFoundException
//    {
//        if (!PubEnvUtilbc.isEmptyObj(qScheme)) return queryByQueryScheme(context, obj, qScheme);
//        if (PubEnvUtilbc.getSize(pk_values) > 0) return queryByPks(context, obj, pk_values);
//        
//        return null;
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：设置pk_org_v版本信息 <br>
//     * @see <br>
//     * @param superVO
//     * @throws DAOException <br>
//     * @void <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#setPk_org_v <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:10 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    private void setPk_org_v(SuperVO superVO) throws DAOException
//    {
//        if (PubEnvUtilbc.isEmptyObj(superVO)) return;
//        Object value = superVO.getAttributeValue("pk_org_v");
//        if (!PubEnvUtilbc.isEmptyObj(value))
//        {
//            String pk_org_v = "SELECT pk_vid FROM  org_orgs WHERE pk_org='" + superVO.getAttributeValue("pk_org") + "'";
//            pk_org_v = (String) YonYouUtilbc.getBaseDAO().executeQuery(pk_org_v, new ColumnProcessor());
//            superVO.setAttributeValue("pk_org_v", pk_org_v);
//        }
//    }
//    
//    @SuppressWarnings({"deprecation"})
//    private Object exeUpdata(Object obj, Boolean isInsert) throws DAOException
//    {
//        if (PubEnvUtilbc.isEmptyObj(obj)) return null;
//        Object object = null;
//        List<SuperVO> insertVOs = new ArrayList<>();
//        List<SuperVO> updateVOs = new ArrayList<>();
//        String pk_value = null;
//        Boolean isAbstractBill = false;
//        if (obj instanceof SuperVO)
//        {
//            SuperVO pVO = (SuperVO) obj;
//            setPk_org_v(pVO);
//            if (isInsert)
//            {
//                pk_value = YonYouUtilbc.getGenerator().generate();
//                pVO.setAttributeValue(pVO.getPKFieldName(), pk_value);
//                pk_value = YonYouUtilbc.getBaseDAO().insertVOWithPK(pVO);
//            }
//            else
//            {
//                YonYouUtilbc.getBaseDAO().updateVO(pVO);
//            }
//            object = pVO;
//        }
//        if (obj instanceof IExAggVO)
//        {
//            isAbstractBill = true;
//            IExAggVO aggVO = (IExAggVO) obj;
//            SuperVO pVO = (SuperVO) aggVO.getParentVO();
//            setPk_org_v(pVO);
//            if (isInsert)
//            {
//                pk_value = YonYouUtilbc.getGenerator().generate();
//                pVO.setAttributeValue(pVO.getPKFieldName(), pk_value);
//                // insertVOs.add(pVO);
//                YonYouUtilbc.getBaseDAO().insertVOWithPK(pVO);
//            }
//            else
//            {
//                // updateVOs.add(pVO);
//                YonYouUtilbc.getBaseDAO().updateVO(pVO);
//                pk_value = StringUtilbc.valueOf(pVO.getAttributeValue(pVO.getPKFieldName()));
//            }
//            aggVO.setParentVO(pVO);
//            String[] tableCodes = aggVO.getTableCodes();
//            for (int i = 0, j = PubEnvUtilbc.getSize(tableCodes); i < j; i++)
//            {
//                String tableCode = tableCodes[i];
//                SuperVO[] childrenVOs = (SuperVO[]) aggVO.getTableVO(tableCode);
//                SuperVO[] newChildrenVOs = new SuperVO[PubEnvUtilbc.getSize(childrenVOs)];
//                for (int x = 0, y = PubEnvUtilbc.getSize(childrenVOs); x < y; x++)
//                {
//                    SuperVO cVO = childrenVOs[x];
//                    if (isInsert)
//                    {
//                        String cPK = YonYouUtilbc.getGenerator().generate();
//                        cVO.setAttributeValue(cVO.getParentPKFieldName(), pk_value);
//                        cVO.setAttributeValue(cVO.getPrimaryKey(), cPK);
//                        insertVOs.add(cVO);
//                    }
//                    else
//                    {
//                        if (PubEnvUtilbc.isEmptyObj(cVO.getPrimaryKey()))
//                        {
//                            String cPK = YonYouUtilbc.getGenerator().generate();
//                            cVO.setAttributeValue(cVO.getPKFieldName(), cPK);
//                            cVO.setAttributeValue(cVO.getParentPKFieldName(), pk_value);
//                            insertVOs.add(cVO);
//                        }
//                        else
//                        {
//                            updateVOs.add(cVO);
//                        }
//                    }
//                    newChildrenVOs[x] = cVO;
//                    if (PubEnvUtilbc.getSize(insertVOs) > 0)
//                    {
//                        YonYouUtilbc.getBaseDAO().insertVOArrayWithPK(insertVOs.toArray(new SuperVO[0]));
//                        insertVOs = new ArrayList<>();
//                    }
//                }
//                aggVO.setTableVO(tableCode, newChildrenVOs);
//            }
//            
//            if (PubEnvUtilbc.getSize(insertVOs) > 0) YonYouUtilbc.getBaseDAO().insertVOArrayWithPK(insertVOs.toArray(new SuperVO[0]));
//            if (PubEnvUtilbc.getSize(updateVOs) > 0) YonYouUtilbc.getBaseDAO().updateVOArray(updateVOs.toArray(new SuperVO[0]));
//            
//            object = aggVO;
//            isAbstractBill = true;
//            
//        }
//        if ((obj instanceof AggregatedValueObject) && (!isAbstractBill))
//        {
//            AggregatedValueObject aggVO = (AggregatedValueObject) obj;
//            SuperVO pVO = (SuperVO) aggVO.getParentVO();
//            setPk_org_v(pVO);
//            if (isInsert)
//            {
//                pk_value = YonYouUtilbc.getGenerator().generate();
//                pVO.setAttributeValue(pVO.getPKFieldName(), pk_value);
//                // insertVOs.add(pVO);
//                YonYouUtilbc.getBaseDAO().insertVOWithPK(pVO);
//            }
//            else
//            {
//                updateVOs.add(pVO);
//                pk_value = StringUtilbc.valueOf(pVO.getAttributeValue(pVO.getPKFieldName()));
//            }
//            aggVO.setParentVO(pVO);
//            SuperVO[] childrenVOs = (SuperVO[]) aggVO.getChildrenVO();
//            SuperVO[] newChildrenVOs = new SuperVO[PubEnvUtilbc.getSize(childrenVOs)];
//            for (int x = 0, y = PubEnvUtilbc.getSize(childrenVOs); x < y; x++)
//            {
//                SuperVO cVO = childrenVOs[x];
//                if (isInsert)
//                {
//                    String cPK = YonYouUtilbc.getGenerator().generate();
//                    cVO.setAttributeValue(cVO.getParentPKFieldName(), pk_value);
//                    cVO.setAttributeValue(cVO.getPKFieldName(), cPK);
//                    insertVOs.add(cVO);
//                }
//                else
//                {
//                    if (PubEnvUtilbc.isEmptyObj(cVO.getPrimaryKey()))
//                    {
//                        String cPK = YonYouUtilbc.getGenerator().generate();
//                        cVO.setAttributeValue(cVO.getPKFieldName(), cPK);
//                        cVO.setAttributeValue(cVO.getParentPKFieldName(), pk_value);
//                        insertVOs.add(cVO);
//                    }
//                    else
//                    {
//                        updateVOs.add(cVO);
//                    }
//                }
//                newChildrenVOs[x] = cVO;
//            }
//            aggVO.setChildrenVO(newChildrenVOs);
//            
//            if (PubEnvUtilbc.getSize(insertVOs) > 0) YonYouUtilbc.getBaseDAO().insertVOArrayWithPK(insertVOs.toArray(new SuperVO[0]));
//            if (PubEnvUtilbc.getSize(updateVOs) > 0) YonYouUtilbc.getBaseDAO().updateVOArray(updateVOs.toArray(new SuperVO[0]));
//            
//            object = aggVO;
//        }
//        return object;
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @param context
//     * @param obj
//     * @param qScheme
//     * @return
//     * @throws InstantiationException
//     * @throws IllegalAccessException
//     * @throws BusinessException <br>
//     * @Object[] <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#queryByQueryScheme <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:29 <br>
//     * @throws ClassNotFoundException
//     * @throws NegativeArraySizeException
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    private Object[] queryByQueryScheme(LoginContext context, Object obj, IQueryScheme qScheme) throws InstantiationException,
//            IllegalAccessException, BusinessException, NegativeArraySizeException, ClassNotFoundException
//    {
//        if (PubEnvUtilbc.isEmptyObj(obj)) return null;
//        Boolean isAbstractBill = false;
//        SuperVO superVO = null;
//        if (obj instanceof SuperVO)
//        {
//            superVO = (SuperVO) obj;
//        }
//        if (obj instanceof IExAggVO)
//        {
//            IExAggVO aggVO = (IExAggVO) obj;
//            superVO = (SuperVO) aggVO.getParentVO();
//            isAbstractBill = true;
//        }
//        if ((obj instanceof AggregatedValueObject) && (!isAbstractBill))
//        {
//            AggregatedValueObject aggVO = (AggregatedValueObject) obj;
//            superVO = (SuperVO) aggVO.getParentVO();
//        }
//        if (PubEnvUtilbc.isEmptyObj(superVO)) return null;
//        
//        String[] pk_values = queryByPageQueryScheme(context, superVO, qScheme);
//        return queryByPks(context, obj, pk_values);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @param context
//     * @param obj
//     * @param pk_values
//     * @return
//     * @throws InstantiationException
//     * @throws IllegalAccessException
//     * @throws BusinessException <br>
//     * @Object[] <br>
//     * @methods pers.bc.utils.yonyou.PubDBService#queryByPK <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-16 <br>
//     * @time 上午11:26:36 <br>
//     * @throws ClassNotFoundException
//     * @throws NegativeArraySizeException
//     * @version 2.0 <br>
//     ************************************************************* <br>
//     */
//    private Object[] queryByPks(LoginContext context, Object obj, String[] pk_values) throws InstantiationException,
//            IllegalAccessException, BusinessException, NegativeArraySizeException, ClassNotFoundException
//    {
//        return queryByPks(context, obj, null, pk_values);
//    }
//    
//    @SuppressWarnings({"deprecation"})
//    public Object[] queryByPks(LoginContext context, Object obj, String conditionKey, String[] pk_values) throws InstantiationException,
//            IllegalAccessException, BusinessException, NegativeArraySizeException, ClassNotFoundException
//    {
//        if (PubEnvUtilbc.getSize(pk_values) < 1 || PubEnvUtilbc.isEmptyObj(obj)) return null;
//        Boolean isAbstractBill = false;
//        Object[] datas = null;
//        SuperVO[] pVOs = null;
//        SuperVO[] cVOs = null;
//        if (obj instanceof SuperVO)
//        {
//            SuperVO superVO = (SuperVO) obj;
//            datas = retrieveVOs(superVO, conditionKey, pk_values);
//        }
//        if (obj instanceof IExAggVO)
//        {
//            IExAggVO aggVO = (IExAggVO) obj;
//            SuperVO superVO = (SuperVO) aggVO.getParentVO();
//            pVOs = retrieveVOs(superVO, conditionKey, pk_values);
//            int size = PubEnvUtilbc.getSize(pVOs);
//            // Object[] aggVOs = new Object[size];
//            Object[] aggVOs = (Object[]) Array.newInstance(Class.forName(aggVO.getClass().getName()), size);
//            String[] tableCodes = aggVO.getTableCodes();
//            for (int i = 0; i < size; i++)
//            {
//                IExAggVO newAggVO = (IExAggVO) ReflectionUtilbc.getNewInstance(aggVO.getClass());
//                SuperVO pVO = (SuperVO) pVOs[i];
//                newAggVO.setParentVO(pVO);
//                for (int x = 0, y = PubEnvUtilbc.getSize(tableCodes); x < y; x++)
//                {
//                    String tableCode = tableCodes[x];
//                    SuperVO[] childrenVOs = (SuperVO[]) aggVO.getTableVO(tableCode);
//                    if (PubEnvUtilbc.getSize(childrenVOs) > 0)
//                    {
//                        cVOs = retrieveVOs(childrenVOs[0], childrenVOs[0].getParentPKFieldName(), new String[]{pVO.getPrimaryKey()});
//                        newAggVO.setTableVO(tableCode, cVOs);
//                    }
//                }
//                aggVOs[i] = newAggVO;
//            }
//            // 为true时,不走下面的代码(AggregatedValueObject)
//            isAbstractBill = true;
//            datas = aggVOs;
//        }
//        if ((obj instanceof AggregatedValueObject) && (!isAbstractBill))
//        {
//            
//            AggregatedValueObject aggVO = (AggregatedValueObject) obj;
//            SuperVO superVO = (SuperVO) aggVO.getParentVO();
//            pVOs = retrieveVOs(superVO, conditionKey, pk_values);
//            int size = PubEnvUtilbc.getSize(pVOs);
//            // AggregatedValueObject[] aggVOs = new AggregatedValueObject[size];
//            AggregatedValueObject[] aggVOs = (AggregatedValueObject[]) Array.newInstance(Class.forName(aggVO.getClass().getName()), size);
//            for (int i = 0; i < size; i++)
//            {
//                AggregatedValueObject newAggVO = (AggregatedValueObject) ReflectionUtilbc.getNewInstance(aggVO.getClass());
//                SuperVO pVO = (SuperVO) pVOs[i];
//                newAggVO.setParentVO(pVO);
//                SuperVO[] childrenVOs = (SuperVO[]) aggVO.getChildrenVO();
//                if (PubEnvUtilbc.getSize(childrenVOs) > 0)
//                {
//                    cVOs = retrieveVOs(childrenVOs[0], childrenVOs[0].getParentPKFieldName(), new String[]{pVO.getPrimaryKey()});
//                    newAggVO.setChildrenVO(cVOs);
//                }
//                aggVOs[i] = newAggVO;
//            }
//            datas = aggVOs;
//        }
//        return datas;
//    }
//    
//    @SuppressWarnings({"unchecked", "deprecation"})
//    private SuperVO[] retrieveVOs(SuperVO superVO, String conditionKey, String[] pk_values) throws BusinessException,
//            NegativeArraySizeException, ClassNotFoundException
//    {
//        if (PubEnvUtilbc.isEmptyObj(superVO)) return null;
//        
//        List<SuperVO> superVOs = new ArrayList<>();
//        if (PubEnvUtilbc.isEmptyObj(conditionKey)) conditionKey = superVO.getPKFieldName();
//        String tableName = superVO.getTableName();
//        String tempTable = null;
//        StringBuilder qrySQL = new StringBuilder();
//        qrySQL.append("SELECT * FROM ");
//        qrySQL.append(tableName).append(" ").append(tableName);
//        // ②小于205 用in
//        if (PubEnvUtilbc.getSize(pk_values) <= inCount)
//        {
//            StringBuilder pks = new StringBuilder();
//            for (String pk : pk_values)
//            {
//                pks.append("'");
//                pks.append(pk);
//                pks.append("',");
//            }
//            pks.deleteCharAt(pks.length() - 1);// 删除最后一个“，”
//            qrySQL.append(" WHERE ");
//            qrySQL.append(tableName).append(".").append(conditionKey);
//            qrySQL.append(" IN(").append(pks).append(")");
//        }
//        // ③大于250用临时表
//        else
//        {
//            tempTable = YonYouUtilbc.getInSQLCreator().createTempTable("temp_table" + System.currentTimeMillis(), pk_values);
//            qrySQL.append(" INNER JOIN ").append(tempTable).append(" ").append(tempTable);
//            qrySQL.append(" ON ").append(tableName).append(".").append(conditionKey).append("=");
//            qrySQL.append(tempTable).append(".in_pk ");
//        }
//        superVOs = (List<SuperVO>) YonYouUtilbc.getBaseDAO().executeQuery(qrySQL.toString(), new BeanListProcessor(superVO.getClass()));
//        
//        // ④临时表用完删除
//        if (!PubEnvUtilbc.isEmptyObj(tempTable)) YonYouUtilbc.getBaseDAO().executeUpdate(" DROP TABLE " + tempTable);
//        
//        return superVOs.toArray((SuperVO[]) Array.newInstance(Class.forName(superVO.getClass().getName()), superVOs.size()));
//    }
//}
