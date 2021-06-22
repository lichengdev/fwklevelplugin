//package pers.bc.utils.yonyou;
//
//import java.util.List;
//
//import nc.bs.dao.DAOException;
//import nc.vo.hr.frame.persistence.AggSaveResultVO;
//import nc.vo.hr.tools.pub.HRAggVO;
//import nc.vo.pub.SuperVO;
//import pers.bc.utils.throwable.MylbcException;
//
//public interface IPersistenceDAO
//{
//    void delete(List<?> paramList) throws MylbcException;
//    
//    void delete(SuperVO paramSuperVO) throws MylbcException;
//    
//    void delete(SuperVO[] paramArrayOfSuperVO) throws MylbcException;
//    
//    boolean deleteByAggVO(String paramString, HRAggVO paramHRAggVO) throws MylbcException;
//    
//    void deleteMulType(SuperVO[] paramArrayOfSuperVO) throws MylbcException;
//    
//    void executeNoResultSQL(String paramString1, String paramString2) throws MylbcException;
//    
//    int getTabColRefCount(String paramString1, String paramString2, String paramString3, String paramString4) throws MylbcException;
//    
//    String[] insert(List<?> paramList) throws MylbcException;
//    
//    String insert(SuperVO paramSuperVO) throws MylbcException;
//    
//    String[] insert(SuperVO[] paramArrayOfSuperVO) throws MylbcException;
//    
//    String[] insertMulType(SuperVO[] paramArrayOfSuperVO) throws MylbcException;
//    
//    String insertWithPK(SuperVO paramSuperVO) throws MylbcException;
//    
//    String[] insertWithPK(SuperVO[] paramArrayOfSuperVO) throws MylbcException;
//    
//    SuperVO[] queryByHeadBodyCondition(String paramString1, Class<? extends SuperVO> paramClass,
//            Class<? extends SuperVO>[] paramArrayOfClass, String paramString2) throws MylbcException;
//    
//    HRAggVO queryByHeadVO(String paramString, SuperVO paramSuperVO, Class<? extends SuperVO>[] paramArrayOfClass,
//            String[] paramArrayOfString1, String[] paramArrayOfString2, String[] paramArrayOfString3) throws MylbcException;
//    
//    SuperVO[] retrieve(SuperVO paramSuperVO) throws MylbcException;
//    
//    <T extends SuperVO> T[] retrieveAll(Class<T> paramClass) throws MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 根据条件获取对应的参照数据信息,没有值返回一个属性为空的对象<br>
//     * @see <br>
//     * @param className
//     * @param condition
//     * @return
//     * @throws Exception <br>
//     * @SuperVO <br>
//     * @methods pers.bc.utils.yonyou.IPersistenceDAO#retrieveFirstEmptyByClause <br>
//     * @author Li Bencheng <br>
//     * @date Created on 2020-4-26 <br>
//     * @time 上午10:41:29 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    SuperVO retrieveFirstEmptyByClause(Class<?> className, String condition) throws Exception;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：根据条件获取对应的参照数据信息，没有值返回null,查詢出來的vogetting不會出現空指針 <br>
//     * @see <br>
//     * @param className
//     * @param condition
//     * @return
//     * @throws Exception <br>
//     * @SuperVO <br>
//     * @methods pers.bc.utils.yonyou.IPersistenceDAO#retrieveFirstNullByClause <br>
//     * @author Li Bencheng <br>
//     * @date Created on 2020-4-26 <br>
//     * @time 上午10:41:43 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    SuperVO retrieveFirstNullByClause(Class<?> className, String condition) throws Exception;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 根据条件获取对应的参照数据信息 <br>
//     * @see <br>
//     * @param className
//     * @param condition
//     * @param isRetNull
//     * @return
//     * @throws DAOException <br>
//     * @SuperVO <br>
//     * @methods pers.bc.utils.yonyou.IPersistenceDAO#retrieveFirstByClause <br>
//     * @author Li Bencheng <br>
//     * @date Created on 2020-4-26 <br>
//     * @time 上午10:43:27 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    SuperVO retrieveFirstByClause(Class<?> className, String condition, boolean isRetNull) throws Exception;
//    
//    SuperVO[] retrieveByClause(Class<?> paramClass, String paramString) throws MylbcException;
//    
//    Object[] retrieveBySQL(String paramString1, Class<?> paramClass, String paramString2) throws MylbcException;
//    
//    AggSaveResultVO saveAggVO(String paramString, HRAggVO paramHRAggVO) throws MylbcException;
//    
//    void update(List<?> paramList, String[] paramArrayOfString) throws MylbcException;
//    
//    void update(SuperVO paramSuperVO, String[] paramArrayOfString) throws MylbcException;
//    
//    void update(SuperVO[] paramArrayOfSuperVO, String[] paramArrayOfString) throws MylbcException;
//    
//    void updateAndSetTs(SuperVO[] paramArrayOfSuperVO, String[] paramArrayOfString1, String[] paramArrayOfString2) throws MylbcException;
//    
//    String[] updateVOArrayByStatus(SuperVO[] paramArrayOfSuperVO, String[] paramArrayOfString) throws MylbcException;
//}
