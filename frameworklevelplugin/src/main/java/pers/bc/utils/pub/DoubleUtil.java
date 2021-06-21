package pers.bc.utils.pub;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import pers.bc.utils.cons.PubConsUtilbc;
import pers.bc.utils.throwable.MylbcException;

public class DoubleUtil implements Serializable
{
    
    /**
     * *********************************************************** <br>
     * *说明： 移位判断是不是2的NCF<br>
     * @see <br>
     * @param inrCodeLength
     * @return <br>
     * @int <br>
     * @methods nc.pub.tools.bc.HRPubUtils#binaryIs2Power <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-26 <br>
     * @time 11:31:16 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int is2PowerBinary(int inrCodeLength)
    {
        if (inrCodeLength < 2) return -1;
        int temp = 1;
        while (inrCodeLength > temp)
        {
            temp <<= 1;
        }
        
        return temp == inrCodeLength ? 1 : -1;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 位与判断，最快<br>
     * @see <br>
     * @param num
     * @return <br>
     * @int <br>
     * @methods nc.pub.tools.bc.HRPubUtils#anotherIs2Power <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-26 <br>
     * @time 11:35:10 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int is2PowerAnother(int num)
    {
        if (num < 2) return -1;
        
        if ((num & num - 1) == 0) return 1;
        else
            return -1;
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 递归算法实现<br>
     * @see <br>
     * @param num
     * @return <br>
     * @int <br>
     * @methods nc.pub.tools.bc.HRPubUtils#is2Power <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-26 <br>
     * @time 11:36:22 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static int is2Power(int num)
    {
        if (num < 2) return -1;
        
        if (num == 2) return 1;
        else if (num % 2 == 0) return is2Power(num / 2);
        else
            return -1;
    }
    
    private static final long serialVersionUID = -4679828183012158453L;
    // 默认除法运算精度
    private static final int DEF_DIV_SCALE = 2;
    
    // private static Logger logger = Logger.getLogger(DoubleUtil.class);
    
    /**
     * *********************************************************** <br>
     * 说明： 百分数转换为小数 <br>
     * @see <br>
     * @param percent
     * @return <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.DoubleUtil#valueOfPercent <br>
     * @author LiBencheng <br>
     * @date Created on 2020-7-21 <br>
     * @time 下午3:24:14 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal valueOfPercent(String percent)
    {
        percent = percent.replace("%", "");
        Float fn = Float.valueOf(percent) / 100;
        BigDecimal big = new BigDecimal(fn);
        
        return big;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：double转化为百分数 <br>
     * @see <br>
     * @param d
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.DoubleUtil#valueOfPercent <br>
     * @author LiBencheng <br>
     * @date Created on 2020-7-21 <br>
     * @time 下午3:14:51 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String valueOfPercent(Double d)
    {
        return valueOfPercent(d, 2, 2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： double转化为百分数 ,<br>
     * 特殊情况：当数字大于0.99995，结果都为00.00%<br>
     * @see <br>
     * @param d
     * @param IntegerDigits 小数点前保留几位
     * @param FractionDigits 小数点后保留几位
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.DoubleUtil#getPercentFormat <br>
     * @author LiBencheng <br>
     * @date Created on 2020-7-21 <br>
     * @time 下午3:12:17 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static String valueOfPercent(Double d, int IntegerDigits, int FractionDigits)
    {
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumIntegerDigits(IntegerDigits);// 小数点前保留几位
        nf.setMinimumFractionDigits(FractionDigits);// 小数点后保留几位
        String str = nf.format(d);
        
        return str;
    }
    
    public static Double valueOfOne(Double oldDouble)
    {
        return (null == oldDouble ? new Double("1.0") : oldDouble);
    }
    
    public static Double valueOfZero(Double oldDouble)
    {
        return (null == oldDouble ? new Double("0.0") : oldDouble);
    }
    
    public static Double doubleValueOf(String dbValue) throws MylbcException
    {
        Double d = new Double("0.0");
        try
        {
            d = Double.valueOf(ArrayUtilbc.isEmpty(dbValue) ? "0.0" : dbValue);
        }
        catch (Exception e)
        {
            throw new MylbcException("Value {[" + dbValue + "]} not a valid number!");
        }
        
        return d;
    }
    
    public static Double dealNull(Double d1, Double d2)
    {
        return (d1 == null) ? d2 : d1;
    }
    
    public static Double dealNull(Object d1, Double d2)
    {
        return (d1 == null) ? d2 : (Double) d1;
    }
    
    public static Double dealNull(String d1) throws MylbcException
    {
        return getDouble(d1);
    }
    
    public static Double dealNull(String d1, Double d2)
    {
        return getDouble(d1, d2);
    }
    
    public static Double dealNull(Object d1, Object d2)
    {
        return (d1 == null) ? (Double) d2 : (Double) d1;
    }
    
    public static Double getDouble(String value) throws MylbcException
    {
        try
        {
            return new Double(value);
        }
        catch (Exception e)
        {
            // logger.error("getDouble" + e.getMessage());
            throw new MylbcException("Cann't ?Convert Double [" + value + "]");
        }
    }
    
    public static Double getDouble(String value, Double defaultDouble)
    {
        try
        {
            if (!StringUtil.isEmpty(value))
            {
                return new Double(value);
            }
        }
        catch (Exception e)
        {
            // logger.error("getDouble" + e.getMessage());
        }
        return defaultDouble;
    }
    
    public static Double getDouble(Object value, Double defaultDouble)
    {
        try
        {
            if (!StringUtil.isEmpty(value))
            {
                return new Double(value.toString());
            }
        }
        catch (Exception e)
        {
            // logger.error("getDouble" + e.getMessage());
        }
        return defaultDouble;
    }
    
    public static String toString(final Double[] l, final String split)
    {
        StringBuilder bufs = new StringBuilder();
        if (l != null && l.length != 0)
        {
            String common = "";
            for (Double g : l)
            {
                bufs.append(common);
                bufs.append(g);
                common = split;
            }
        }
        return bufs.toString();
    }
    
    public static String toString(List<Double> l, String split)
    {
        StringBuilder bufs = new StringBuilder();
        if (l != null && !l.isEmpty())
        {
            String common = "";
            for (Double g : l)
            {
                bufs.append(common);
                bufs.append(g);
                common = split;
            }
        }
        return bufs.toString();
    }
    
    public static List<Double> toList(final String str, final String split)
    {
        if (str == null)
        {
            return null;
        }
        StringTokenizer sts = new StringTokenizer(str, split);
        List<Double> args = new ArrayList<Double>(sts.countTokens());
        while (sts.hasMoreElements())
        {
            args.add(getDouble(sts.nextElement(), null));
        }
        return args;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的加法运算。 ?
     * @param v1 ? ? ? ? ? ?被加数
     * @param v2 ? ? ? ? ? ?加数
     * @return 两个参数的和<br>
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#add <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:14:20 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double add(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 提供精确的减法运算。 ?
     * @param v1 ? ? ? ? ? ?被减数
     * @param v2 ? ? ? ? ? ?减数
     * @return 两个参数的差<br>
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#sub <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:14:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double sub(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }
    
    /**
     * *********************************************************** <br>
     * @explain 提供精确的乘法运算。 ?
     * @param v1 ? ? ? ? ? ?被乘数
     * @param v2 ? ? ? ? ? ?乘数
     * @return 两个参数的积 <br>
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#mul <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:13:43 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double mul(double v1, double v2)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }
    
    /**
     * *********************************************************** <br>
     * @explain： ? 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 ? 小数点以后2位，以后的数字四舍五入。 ?
     * @param v1 ? ? ? ? ? ?被除数 ?
     * @param v2 ? ? ? ? ? ?除数 ?
     * @return 两个参数的商
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#div <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:12:50 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 ? 定精度，以后的数字四舍五入。 ?
     * @param v1 ? ? ? ? ? ?被除数 ?
     * @param v2 ? ? ? ? ? ?除数 ?
     * @param scale ? ? ? ? ? ?表示表示需要精确到小数点以后几位。 ?
     * @return 两个参数的商 ?
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#div <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:12:24 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double div(double v1, double v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 提供精确的小数位四舍五入处理。 <br>
     * @see <br>
     * @param v?需要四舍五入的数字
     * @param scale?小数点后保留几位 ?
     * @return 四舍五入后的结果<br>
     * @double <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#round <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:11:59 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double round(double v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal(1);
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 1,2,3,4,5,6<br>
     * @see <br>
     * @param str 带数值字符串
     * @param split 截取符號
     * @return <br>
     * @Double[] <br>
     * @methods nc.pub.itf.tools.pub.DoubleUtil#toArray <br>
     * @author licheng <br>
     * @date Created on 2019-10-31 <br>
     * @time 下午3:22:39 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Double[] toArray(String str, String split)
    {
        if (str == null)
        {
            return null;
        }
        StringTokenizer sts = new StringTokenizer(str, split);
        Double[] args = new Double[sts.countTokens()];
        for (int i = 0; sts.hasMoreElements(); i++)
        {
            args[i] = getDouble(sts.nextElement(), null);
        }
        return args;
    }
    
    // 截取3位
    public static double round(double v)
    {
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal(PubConsUtilbc.ONE);
        return b.divide(one, 3, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static String decimalFormat(String pattern, double value)
    {
        return new DecimalFormat(pattern).format(value);
    }
    
    public static String decimalFormat(double value)
    {
        return new DecimalFormat("0.00").format(value);
    }
    
    public static String decimalFormat(double value, String pattern)
    {
        return new DecimalFormat(pattern).format(value);
    }
    
    public static String decimalBlankFormat(double value)
    {
        return new DecimalFormat(PubConsUtilbc.ZERO).format(value);
    }
    
}
