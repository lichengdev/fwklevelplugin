//package pers.bc.utils.yonyou;
//
//import nc.vo.pub.AggregatedValueObject;
//import nc.vo.pub.CircularlyAccessibleValueObject;
//
///**
// * 总则 通用的NCAGGVO类
// * @qualiFild nc.itf.mmpac.tree.analysis.AggAnalysisVO.java<br>
// * @author：LiBencheng<br>
// * @date Created on 2020-5-10<br>
// * @version 1.0<br>
// */
//public class BcAggGeneralVO extends AggregatedValueObject
//{
//    
//    /**
//     * @date 2020-5-10
//     */
//    private static final long serialVersionUID = -8345679649349041716L;
//    private CircularlyAccessibleValueObject obj;
//    private CircularlyAccessibleValueObject objs[];
//    
//    @Override
//    public CircularlyAccessibleValueObject[] getChildrenVO()
//    {
//        return objs;
//    }
//    
//    @Override
//    public CircularlyAccessibleValueObject getParentVO()
//    {
//        return obj;
//    }
//    
//    @Override
//    public void setChildrenVO(CircularlyAccessibleValueObject[] objs)
//    {
//        this.objs = objs;
//    }
//    
//    @Override
//    public void setParentVO(CircularlyAccessibleValueObject obj)
//    {
//        this.obj = obj;
//    }
//    
//}
