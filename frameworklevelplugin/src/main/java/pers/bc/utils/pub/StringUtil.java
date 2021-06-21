package pers.bc.utils.pub;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pers.bc.utils.cons.PubConsUtilbc;

/**
 * 提供些常用的字符串相关的工具方法
 * @qualiFild nc.pub.itf.tools.pub.StringUtil.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class StringUtil implements PubConsUtilbc
{
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * 判断是否是空字符串 null和"" 都返回 true
     * @see <br>
     * @param str
     * @return <br>
     * @boolean <br>
     * @methods nc.pub.itf.tools.pub.StringUtil#isEmpty <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午10:23:39 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean isEmpty(String str)
    {
        return str == null || str.equals("");
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param obj
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午7:50:43
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean isEmpty(Object obj)
    {
        return obj == null || obj.equals("");
    }
    
    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     * 
     * @param list 需要处理的列表
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(List<?> list, String symbol)
    {
        String result = "";
        if (list != null)
        {
            for (Object o : list)
            {
                String temp = o.toString();
                if (temp.trim().length() > 0) result += (temp + symbol);
            }
            if (result.length() > 1)
            {
                result = result.substring(0, result.length() - 1);
            }
        }
        return result;
    }
    
    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     * 
     * @param str1 测试的字符串
     * @param str2 字符串数组(用,分割)
     * @return 是否包含
     */
    public static boolean requals(String str1, String str2)
    {
        if (str1 != null && str2 != null)
        {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(",");
            for (String t : arr)
            {
                if (t.equals(str1.trim()))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 判定第一个字符串是否等于的第二个字符串中的某一个值
     * 
     * @param str1 测试的字符串
     * @param str2 字符串数组
     * @param split str2字符串的分隔符
     * @return 是否包含
     */
    public static boolean requals(String str1, String str2, String split)
    {
        if (str1 != null && str2 != null)
        {
            str2 = str2.replaceAll("\\s*", "");
            String[] arr = str2.split(split);
            for (String t : arr)
            {
                if (t.equals(str1.trim()))
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 字符串省略截取 字符串截取到指定长度size+...的形式
     * 
     * @param subject 需要处理的字符串
     * @param size 截取的长度
     * @return 处理后的字符串
     */
    public static String subStringOmit(String subject, int size)
    {
        if (subject != null && subject.length() > size)
        {
            subject = subject.substring(0, size) + "...";
        }
        return subject;
    }
    
    /**
     * 截取字符串 超出的字符用symbol代替
     * 
     * @param str 需要处理的字符串
     * @param len 字符串长度
     * @param symbol 最后拼接的字符串
     * @return 测试后的字符串
     */
    public static String subStringSymbol(String str, int len, String symbol)
    {
        String temp = "";
        if (str != null && str.length() > len)
        {
            temp = str.substring(0, len) + symbol;
        }
        return temp;
    }
    
    /**
     * 把string array or list用给定的符号symbol连接成一个字符串
     * 
     * @param array 需要处理的字符串数组
     * @param symbol 链接的符号
     * @return 处理后的字符串
     */
    public static String joinString(String[] array, String symbol)
    {
        String result = "";
        if (array != null)
        {
            for (String temp : array)
            {
                if (temp != null && temp.trim().length() > 0) result += (temp + symbol);
            }
            if (result.length() > 1 && !ArrayUtilbc.isEmpty(symbol))
            {
                result = result.substring(0, result.length() - symbol.length());
            }
        }
        return result;
    }
    
    /**
     * 将一组字符才以指定的字符链接起来
     * 
     * @param linkStr 链接字符
     * @param strs 需要连接的动态参数
     * @return
     */
    public static String join(String linkStr, String... strs)
    {
        StringBuffer result = new StringBuffer();
        for (String temp : strs)
        {
            if (temp != null && temp.trim().length() > 0) result.append(temp + linkStr);
        }
        if (result.length() > 1 && ArrayUtilbc.isEmpty(linkStr))
        {
            return result.substring(0, result.length() - linkStr.length());
        }
        return result.toString();
    }
    
    /**
     * 隐藏邮件地址前缀。
     * 
     * @param email - EMail邮箱地址 例如: ssss@koubei.com 等等...
     * @return 返回已隐藏前缀邮件地址, 如 *********@koubei.com.
     */
    public static String getHideEmailPrefix(String email)
    {
        if (null != email)
        {
            int index = email.lastIndexOf('@');
            if (index > 0)
            {
                email = repeat("*", index).concat(email.substring(index));
            }
        }
        return email;
    }
    
    /**
     * repeat - 通过源字符串重复生成N次组成新的字符串。
     * 
     * @param src - 源字符串 例如: 空格(" "), 星号("*"), "浙江" 等等...
     * @param num - 重复生成次数
     * @return 返回已生成的重复字符串
     */
    public static String repeat(String src, int num)
    {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < num; i++)
            s.append(src);
        return s.toString();
    }
    
    /**
     * 截取字符串左侧的Num位截取到末尾
     * 
     * @param str1 处理的字符串
     * @param num 开始位置
     * @return 截取后的字符串
     */
    public static String ltrim(String str1, int num)
    {
        String tt = "";
        if (!isEmpty(str1) && str1.length() >= num)
        {
            tt = str1.substring(num, str1.length());
        }
        return tt;
        
    }
    
    /**
     * 截取字符串右侧第0位到第Num位
     * 
     * @param str 处理的字符串
     * @param num 截取的位置
     * @return 截取后的字符串
     */
    public static String rtrim(String str, int num)
    {
        if (!isEmpty(str) && str.length() > num)
        {
            str = str.substring(0, str.length() - num);
        }
        return str;
    }
    
    /**
     * 根据指定的字符把源字符串分割成一个list
     * 
     * @param src 处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> parseString2List(String src, String pattern)
    {
        List<String> list = new ArrayList<String>();
        if (src != null)
        {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }
    
    /**
     * 格式化一个float
     * 
     * @param format 要格式化成的格式 such as #.00, #.#
     * @return 格式化后的字符串
     */
    public static String formatDouble(double f, String format)
    {
        DecimalFormat df = new DecimalFormat(format);
        return df.format(f);
    }
    
    /**
     * 截取字符串左侧指定长度的字符串
     * 
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String left(String input, int count)
    {
        if (isEmpty(input))
        {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(0, count);
    }
    
    /**
     * 截取字符串右侧指定长度的字符串
     * 
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串 Summary 其他编码的有待测试
     */
    public static String right(String input, int count)
    {
        if (isEmpty(input))
        {
            return "";
        }
        count = (count > input.length()) ? input.length() : count;
        return input.substring(input.length() - count, input.length());
    }
    
    /**
     * 全角字符变半角字符
     * 
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String full2Half(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        return qj2bj(str);
    }
    
    /**
     * 半角字符变全角字符
     * 
     * @param str 需要处理的字符串
     * @return 处理后的字符串
     */
    public static String Half2Full(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        return bj2qj(str);
    }
    
    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     * 
     * @param str 需要处理的字符串
     */
    public static String replaceBlank(String str)
    {
        if (str != null)
        {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }
    
    /**
     * 判断字符串数组中是否包含某字符串元素
     * 
     * @param substring 某字符串
     * @param source 源字符串数组
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String[] source)
    {
        if (isEmpty(substring) || !ArrayUtilbc.isEmpty(source))
        {
            return false;
        }
        for (String t : source)
        {
            if (substring.equals(t))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： *
     * 
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     * <br>
     * @see <br>
     * @param cs
     * @return <br>
     * @boolean <br>
     * @methods pers.bc.utils.pub.StringUtil#isBlank <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-19 <br>
     * @time 14:28:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean isBlank(final CharSequence cs)
    {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0)
        {
            return true;
        }
        for (int i = 0; i < strLen; i++)
        {
            if (!Character.isWhitespace(cs.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 字符串转换unicode.实现native2ascii.exe类似的功能
     * 
     * @param string 需要处理的字符串
     */
    public static String string2Unicode(String string)
    {
        StringBuilder uni = new StringBuilder();
        for (int i = 0; i < string.length(); i++)
        {
            String temp = "\\u" + String.valueOf(Integer.toHexString(string.charAt(i)));
            uni.append(temp);
        }
        return uni.toString();
    }
    
    /**
     * 转字符串 实现native2ascii.exe类似的功能
     * 
     * @param unicode 需要处理的字符串
     */
    public static String unicode2String(String unicode)
    {
        StringBuilder str = new StringBuilder();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++)
        {
            int data = Integer.parseInt(hex[i], 16);
            str.append((char) data);
        }
        return str.toString();
    }
    
    /**
     * 删除所有的标点符号
     * 
     * @param str 处理的字符串
     */
    public static String trimPunct(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        return str.replaceAll("[\\pP\\p{Punct}]", "");
    }
    
    /**
     * 获取字符串str在String中出现的次数
     * 
     * @param string 处理的字符串
     * @param str 子字符串
     */
    public static int countSubStr(String string, String str)
    {
        if ((str == null) || (str.length() == 0) || (string == null) || (string.length() == 0))
        {
            return 0;
        }
        int count = 0;
        int index = 0;
        while ((index = string.indexOf(str, index)) != -1)
        {
            count++;
            index += str.length();
        }
        return count;
    }
    
    /**
     * 替换一个出现的子串
     * 
     * @param s source string
     * @param sub substring to replace
     * @param with substring to replace with
     */
    public static String replaceFirst(String s, String sub, String with)
    {
        int i = s.indexOf(sub);
        if (i == -1)
        {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }
    
    /**
     * 替换最后一次出现的字串 Replaces the very last occurrence of a substring with supplied string.
     * 
     * @param s source string
     * @param sub substring to replace
     * @param with substring to replace with
     */
    public static String replaceLast(String s, String sub, String with)
    {
        int i = s.lastIndexOf(sub);
        if (i == -1)
        {
            return s;
        }
        return s.substring(0, i) + with + s.substring(i + sub.length());
    }
    
    /**
     * 删除所有的子串 Removes all substring occurrences from the string.
     * 
     * @param s source string
     * @param sub substring to remove
     */
    public static String remove(String s, String sub)
    {
        int c = 0;
        int sublen = sub.length();
        if (sublen == 0)
        {
            return s;
        }
        int i = s.indexOf(sub, c);
        if (i == -1)
        {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        do
        {
            sb.append(s.substring(c, i));
            c = i + sublen;
        }
        while ((i = s.indexOf(sub, c)) != -1);
        if (c < s.length())
        {
            sb.append(s.substring(c, s.length()));
        }
        return sb.toString();
    }
    
    /**
     * 将字符串首字母转大写
     * 
     * @param str 需要处理的字符串
     */
    public static String upperFirstChar(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= 'a') && (cs[0] <= 'z'))
        {
            cs[0] -= (char) 0x20;
        }
        return String.valueOf(cs);
    }
    
    /**
     * 将字符串首字母转小写
     * 
     * @param str
     * @return
     */
    public static String lowerFirstChar(String str)
    {
        if (isEmpty(str))
        {
            return "";
        }
        char[] cs = str.toCharArray();
        if ((cs[0] >= 'A') && (cs[0] <= 'Z'))
        {
            cs[0] += (char) 0x20;
        }
        return String.valueOf(cs);
    }
    
    /**
     * 判读俩个字符串右侧的length个字符串是否一样
     * 
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public static boolean rigthEquals(String str1, String str2, int length)
    {
        return right(str1, length).equals(right(str2, length));
    }
    
    /**
     * 判读俩个字符串左侧的length个字符串是否一样
     * 
     * @param str1
     * @param str2
     * @param length
     * @return
     */
    public static boolean leftEquals(String str1, String str2, int length)
    {
        return left(str1, length).equals(left(str2, length));
    }
    
    /**
     * <PRE>
     * 半角字符->全角字符转换
     * 只处理空格，!到?之间的字符，忽略其他
     * </PRE>
     */
    public static String bj2qj(String src)
    {
        if (StringUtil.isEmpty(src))
        {
            return "";
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (char t : ca)
        {
            if (t == DBC_SPACE)
            {
                // 如果是半角空格，直接用全角空格替代
                buf.append(SBC_SPACE);
            }
            else if ((t >= DBC_CHAR_START) && (t <= DBC_CHAR_END))
            {
                // 字符是!到~之间的可见字符
                buf.append((char) (t + CONVERT_STEP));
            }
            else
            {
                // 不对空格以及ascii表中其他可见字符之外的字符做任何处理
                buf.append(t);
            }
        }
        return buf.toString();
    }
    
    /**
     * <PRE>
     * 全角字符->半角字符转换
     * 只处理全角的空格，全角！到全角～之间的字符，忽略其他
     * </PRE>
     */
    public static String qj2bj(String src)
    {
        if (StringUtil.isEmpty(src))
        {
            return "";
        }
        StringBuilder buf = new StringBuilder(src.length());
        char[] ca = src.toCharArray();
        for (int i = 0; i < src.length(); i++)
        {
            if (ca[i] >= SBC_CHAR_START && ca[i] <= SBC_CHAR_END)
            {
                // 如果位于全角！到全角～区间内
                buf.append((char) (ca[i] - CONVERT_STEP));
            }
            else if (ca[i] == SBC_SPACE)
            {
                // 如果是全角空格
                buf.append(DBC_SPACE);
            }
            else
            {
                // 不处理全角空格，全角！到全角～区间外的字符
                buf.append(ca[i]);
            }
        }
        return buf.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：判断字符串是否包含汉字 <br>
     * @see <br>
     * @param str
     * @return <br>
     * @boolean <br>
     * @methods nc.pub.itf.tools.pub.StringUtil#isContainChinese <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 上午11:57:36 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static boolean isContainChinese(String str)
    {
        if (Pattern.compile("[\u4e00-\u9fa5]").matcher(str).find()) return true;
        return false;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：把字符串的数字，提取出来 <br>
     * @see <br>
     * @param str
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StringUtil#getNumberStr <br>
     * @author licheng <br>
     * @date Created on 2019-8-26 <br>
     * @time 上午11:49:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getNumberByStr(String str)
    {
        return Pattern.compile("[^A-Za-z0-9]").matcher(str).replaceAll("").trim();
    }
    
    // ===============================================================
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * 第一种实现方式
     * @see <br>
     * @param strA
     * @param strB
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StringImpl#longestCommonSubstring <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午10:23:24 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static String longestCommonSubstring(String strA, String strB)
    {
        char[] chars_strA = strA.toCharArray();
        char[] chars_strB = strB.toCharArray();
        int m = chars_strA.length;
        int n = chars_strB.length;
        int[][] matrix = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (chars_strA[i - 1] == chars_strB[j - 1]) matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
            }
        }
        char[] result = new char[matrix[m][n]];
        int currentIndex = result.length - 1;
        while (matrix[m][n] != 0)
        {
            if (matrix[n] == matrix[n - 1]) n--;
            else if (matrix[m][n] == matrix[m - 1][n]) m--;
            else
            {
                result[currentIndex] = chars_strA[m - 1];
                currentIndex--;
                n--;
                m--;
            }
        }
        return new String(result);
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param charValue
     * @return
     * @boolean
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:44:54
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static boolean charReg(char charValue)
    {
        return (charValue >= 0x4E00 && charValue <= 0X9FA5) || (charValue >= 'a' && charValue <= 'z')
            || (charValue >= 'A' && charValue <= 'Z') || (charValue >= '0' && charValue <= '9');
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param str
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:45:01
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static String removeSign(String str)
    {
        StringBuffer sb = new StringBuffer();
        for (char item : str.toCharArray())
        {
            if (charReg(item))
            {
                sb.append(item);
            }
        }
        return sb.toString();
    }
    
    /**
     * 快速比较俩个字符串的相似度
     * 
     * @param strL 较长的字符串
     * @param strS 较短的字符串
     * @return 俩个字符串的相似度
     *         <p>
     *         summary
     *         </p>
     *         :较长的字符串放到前面有助于提交效率
     */
    public static double SimilarDegree(String strL, String strS)
    {
        String newStrA = removeSign(strL);
        String newStrB = removeSign(strS);
        int temp = Math.max(newStrA.length(), newStrB.length());
        int temp2 = longestCommonSubstring(newStrA, newStrB).length();
        return temp2 * 1.0 / temp;
    }
    
    // 第二种实现方式
    private static int compare(String str, String target)
    {
        int d[][]; // 矩阵
        int n = str.length();
        int m = target.length();
        int i; // 遍历str的
        int j; // 遍历target的
        char ch1; // str的
        char ch2; // target的
        int temp; // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
        if (n == 0)
        {
            return m;
        }
        if (m == 0)
        {
            return n;
        }
        d = new int[n + 1][m + 1];
        for (i = 0; i <= n; i++)
        { // 初始化第一列
            d[i][0] = i;
        }
        
        for (j = 0; j <= m; j++)
        { // 初始化第一行
            d[0][j] = j;
        }
        
        for (i = 1; i <= n; i++)
        { // 遍历str
            ch1 = str.charAt(i - 1);
            // 去匹配target
            for (j = 1; j <= m; j++)
            {
                ch2 = target.charAt(j - 1);
                if (ch1 == ch2)
                {
                    temp = 0;
                }
                else
                {
                    temp = 1;
                }
                
                // 左边+1,上边+1, 左上角+temp取最小
                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
            }
        }
        return d[n][m];
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see
     * @param one
     * @param two
     * @param three
     * @return
     * @int
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午7:29:49
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static int min(int one, int two, int three)
    {
        return (one = one < two ? one : two) < three ? one : three;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取字符串的相似度
     * @see
     * @param str
     * @param target
     * @return
     * @double
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:45:59
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static double SimilarityRatio(String str, String target)
    {
        return 1 - (double) compare(str, target) / Math.max(str.length(), target.length());
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取字符串编码
     * @see
     * @param str 需要处理的字符串
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-7-24
     * @time 下午6:46:09
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String simpleEncoding(String str)
    {
        try
        {
            byte[] bs = str.getBytes(SysUtil.JVM_ENCODING);
            if (str.equals(new String(bs, PubConsUtilbc.UTF_8)))
            {
                return PubConsUtilbc.UTF_8;
            }
            if (str.equals(new String(bs, PubConsUtilbc.GBK)))
            {
                return PubConsUtilbc.GBK;
            }
            if (str.equals(new String(bs, PubConsUtilbc.ISO_8859_1)))
            {
                return PubConsUtilbc.ISO_8859_1;
            }
            if (str.equals(new String(str.getBytes(PubConsUtilbc.GB2312), PubConsUtilbc.GB2312)))
            {
                return PubConsUtilbc.GB2312;
            }
            if (str.equals(new String(str.getBytes(PubConsUtilbc.ISO_8859_1), PubConsUtilbc.ISO_8859_1)))
            {
                return PubConsUtilbc.ISO_8859_1;
            }
            if (str.equals(new String(str.getBytes(PubConsUtilbc.UTF_8), PubConsUtilbc.UTF_8)))
            {
                return PubConsUtilbc.UTF_8;
            }
            if (str.equals(new String(str.getBytes(PubConsUtilbc.GBK), PubConsUtilbc.GBK)))
            {
                return PubConsUtilbc.GBK;
            }
        }
        catch (UnsupportedEncodingException exception1)
        {
            exception1.printStackTrace();
        }
        return "";
    }
}
