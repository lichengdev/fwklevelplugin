//package pers.bc.utils.yonyou;
//
//public interface NC_Itf_PubDBService
//{
//    
//    String[] unValue = new String[]{"~", "null", "", null};
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：新增 <br>
//     * @see <br>
//     * @param nc.vo.pub.SuperVO
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @Object <br>
//     * @methods pers.bc.utils.yonyou.YonYouPubDBService#insert <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-23 <br>
//     * @time 下午2:46:21 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object insert(Object pramObj) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：修改 <br>
//     * @see <br>
//     * @param nc.vo.pub.SuperVO
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @Object <br>
//     * @methods pers.bc.utils.yonyou.YonYouPubDBService#update <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-23 <br>
//     * @time 下午2:46:30 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object update(Object pramObj) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：删除 <br>
//     * @see <br>
//     * @param nc.vo.pub.SuperVO
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @void <br>
//     * @methods pers.bc.utils.yonyou.YonYouPubDBService#delete <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-23 <br>
//     * @time 下午2:46:38 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    void delete(Object pramObj) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 分页查询 ,得到pks<br>
//     * @see <br>
//     * @param context
//     * @param treePk，左树，没有则不需要传
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @String[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPKSByPagination <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-30 <br>
//     * @time 下午11:23:52 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    String[] queryPKSByPagination(nc.vo.uif2.LoginContext context, String primaryKey) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 带查询条件的分页<br>
//     * @see <br>
//     * @param context
//     * @param qScheme
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @String[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPKSByPagination <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-12 <br>
//     * @time 下午1:29:06 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    String[] queryByPageQueryScheme(nc.vo.uif2.LoginContext context, nc.ui.querytemplate.querytree.IQueryScheme qScheme)
//            throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 帶左树的 分页查询 ,得到pks<br>
//     * @see <br>
//     * @param context
//     * @param primaryKey
//     * @param treePk
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @String[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPKSByPagination <br>
//     * @author licheng <br>
//     * @date Created on 2020-4-2 <br>
//     * @time 上午9:06:59 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    String[] queryPKSByPagination(nc.vo.uif2.LoginContext context, String primaryKey, String treePk)
//            throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：分页查询 ,得到具体数据 <br>
//     * @see <br>
//     * @param context
//     * @param pks
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @nc.vo.pub.SuperVO[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPageByPks <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-30 <br>
//     * @time 下午11:24:30 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object[] queryPageByPks(nc.vo.uif2.LoginContext context, String[] pks) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：查询模板查询操作 <br>
//     * @see <br>
//     * @param context
//     * @param qScheme
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @nc.vo.pub.SuperVO[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryByQueryScheme <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-30 <br>
//     * @time 下午11:24:56 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object[] queryByQueryScheme(nc.vo.uif2.LoginContext context, nc.ui.querytemplate.querytree.IQueryScheme qScheme)
//            throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：主键查询操作 <br>
//     * @see <br>
//     * @param context
//     * @param primaryKey 主键
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @nc.vo.pub.SuperVO[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPKSByCondition <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-30 <br>
//     * @time 下午11:26:56 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object[] queryPKSByCondition(nc.vo.uif2.LoginContext context, String primaryKey) throws pers.bc.utils.throwable.MylbcException;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：左树主键查询操作 <br>
//     * @see <br>
//     * @param context
//     * @param treePk 左树PK
//     * @param primaryKey 主鍵
//     * @return
//     * @throws pers.bc.utils.throwable.MylbcException <br>
//     * @nc.vo.pub.SuperVO[] <br>
//     * @methods pers.bc.utils.yonyou.NC_Itf_PubDBService#queryPKSByCondition <br>
//     * @author 李本城 <br>
//     * @date Created on 2020-3-30 <br>
//     * @time 下午11:25:55 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    Object[] queryPKSByCondition(nc.vo.uif2.LoginContext context, String treePk, String primaryKey)
//            throws pers.bc.utils.throwable.MylbcException;
//    
//}
