package pers.bc.utils.math;
import java.text.DecimalFormat;

/**
 * Java工具集-数学(三角形工具类)
 * @qualiFild pers.bc.utils.math.MathTriangleUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-3<br>
 * @version 1.0<br>
 */
public class MathTriangleUtilbc
{
    
    // 格式化四舍五入
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
     * 功能描述: 〈判断是否为三角形〉
     * 
     * @params : [x, y, z]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 11:55
     */
    public static boolean isTriangle(double x, double y, double z)
    {
        if (x == 0 || y == 0 || z == 0)
        {
            throw new IllegalArgumentException("This isn't Triangle");
        }
        // 判断只需要任意两边之和大于第三边就是三角形
        if (x < y + z || y < x + z || z > x + z)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 功能描述: 〈是否是直角三角形〉
     * 
     * @params : [x, y, z]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 11:58
     */
    public static boolean isRightAngleTriangle(double x, double y, double z)
    {
        // 判断是三角形
        if (isTriangle(x, y, z))
        {
            double zEqual = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            double yEqual = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
            double xEqual = Math.sqrt(Math.pow(z, 2) + Math.pow(y, 2));
            // 存在勾股定理成立就是直角三角形
            if (x == xEqual || y == yEqual || z == zEqual)
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 功能描述: 〈是否是等腰直角三角形〉
     * 
     * @params : [x, y, z]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 14:08
     */
    public static boolean isIsoscelesRightTriangle(double x, double y, double z)
    {
        if (isTriangle(x, y, z))
        {
            // 是直角三角形,并且存在任意两边相等,即是等腰直角三角形
            if (isRightAngleTriangle(x, y, z) && (x == y || y == z || x == z))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 功能描述: 〈是否是30度 60度角的直角三角形〉
     * 
     * @params : [x, y, z]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 14:12
     */
    public static boolean is3060RightTriangle(double x, double y, double z)
    {
        // 是三角形并且是直角三角形
        if (isTriangle(x, y, z) && isRightAngleTriangle(x, y, z))
        {
            // 之前的版本存在两个直角边存在两倍关系的情况,所以条件不成立,所以这里修复了限定第三边的情况下去判定斜边是任意直角边的两倍,
            // 则结果就是30度与60度角的直角三角形
            boolean xIsHypotenuse = x > y && x > z;
            boolean yIsHypotenuse = y > x && y > z;
            boolean zIsHypotenuse = z > y && z > x;
            if (xIsHypotenuse && (x / 2 == y || x / 2 == z))
            {
                return true;
            }
            if (yIsHypotenuse && (y / 2 == x || y / 2 == z))
            {
                return true;
            }
            if (zIsHypotenuse && (z / 2 == x || z / 2 == y))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 功能描述: 〈获得三角形的面积〉
     * 
     * @params : [baseLine, highth]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/3 14:42
     */
    public static double getArea(double baseLine, double highth)
    {
        if (baseLine == 0 || highth == 0)
        {
            throw new IllegalArgumentException("This isn't Triangle");
        }
        return roundValue((baseLine * highth) / 2);
    }
    
    /**
     * 功能描述: 〈获得三角形的周长〉
     * 
     * @params : [x, y, z]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 14:48
     */
    public static double getPerimeter(double x, double y, double z)
    {
        if (isTriangle(x, y, z))
        {
            return roundValue(x + y + z);
        }
        return 0;
    }
    
    /**
     * 功能描述: 〈获得直角三角形的周长〉
     * 
     * @params : [x, y]
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 14:49
     */
    public static double getPerimeter(double x, double y)
    {
        if (x == 0 || y == 0)
        {
            throw new IllegalArgumentException("This isn't Triangle");
        }
        double z = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return roundValue(x + y + z);
    }
    
    /**
     * 功能描述: 〈获得三角函数的sin值,传入读书值〉
     * 
     * @params : []
     * @return : double
     * @author : cwl
     * @date : 2019/6/3 15:31
     */
    public static double getSin(double degree)
    {
        if (degree >= 179)
        {
            throw new IllegalArgumentException("Argument isn't more than 180");
        }
        if (degree == 0)
        {
            return 0;
        }
        // 获得度数占比
        double proportion = Double.valueOf(ROUNDING_OFF.format(degree / 180));
        // Math.PI即是π
        return roundValue(Math.PI * proportion);
    }
    
    // =================三角函数 -未完待续========================
}
