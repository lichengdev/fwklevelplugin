package pers.bc.utils.math;
import java.text.DecimalFormat;

/**
 * Java工具类-数学(四边形工具类)
 * @qualiFild pers.bc.utils.math.MathQuadrangleUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-3<br>
 * @version 1.0<br>
 */
public class MathQuadrangleUtilbc
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
     * 功能描述: 〈判断是否是矩形〉
     * 
     * @params : [length, wideth]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 10:25
     */
    public static boolean isRectangle(double length, double wideth)
    {
        if (length == 0 || wideth == 0)
        {
            throw new IllegalArgumentException("This isn't Rectangle");
        }
        return true;
    }
    
    /**
     * 功能描述: 〈判断是否是矩形 ==> 正方形〉
     * 
     * @params : [side]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 10:28
     */
    public static boolean isRectangle(double side)
    {
        if (side == 0)
        {
            throw new IllegalArgumentException("This isn't Rectangle");
        }
        return true;
    }
    
    /**
     * 功能描述: 〈判断是否是正方形〉
     * 
     * @params : [length, wideth]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 10:26
     */
    public static boolean isSquare(double length, double wideth)
    {
        isRectangle(length, wideth);
        if (length == wideth)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 功能描述: 〈获取长方形周长〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:00
     */
    public static double getPerimeter(double length, double wideth)
    {
        isRectangle(length, wideth);
        double perimeter = (length + wideth) * 2;
        return roundValue(perimeter);
    }
    
    /**
     * 功能描述: 〈获取长方形面积〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:03
     */
    public static double getArea(double length, double wideth)
    {
        isRectangle(length, wideth);
        double area = length * wideth;
        return roundValue(area);
    }
    
    /**
     * 功能描述: 〈获取正方形周长〉
     * 
     * @params : [side]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:16
     */
    public static double getPerimeter(double side)
    {
        isRectangle(side);
        double perimeter = side * 4;
        return roundValue(perimeter);
    }
    
    /**
     * 功能描述: 〈获取正方形面积〉
     * 
     * @params : [side]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:29
     */
    public static double getArea(double side)
    {
        isRectangle(side);
        double area = side * side;
        return roundValue(area);
    }
    
    /**
     * 功能描述: 〈获得矩形内部对角线分割成的三角形周长〉
     * 
     * @params : []
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:32
     */
    public static double diagonalTrianglePerimeter(double length, double wideth)
    {
        isRectangle(length, wideth);
        // 获取斜边
        double hypotenuse = Math.sqrt(Math.pow(length, 2) + Math.pow(wideth, 2));
        double result = hypotenuse + length + wideth;
        return roundValue(result);
    }
    
    /**
     * 功能描述: 〈获得正方形对角线对应的三角形周长〉
     * 
     * @params : [side]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:41
     */
    public static double diagonalTrianglePerimeter(double side)
    {
        isRectangle(side);
        // 获取斜边
        double hypotenuse = Math.sqrt(2) * side;
        double result = hypotenuse + side * 2;
        return roundValue(result);
    }
    
    /**
     * 功能描述: 〈获取矩形内部对角线分割成的三角形的面积〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:43
     */
    public static double diagonalTrianglArea(double length, double wideth)
    {
        return getArea(length, wideth) / 2;
    }
    
    /**
     * 功能描述: 〈获取正方形内部对角线分割成的三角形的面积〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:45
     */
    public static double diagonalTrianglArea(double side)
    {
        return getArea(side) / 2;
    }
    
    /**
     * 功能描述: 〈获得梯形面积〉
     * 
     * @params : [upSide(上边长), lowSide(下边长), high(垂直高)]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 10:53
     */
    public static double getTrapezoidArea(double upSide, double lowSide, double high)
    {
        if (upSide == 0 || lowSide == 0)
        {
            throw new IllegalArgumentException("This isn't Trapezoid");
        }
        return roundValue(((upSide + lowSide) * high) / 2);
    }
    
    /**
     * 功能描述: 〈获取矩形内切圆周长〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:39
     */
    public static double getIncirclePerimeter(double length, double wideth)
    {
        // 取比较短的一边作为内切圆的直径 则perimeter = diameter * π
        if (Math.abs(length) >= Math.abs(wideth))
        {
            return roundValue(wideth * Math.PI);
        }
        return roundValue(length * Math.PI);
    }
    
    /**
     * 功能描述: 〈获取矩形内切圆面积〉
     * 
     * @params : [length, wideth]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 17:50
     */
    public static double getIncircleArea(double length, double wideth)
    {
        // 取比较短的一边作为内切圆的直径 则perimeter = diameter * π
        double r;
        if (Math.abs(length) >= Math.abs(wideth))
        {
            r = wideth / 2;
            return roundValue(r * r * Math.PI);
        }
        r = length / 2;
        return roundValue(r * r * Math.PI);
    }
}
