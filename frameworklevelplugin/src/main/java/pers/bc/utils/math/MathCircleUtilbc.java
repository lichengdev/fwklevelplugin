package pers.bc.utils.math;

import java.text.DecimalFormat;

/**
 * Java工具集-数学(圆的计算公式)
 * @qualiFild pers.bc.utils.math.MathCircleUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-2<br>
 * @version 1.0<br>
 */
public class MathCircleUtilbc
{
    
    public static final double π = Math.PI;
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
     * 功能描述: 〈获得圆的面积〉
     * 
     * @params : [r]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 16:06
     */
    public static double getArea(double r)
    {
        return roundValue(π * Math.pow(r, 2));
    }
    
    /**
     * 功能描述: 〈获得圆的周长〉
     * 
     * @params : [r]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 16:12
     */
    public static double getPerimeter(double r)
    {
        return roundValue(π * 2 * r);
    }
    
}
