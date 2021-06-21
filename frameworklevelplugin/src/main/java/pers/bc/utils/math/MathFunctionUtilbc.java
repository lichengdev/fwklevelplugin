package pers.bc.utils.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;

/**
 * 数学函数工具集合
 * @qualiFild pers.bc.utils.math.MathFunctionUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-2<br>
 * @version 1.0<br>
 */
public class MathFunctionUtilbc
{
    private static CircleFunction circle;
    private static ExponentialFunction exponential;
    private static FactorialFunction factorial;
    private static InverseFunction inverse;
    private static LinearFunction linear;
    private static LogFunction log;
    private static QuadraticFunction quadratic;
    
    /**
     * *********************************************************** <br>
     * 说明：Java工具集-数学(函数圆) <br>
     * @see <br>
     * @return <br>
     * @CircleFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceCircle <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:37:52 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final CircleFunction getInstanceCircle()
    {
        if (circle == null)
        {
            synchronized (CircleFunction.class)
            {
                if (circle == null)
                {
                    circle = new CircleFunction();
                }
            }
        }
        return circle;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Java工具集-数学(指数函数) <br>
     * @see <br>
     * @return <br>
     * @ExponentialFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceExponential <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:37:41 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final ExponentialFunction getInstanceExponential()
    {
        if (exponential == null)
        {
            synchronized (CircleFunction.class)
            {
                if (exponential == null)
                {
                    exponential = new ExponentialFunction();
                }
            }
        }
        return exponential;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Java工具集-数学(阶乘函数)<br>
     * @see <br>
     * @return <br>
     * @FactorialFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceFactorial <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:30:48 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final FactorialFunction getInstanceFactorial()
    {
        if (factorial == null)
        {
            synchronized (FactorialFunction.class)
            {
                if (factorial == null)
                {
                    factorial = new FactorialFunction();
                }
            }
        }
        return factorial;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Java工具集-数学(反比例函数) <br>
     * @see <br>
     * @return <br>
     * @InverseFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceInverse <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:37:16 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final InverseFunction getInstanceInverse()
    {
        if (inverse == null)
        {
            synchronized (InverseFunction.class)
            {
                if (inverse == null)
                {
                    inverse = new InverseFunction();
                }
            }
        }
        return inverse;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： Java工具集-数学(一次函数)<br>
     * @see <br>
     * @return <br>
     * @LinearFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceLinear <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:37:04 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final LinearFunction getInstanceLinear()
    {
        if (linear == null)
        {
            synchronized (LinearFunction.class)
            {
                if (linear == null)
                {
                    linear = new LinearFunction();
                }
            }
        }
        return linear;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Java工具集-数学(对数函数) <br>
     * @see <br>
     * @return <br>
     * @LogFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceLog <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:36:44 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final LogFunction getInstanceLog()
    {
        if (log == null)
        {
            synchronized (LogFunction.class)
            {
                if (log == null)
                {
                    log = new LogFunction();
                }
            }
        }
        return log;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：Java工具集-数学(二次函数) <br>
     * @see <br>
     * @return <br>
     * @QuadraticFunction <br>
     * @methods pers.bc.utils.math.MathFunctionUtilbc#getInstanceQuadratic <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午9:36:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static final QuadraticFunction getInstanceQuadratic()
    {
        if (quadratic == null)
        {
            synchronized (LogFunction.class)
            {
                if (quadratic == null)
                {
                    quadratic = new QuadraticFunction();
                }
            }
        }
        return quadratic;
    }
    
    /**
     * Java工具集-数学(函数圆)
     * @qualiFild pers.bc.utils.math.CircleFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class CircleFunction
    {
        
        // 圆心点
        private static Point centerPoint = new Point(0, 0);
        // 默认半径
        private double r = 1;
        private CircleFunction instance;
        
        private double distance = 0;
        // 在圆上
        private final int ONSIDE_CIRCLE = 0;
        // 在圆内
        private final int INSIDE_CIRCLE = 1;
        // 在圆外
        private final int OUTSIDE_CIRCLE = 2;
        // 求距离
        private final int DISTANCE_CENTER_POINT = 3;
        
        /**
         * 功能描述: 〈初始化一个原点为中心,r为半径的圆〉
         * 
         * @params : [r]
         * @return : void
         * @author : cwl
         * @date : 2019/10/24 17:46
         */
        public void init(double r)
        {
            instance.setR(r);
        }
        
        /**
         * 功能描述: 〈初始化一个半径为r,圆心为point的圆〉
         * 
         * @params : [r, point]
         * @return : void
         * @author : cwl
         * @date : 2019/10/24 17:47
         */
        public void init(double r, Point point)
        {
            instance.setR(r);
            instance.setcenterPoint(point);
        }
        
        /**
         * 功能描述: 〈判断点是否在圆上〉 (x-a)^2 + (y - b)^2 = r^2
         * 
         * @params : [point]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 17:51
         */
        public boolean isOnsideCircle(Point point)
        {
            return handleCompare(point, ONSIDE_CIRCLE);
        }
        
        /**
         * 功能描述: 〈处理圆上/圆内/圆外的比较〉
         * 
         * @params : [point, type]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 18:15
         */
        private boolean handleCompare(Point point, int type)
        {
            if (point == null)
            {
                throw new RuntimeException("point is not be null !");
            }
            Point centerPoint = instance.getcenterPoint();
            double r = instance.getR();
            double a = centerPoint.getX();
            double b = centerPoint.getY();
            double x = point.getX();
            double y = point.getY();
            switch (type)
            {
                case ONSIDE_CIRCLE :
                    return Math.pow(2, r) == Math.pow(2, x - a) + Math.pow(2, y - b);
                case INSIDE_CIRCLE :
                    return Math.pow(2, r) >= Math.pow(2, x - a) + Math.pow(2, y - b);
                case OUTSIDE_CIRCLE :
                    return Math.pow(2, r) <= Math.pow(2, x - a) + Math.pow(2, y - b);
                case DISTANCE_CENTER_POINT :
                    distance =
                        new BigDecimal(Math.sqrt(Math.abs(Math.pow(2, x - a) + Math.pow(2, y - b)))).setScale(2, RoundingMode.HALF_UP)
                            .doubleValue();
                    return true;
                default :
                    return false;
            }
        }
        
        /**
         * 功能描述: 〈判断点是否在圆内〉 (x-a)^2 + (y - b)^2 = r^2
         * 
         * @params : [point]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 18:01
         */
        public boolean isInsideCircle(Point point)
        {
            return handleCompare(point, INSIDE_CIRCLE);
        }
        
        /**
         * 功能描述: 〈判断点是否在圆外〉
         * 
         * @params : [point]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 18:17
         */
        public boolean isOutsideCircle(Point point)
        {
            return handleCompare(point, OUTSIDE_CIRCLE);
        }
        
        /**
         * 功能描述: 〈获得点到圆心的距离〉
         * 
         * @params : [point]
         * @return : double
         * @author : cwl
         * @date : 2019/10/24 19:34
         */
        public double getCenterDistance(Point point)
        {
            if (point == null)
            {
                throw new RuntimeException("point is not be null!");
            }
            double result;
            if (handleCompare(point, DISTANCE_CENTER_POINT))
            {
                result = distance;
                // 恢复距离的初始化
                distance = 0;
                return result;
            }
            return distance;
        }
        
        public double getR()
        {
            return r;
        }
        
        public void setR(double r)
        {
            this.r = r;
        }
        
        public Point getcenterPoint()
        {
            return centerPoint;
        }
        
        public void setcenterPoint(Point centerPoint)
        {
            CircleFunction.centerPoint = centerPoint;
        }
    }
    
    /**
     * Java工具集-数学(指数函数)
     * @qualiFild pers.bc.utils.math.ExponentialFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class ExponentialFunction
    {
        
        // y=a^x函数(a为常数且以a>0，a≠1)
        private double a = 2;
        public ExponentialFunction instance;
        private final Point DEFAULT_POINT = new Point(0, 1);
        
        public ExponentialFunction()
        {
        }
        
        // 默认创建一个正比例函数
        
        {
            if (instance == null)
            {
                synchronized (ExponentialFunction.class)
                {
                    if (instance == null)
                    {
                        instance = new ExponentialFunction();
                    }
                }
            }
        }
        
        /**
         * 功能描述: 〈创建一个指数函数〉
         * 
         * @params : [a]
         * @return : void
         * @author : cwl
         * @date : 2019/10/25 10:11
         */
        public void init(double a)
        {
            if (a < 0 || a == 1)
            {
                throw new RuntimeException("a is not less than zero and not be one");
            }
            instance.setA(a);
        }
        
        /**
         * 功能描述: 〈根据Y获取X的值〉
         * 
         * @params : [y]
         * @return : double
         * @author : cwl
         * @date : 2019/10/25 10:15
         */
        public double getX(double y, int result)
        {
            double a = instance.getA();
            double nextTime;
            if (y % a == 0 && y % a != 1)
            {
                nextTime = y / a;
                result++;
                getX(nextTime, result);
            }
            if (y % a == 1)
            {
                return result;
            }
            throw new RuntimeException("this y is not on line");
        }
        
        /**
         * 功能描述: 〈根据x获取y的值〉
         * 
         * @params : [x]
         * @return : double
         * @author : cwl
         * @date : 2019/10/25 10:31
         */
        public double getY(double x)
        {
            double a = instance.getA();
            return Math.pow(x, a);
        }
        
        /**
         * 功能描述: 〈获取指数函数默认经过的点〉
         * 
         * @params : []
         * @return : com.simple.util.math.function.ExponentialFunction.Point
         * @author : cwl
         * @date : 2019/10/25 10:32
         */
        public Point getDefaultPoint()
        {
            return DEFAULT_POINT;
        }
        
        /**
         * 功能描述: 〈判断点是否在指数函数上〉
         * 
         * @params : [point]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/25 10:33
         */
        public boolean isOnline(Point point)
        {
            double x = point.getX();
            double y = point.getY();
            return y == Math.pow(x, instance.getA());
        }
        
        public double getA()
        {
            return a;
        }
        
        public void setA(double a)
        {
            this.a = a;
        }
    }
    
    /**
     * Java工具集-数学(阶乘函数)
     * @qualiFild pers.bc.utils.math.FactorialFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class FactorialFunction
    {
        
        /**
         * 功能描述: 〈递归方式实现阶乘函数〉
         * 
         * @params : [num]
         * @return : int
         * @author : cwl
         * @date : 2019/11/15 11:47
         */
        public int recursion(int num)
        {
            if (num == 1)
            {
                return 1;
            }
            else if (num == 2)
            {
                return 2;
            }
            else
            {
                return num * recursion(num - 1);
            }
        }
        
        /**
         * 功能描述: 〈非递归方法实现阶乘函数〉
         * 
         * @params : [num]
         * @return : int
         * @author : cwl
         * @date : 2019/11/15 11:49
         */
        public int unRecursion(int num)
        {
            int jiecheng = 1;
            if (num == 0)
            {
                return 0;
            }
            for (int i = 1; i <= num; i++)
            {
                jiecheng *= i;
            }
            return jiecheng;
        }
    }
    
    /**
     * Java工具集-数学(反比例函数)
     * @qualiFild pers.bc.utils.math.InverseFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class InverseFunction
    {
        
        // y = k/x
        // 斜率
        private double k = 1;
        private InverseFunction instance;
        
        public InverseFunction()
        {
        };
        
        {
            if (instance == null)
            {
                synchronized (InverseFunction.class)
                {
                    if (instance == null)
                    {
                        instance = new InverseFunction();
                    }
                }
            }
        }
        
        /**
         * 创建反比例函数
         * @param k
         */
        public void init(double k)
        {
            if (k == 0)
            {
                throw new RuntimeException("param k is not be zero");
            }
            instance.setK(k);
        }
        
        /**
         * 使用一个点初始化反比例函数
         * @param point
         */
        public void init(Point point)
        {
            if (point == null)
            {
                throw new RuntimeException("point k is not be null");
            }
            double x = point.getX();
            double y = point.getY();
            double k = new BigDecimal(x / y).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            instance.setK(k);
        }
        
        /**
         * 判断点point是否在线上
         * @param point
         * @return
         */
        public boolean isOnline(Point point)
        {
            if (point == null)
            {
                throw new RuntimeException("point is not be null !");
            }
            double x = point.getX();
            double y = point.getY();
            return instance.getK() == x / y;
        }
        
        public double getK()
        {
            return k;
        }
        
        public void setK(double k)
        {
            this.k = k;
        }
    }
    
    /**
     * Java工具集-数学(一次函数)
     * @qualiFild pers.bc.utils.math.LinearFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class LinearFunction
    {
        
        // 默认对结果都进行四舍五入操作
        // 自变量: independent 因变量: dependent
        // 斜率
        private BigDecimal slope = BigDecimal.ONE;
        // 与Y轴的交点
        private BigDecimal point = BigDecimal.ZERO;
        public LinearFunction instance;
        
        // 公式 : y=kx+b
        
        // 私有化构造器
        public LinearFunction()
        {
        }
        
        public LinearFunction(BigDecimal slope, BigDecimal point)
        {
            this.point = point;
            this.slope = slope;
        }
        
        // 默认创建一个正比例函数
        
        {
            if (instance == null)
            {
                synchronized (LinearFunction.class)
                {
                    if (instance == null)
                    {
                        instance = new LinearFunction();
                    }
                }
            }
        }
        
        /**
         * 功能描述: 〈创建一次函数〉
         * 
         * @params : [slope, point]
         * @return : com.simple.util.math.LinearFunction
         * @author : cwl
         * @date : 2019/10/24 11:02
         */
        public void init(double slope, double point)
        {
            if (slope == 0)
            {
                throw new RuntimeException("slope is not be zero");
            }
            instance.setPoint(new BigDecimal(point));
            instance.setSlope(new BigDecimal(slope));
        }
        
        /**
         * 功能描述: 〈判断点是否在线上〉
         * 
         * @params : [x, y]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 11:03
         */
        public boolean isOnline(double x, double y)
        {
            return new BigDecimal(y).setScale(2, RoundingMode.HALF_UP).equals(
                instance.getSlope().multiply(new BigDecimal(x)).add(new BigDecimal(y)).setScale(2, RoundingMode.HALF_UP));
        }
        
        /**
         * 功能描述: 〈通过y求x的值〉
         * 
         * @params : [y]
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 11:35
         */
        public BigDecimal getX(double y)
        {
            BigDecimal dependent = new BigDecimal(y);
            return (dependent.subtract(instance.getPoint())).divide(instance.getSlope(), 2, RoundingMode.HALF_UP);
        }
        
        /**
         * 功能描述: 〈根据x求y〉
         * 
         * @params : [x]
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 11:44
         */
        public BigDecimal getY(double x)
        {
            BigDecimal independent = new BigDecimal(x);
            return independent.multiply(instance.slope).add(instance.getPoint()).setScale(2, RoundingMode.HALF_UP);
        }
        
        /**
         * 功能描述: 〈获得斜率〉
         * 
         * @params : [x, y, b]
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 11:57
         */
        public BigDecimal getSlope(double x, double y, double b)
        {
            BigDecimal dependent = new BigDecimal(y);
            return (dependent.subtract(new BigDecimal(b))).divide(new BigDecimal(x), 2, RoundingMode.HALF_UP);
        }
        
        /**
         * 功能描述: 〈正比例函数获得斜率〉
         * 
         * @params : [x, y]
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 11:58
         */
        public BigDecimal getSlope(double x, double y)
        {
            BigDecimal dependent = new BigDecimal(y);
            return dependent.divide(new BigDecimal(x), 2, RoundingMode.HALF_UP);
        }
        
        @Override
        public String toString()
        {
            return "LinearFunction{" + "slope=" + slope + ", point=" + point + '}';
        }
        
        private BigDecimal getSlope()
        {
            return slope;
        }
        
        private void setSlope(BigDecimal slope)
        {
            this.slope = slope;
        }
        
        private BigDecimal getPoint()
        {
            return point;
        }
        
        private void setPoint(BigDecimal point)
        {
            this.point = point;
        }
    }
    
    /**
     * Java工具集-数学(对数函数)
     * @qualiFild pers.bc.utils.math.LogFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class LogFunction
    {
        
        // y=logaX （a>0，且a≠1）
        private double a = 2;
        private final Point DEFAULT_POINT = new Point(1, 0);
        public LogFunction instance;
        
        public LogFunction()
        {
        }
        
        {
            if (instance == null)
            {
                synchronized (LogFunction.class)
                {
                    if (instance == null)
                    {
                        instance = new LogFunction();
                    }
                }
            }
        }
        
        /**
         * 功能描述: 〈初始化对数函数〉
         * 
         * @return : void
         * @params : [a]
         * @author : cwl
         * @date : 2019/10/25 9:11
         */
        public void init(double a)
        {
            if (a < 0 || a == 1)
            {
                throw new RuntimeException("a is not less than zero and not be one");
            }
            instance.setA(a);
        }
        
        /**
         * 功能描述: 〈判断点是否在对数函数上〉
         * 
         * @params : [point]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/25 9:18
         */
        public boolean isOnline(Point point)
        {
            if (point == null)
            {
                throw new RuntimeException("point is not be null");
            }
            double x = point.getX();
            double y = point.getY();
            return y == Math.pow(x, instance.getA());
        }
        
        /**
         * 功能描述: 〈每个对数函数都会过点(1,0)〉
         * 
         * @params : []
         * @return : com.simple.util.math.function.LogFunction.Point
         * @author : cwl
         * @date : 2019/10/25 9:23
         */
        public Point getDefaultPoint()
        {
            return DEFAULT_POINT;
        }
        
        public double getA()
        {
            return a;
        }
        
        public void setA(double a)
        {
            this.a = a;
        }
    }
    
    /**
     * Java工具集-数学(二次函数)
     * @qualiFild pers.bc.utils.math.QuadraticFunction.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class QuadraticFunction
    {
        
        // y=ax^2 + bx + c
        // 自变量: independent 因变量: dependent
        private BigDecimal a = BigDecimal.ONE;
        private BigDecimal b = BigDecimal.ZERO;
        private BigDecimal c = BigDecimal.ZERO;
        private QuadraticFunction instance;
        // 抛物线上的点
        private List<Point> points = Collections.emptyList();
        
        public QuadraticFunction()
        {
        }
        
        {
            if (instance == null)
            {
                synchronized (QuadraticFunction.class)
                {
                    if (instance == null)
                    {
                        instance = new QuadraticFunction();
                    }
                }
            }
        }
        
        /**
         * 功能描述: 〈初始化二次函数〉
         * 
         * @params : [a, b, c]
         * @return : void
         * @author : cwl
         * @date : 2019/10/24 15:32
         */
        public void init(double a, double b, double c)
        {
            if (a == 0)
            {
                throw new RuntimeException("param a is not be zero");
            }
            instance.setA(new BigDecimal(a));
            instance.setB(new BigDecimal(b));
            instance.setC(new BigDecimal(c));
        }
        
        /**
         * 功能描述: 〈初始化二次函数〉
         * 
         * @params : [a, b]
         * @return : void
         * @author : cwl
         * @date : 2019/10/24 15:33
         */
        public void init(double a, double b)
        {
            if (a == 0)
            {
                throw new RuntimeException("param a is not be zero");
            }
            instance.setA(new BigDecimal(a));
            instance.setB(new BigDecimal(b));
        }
        
        /**
         * 功能描述: 〈初始化二次函数〉
         * 
         * @params : [a]
         * @return : void
         * @author : cwl
         * @date : 2019/10/24 15:34
         */
        public void init(double a)
        {
            if (a == 0)
            {
                throw new RuntimeException("param a is not be zero");
            }
            instance.setA(new BigDecimal(a));
        }
        
        /**
         * 功能描述: 〈判断点是否在抛物线上〉
         * 
         * @params : [x, y]
         * @return : boolean
         * @author : cwl
         * @date : 2019/10/24 15:50
         */
        public boolean isOnline(double x, double y)
        {
            BigDecimal dependent = new BigDecimal(y);
            BigDecimal square = new BigDecimal(Math.pow(2, x));
            BigDecimal independent = new BigDecimal(x);
            return dependent.equals(square.multiply(instance.getA()).add(independent.multiply(instance.getB())).add(instance.getC()));
        }
        
        // -b/2a,(4ac-b^2)/4a
        // AxisOfSymmetry
        /**
         * 功能描述: 〈获得对称轴 -b/2a〉
         * 
         * @params : []
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 16:22
         */
        public BigDecimal getAxisOfSymmetry()
        {
            return instance.getB().divide(instance.getA().multiply(new BigDecimal(2)), 2, RoundingMode.HALF_UP).negate();
        }
        
        /**
         * 功能描述: 〈获得抛物线的顶点 (4ac-b^2)/4a〉
         * 
         * @params : []
         * @return : java.math.BigDecimal
         * @author : cwl
         * @date : 2019/10/24 16:28
         */
        public BigDecimal getMaxValue()
        {
            BigDecimal a = instance.getA();
            BigDecimal b = instance.getB();
            BigDecimal c = instance.getC();
            BigDecimal subtract = new BigDecimal(4).multiply(a).multiply(c).subtract(new BigDecimal(Math.pow(2, b.doubleValue())));
            return subtract.divide(new BigDecimal(4).multiply(a), 2, RoundingMode.HALF_UP);
        }
        
        /**
         * 功能描述: 〈获得抛物线顶点〉
         * 
         * @params : []
         * @return : java.util.Map
         * @author : cwl
         * @date : 2019/10/24 16:45
         */
        public List<Point> getTopPoint()
        {
            Point point = new Point();
            BigDecimal axisOfSymmetry = getAxisOfSymmetry();
            BigDecimal maxValue = getMaxValue();
            point.setA(axisOfSymmetry);
            point.setB(maxValue);
            List<Point> points = instance.getPoints();
            points.add(point);
            return points;
        }
        
        // PointOfIntersection
        // -b +- 根号b^2 -4ac / 2a
        /**
         * 功能描述: 〈获得与X轴的交点〉
         * 
         * @params : []
         * @return : java.util.List<com.simple.util.math.QuadraticFunction.Point>
         * @author : cwl
         * @date : 2019/10/24 16:55
         */
        public List<Point> getPointOfIntersectionX()
        {
            BigDecimal bSquare =
                new BigDecimal(Math.pow(2, instance.getB().doubleValue())).setScale(2, RoundingMode.HALF_UP).subtract(
                    new BigDecimal(4).multiply(instance.getA()).multiply(instance.getC()));
            BigDecimal result = new BigDecimal(Math.sqrt(bSquare.doubleValue()));
            BigDecimal x1 =
                instance.getB().negate().add(result).divide(new BigDecimal(2).multiply(instance.getA()), 2, RoundingMode.HALF_UP);
            BigDecimal x2 =
                instance.getB().negate().subtract(result).divide(new BigDecimal(2).multiply(instance.getA()), 2, RoundingMode.HALF_UP);
            Point point1 = new Point();
            point1.setA(x1);
            point1.setB(BigDecimal.ZERO);
            Point point2 = new Point();
            point2.setB(x2);
            point2.setB(BigDecimal.ZERO);
            instance.setPoints(Collections.<Point> emptyList());
            List<Point> points = instance.getPoints();
            points.add(point1);
            points.add(point2);
            return points;
        }
        
        public List<Point> getPoints()
        {
            return points;
        }
        
        public void setPoints(List<Point> points)
        {
            this.points = points;
        }
        
        private BigDecimal getA()
        {
            return a;
        }
        
        private void setA(BigDecimal a)
        {
            this.a = a;
        }
        
        private BigDecimal getB()
        {
            return b;
        }
        
        private void setB(BigDecimal b)
        {
            this.b = b;
        }
        
        private BigDecimal getC()
        {
            return c;
        }
        
        private void setC(BigDecimal c)
        {
            this.c = c;
        }
    }
    
    /**
     * 點坐标
     * @qualiFild pers.bc.utils.math.Point.java<br>
     * @author：李本城<br>
     * @date Created on 2020-4-2<br>
     * @version 1.0<br>
     */
    static class Point
    {
        // 坐标x
        private double x;
        // 坐标y
        private double y;
        
        public Point(double x, double y)
        {
            this.x = x;
            this.y = y;
        }
        
        public Point()
        {
            
        }
        
        public double getX()
        {
            return x;
        }
        
        public void setX(double x)
        {
            this.x = x;
        }
        
        public double getY()
        {
            return y;
        }
        
        public void setY(double y)
        {
            this.y = y;
        }
        
        // x坐标
        private BigDecimal a;
        // y坐标
        private BigDecimal b;
        
        public Point(BigDecimal a, BigDecimal b)
        {
            this.a = a;
            this.b = b;
        }
        
        public BigDecimal getA()
        {
            return a;
        }
        
        public void setA(BigDecimal a)
        {
            this.a = a;
        }
        
        public BigDecimal getB()
        {
            return b;
        }
        
        public void setB(BigDecimal b)
        {
            this.b = b;
        }
        
    }
}
