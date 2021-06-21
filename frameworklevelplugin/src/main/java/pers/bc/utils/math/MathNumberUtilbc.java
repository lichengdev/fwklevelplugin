package pers.bc.utils.math;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Java工具集-数学(数字工具类)
 * @qualiFild pers.bc.utils.math.NumberUtilbc.java<br>
 * @author：李本城<br>
 * @date Created on 2020-4-2<br>
 * @version 1.0<br>
 */
public class MathNumberUtilbc
{
    
    // long类型 0 1 -1
    public static final Long LONG_ZERO = new Long(0L);
    public static final Long LONG_ONE = new Long(1L);
    public static final Long LONG_MINUS_ONE = new Long(-1L);
    
    // Integer类型 0 1 -1
    public static final Integer INTEGER_ZERO = new Integer(0);
    public static final Integer INTEGER_ONE = new Integer(1);
    public static final Integer INTEGER_MINUS_ONE = new Integer(-1);
    
    // Short类型 0 1 -1
    public static final Short SHORT_ZERO = new Short((short) 0);
    public static final Short SHORT_ONE = new Short((short) 1);
    public static final Short SHORT_MINUS_ONE = new Short((short) -1);
    
    // Byte类型 0 1 -1
    public static final Byte BYTE_ZERO = new Byte((byte) 0);
    public static final Byte BYTE_ONE = new Byte((byte) 1);
    public static final Byte BYTE_MINUS_ONE = new Byte((byte) -1);
    
    // Double类型 0 1 -1
    public static final Double DOUBLE_ZERO = new Double(0.0d);
    public static final Double DOUBLE_ONE = new Double(1.0d);
    public static final Double DOUBLE_MINUS_ONE = new Double(-1.0d);
    
    // Float类型 0 1 -1
    public static final Float FLOAT_ZERO = new Float(0.0f);
    public static final Float FLOAT_ONE = new Float(1.0f);
    public static final Float FLOAT_MINUS_ONE = new Float(-1.0f);
    
    /**
     * 功能描述: 〈String转int类型〉
     * 
     * @params : [str]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 16:32
     */
    public static int stringToInt(String str)
    {
        return toInt(str);
    }
    
    /**
     * 功能描述: 〈str转int,当str为空,则返回默认值0〉
     * 
     * @params : [str]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 16:33
     */
    public static int toInt(String str)
    {
        return toInt(str, 0);
    }
    
    /**
     * 功能描述: 〈str转int,当str为空,则返回默认值〉
     * 
     * @params : [str, defaultValue]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 16:34
     */
    public static int stringToInt(String str, int defaultValue)
    {
        return toInt(str, defaultValue);
    }
    
    /**
     * 功能描述: 〈str转int,当str为空,则返回默认值〉
     * 
     * @params : [str, defaultValue]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 16:34
     */
    public static int toInt(String str, int defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }
        try
        {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException nfe)
        {
            return defaultValue;
        }
    }
    
    /**
     * 功能描述: 〈str转成int,不是字符串的使用0替换〉
     * 
     * @params : [str, defaultValue]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 16:36
     */
    public static int toIntDefaultZero(String str)
    {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            // 如果字符不是数字,则记录它的索引位置
            if (chars[i] >= 48 && chars[i] <= 57)
            {
                continue;
            }
            chars[i] = 48;
        }
        return Integer.valueOf(new String(chars));
    }
    
    /**
     * 功能描述: 〈字符串转long类型〉
     * 
     * @params : [str]
     * @return : long
     * @author : cwl
     * @date : 2019/6/6 17:24
     */
    public static long toLong(String str)
    {
        return toLong(str, 0L);
    }
    
    /**
     * 功能描述: 〈字符串转long类型,如果字符串为空,则返回默认值〉
     * 
     * @params : [str, defaultValue]
     * @return : long
     * @author : cwl
     * @date : 2019/6/6 17:24
     */
    public static long toLong(String str, long defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }
        try
        {
            return Long.parseLong(str);
        }
        catch (NumberFormatException nfe)
        {
            return defaultValue;
        }
    }
    
    /**
     * 功能描述: 〈字符串转float类型〉
     * 
     * @params : [str]
     * @return : float
     * @author : cwl
     * @date : 2019/6/6 17:25
     */
    public static float toFloat(String str)
    {
        return toFloat(str, 0.0f);
    }
    
    /**
     * 功能描述: 〈字符串转float类型,如果字符串为空,则返回默认值〉
     * 
     * @params : [str, defaultValue]
     * @return : float
     * @author : cwl
     * @date : 2019/6/6 17:25
     */
    public static float toFloat(String str, float defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }
        try
        {
            return Float.parseFloat(str);
        }
        catch (NumberFormatException nfe)
        {
            return defaultValue;
        }
    }
    
    /**
     * 功能描述: 〈字符串转double类型〉
     * 
     * @params : [str]
     * @return : double
     * @author : cwl
     * @date : 2019/6/6 17:26
     */
    public static double toDouble(String str)
    {
        return toDouble(str, 0.0d);
    }
    
    /**
     * 功能描述: 〈字符串转double类型,如果字符串为空,则返回默认值〉
     * 
     * @params : [str, defaultValue]
     * @return : double
     * @author : cwl
     * @date : 2019/6/6 17:26
     */
    public static double toDouble(String str, double defaultValue)
    {
        if (str == null)
        {
            return defaultValue;
        }
        try
        {
            return Double.parseDouble(str);
        }
        catch (NumberFormatException nfe)
        {
            return defaultValue;
        }
    }
    
    /**
     * 功能描述: 〈创建一个Number〉
     * 
     * @params : [str]
     * @return : java.lang.Number
     * @author : cwl
     * @date : 2019/6/6 17:33
     */
    public static Number createNumber(String str) throws NumberFormatException
    {
        if (str == null)
        {
            return null;
        }
        if (null == str || str.length() == 0 || " ".equals(str))
        {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        if (str.startsWith("--"))
        {
            // 对于Bigdecimal值的一种处理
            return null;
        }
        if (str.startsWith("0x") || str.startsWith("-0x"))
        {
            return createInteger(str);
        }
        char lastChar = str.charAt(str.length() - 1);
        String mant;
        String dec;
        String exp;
        int decPos = str.indexOf('.');
        int expPos = str.indexOf('e') + str.indexOf('E') + 1;
        
        if (decPos > -1)
        {
            
            if (expPos > -1)
            {
                if (expPos < decPos)
                {
                    throw new NumberFormatException(str + " is not a valid number.");
                }
                dec = str.substring(decPos + 1, expPos);
            }
            else
            {
                dec = str.substring(decPos + 1);
            }
            mant = str.substring(0, decPos);
        }
        else
        {
            if (expPos > -1)
            {
                mant = str.substring(0, expPos);
            }
            else
            {
                mant = str;
            }
            dec = null;
        }
        if (!Character.isDigit(lastChar))
        {
            if (expPos > -1 && expPos < str.length() - 1)
            {
                exp = str.substring(expPos + 1, str.length() - 1);
            }
            else
            {
                exp = null;
            }
            String numeric = str.substring(0, str.length() - 1);
            boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
            switch (lastChar)
            {
                case 'l' :
                case 'L' :
                    if (dec == null && exp == null && (numeric.charAt(0) == '-' && isDigits(numeric.substring(1)) || isDigits(numeric)))
                    {
                        try
                        {
                            return createLong(numeric);
                        }
                        catch (NumberFormatException nfe)
                        {
                            // Too big for a long
                        }
                        return createBigInteger(numeric);
                        
                    }
                    throw new NumberFormatException(str + " is not a valid number.");
                case 'f' :
                case 'F' :
                    try
                    {
                        Float f = MathNumberUtilbc.createFloat(numeric);
                        if (!(f.isInfinite() || (f.floatValue() == 0.0F && !allZeros)))
                        {
                            // 如果浮点数太大，或者浮点值= 0，并且字符串中有非零，那么浮点数就没有我们想要的精度
                            return f;
                        }
                        
                    }
                    catch (NumberFormatException nfe)
                    {
                        // 忽略非数字类型
                    }
                    // 顺序执行,无需break
                case 'd' :
                case 'D' :
                    try
                    {
                        Double d = MathNumberUtilbc.createDouble(numeric);
                        if (!(d.isInfinite() || (d.floatValue() == 0.0D && !allZeros)))
                        {
                            return d;
                        }
                    }
                    catch (NumberFormatException nfe)
                    {
                        // 忽略非数字类型
                    }
                    try
                    {
                        return createBigDecimal(numeric);
                    }
                    catch (NumberFormatException e)
                    {
                        // 忽略非数字类型
                    }
                    // 顺序执行,无需break
                default :
                    throw new NumberFormatException(str + " is not a valid number.");
                    
            }
        }
        else
        {
            if (expPos > -1 && expPos < str.length() - 1)
            {
                exp = str.substring(expPos + 1, str.length());
            }
            else
            {
                exp = null;
            }
            if (dec == null && exp == null)
            {
                // 必须是int或者是long类型
                try
                {
                    return createInteger(str);
                }
                catch (NumberFormatException nfe)
                {
                    // ignore the bad number
                }
                try
                {
                    return createLong(str);
                }
                catch (NumberFormatException nfe)
                {
                    // ignore the bad number
                }
                return createBigInteger(str);
                
            }
            else
            {
                // 必须是float或者double或者bigdecmail
                boolean allZeros = isAllZeros(mant) && isAllZeros(exp);
                try
                {
                    Float f = createFloat(str);
                    if (!(f.isInfinite() || (f.floatValue() == 0.0F && !allZeros)))
                    {
                        return f;
                    }
                }
                catch (NumberFormatException nfe)
                {
                    // ignore the bad number
                }
                try
                {
                    Double d = createDouble(str);
                    if (!(d.isInfinite() || (d.doubleValue() == 0.0D && !allZeros)))
                    {
                        return d;
                    }
                }
                catch (NumberFormatException nfe)
                {
                    // ignore the bad number
                }
                
                return createBigDecimal(str);
                
            }
        }
    }
    
    /**
     * 功能描述: 〈判断该字符串是否都是0〉
     * 
     * @params : [str]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/6 17:41
     */
    private static boolean isAllZeros(String str)
    {
        if (str == null)
        {
            return true;
        }
        for (int i = str.length() - 1; i >= 0; i--)
        {
            if (str.charAt(i) != '0')
            {
                return false;
            }
        }
        return str.length() > 0;
    }
    
    /**
     * 功能描述: 〈str转float〉
     * 
     * @params : [str]
     * @return : java.lang.Float
     * @author : cwl
     * @date : 2019/6/6 17:42
     */
    public static Float createFloat(String str)
    {
        if (str == null)
        {
            return null;
        }
        return Float.valueOf(str);
    }
    
    /**
     * 功能描述: 〈str转double类型〉
     * 
     * @params : [str]
     * @return : java.lang.Double
     * @author : cwl
     * @date : 2019/6/6 17:42
     */
    public static Double createDouble(String str)
    {
        if (str == null)
        {
            return null;
        }
        return Double.valueOf(str);
    }
    
    /**
     * 功能描述: 〈str转Integer类型〉
     * 
     * @params : [str]
     * @return : java.lang.Integer
     * @author : cwl
     * @date : 2019/6/6 17:42
     */
    public static Integer createInteger(String str)
    {
        if (str == null)
        {
            return null;
        }
        // decode() handles 0xAABD and 0777 (hex and octal) as well.
        return Integer.decode(str);
    }
    
    /**
     * 功能描述: 〈str转Long类型〉
     * 
     * @params : [str]
     * @return : java.lang.Long
     * @author : cwl
     * @date : 2019/6/6 17:43
     */
    public static Long createLong(String str)
    {
        if (str == null)
        {
            return null;
        }
        return Long.valueOf(str);
    }
    
    /**
     * 功能描述: 〈str转BigInteger〉
     * 
     * @params : [str]
     * @return : java.math.BigInteger
     * @author : cwl
     * @date : 2019/6/6 17:44
     */
    public static BigInteger createBigInteger(String str)
    {
        if (str == null)
        {
            return null;
        }
        return new BigInteger(str);
    }
    
    /**
     * 功能描述: 〈str转BigDecimal〉
     * 
     * @params : [str]
     * @return : java.math.BigDecimal
     * @author : cwl
     * @date : 2019/6/6 17:47
     */
    public static BigDecimal createBigDecimal(String str)
    {
        if (str == null)
        {
            return null;
        }
        // 处理JDK1.3.1当中空字符串也会抛出索引越界异常问题
        if (" ".equals(str.trim()) || str.length() == 0)
        {
            throw new NumberFormatException("A blank string is not a valid number");
        }
        return new BigDecimal(str);
    }
    
    /**
     * 功能描述: 〈返回long数组当中的最小值〉
     * 
     * @params : [array]
     * @return : long
     * @author : cwl
     * @date : 2019/6/6 17:47
     */
    public static long min(long[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        long min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回int数组当中的最小值〉
     * 
     * @params : [array]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 17:49
     */
    public static int min(int[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        int min = array[0];
        for (int j = 1; j < array.length; j++)
        {
            if (array[j] < min)
            {
                min = array[j];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回short数组当中的最小值〉
     * 
     * @params : [array]
     * @return : short
     * @author : cwl
     * @date : 2019/6/6 17:49
     */
    public static short min(short[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        short min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回byte数组当中的最小值〉
     * 
     * @params : [array]
     * @return : byte
     * @author : cwl
     * @date : 2019/6/6 17:50
     */
    public static byte min(byte[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        byte min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回double数组中的最小值〉
     * 
     * @params : [array]
     * @return : double
     * @author : cwl
     * @date : 2019/6/6 17:50
     */
    public static double min(double[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        double min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (Double.isNaN(array[i]))
            {
                return Double.NaN;
            }
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回float数组中的最小值〉
     * 
     * @params : [array]
     * @return : float
     * @author : cwl
     * @date : 2019/6/6 17:50
     */
    public static float min(float[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns min
        float min = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (Float.isNaN(array[i]))
            {
                return Float.NaN;
            }
            if (array[i] < min)
            {
                min = array[i];
            }
        }
        
        return min;
    }
    
    /**
     * 功能描述: 〈返回long数组的最大值〉
     * 
     * @params : [array]
     * @return : long
     * @author : cwl
     * @date : 2019/6/6 17:52
     */
    public static long max(long[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        long max = array[0];
        for (int j = 1; j < array.length; j++)
        {
            if (array[j] > max)
            {
                max = array[j];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈返回int数组最大值〉
     * 
     * @params : [array]
     * @return : int
     * @author : cwl
     * @date : 2019/6/6 17:53
     */
    public static int max(int[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        int max = array[0];
        for (int j = 1; j < array.length; j++)
        {
            if (array[j] > max)
            {
                max = array[j];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈返回short数组最大值〉
     * 
     * @params : [array]
     * @return : short
     * @author : cwl
     * @date : 2019/6/6 17:53
     */
    public static short max(short[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        short max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > max)
            {
                max = array[i];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈返回byte数组最大值〉
     * 
     * @params : [array]
     * @return : byte
     * @author : cwl
     * @date : 2019/6/6 17:54
     */
    public static byte max(byte[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        byte max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] > max)
            {
                max = array[i];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈获取double数组最大值〉
     * 
     * @params : [array]
     * @return : double
     * @author : cwl
     * @date : 2019/6/6 17:54
     */
    public static double max(double[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        double max = array[0];
        for (int j = 1; j < array.length; j++)
        {
            if (Double.isNaN(array[j]))
            {
                return Double.NaN;
            }
            if (array[j] > max)
            {
                max = array[j];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈获取float数组最大值〉
     * 
     * @params : [array]
     * @return : float
     * @author : cwl
     * @date : 2019/6/6 17:55
     */
    public static float max(float[] array)
    {
        // Validates input
        if (array == null)
        {
            throw new IllegalArgumentException("The Array must not be null");
        }
        else if (array.length == 0)
        {
            throw new IllegalArgumentException("Array cannot be empty.");
        }
        
        // Finds and returns max
        float max = array[0];
        for (int j = 1; j < array.length; j++)
        {
            if (Float.isNaN(array[j]))
            {
                return Float.NaN;
            }
            if (array[j] > max)
            {
                max = array[j];
            }
        }
        
        return max;
    }
    
    /**
     * 功能描述: 〈判断字符是否是纯数字〉
     * 
     * @params : [str]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/6 17:58
     */
    public static boolean isDigits(String str)
    {
        if (null == str || str.length() == 0 || " ".equals(str))
        {
            return false;
        }
        for (int i = 0; i < str.length(); i++)
        {
            if (!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 功能描述: 〈判断是否是一个数字类型〉
     * 
     * @params : [str]
     * @return : boolean
     * @author : cwl
     * @date : 2019/6/6 17:59
     */
    public static boolean isNumber(String str)
    {
        if (null == str || str.length() == 0 || " ".equals(str))
        {
            return false;
        }
        char[] chars = str.toCharArray();
        int sz = chars.length;
        boolean hasExp = false;
        boolean hasDecPoint = false;
        boolean allowSigns = false;
        boolean foundDigit = false;
        // deal with any possible sign up front
        int start = (chars[0] == '-') ? 1 : 0;
        if (sz > start + 1)
        {
            if (chars[start] == '0' && chars[start + 1] == 'x')
            {
                int i = start + 2;
                if (i == sz)
                {
                    return false; // str == "0x"
                }
                // checking hex (it can't be anything else)
                for (; i < chars.length; i++)
                {
                    if ((chars[i] < '0' || chars[i] > '9') && (chars[i] < 'a' || chars[i] > 'f') && (chars[i] < 'A' || chars[i] > 'F'))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        sz--; // don't want to loop to the last char, check it afterwords
        // for type qualifiers
        int i = start;
        // loop to the next to last char or to the last char if we need another digit to
        // make a valid number (e.g. chars[0..5] = "1234E")
        while (i < sz || (i < sz + 1 && allowSigns && !foundDigit))
        {
            if (chars[i] >= '0' && chars[i] <= '9')
            {
                foundDigit = true;
                allowSigns = false;
                
            }
            else if (chars[i] == '.')
            {
                if (hasDecPoint || hasExp)
                {
                    // two decimal points or dec in exponent
                    return false;
                }
                hasDecPoint = true;
            }
            else if (chars[i] == 'e' || chars[i] == 'E')
            {
                // we've already taken care of hex.
                if (hasExp)
                {
                    // two E's
                    return false;
                }
                if (!foundDigit)
                {
                    return false;
                }
                hasExp = true;
                allowSigns = true;
            }
            else if (chars[i] == '+' || chars[i] == '-')
            {
                if (!allowSigns)
                {
                    return false;
                }
                allowSigns = false;
                foundDigit = false; // we need a digit after the E
            }
            else
            {
                return false;
            }
            i++;
        }
        if (i < chars.length)
        {
            if (chars[i] >= '0' && chars[i] <= '9')
            {
                // no type qualifier, OK
                return true;
            }
            if (chars[i] == 'e' || chars[i] == 'E')
            {
                // can't have an E at the last byte
                return false;
            }
            if (!allowSigns && (chars[i] == 'd' || chars[i] == 'D' || chars[i] == 'f' || chars[i] == 'F'))
            {
                return foundDigit;
            }
            if (chars[i] == 'l' || chars[i] == 'L')
            {
                // not allowing L with an exponent
                return foundDigit && !hasExp;
            }
            // last character is illegal
            return false;
        }
        // allowSigns is true iff the val ends in 'E'
        // found digit it to make sure weird stuff like '.' and '1E-' doesn't pass
        return !allowSigns && foundDigit;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： i小于等于0，默认为0 <br>
     * @see <br>
     * @param i
     * @return <br>
     * @Integer <br>
     * @methods pers.bc.utils.math.MathNumberUtilbc#isIntDefaultZero <br>
     * @author licheng <br>
     * @date Created on 2020-4-2 <br>
     * @time 下午10:20:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static Integer isIntDefaultZero(Integer i)
    {
        return i <= MathNumberUtilbc.INTEGER_ZERO ? MathNumberUtilbc.INTEGER_ZERO : i;
    }
}
