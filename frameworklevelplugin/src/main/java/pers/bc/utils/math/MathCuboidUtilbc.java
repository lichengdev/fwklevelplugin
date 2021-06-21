package pers.bc.utils.math;

import java.text.DecimalFormat;

/**
 * Java工具集-数学(立方体操作工具类)
 * @qualiFild pers.bc.utils.math.MathCuboidUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-2<br>
 * @version 1.0<br>
 */
public class MathCuboidUtilbc
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
     * 功能描述: 〈判断是否是立方体〉
     * 
     * @params : [length, wideth, highth]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 17:01
     */
    public static boolean isCuboid(double length, double wideth, double highth)
    {
        if (length == 0 || wideth == 0 || highth == 0)
        {
            return false;
        }
        return true;
    }
    
    /**
     * 功能描述: 〈获取立方体周长,长宽高各不相同情况〉
     * 
     * @params : [length, wideth, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:00
     */
    public static double getPerimeter(double length, double wideth, double highth)
    {
        if (isCuboid(length, wideth, highth))
        {
            return roundValue(4 * (highth + length + wideth));
        }
        return 0;
    }
    
    /**
     * 功能描述: 〈获取立方体周长,有两面是正方形情况〉
     * 
     * @params : [side, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:05
     */
    public static double getPerimeter(double side, double highth)
    {
        if (side == 0 || highth == 0)
        {
            throw new IllegalArgumentException("This isn't Cuboid");
        }
        return roundValue(side * 8 + highth * 4);
    }
    
    /**
     * 功能描述: 〈获取正方体周长〉
     * 
     * @params : [side]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:26
     */
    public static double getPerimeter(double side)
    {
        return roundValue(side * 12);
    }
    
    /**
     * 功能描述: 〈获取立方体的面积〉
     * 
     * @params : [length, wideth, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:27
     */
    public static double getArea(double length, double wideth, double highth)
    {
        if (isCuboid(length, wideth, highth))
        {
            double xArea = length * wideth * 2;
            double yArea = length * highth * 2;
            double zArea = wideth * highth * 2;
            return roundValue(xArea + yArea + zArea);
        }
        return 0;
    }
    
    /**
     * 功能描述: 〈获取立方体面积,有两面是正方形情况〉
     * 
     * @params : [side, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:30
     */
    public static double getArea(double side, double highth)
    {
        if (side == 0 || highth == 0)
        {
            throw new IllegalArgumentException("This isn't Cuboid");
        }
        double result = side * side * 2 + side * highth * 4;
        return roundValue(result);
    }
    
    /**
     * 功能描述: 〈获取正方体面积〉
     * 
     * @params : [side, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:30
     */
    public static double getArea(double side)
    {
        return roundValue(side * side * 6);
    }
    
    /**
     * 功能描述: 〈获取立方体体积〉
     * 
     * @params : [length, wideth, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:35
     */
    public static double getVolume(double length, double wideth, double highth)
    {
        return roundValue(length * wideth * highth);
    }
    
    /**
     * 功能描述: 〈获取立方体体积,有两面是正方形情况〉
     * 
     * @params : [side, highth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:36
     */
    public static double getVolume(double side, double highth)
    {
        return roundValue(side * side * highth);
    }
    
    /**
     * 功能描述: 〈获取正方体体积〉
     * 
     * @params : [side]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:36
     */
    public static double getVolume(double side)
    {
        return roundValue(side * side * side);
    }
    
}
