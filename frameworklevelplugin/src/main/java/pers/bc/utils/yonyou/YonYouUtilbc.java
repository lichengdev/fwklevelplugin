//package pers.bc.utils.yonyou;
//
//import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
//import nc.md.model.IBean;
//import nc.vo.pub.SuperVO;
//
///**
// * 用友常用类集合set
// * @qualiFild com.pub.utils.yonyou.YonYouUtilbc.java<br>
// * @author：licheng<br>
// * @date Created on 2019-10-22<br>
// * @version 1.0<br>
// */
//public class YonYouUtilbc
//{
//    private static YonYouUtilbc youYonUseUtils = null;
//    
//    public static YonYouUtilbc getInstance()
//    {
//        if (youYonUseUtils == null)
//        {
//            youYonUseUtils = new YonYouUtilbc();
//        }
//        return youYonUseUtils;
//    }
//    
//    // /////////////////// 审批流业务流工作流、单据转换规则相关 start 20200325/////////////////////
//    public static PubDBService getPubDBService()
//    {
//        return new PubDBService();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 获取元数据表名<br>
//     * @see <br>
//     * @param vo
//     * @return 元数据表名 <br>
//     * @String <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getTable <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-11-18 <br>
//     * @time 下午3:00:50 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static String getTable(SuperVO vo)
//    {
//        String clazz = vo.getClass().getName();
//        IBean bean = MDQueryFacade.getBeanByFullClassName(clazz);
//        return bean.getTable().getName();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：元数据管理器 <br>
//     * 更新元数据缓存.updateVersionWithClearCache(null, null, null);
//     * @see <br>
//     * @return <br>
//     * @nc.md.innerservice.IMetaDataManagerService <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIMetaDataManagerService <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午11:27:11 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.md.innerservice.IMetaDataManagerService getIMetaDataManagerService()
//    {
//        return NCLocator(nc.md.innerservice.IMetaDataManagerService.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：审批流 <br>
//     * 审批流启动.startApproveFlowAfterAction(<br>
//     * 工作流启动.startWorkflowAfterAction<br>
//     * 单据动作驱动.actiondrive<br>
//     * @see <br>
//     * @return <br>
//     * @nc.bs.pub.pf.pfframe.PFBusiAction <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getPFBusiAction <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:47:35 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.uap.pf.IPFBusiAction getIPFBusiAction()
//    {
//        return NCLocator(nc.itf.uap.pf.IPFBusiAction.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：单据转换规则 <br>
//     * .runChangeDataAry()
//     * @see <br>
//     * @return <br>
//     * @nc.itf.uap.pf.IPfExchangeService <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIFormulaService <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:44:16 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.uap.pf.IPfExchangeService getIPfExchangeService()
//    {
//        return NCLocator(nc.itf.uap.pf.IPfExchangeService.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 公式服务<br>
//     * @see <br>
//     * @return <br>
//     * @IFormulaService <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIFormulaService <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:34:55 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.pubitf.dm.formula.IFormulaService getIFormulaService()
//    {
//        return NCLocator(nc.pubitf.dm.formula.IFormulaService.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 单据转换时映射&&单据转换时执行公式<br>
//     * @see <br>
//     * @return
//     * @throws InstantiationException
//     * @throws IllegalAccessException <br>
//     * @nc.bs.pf.change.BillMappingConvertor <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getBillMappingConvertor <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:42:46 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.bs.pf.change.BillMappingConvertor getBillMappingConvertor() throws InstantiationException, IllegalAccessException
//    {
//        // Class<?> forName = Class.forName(nc.bs.pf.change.BillMappingConvertor.class.getName());
//        return nc.bs.pf.change.BillMappingConvertor.class.newInstance();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 分单 <br>
//     * 前分单.splitBeforProc(AggregatedValueObject[], ChangeVOAdjustContext)<br>
//     * 后分单.splitAfterProc(AggregatedValueObject[], AggregatedValueObject[], ChangeVOAdjustContext)
//     * @see <br>
//     * @return
//     * @throws InstantiationException
//     * @throws IllegalAccessException <br>
//     * @BillSplitter <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getBillSplitter <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:42:00 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.uap.pf.IPFSplitBill getBillSplitter() throws InstantiationException, IllegalAccessException
//    {
//        return nc.bs.pf.change.BillSplitter.class.newInstance();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：模块部署、接口调度和数据源相关【集群主机相关（在master上有效）】 <br>
//     * @see <br>
//     * @return <br>
//     * @BusinessAppServer <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getBusinessAppServer <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:22:59 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.bs.framework.server.BusinessAppServer getBusinessAppServer()
//    {
//        return nc.bs.framework.server.BusinessAppServer.getInstance();
//    }
//    
//    // /////////////////////// 20200325 end////////////////////////////////////////////////////////////
//    /**
//     * *********************************************************** <br>
//     * 说明：万能登陆 <br>
//     * @see <br>
//     * <br>
//     * @void <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#login <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月24日 <br>
//     * @time 下午8:49:17 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static void login()
//    {
//        NCLocator(nc.bs.framework.server.ISecurityTokenCallback.class).token("__system".getBytes(), "admin".getBytes());
//    }
//    
//    /* ============================ public======================================== */
//    /** 是否是WebLogic环境 */
//    public static boolean isWeblogic = nc.bs.framework.common.RuntimeEnv.isRunningInWeblogic();
//    /** 是否是WAS环境 */
//    public static boolean isSphere = nc.bs.framework.common.RuntimeEnv.isRunningInWebSphere();
//    /** 获取集团ID */
//    public static String pk_group = getInvocationInfoProxy().getGroupId();
//    public static nc.jdbc.framework.generator.SequenceGenerator Generator = new nc.jdbc.framework.generator.SequenceGenerator();
//    /** 后台任务实现接口 */
//    public static nc.bs.pub.taskcenter.IBackgroundWorkPlugin backgroundWorkPlugin = null;
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：参数的前后台公共服务类, 前后台使用参数的必须通过该接口调用 <br>
//     * @see <br>
//     * @return <br>
//     * @nc.pubitf.para.SysInitQuery <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getSysInit <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:57:42 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.pubitf.para.SysInitQuery getSysInit()
//    {
//        return new nc.pubitf.para.SysInitQuery();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：nc调用单据转换规则 <br>
//     * @see <br>
//     * @return <br>
//     * @nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getPfServiceScmUtil <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:57:07 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil getPfServiceScmUtil()
//    {
//        return new nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：流程平台动作处理的公共入口接口 <br>
//     * @see <br>
//     * @return <br>
//     * @nc.itf.uap.pf.IplatFormEntry <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIplatFormEntry <br>
//     * @author Li Bencheng <br>
//     * @date Created on 2020-4-27 <br>
//     * @time 上午11:09:20 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.uap.pf.IplatFormEntry getIplatFormEntry()
//    {
//        return NCLocator(nc.itf.uap.pf.IplatFormEntry.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @return <br>
//     * @nc.jdbc.framework.generator.SequenceGenerator <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getGenerator <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:56:39 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.jdbc.framework.generator.SequenceGenerator getGenerator()
//    {
//        return new nc.jdbc.framework.generator.SequenceGenerator();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：GodLikeDataSource 判断变量为系统变量还是公式等变量 <br>
//     * @see <br>
//     * @return <br>
//     * @nc.ui.pub.print.version55.print.ds.GodLikeDataSource <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getGodLikeDataSource <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:55:11 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.ui.pub.print.version55.print.ds.GodLikeDataSource getGodLikeDataSource()
//    {
//        return new nc.ui.pub.print.version55.print.ds.GodLikeDataSource(null);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：获取当前选中的环境变量 <br>
//     * @see <br>
//     * @return <br>
//     * @nc.vo.uif2.LoginContext <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getLoginContext <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:52:14 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.vo.uif2.LoginContext getLoginContext()
//    {
//        return new nc.vo.uif2.LoginContext();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：（解析公式） <br>
//     * @see <br>
//     * @return <br>
//     * @nc.ui.pub.print.version55.print.template.cell.value.VarSyntax <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getVarSyntax <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午10:52:02 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.ui.pub.print.version55.print.template.cell.value.VarSyntax getVarSyntax()
//    {
//        return new nc.ui.pub.print.version55.print.template.cell.value.VarSyntax();
//    }
//    
//    /** 获取当前默认的环境变量 */
//    public static nc.pubitf.setting.defaultdata.UAPOrgSettingAccessor getUAPOrgSettingAccessor()
//    {
//        return new nc.pubitf.setting.defaultdata.UAPOrgSettingAccessor();
//    }
//    
//    /** 获取环境变量 */
//    public static nc.vo.pubapp.AppContext getAppcontext()
//    {
//        return nc.vo.pubapp.AppContext.getInstance();
//    }
//    
//    public static nc.bs.framework.common.RuntimeEnv getRuntimeEnv()
//    {
//        return nc.bs.framework.common.RuntimeEnv.getInstance();
//    }
//    
//    /** 获取环境变量 */
//    public static nc.desktop.ui.WorkbenchEnvironment getWorkbenchEnvironment()
//    {
//        return nc.desktop.ui.WorkbenchEnvironment.getInstance();
//    }
//    
//    /** 获取环境变量 */
//    public static nc.vo.am.common.BizContext getBizContext()
//    {
//        return nc.vo.am.common.BizContext.getInstance();
//    }
//    
//    /** 获取环境变量 */
//    @SuppressWarnings("deprecation")
//    public static nc.ui.pub.ClientEnvironment getClientEnvironment()
//    {
//        return nc.ui.pub.ClientEnvironment.getInstance();
//    }
//    
//    /** 锁 PKLock */
//    public static nc.bs.uap.lock.PKLock getPKLock()
//    {
//        return nc.bs.uap.lock.PKLock.getInstance();
//    }
//    
//    /** 锁定子方案 */
//    public static nc.bs.framework.server.ServerConfiguration getServerConfiguration()
//    {
//        return nc.bs.framework.server.ServerConfiguration.getServerConfiguration();
//    }
//    
//    /** 文件操作系统 */
//    public static nc.itf.tb.nba.FileSpaceDAO fileSpaceDAO = new nc.itf.tb.nba.FileSpaceDAO(null);
//    
//    /** 文件操作系统 */
//    public static nc.itf.tb.nba.FileSpaceDAO getFileSpaceDAO(String dataSource)
//    {
//        return new nc.itf.tb.nba.FileSpaceDAO(dataSource);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：文件监控 <br>
//     * @see <br>
//     * @param dataSource
//     * @return <br>
//     * @nc.bs.framework.codesync.monitor.FileMonitor <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getFileMonitor <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午11:10:30 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.bs.framework.codesync.monitor.FileMonitor getFileMonitor()
//    {
//        return nc.bs.framework.codesync.monitor.FileMonitor.getInstance();
//    }
//    
//    /* ============================ EJB 远程调用对象======================================== */
//    /**
//     * *********************************************************** <br>
//     * 说明：EJB 远程调用对象 <br>
//     * @see <br>
//     * @param <T>
//     * @param clazz 全类名
//     * @return <br>
//     * @T <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#NCLocator <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午11:02:34 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static <T> T NCLocator(Class<T> clazz)
//    {
//        return nc.bs.framework.common.NCLocator.getInstance().lookup(clazz);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： NCCloud 远程调用对象<br>
//     * @see <br>
//     * @param clazz
//     * @return <br>
//     * @T <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#NCCLocator <br>
//     * @author LiBencheng <br>
//     * @date Created on 2020-5-13 <br>
//     * @time 下午12:58:25 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static <T> T NCCLocator(Class<T> clazz)
//    {
//        return ServiceLocator(clazz);
//    }
//    
//    /** 批量数据库操作对象 客户端远程调用使用 */
//    public static pers.bc.utils.yonyou.IPersistenceDAO getPersistenceDAOC()
//    {
//        return NCLocator(pers.bc.utils.yonyou.IPersistenceDAO.class);
//    }
//    
//    /** 数据库操作对象 客户端远程调用使用 数据变动使用 */
//    public static nc.itf.uap.IVOPersistence getBaseDAOServiceC()
//    {
//        return NCLocator(nc.itf.uap.IVOPersistence.class);
//    }
//    
//    /** 数据库操作对象 客户端远程调用 查询使用 */
//    public static nc.itf.uap.IUAPQueryBS getBaseDAOQueryBSyC()
//    {
//        return NCLocator(nc.itf.uap.IUAPQueryBS.class);
//    }
//    
//    /** 系统生成编码规则 */
//    // getPreBillCode_RequiresNew(HICommonValue.NBCR_PSNDOC_CODE, HwPsnCont.PK_GROUP, pk_org);
//    public static nc.pub.billcode.itf.IBillcodeManage getIBillcodeManage()
//    {
//        return NCLocator(nc.pub.billcode.itf.IBillcodeManage.class);
//    }
//    
//    public static nc.login.bs.IServerEnvironmentService getIServerEnvironmentService()
//    {
//        return NCLocator(nc.login.bs.IServerEnvironmentService.class);
//    }
//    
//    /* ============================ EJB 远程调用对象 end====================================== */
//    /* ============================ 数据库操作对象============================================ */
//    
//    /** 承继BaseDAO 数据库操作对象 支持批量 */
//    public static pers.bc.utils.yonyou.PersistenceDAO getPersistenceDAO()
//    {
//        return getPersistenceDAO(getInvocationInfoProxy().getUserDataSource());
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：承继BaseDAO 数据库操作对象 <br>
//     * @see <br>
//     * @param dataSource 数据源名称
//     * @return <br>
//     * @com.pub.utils.yonyou.PersistenceDAO <br>
//     * @methods com.pub.utils.yonyou.YonYouUtilbc#getPersistenceDAO <br>
//     * @author licheng <br>
//     * @date Created on 2019-10-22 <br>
//     * @time 上午9:52:19 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static pers.bc.utils.yonyou.PersistenceDAO getPersistenceDAO(String dataSource)
//    {
//        return new pers.bc.utils.yonyou.PersistenceDAO(dataSource);
//    }
//    
//    /** 内部的SuperDMO 数据库操作对象 */
//    public static nc.bs.dao.BaseDAO getBaseDAO()
//    {
//        return getBaseDAO(getInvocationInfoProxy().getUserDataSource());
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： 内部的SuperDMO 数据库操作对象<br>
//     * @see <br>
//     * @param dataSource 数据源名称
//     * @return <br>
//     * @nc.bs.dao.BaseDAO <br>
//     * @methods com.pub.utils.yonyou.YonYouUtilbc#getBaseDAO <br>
//     * @author licheng <br>
//     * @date Created on 2019-10-22 <br>
//     * @time 上午9:52:36 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.bs.dao.BaseDAO getBaseDAO(String dataSource)
//    {
//        return new nc.bs.dao.BaseDAO(dataSource);
//    }
//    
//    /* ============================ 数据库操作对象 end ======================================== */
//    
//    public static nc.bs.framework.common.InvocationInfoProxy getInvocationInfoProxy()
//    {
//        return nc.bs.framework.common.InvocationInfoProxy.getInstance();
//    }
//    
//    public static nc.jdbc.framework.generator.SequenceGenerator getSequenceGenerator()
//    {
//        return new nc.jdbc.framework.generator.SequenceGenerator();
//    }
//    
//    public static nc.ui.bd.ref.AbstractRefModel getAbstractRefModel()
//    {
//        // Object superVO = (Object) ReflectionUtilbc.getNewInstance(AbstractRefModel.class);
//        return null;
//    }
//    
//    /* ============================ 其他操着 end ======================================== */
//    
//    /** 锁定子方案 */
//    public static nc.hr.frame.persistence.SimpleDocLocker getSimpleDocLocker()
//    {
//        return new nc.hr.frame.persistence.SimpleDocLocker();
//    }
//    
//    /* ============================ HR操操作 add ======================================== */
//    /** 简单档案查询 */
//    public static nc.hr.frame.persistence.SimpleDocServiceTemplate getDocService(String dataSource)
//    {
//        return new nc.hr.frame.persistence.SimpleDocServiceTemplate(dataSource);
//    }
//    
//    /** 更新序列值 */
//    public static nc.itf.hi.IPersonRecordService getPersonRecordImpl()
//    {
//        return NCLocator(nc.itf.hi.IPersonRecordService.class);
//    }
//    
//    /** sql 语句insql帮助 */
//    public static nc.hr.utils.InSQLCreator getInSQLCreator()
//    {
//        return new nc.hr.utils.InSQLCreator();
//    }
//    
//    /* ============================ HR操操作 end ======================================== */
//    
//    /* ============================ NCC环境变量 add======================================== */
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @return <br>
//     * @IMenuActionInfoFactory <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIMenuActionInfoFactory <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月5日 <br>
//     * @time 下午6:40:16 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static com.ufida.report.free.userdef.IMenuActionInfoFactory getIMenuActionInfoFactory()
//    {
//        return ServiceLocator(com.ufida.report.free.userdef.IMenuActionInfoFactory.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：工作流 <br>
//     * @see <br>
//     * @return <br>
//     * @IWorkflowDefine <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getIWorkflowDefine <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月4日 <br>
//     * @time 下午7:12:40 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nc.itf.uap.pf.IWorkflowDefine getIWorkflowDefine()
//    {
//        return ServiceLocator(nc.itf.uap.pf.IWorkflowDefine.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： NCCloud 远程调用对象<br>
//     * @see <br>
//     * @param <T>
//     * @param clazz
//     * @return <br>
//     * @T <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#ServiceLocator <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月4日 <br>
//     * @time 下午7:11:22 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static <T> T ServiceLocator(Class<T> clazz)
//    {
//        return nccloud.framework.service.ServiceLocator.find(clazz);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：NCCloud各级缓存 万能缓存 <br>
//     * cache.clear();
//     * @see <br>
//     * @return <br>
//     * @nccloud.framework.core.cache.MemeryCacheServer <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getMemeryCacheServer <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月25日 <br>
//     * @time 下午11:29:38 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nccloud.framework.core.cache.MemeryCacheServer getMemeryCacheServer()
//    {
//        return nccloud.framework.core.cache.MemeryCacheServer.getInstance();
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明：流程平台 <br>
//     * @see <br>
//     * @return <br>
//     * @ICloudScriptPFlowService <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getICloudScriptPFlowService <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月4日 <br>
//     * @time 下午7:11:13 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nccloud.pubitf.riart.pflow.ICloudScriptPFlowService getICloudScriptPFlowService()
//    {
//        return ServiceLocator(nccloud.pubitf.riart.pflow.ICloudScriptPFlowService.class);
//    }
//    
//    /**
//     * *********************************************************** <br>
//     * 说明： <br>
//     * @see <br>
//     * @return <br>
//     * @INCCloudQueryService <br>
//     * @methods pers.bc.utils.yonyou.YonYouUtilbc#getINCCloudQueryService <br>
//     * @author licheng <br>
//     * @date Created on 2020年3月5日 <br>
//     * @time 下午7:37:02 <br>
//     * @version 1.0 <br>
//     ************************************************************* <br>
//     */
//    public static nccloud.pubitf.platform.query.INCCloudQueryService getINCCloudQueryService()
//    {
//        return ServiceLocator(nccloud.pubitf.platform.query.INCCloudQueryService.class);
//    }
//    
//    // 【client端yyconfig包内容部署位置】
//    // hotwebs\nccloud\WEB-INF\extend
//    // 【client端类部署位置】
//    // hotwebs\nccloud\WEB-INF\classes
//    // 【public端部署位置（和NC65一致）】
//    // modules\模块名\classes
//    // 【private端部署位置（和NC65一致）】
//    // modules\模块名\META-INF\classes
//    // 【resources部署位置】
//    // resources\
//    // 【UPM接口部署位置】
//    // modules\模块名\META-INF
//    // 【module.xml部署位置】
//    // modules\模块名\META-INF
//    
//    // 表头实现：
//    // IBDObject
//    // IOrgInfo
//    // IAuditInfo
//    // IBillNo（未提供特性）
//    // IBillDate（未提供特性）
//    // IMakeTime
//    // 流程信息获取、回写
//    // 业务PK锁
//    // 单据主子VO查询（未提供特性）
//    //
//    // 表体实现：
//    // IRowNo
//    /* ============================ NCC环境变量 end======================================== */
//}
