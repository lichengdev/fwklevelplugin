package pers.bc.utils.cons;

/**
 * Java常用工具类之RegexCons，正则表达式工具类
 * @qualiFild pers.bc.utils.cons.RegexCons.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public interface RegexCons
{
    
    /**
     * 匹配图象
     * 
     * 
     * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
     * 
     * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp
     * 
     * 
     * 不匹配: c:/admins4512.gif
     * 
     */
    String ICON_REGEXP = "^(/{0,1}//w){1,}//.(gif|dmp|png|jpg)$|^//w{1,}//.(gif|dmp|png|jpg)$";
    
    /**
     * 匹配email地址
     * 
     * 
     * 格式: XXX@XXX.XXX.XX
     * 
     * 匹配 : foo@bar.com 或 foobar@foobar.com.au
     * 
     * 不匹配: foo@bar 或 $$$@bar.com
     * 
     */
    String EMAIL_REGEXP = "(?://w[-._//w]*//w@//w[-._//w]*//w//.//w{2,3}$)";
    
    /**
     * 匹配匹配并提取url
     * 
     * 
     * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
     * 
     * 匹配 : http://www.suncer.com 或news://www
     * 
     * 不匹配: c:/window
     * 
     */
    String URL_REGEXP = "(//w+)://([^/:]+)(://d*)?([^#//s]*)";
    
    /**
     * 匹配并提取http
     * 
     * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或 https://XXX
     * 
     * 匹配 : http://www.suncer.com:8080/index.html?login=true
     * 
     * 不匹配: news://www
     * 
     */
    String HTTP_REGEXP = "(http|https|ftp)://([^/:]+)(://d*)?([^#//s]*)";
    
    /**
     * 匹配日期
     * 
     * 
     * 格式(首位不为0): XXXX-XX-XX或 XXXX-X-X
     * 
     * 
     * 范围:1900--2099
     * 
     * 
     * 匹配 : 2005-04-04
     * 
     * 
     * 不匹配: 01-01-01
     * 
     */
    String DATE_BARS_REGEXP = "^((((19){1}|(20){1})\\d{2})|\\d{2})-[0,1]?\\d{1}-[0-3]?\\d{1}$";
    
    /**
     * 匹配日期
     * 
     * 
     * 格式: XXXX/XX/XX
     * 
     * 
     * 范围:
     * 
     * 
     * 匹配 : 2005/04/04
     * 
     * 
     * 不匹配: 01/01/01
     * 
     */
    String DATE_SLASH_REGEXP =
        "^[0-9]{4}/(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))$";
    
    /**
     * 匹配电话
     * 
     * 
     * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或
     * 
     * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或 XXXXXXXXXXX(11位首位不为0)
     * 
     * 
     * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或 010-12345678 或 12345678912
     * 
     * 
     * 不匹配: 1111-134355 或 0123456789
     * 
     */
    String PHONE_REGEXP = "^(?:0[0-9]{2,3}[-//s]{1}|//(0[0-9]{2,4}//))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";
    
    /**
     * 匹配身份证
     * 
     * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或 XXXXXXXXXXXXXXXXXX(18位)
     * 
     * 匹配 : 0123456789123
     * 
     * 不匹配: 0123456
     * 
     */
    String ID_CARD_REGEXP = "^//d{10}|//d{13}|//d{15}|//d{18}$";
    
    /**
     * 匹配邮编代码
     * 
     * 格式为: XXXXXX(6位)
     * 
     * 匹配 : 012345
     * 
     * 不匹配: 0123456
     * 
     */
    String ZIP_REGEXP = "^[0-9]{6}$";// 匹配邮编代码
    
    /**
     * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠/ 即空格,制表符,回车符等 )
     * 
     * 格式为: x 或 一个一上的字符
     * 
     * 匹配 : 012345
     * 
     * 不匹配: 0123456 // ;,:-<>//s].+$";//
     */
    String NON_SPECIAL_CHAR_REGEXP = "^[^'/";
    // 匹配邮编代码
    
    /**
     * 匹配非负整数（正整数 + 0)
     */
    String NON_NEGATIVE_INTEGERS_REGEXP = "^//d+$";
    
    /**
     * 匹配不包括零的非负整数（正整数 > 0)
     */
    String NON_ZERO_NEGATIVE_INTEGERS_REGEXP = "^[1-9]+//d*$";
    
    /**
     * 
     * 匹配正整数
     * 
     */
    String POSITIVE_INTEGER_REGEXP = "^[0-9]*[1-9][0-9]*$";
    
    /**
     * 
     * 匹配非正整数（负整数 + 0）
     * 
     */
    String NON_POSITIVE_INTEGERS_REGEXP = "^((-//d+)|(0+))$";
    
    /**
     * 
     * 匹配负整数
     * 
     */
    String NEGATIVE_INTEGERS_REGEXP = "^-[0-9]*[1-9][0-9]*$";
    
    /**
     * 
     * 匹配整数
     * 
     */
    String INTEGER_REGEXP = "^-?//d+$";
    
    /**
     * 
     * 匹配非负浮点数（正浮点数 + 0）
     * 
     */
    String NON_NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^//d+(//.//d+)?$";
    
    /**
     * 
     * 匹配正浮点数
     * 
     */
    String POSITIVE_RATIONAL_NUMBERS_REGEXP = "^(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*))$";
    
    /**
     * 
     * 匹配非正浮点数（负浮点数 + 0）
     * 
     */
    String NON_POSITIVE_RATIONAL_NUMBERS_REGEXP = "^((-//d+(//.//d+)?)|(0+(//.0+)?))$";
    
    /**
     * 
     * 匹配负浮点数
     * 
     */
    String NEGATIVE_RATIONAL_NUMBERS_REGEXP = "^(-(([0-9]+//.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*//.[0-9]+)|([0-9]*[1-9][0-9]*)))$";
    
    /**
     * 
     * 匹配浮点数
     * 
     */
    String RATIONAL_NUMBERS_REGEXP = "^(-?//d+)(//.//d+)?$";
    
    /**
     * 
     * 匹配由26个英文字母组成的字符串
     * 
     */
    String LETTER_REGEXP = "^[A-Za-z]+$";
    
    /**
     * 
     * 匹配由26个英文字母的大写组成的字符串
     * 
     */
    String UPWARD_LETTER_REGEXP = "^[A-Z]+$";
    
    /**
     * 
     * 匹配由26个英文字母的小写组成的字符串
     * 
     */
    String LOWER_LETTER_REGEXP = "^[a-z]+$";
    
    /**
     * 
     * 匹配由数字和26个英文字母组成的字符串
     * 
     */
    String LETTER_NUMBER_REGEXP = "^[A-Za-z0-9]+$";
    
    /**
     * 
     * 匹配由数字、26个英文字母或者下划线组成的字符串
     * 
     */
    String LETTER_NUMBER_UNDERLINE_REGEXP = "^//w+$";
    
}
