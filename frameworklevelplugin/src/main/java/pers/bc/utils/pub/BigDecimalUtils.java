package pers.bc.utils.pub;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BigDecimal工具类
 * @qualiFild pers.bc.utils.pub.BigDecimalUtils.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2020-10-29<br>
 * @version 1.0<br>
 */
public class BigDecimalUtils extends DoubleUtil
{
    
    /**
     * *********************************************************** <br>
     * *说明：判断是否是零或正整数 <br>
     * @see <br>
     * @param str
     * @return <br>
     * @boolean <br>
     * @methods nc.impl.hi.psndoc.DateToUpperChinese#isNumeric <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-16 <br>
     * @time 10:28:01 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches())
        {
            return false;
        }
        return true;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 匹配 <br>
     * @see <br>
     * @param regex
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isMatch <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:09:00 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static boolean isMatch(String regex, String orginal)
    {
        if (orginal == null || orginal.trim().equals(""))
        {
            return false;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher isNum = pattern.matcher(orginal);
        return isNum.matches();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： is Positive Integer 判斷是正整数 <br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isPositiveInteger <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:08:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isPositiveInteger(String orginal)
    {
        return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：判斷是负整数 <br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isNegativeInteger <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:08:11 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isNegativeInteger(String orginal)
    {
        return isMatch("^-[1-9]\\d*", orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：判斷是整数 <br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isWholeNumber <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:08:05 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isWholeNumber(String orginal)
    {
        return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： is Positive Decimal 判斷是正小数<br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isPositiveDecimal <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:07:46 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isPositiveDecimal(String orginal)
    {
        return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： is Negative Decimal 判斷是负小数 <br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isNegativeDecimal <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:07:33 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isNegativeDecimal(String orginal)
    {
        return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 判斷是十进制的<br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isDecimal <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:07:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isDecimal(String orginal)
    {
        return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：是实数 <br>
     * @see <br>
     * @param orginal
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#isRealNumber <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-20 <br>
     * @time 下午10:06:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean isRealNumber(String orginal)
    {
        return isWholeNumber(orginal) || isDecimal(orginal);
    }
    
    /** @date 2019-10-29 */
    private static final long serialVersionUID = -8855852219001748442L;
    /** 小数精确的位数 */
    private static final int DEF_DIV_SCALE = 10;
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的加法运算。
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和 <br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#add <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:26:20 <br>
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
     * 说明：提供精确的加法运算。
     * 
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和 <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#add <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:26:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal add(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 提供精确的加法运算。 String
     * 
     * @param v1 被加数
     * @param v2 加数
     * @param scale 小数点的位数
     * @return 两个参数的和<br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strAdd <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:25:55 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strAdd(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的减法运算。
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差 <br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#sub <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:25:39 <br>
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
     * 说明： 提供精确的减法运算。
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     * @return <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#sub <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:25:28 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal sub(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：对一个数字取精度
     * @param v
     * @param scale 四舍五入保留的小数点位数
     * @return <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#round <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:25:11 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal round(String v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        /*
         * BigDecimal one = new BigDecimal("1"); return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
         */
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的减法运算。String
     * 
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差 <br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strSub <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:24:41 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strSub(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的乘法运算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积 <br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#mul <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:24:19 <br>
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
     * 说明：提供精确的乘法运算。
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积<br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#mul <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:24:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal mul(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的乘法运算。 保留scale 位小数
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @param scale 保留scale 位小数
     * @return 两个参数的积 <br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#mul2 <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:23:48 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double mul2(double v1, double v2, int scale)
    {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return round(b1.multiply(b2).doubleValue(), scale);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 提供精确的乘法运算。 保留scale 位小数 String
     * 
     * @param v1 被乘数
     * @param v2 乘数
     * @return 两个参数的积 <br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strMul2 <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:23:38 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strMul2(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商 <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#div <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:23:22 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal div(String v1, String v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @return 两个参数的商<br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#div <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:23:07 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static double div(double v1, double v2)
    {
        return div(v1, v2, DEF_DIV_SCALE);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商 <br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#div <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:22:58 <br>
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
     * 说明：提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商<br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#div <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:22:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal div(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string
     * 
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商<br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strDiv <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:22:08 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strDiv(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string <br>
     * @see
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商<br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#bigDiv <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:21:42 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal bigDiv(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：取余数 string <br>
     * @see <br>
     * @param v1
     * @param v2
     * @param scale
     * @return <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strRemainder <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:21:35 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal strRemainder(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 取余数 string<br>
     * @see <br>
     * @param v1
     * @param v2
     * @param scale
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strRemainder2Str <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:21:17 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strRemainder2Str(String v1, String v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 比较大小 如果v1 大于v2 则 返回true 否则false<br>
     * @see <br>
     * @param v1
     * @param v2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strcompareTo <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:21:09 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean strcompareTo(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj > 0) res = true;
        else
            res = false;
        return res;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：比较大小 如果v1 大于等于v2 则 返回true 否则false <br>
     * @see <br>
     * @param v1
     * @param v2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strcompareTo2 <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:21:01 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean strcompareTo2(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj >= 0) res = true;
        else
            res = false;
        return res;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：比较大小 如果v1 等于v2 则 返回true 否则false <br>
     * @see <br>
     * @param v1
     * @param v2
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strcompareTo3 <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:20:51 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static boolean strcompareTo3(String v1, String v2)
    {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        int bj = b1.compareTo(b2);
        boolean res;
        if (bj == 0) res = true;
        else
            res = false;
        return res;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：取余数 BigDecimal <br>
     * @see <br>
     * @param v1
     * @param v2
     * @param scale
     * @return <br>
     * @BigDecimal <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#bigRemainder <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:20:26 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static BigDecimal bigRemainder(BigDecimal v1, BigDecimal v2, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        return v1.remainder(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的小数位四舍五入处理 <br>
     * @see
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果<br>
     * @double <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#round <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:19:49 <br>
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
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：提供精确的小数位四舍五入处理 <br>
     * @see
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果<br>
     * @String <br>
     * @methods pers.bc.utils.pub.BigDecimalUtils#strRound <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-29 <br>
     * @time 上午11:19:20 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String strRound(String v, int scale)
    {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
    
}
