package pers.bc.utils.pub;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DateToUpperChinese
 ** 
 * @qualiFild nc.impl.hi.psndoc.NCC_Impl_HRWaPubService.java<br>
 * @author：LiBencheng<br>
 * @date Created on 2021-3-16<br>
 * @version 1.0<br>
 */
public class DateToUpperChinese
{
    
    private static final String[] MONEYNUMBERS = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖", "拾"};
    private static final String[] CHINESENUMS = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十"};
    
    /**
     * *********************************************************** <br>
     * *说明：通过 yyyy-MM-dd 得到中文大写格式 yyyy MM dd 日期 <br>
     * @see <br>
     * @param str
     * @return <br>
     * @String <br>
     * @methods nc.impl.hi.psndoc.DateToUpperChinese#toChinese <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-16 <br>
     * @time 10:27:44 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String toChinese(String str, Boolean isChineseMoneyNum)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(getSplitDateStr(str, 0, isChineseMoneyNum)).append(" ").append(getSplitDateStr(str, 1, isChineseMoneyNum)).append(" ")
            .append(getSplitDateStr(str, 2, isChineseMoneyNum));
        return sb.toString();
    }
    
    /**
     * *********************************************************** <br>
     * *说明： 分别得到年月日的大写 默认分割符 "-"<br>
     * @see <br>
     * @param str
     * @param unit
     * @return <br>
     * @String <br>
     * @methods nc.impl.hi.psndoc.DateToUpperChinese#getSplitDateStr <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-16 <br>
     * @time 10:27:37 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static String getSplitDateStr(String str, int unit, Boolean isChineseMoneyNum)
    {
        // unit是单位 0=年 1=月 2日
        String[] DateStr = str.split("-");
        if (PubEnvUtilbc.getSize(DateStr) < 2) DateStr = str.split("/");
        
        if (unit > DateStr.length) unit = 0;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < DateStr[unit].length(); i++)
        {
            
            if ((unit == 1 || unit == 2) && Integer.valueOf(DateStr[unit]) > 9)
            {
                if (PubEnvUtilbc.equals(DateStr[unit], "10"))
                {
                    sb.append(convertChineseNum("10", isChineseMoneyNum));
                }
                else if (!PubEnvUtilbc.equals(DateStr[unit], "10")//
                    && (DateStr[unit].length() == 2 && DateStr[unit].startsWith("1")))
                {
                    sb.append(convertChineseNum("10", isChineseMoneyNum)).append(
                        convertChineseNum(DateStr[unit].substring(1), isChineseMoneyNum));
                }
                else
                    sb.append(convertChineseNum(DateStr[unit].substring(0, 1), isChineseMoneyNum))
                        .append(convertChineseNum("10", isChineseMoneyNum))
                        .append(convertChineseNum(DateStr[unit].substring(1, 2), isChineseMoneyNum));
                break;
            }
            else
            {
                sb.append(convertChineseNum(DateStr[unit].substring(i, i + 1), isChineseMoneyNum));
            }
        }
        if (unit == 1 || unit == 2)
        {
            return sb.toString().replaceAll("^壹", "").replace("零", "");
        }
        return sb.toString();
        
    }
    
    /**
     * *********************************************************** <br>
     * *说明：转换数字为大写 <br>
     * @see <br>
     * @param str
     * @return <br>
     * @String <br>
     * @methods nc.impl.hi.psndoc.DateToUpperChinese#convertNum <br>
     * @author LiBencheng <br>
     * @date Created on 2021-3-16 <br>
     * @time 10:27:56 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    private static String convertChineseNum(String str, Boolean isChineseMoneyNum)
    {
        return isChineseMoneyNum ? MONEYNUMBERS[Integer.valueOf(str)] : CHINESENUMS[Integer.valueOf(str)];
    }
    
}
