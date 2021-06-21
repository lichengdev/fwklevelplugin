package pers.bc.utils.pub;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import pers.bc.utils.Bean.BeanHelper;

/**
 * 
 * @qualiFild nc.pub.itf.tools.pub.StringUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public final class StringUtilbc extends StringUtil
{
    
    /**
     * *********************************************************** <br>
     * 说明：根据身份证获取男女 <br>
     * @see <br>
     * @param ID
     * @return <br>
     * @Integer <br>
     * @methods pers.bc.utils.pub.StringUtilbc#getSex <br>
     * @author licheng <br>
     * @date Created on 2020-4-10 <br>
     * @time 下午3:20:11 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Integer getSex(String ID)
    {
        if (ID.length() != 15 && ID.length() != 18)
        {
            return null;
        }
        int isex = 2;
        isex = ID.length() == 15 ? Integer.parseInt(ID.substring(14)) : Integer.parseInt(ID.substring(16, 17));
        return isex % 2 == 0 ? 2 : 1;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 获取xml格式片段<br>
     * @see <br>
     * @param key
     * @param value
     * @return { key> value /key> }<br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#getXMLTypeStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-13 <br>
     * @time 下午12:58:46 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getXMLTypeStr(String key, String value)
    {
        // return "<" + key + ">" + value + "</" + key + ">";
        // return LEFT_CHEV + key + RIGHT_CHEV + value + LEFT_CHEV + SLASH + key + RIGHT_CHEV;
        return new StringBuffer().append(LEFT_CHEV).append(key).append(RIGHT_CHEV).append(value).append(LEFT_CHEV).append(SLASH).append(key)
            .append(RIGHT_CHEV).toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：获取JSON格式片段 <br>
     * @see <br>
     * @param key
     * @param value
     * @return "key":"value" <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#getJSONTypeStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-13 <br>
     * @time 下午1:01:27 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getJSONTypeStr(String key, String value)
    {
        // return "\"" + key + "\":\"" + value + "\"";
        // QUOTE + key + QUOTE + COLON + QUOTE + value + QUOTE;
        return new StringBuffer().append(QUOTE).append(key).append(QUOTE).append(COLON).append(QUOTE).append(value).append(QUOTE)
            .toString();
        
    }
    
    public static String getJSONSingleStr(String key, String value)
    {
        return new StringBuffer().append(LEFT_BRACE).append(getJSONTypeStr(key, value)).append(RIGHT_BRACE).toString();
        
    }
    
    /**
     * *********************************************************** <br>
     * 说明：根据身份证获取生日 <br>
     * @see <br>
     * @param ID
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#getBirthdate <br>
     * @author licheng <br>
     * @date Created on 2020-4-10 <br>
     * @time 下午3:20:43 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String getBirthdate(String ID)
    {
        if (ID.length() != 15 && ID.length() != 18)
        {
            // 不是15位或18位返回null
            return null;
        }
        String birth = ID.length() == 15 ? "19" + ID.substring(6, 12) : ID.substring(6, 14);
        String year = birth.substring(0, 4);
        String month = birth.substring(4, 6);
        String date = birth.substring(6);
        return year + "-" + month + "-" + date;
    }
    
    static final char[] digits = {
        '0',
        '1',
        '2',
        '3',
        '4',
        '5',
        '6',
        '7',
        '8',
        '9',
        'a',
        'b',
        'c',
        'd',
        'e',
        'f',
        'g',
        'h',
        'i',
        'j',
        'k',
        'l',
        'm',
        'n',
        'o',
        'p',
        'q',
        'r',
        's',
        't',
        'u',
        'v',
        'w',
        'x',
        'y',
        'z'};
    
    public StringUtilbc()
    {
    }
    
    /**
     * *********************************************************** <br>
     * 说明：首字母小写 <br>
     * @see <br>
     * @param function 名称
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#firstCharLowerCase <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:02:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String firstCharLowerCase(String function)
    {
        if (function.length() >= 1) function = function.substring(0, 1).toLowerCase() + function.substring(1);
        return function;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 首字母大写<br>
     * @see <br>
     * @param function 名称
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#firstCharUpperCase <br>
     * @author licheng <br>
     * @date Created on 2020年3月24日 <br>
     * @time 下午8:03:03 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String firstCharUpperCase(String function)
    {
        if (function.length() >= 1) function = function.substring(0, 1).toUpperCase() + function.substring(1);
        return function;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param sbStr
     * @param i
     * @return <br>
     * @StringBuilder <br>
     * @methods pers.bc.utils.pub.CollectionUtil#deleteStr <br>
     * @author LiBencheng <br>
     * @date Created on 2020-10-17 <br>
     * @time 上午11:03:45 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static StringBuilder deleteStr(StringBuilder sbStr, int i)
    {
        return sbStr.delete(sbStr.length() - i, sbStr.length());
    }
    
    public static String to32Signed(int i)
    {
        char[] buf = new char[32];
        int charPos = 32;
        int radix = 32;
        int mask = radix - 1;
        do
        {
            buf[(--charPos)] = digits[(i & mask)];
            i >>>= 5;
        }
        while (i != 0);
        
        String s = new String(buf, charPos, 32 - charPos);
        return s.toUpperCase();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#toString <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-10 <br>
     * @time 下午5:05:52 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String toString(Object obj)
    {
        String msg = null;
        
        try
        {
            msg = toString(obj, Boolean.FALSE);
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("publogs").exception("toString(Object obj)Thw", e);
        }
        
        return msg;
    }
    
    public static String toString(Object obj, Boolean isPrintBean) throws Exception
    {
        
        if (obj == null)
        {
            return "null";
        }
        if ((obj instanceof String))
        {
            return (String) obj;
        }
        
        if (obj.getClass().isArray())
        {
            return toString((Object[]) obj);
        }
        
        if ((obj instanceof Map))
        {
            return toString((Map<?, ?>) obj);
        }
        
        if ((obj instanceof Collection))
        {
            return toString((Collection<?>) obj);
        }
        
        if ((obj instanceof Throwable))
        {
            return toString((Throwable) obj);
        }
        
        if ((obj instanceof Thread))
        {
            return toString(((Thread) obj).getStackTrace());
        }
        if (isPrintBean && BeanHelper.isBeanClass(obj.getClass()))
        {
            return toBeanString(obj);
        }
        
        return obj.toString();
    }
    
    public static String toBeanString(Object obj) throws Exception
    {
        if (obj == null)
        {
            return NULL;
        }
        if (BeanHelper.isBeanClass(obj.getClass()))
        {
            Map<String, Object> beanMap = BeanHelper.getBeanInfo(obj);
            beanMap.putAll(BeanHelper.transBean2Map(obj));
            
            return toString(CollectionUtil.map2JosnStr(beanMap));
        }
        return obj.toString();
    }
    
    public static String toString(Object[] objs) throws Exception
    {
        if (PubEnvUtilbc.getSize(objs) < 1) return EMPTY;
        
        StringBuilder sb = new StringBuilder(LEFT_SQ_BRACKET);
        for (Object obj : objs)
        {
            sb.append(toString(obj)).append(COMMA).append(RN);
        }
        
        return deleteStr(sb, 3).append(RIGHT_SQ_BRACKET).toString();
    }
    
    public static String toString(Map<?, ?> map) throws Exception
    {
        if (PubEnvUtilbc.getSize(map) < 1) return "";
        StringBuilder sb = new StringBuilder(LEFT_BRACE);
        for (Object key : map.keySet())
        {
            Object value = map.get(key);
            sb.append(toString(key)).append(EQUALS).append(toString(value)).append(COMMA).append(RN);
        }
        
        return deleteStr(sb, 3).append(RIGHT_BRACE).toString();
    }
    
    public static String toString(Collection<?> list) throws Exception
    {
        if (PubEnvUtilbc.getSize(list) < 1) return "";
        
        StringBuilder sb = new StringBuilder(LEFT_SQ_BRACKET);
        for (Object e : list)
        {
            sb.append(toString(e)).append(COMMA).append(RN);
        }
        
        return deleteStr(sb, 3).append(RIGHT_SQ_BRACKET).toString();
    }
    
    public static String toString(Throwable e)
    {
        if (e == null)
        {
            return "";
        }
        StringBuilder log = new StringBuilder();
        int count = 0;
        while ((e != null) && (count < 50))
        {
            count++;
            log.append(e.toString());
            StackTraceElement[] trace = e.getStackTrace();
            for (int i = 0; i < trace.length; i++)
            {
                log.append("\r\n\tat " + trace[i]);
            }
            e = e.getCause();
        }
        return log.toString();
    }
    
    public static String subString(String input, int len)
    {
        if ((input == null) || (input.length() * 2 < len))
        {
            return input;
        }
        StringBuffer sb = new StringBuffer(len);
        int count = 0;
        for (int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if (c <= '?')
            {
                count++;
            }
            else
            {
                count += 2;
            }
            if (count > len)
            {
                return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String arr2Str(Object[] objs, String regex)
    {
        if ((objs == null) || (objs.length < 1))
        {
            return null;
        }
        StringBuffer bf = new StringBuffer();
        for (Object obj : objs)
        {
            bf.append(regex).append(obj);
        }
        return bf.substring(regex.length());
    }
    
    public static String stringTrimRight(String strValue)
    {
        if (strValue == null)
        {
            return strValue;
        }
        int count = strValue.length();
        int len = strValue.length();
        int off = 0;
        char[] val = strValue.toCharArray();
        
        while (val[(off + len - 1)] <= ' ')
        {
            len--;
        }
        return len < count ? strValue.substring(0, len) : strValue;
    }
    
    public static final String replaceAll(String tmpl, String src, String desc)
    {
        StringBuffer buf = new StringBuffer();
        int index = tmpl.indexOf(src);
        int len = src.length();
        while (index >= 0)
        {
            buf.append(tmpl.substring(0, index));
            buf.append(desc);
            tmpl = tmpl.substring(index + len);
            index = tmpl.indexOf(src);
        }
        buf.append(tmpl);
        return buf.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：去掉字符串中的常用符号 <br>
     * @see <br>
     * @param str
     * @param newChar
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#replaceCharSpecial <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-17 <br>
     * @time 下午3:48:28 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String replaceCharSpecial(String str, CharSequence newChar)
    {
        List<String> specials = new ArrayList<String>();
        
        char[] charArray = (HXWFHS + MATH_SYMBOL).toCharArray();
        for (int i = 0, j = charArray.length; i < j; i++)
        {
            specials.add(String.valueOf(charArray[i]));
        }
        
        specials.add("（");
        specials.add("）");
        specials.add(LEFT_BRACKET);
        specials.add(RIGHT_BRACKET);
        specials.add(CRLF);
        specials.add(TAB);
        specials.add(RETURN);
        specials.add(NEWLINE);
        specials.add(HTML_NBSP);
        specials.add(HTML_AMP);
        specials.add(HTML_QUOTE);
        specials.add(HTML_LT);
        specials.add(HTML_GT);
        
        String newStr = replaceChar(str, specials.toArray(new String[0]), newChar);
        
        return newStr;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：把字符串中去掉将指定的字符 <br>
     * @see <br>
     * @param str
     * @param oldChars
     * @param newChar
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#replaceChar <br>
     * @author LiBencheng <br>
     * @date Created on 2020-12-17 <br>
     * @time 下午3:48:54 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String replaceChar(String str, CharSequence[] oldChars, CharSequence newChar)
    {
        String newStr = str;
        for (int i = 16, j = PubEnvUtilbc.getEncryptObjSize(oldChars); i < j; i++)
        {
            newStr = newStr.replace(oldChars[PubEnvUtilbc.getDecryptObjSize(i)], newChar);
            // str.replaceAll(StringUtilbc.valueOf(oldChars[PubEnvUtilbc.getDecryptObjSize(i)]),
            // StringUtilbc.valueOf(newChar));
        }
        
        return newStr;
    }
    
    public static String replace(String text, String repl, String with)
    {
        return replace(text, repl, with, -1);
    }
    
    public static String replace(String text, String repl, String with, int max)
    {
        if ((text == null) || (isEmpty(repl)) || (with == null) || (max == 0))
        {
            return text;
        }
        
        StringBuffer buf = new StringBuffer(text.length());
        int start = 0;
        int end = 0;
        for (; (end = text.indexOf(repl, start)) != -1;)
        {
            buf.append(text.substring(start, end)).append(with);
            start = end + repl.length();
            
            max--;
        }
        
        buf.append(text.substring(start));
        return buf.toString();
    }
    
    public static final String innerText(String html)
    {
        if (((html) == null) || ("".equals((html))))
        {
            return html;
        }
        String value = html;
        int nEnd = 0;
        int nStart;
        while ((nStart = value.indexOf("<")) >= 0)
        {
            nEnd = value.indexOf(">", nStart);
            if (nEnd <= nStart) break;
            value = value.substring(0, nStart) + value.substring(nEnd + 1);
        }
        
        return value;
    }
    
    public static String capitalize(String str)
    {
        int strLen;
        
        if ((str == null) || ((strLen = str.length()) == 0)) return str;
        return
        
        strLen + Character.toTitleCase(str.charAt(0)) + str.substring(1);
    }
    
    public static String[] addStringToArray(String[] array, String str)
    {
        if (array == null)
        {
            return new String[]{str};
        }
        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }
    
    public static String[] extractArrayFromIn(String in)
    {
        if ((in == null) || (in.trim().length() == 0))
        {
            return new String[0];
        }
        if (in.startsWith("(")) in = in.substring(1, in.length());
        if (in.endsWith(")"))
        {
            in = in.substring(0, in.length() - 1);
        }
        String[] result = in.split(",");
        ArrayList<String> results = new ArrayList<String>();
        for (String s : result)
        {
            s = s.trim();
            if (s.startsWith("'")) s = s.substring(1, s.length());
            if (s.endsWith("'")) s = s.substring(0, s.length() - 1);
            if (s.startsWith("\"")) s = s.substring(1, s.length());
            if (s.endsWith("\"")) s = s.substring(0, s.length() - 1);
            
            results.add(s);
        }
        return (String[]) results.toArray(new String[0]);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param sttrs
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StringUtilbc#convert2String <br>
     * @author licheng <br>
     * @date Created on 2019-8-12 <br>
     * @time 上午11:05:54 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String convert2String(List<String> sttrs)
    {
        StringBuffer newStr = new StringBuffer();
        int len = sttrs.size();
        for (int i = 0; i < len; i++)
        {
            newStr.append((String) sttrs.get(i));
            if (i != len - 1)
            {
                newStr.append(",");
            }
        }
        
        return newStr.toString();
    }
    
    public static String convert2String(List<String> sttrs, boolean bChar)
    {
        StringBuffer newStr = new StringBuffer();
        int len = sttrs.size();
        for (int i = 0; i < len; i++)
        {
            if (bChar)
            {
                newStr.append("'");
            }
            newStr.append((String) sttrs.get(i));
            if (bChar)
            {
                newStr.append("'");
            }
            if (i != len - 1)
            {
                newStr.append(",");
            }
        }
        
        return newStr.toString();
    }
    
    public static boolean containsChinese(String str)
    {
        if (StringUtil.isEmpty(str))
        {
            return false;
        }
        for (int i = 0; i < str.length(); i++)
        {
            String c = str.charAt(i) + "";
            if (c.matches("[\\u4E00-\\u9FA5]+")) return true;
        }
        return false;
    }
    
    public static boolean isMultiLangResID(String resId)
    {
        if ((resId == null) || (resId.length() < 6))
        {
            return false;
        }
        
        if ((resId.contains("@")) && (!resId.startsWith("@")) && (!resId.endsWith("@")))
        {
            return true;
        }
        
        if (containsChinese(resId))
        {
            return false;
        }
        
        return true;
    }
    
    public static String getFuncCodeFromResID(String resId)
    {
        if ((resId == null) || (resId.length() < 6))
        {
            return null;
        }
        
        if ((resId.contains("@")) && (!resId.startsWith("@")) && (!resId.endsWith("@")))
        {
            return resId.split("@")[1];
        }
        
        int endIndex = 1;
        for (int i = resId.length() - 1; i > 1; i--)
        {
            char c = resId.charAt(i);
            if (c == '-')
            {
                endIndex = i - 1;
                break;
            }
        }
        
        if (endIndex < 2)
        {
            endIndex = resId.length() - 5;
        }
        String funcCode = resId.substring(1, endIndex + 1);
        return funcCode;
    }
    
    /**
     * Returns the string representation of the <code>Object</code> argument.
     * 
     * @param obj an <code>Object</code>.
     * @return if the argument is <code>null</code>, then a string equal to <code>"null"</code>; otherwise,
     *         the value of <code>obj.toString()</code> is returned.
     * @see Object#toString()
     */
    public static String valueOf(Object obj)
    {
        return (null == obj) ? null : obj.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明：为空时默认为0 <br>
     * @see <br>
     * @param obj
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#valueOfZero <br>
     * @author LiBencheng <br>
     * @date Created on 2020-11-7 <br>
     * @time 下午11:39:00 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String valueOfZero(Object obj)
    {
        return valueOfDefault(obj, "0");
    }
    
    public static String valueOfDefault(Object obj, String defValue)
    {
        return (valueOfEmpty(obj).length() < 1) ? defValue : obj.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StringUtilbc#valueOfNull <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午5:43:33 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String valueOfNull(Object obj)
    {
        return String.valueOf(obj);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param obj
     * @return <br>
     * @String <br>
     * @methods nc.pub.itf.tools.pub.StringUtilbc#valueOfEmpty <br>
     * @author licheng <br>
     * @date Created on 2019-9-24 <br>
     * @time 下午5:43:37 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String valueOfEmpty(Object obj)
    {
        return (null == obj) ? "" : obj.toString();
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param str
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.StringUtilbc#deleteWhitespace <br>
     * @author licheng <br>
     * @date Created on 2019-12-19 <br>
     * @time 下午2:36:24 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static String deleteWhitespace(String str)
    {
        if (isEmpty(str))
        {
            return str;
        }
        int sz = str.length();
        char[] chs = new char[sz];
        int count = 0;
        for (int i = 0; i < sz; i++)
        {
            if (!Character.isWhitespace(str.charAt(i)))
            {
                chs[count++] = str.charAt(i);
            }
        }
        if (count == sz)
        {
            return str;
        }
        return new String(chs, 0, count);
    }
    
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
            if (str.equals(new String(bs, UTF_8)))
            {
                return UTF_8;
            }
            if (str.equals(new String(bs, GBK)))
            {
                return GBK;
            }
            if (str.equals(new String(bs, ISO_8859_1)))
            {
                return ISO_8859_1;
            }
            if (str.equals(new String(str.getBytes(GB2312), GB2312)))
            {
                return GB2312;
            }
            if (str.equals(new String(str.getBytes(ISO_8859_1), ISO_8859_1)))
            {
                return ISO_8859_1;
            }
            if (str.equals(new String(str.getBytes(UTF_8), UTF_8)))
            {
                return UTF_8;
            }
            if (str.equals(new String(str.getBytes(GBK), GBK)))
            {
                return GBK;
            }
        }
        catch (UnsupportedEncodingException exception1)
        {
            exception1.printStackTrace();
        }
        return "";
    }
    
    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarityRatioUnPunct(String str1, String str2)
    {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length())
        {
            return SimilarityRatio(str1, str2);
        }
        else
        {
            return SimilarityRatio(str2, str1);
        }
        
    }
    
    /**
     * 字符串相似度比较(速度较快)
     */
    public static double SimilarDegreeUnPunct(String str1, String str2)
    {
        str1 = StringUtil.trimPunct(str1);
        str2 = StringUtil.trimPunct(str2);
        if (str1.length() > str2.length())
        {
            return SimilarDegree(str1, str2);
        }
        else
        {
            return SimilarDegree(str2, str1);
        }
    }
}
