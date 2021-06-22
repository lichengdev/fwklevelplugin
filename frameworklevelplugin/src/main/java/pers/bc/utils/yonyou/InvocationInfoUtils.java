//package pers.bc.utils.yonyou;
//
//import nc.bs.framework.common.InvocationInfo;
//import nc.bs.framework.common.InvocationInfoProxy;
//
///**
// * 线程里面设置环境变量
// * @Author: Qingr
// * @Date: 2020-11-12 18:06
// */
//public class InvocationInfoUtils
//{
//    
//    public static InvocationInfo getInvocationInfo()
//    {
//        InvocationInfo info = new InvocationInfo();
//        info.setBizCenterCode(InvocationInfoProxy.getInstance().getBizCenterCode());
//        info.setBizDateTime(InvocationInfoProxy.getInstance().getBizDateTime());
//        info.setBusiAction(InvocationInfoProxy.getInstance().getBusiAction());
//        info.setGroupId(InvocationInfoProxy.getInstance().getGroupId());
//        info.setGroupNumber(InvocationInfoProxy.getInstance().getGroupNumber());
//        info.setUserDataSource(InvocationInfoProxy.getInstance().getUserDataSource());
//        info.setUserId(InvocationInfoProxy.getInstance().getUserId());
//        info.setLangCode(InvocationInfoProxy.getInstance().getLangCode());
//        info.setLogLevel(InvocationInfoProxy.getInstance().getLogLevel());
//        info.setHyCode(InvocationInfoProxy.getInstance().getHyCode());
//        info.setSysid(InvocationInfoProxy.getInstance().getSysid());
//        info.setDeviceId(InvocationInfoProxy.getInstance().getDeviceId());
//        info.setRunAs(InvocationInfoProxy.getInstance().getRunAs());
//        info.setCallId(InvocationInfoProxy.getInstance().getCallId());
//        info.setUserCode(InvocationInfoProxy.getInstance().getUserCode());
//        return info;
//    }
//    
//    public static void setInvocationInfo(InvocationInfo invocationInfo)
//    {
//        if (invocationInfo == null)
//        {
//            return;
//        }
//        synchronized (InvocationInfoProxy.class)
//        {
//            InvocationInfoProxy.getInstance().setBizCenterCode(invocationInfo.getBizCenterCode());
//            InvocationInfoProxy.getInstance().setBizDateTime(invocationInfo.getBizDateTime());
//            InvocationInfoProxy.getInstance().setBusiAction(invocationInfo.getBusiAction());
//            InvocationInfoProxy.getInstance().setGroupId(invocationInfo.getGroupId());
//            InvocationInfoProxy.getInstance().setGroupNumber(invocationInfo.getGroupNumber());
//            InvocationInfoProxy.getInstance().setUserDataSource(invocationInfo.getUserDataSource());
//            InvocationInfoProxy.getInstance().setUserId(invocationInfo.getUserId());
//            InvocationInfoProxy.getInstance().setLangCode(invocationInfo.getLangCode());
//            InvocationInfoProxy.getInstance().setLogLevel(invocationInfo.getLogLevel());
//            InvocationInfoProxy.getInstance().setHyCode(invocationInfo.getHyCode());
//            InvocationInfoProxy.getInstance().setSysid(invocationInfo.getSysid());
//            InvocationInfoProxy.getInstance().setDeviceId(invocationInfo.getDeviceId());
//            InvocationInfoProxy.getInstance().setRunAs(invocationInfo.getRunAs());
//            InvocationInfoProxy.getInstance().setCallId(invocationInfo.getCallId());
//            InvocationInfoProxy.getInstance().setUserCode(invocationInfo.getUserCode());
//        }
//    }
//}
