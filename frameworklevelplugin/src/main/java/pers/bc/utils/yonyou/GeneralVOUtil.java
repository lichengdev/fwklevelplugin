//package pers.bc.utils.yonyou;
//
//import java.util.Arrays;
//import java.util.Comparator;
//
//import pers.bc.utils.Bean.BeanHelper;
//
//import nc.itf.scmpub.reference.uap.md.MDQueryFacade;
//import nc.md.model.IBean;
//import nc.vo.pub.SuperVO;
//
///**
// * 总则 通用的NCVO类
// * @qualiFild pers.bc.utils.yonyou.GeneralVO.java<br>
// * @author：LiBencheng<br>
// * @date Created on 2020-5-19<br>
// * @version 1.0<br>
// */
//public class GeneralVOUtil extends SuperVO
//{
//    public static final int ASC = 1;
//    public static final int DESC = -1;
//    
//    public static void ascSort(Object[] vos, String[] fields)
//    {
//        if (vos == null) return;
//        if ((fields == null) || (fields.length == 0)) return;
//        int[] ascFlags = new int[fields.length];
//        Arrays.fill(ascFlags, ASC);
//        sort(vos, fields, ascFlags);
//    }
//    
//    public static void descSort(Object[] vos, String[] fields)
//    {
//        if (vos == null) return;
//        if ((fields == null) || (fields.length == 0)) return;
//        int[] ascFlags = new int[fields.length];
//        Arrays.fill(ascFlags, DESC);
//        sort(vos, fields, ascFlags);
//    }
//    
//    public static void sort(Object[] vos, String[] fields, int[] ascFlags)
//    {
//        sort(vos, fields, ascFlags, false);
//    }
//    
//    public static void sort(Object[] vos, String[] fields, int[] ascFlags, boolean nullLast)
//    {
//    }
//    
//    /**
//     * 获取元数据表名
//     * 
//     * @param vo
//     * @return 元数据表名
//     */
//    public static String getTable(SuperVO vo)
//    {
//        String clazz = vo.getClass().getName();
//        IBean bean = MDQueryFacade.getBeanByFullClassName(clazz);
//        return bean.getTable().getName();
//    }
//    
//    /**
//     * 将ObjectVO数组以某一字段排序。
//     * <p>
//     * 创建日期：(2003-7-9 15:25:03)
//     * 
//     * @param vos SuperVO数组
//     * @param attributeName 排序字段名
//     * @param isAscend 是否升序
//     */
//    public static void sortByAttributeName(Object[] vos, final String attributeName, final boolean isAscend)
//    {
//        if (vos == null || attributeName == null) return;
//        Comparator c = new Comparator()
//        {
//            public int compare(Object o1, Object o2)
//            {
//                
//                Object v1 = BeanHelper.getProperty(o1, attributeName);
//                Object v2 = BeanHelper.getProperty(o2, attributeName);
//                if (v1 == null && v2 == null)
//                {
//                    return 0;
//                }
//                else if (v1 == null)
//                {
//                    // 默认null比所有值小
//                    return -1;
//                }
//                else if (v2 == null)
//                {
//                    return 1;
//                }
//                int r = ((Comparable) v1).compareTo(v2);
//                // int r = compareAttribute(v1, v2);
//                if (isAscend) return r;
//                if (r > 0) return -1;
//                if (r < 0) return 1;
//                return 0;
//            }
//        };
//        Arrays.sort(vos, c);
//    }
//    
//}
