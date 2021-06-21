package pers.bc.utils.math;

import java.text.DecimalFormat;

/**
 * Java工具集-数学(圆柱体,椎体工具类)
 * @qualiFild pers.bc.utils.math.MathCylinderUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-2<br>
 * @version 1.0<br>
 */
public class MathCylinderUtilbc
{
    
    public static final DecimalFormat ROUNDING_OFF = new DecimalFormat("#.00");
    
    /**
     * 功能描述: 〈获取四舍五入的结果值,并且取得绝对值,解决负数情况〉
     * 
     * @params : [value]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 15:50
     */
    public static double roundValue(double value)
    {
        return Math.abs(Double.valueOf(ROUNDING_OFF.format(value)));
    }
    
    /**
     * 功能描述: 〈是否是圆柱体〉
     * 
     * @params : [r, highth]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 18:01
     */
    public static boolean isCylinder(double r, double highth)
    {
        if (r == 0 || highth == 0)
        {
            throw new IllegalArgumentException("This isn't Cylinder");
        }
        return true;
    }
    
    /**
     * 功能描述: 〈获取圆柱体表面积〉
     * 
     * @params : [r, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 18:03
     */
    public static double getArea(double r, double highth)
    {
        isCylinder(r, highth);
        return roundValue(2 * Math.PI * r * highth);
    }
    
    /**
     * 功能描述: 〈获取圆柱体体积〉
     * 
     * @params : [r, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 18:06
     */
    public static double getVolume(double r, double highth)
    {
        isCylinder(r, highth);
        return calculateVolume(r, highth, false);
    }
    
    /**
     * 功能描述: 〈计算是圆柱体还是圆锥体的体积〉
     * 
     * @params : [r, highth, isCircularCone]
     * @return : double
     * @author : cwl
     * @date : 2019/6/4 10:56
     */
    private static double calculateVolume(double r, double highth, boolean isCircularCone)
    {
        if (isCircularCone)
        {
            double proportion = 1 / 3;
            return roundValue(proportion * r * r * Math.PI * highth);
        }
        return roundValue(r * r * Math.PI * highth);
    }
    
    /**
     * 功能描述: 〈获取圆柱体横截面积〉
     * 
     * @params : [r, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/4 10:10
     */
    public static double getCAS(double r, double highth)
    {
        isCylinder(r, highth);
        return roundValue(2 * r * highth);
    }
    
    /**
     * 功能描述: 〈获取圆锥体的体积〉
     * 
     * @params : [r, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/4 10:36
     */
    public static double getVolume(double r, double highth, boolean isCircularCone)
    {
        isCylinder(r, highth);
        return calculateVolume(r, highth, isCircularCone);
    }
    
}
