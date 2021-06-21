package pers.bc.utils.cons;

import java.util.regex.Pattern;

/**
 * 一些常用的常量
 * @qualiFild com.pub.utils.cons.PubConsUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public interface PubConsUtilbc
{
    
    int BUFFER_SIZE = 4096;
    
    String XMLProcessor = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    
    String RN = "\r\n";
    /************************************************************************************************************/
    /**
     * Alphanumeric characters
     */
    String REG_ALNUM = "\\p{Alnum}";
    /**
     * Alphabetic characters
     */
    String REG_ALPHA = "\\p{Alpha}";
    /**
     * ASCII characters
     */
    String REG_ASCII = "\\p{ASCII}";
    /**
     * Space and tab
     */
    String REG_BLANK = "\\p{Blank}";
    /**
     * Control characters
     */
    String REG_CNTRL = "\\p{Cntrl}";
    /**
     * Digits
     */
    String REG_DIGITS = "\\p{Digit}";
    /**
     * SVisible characters (i.e. anything except spaces, control characters, etc.)
     */
    String REG_GRAPH = "\\p{Graph}";
    /**
     * Lowercase letters
     */
    String REG_LOWER = "\\p{Lower}";
    /**
     * Visible characters and spaces (i.e. anything except control characters, etc.)
     */
    String REG_PRINT = "\\p{Print}";
    /**
     * Punctuation and symbols.
     */
    String REG_PUNCT = "\\p{Punct}";
    /**
     * All whitespace characters, including line breaks
     */
    String REG_SPACE = "\\p{Space}";
    /**
     * Uppercase letters
     */
    String REG_UPPER = "\\p{Upper}";
    /**
     * Hexadecimal digits
     */
    String REG_XDIGIT = "\\p{XDigit}";
    /**
     * 空白行
     */
    String REG_SPACE_LINE = "\\n\\s*\\r";
    /**
     * 首尾空白字符
     */
    String REG_SPACE_POINT = "^\\s*|\\s*$";
    /**
     * HTML
     */
    String REG_HTML = "<(\\S*?)[^>]*>.*?</\\1>|<.*? />";
    /**
     * Email
     */
    String REG_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * 国内固定电话
     */
    String REG_FIXED_TELEPHONE = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
    /**
     * 邮政编码
     */
    String REG_POSTALCODE = "[1-9]\\d{5}(?!\\d)";
    /**
     * 身份证编码
     */
    String REG_IDENTIFICATION_CARD = "\\d{15}|\\d{18}";
    /**
     * URL地址
     */
    String REG_URL = "^http://([w-]+.)+[w-]+(/[w-./?%&=]*)?$";
    /**
     * 移动电话
     */
    String REG_MOBILE_TELEPHONE = "^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
    /**
     * 合法的名字（字母开头，允许5-16字节，允许字母数字下划线）
     */
    String REG_LEGAL_ACCOUNT = "^[a-zA-Z][a-zA-Z0-9_]{4,15}$";
    /**
     * i地址
     */
    String REG_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
    Pattern numericPattern = Pattern.compile("^[0-9\\-]+$");
    Pattern numericStringPattern = Pattern.compile("^[0-9\\-\\-]+$");
    Pattern floatNumericPattern = Pattern.compile("^[0-9\\-\\.]+$");
    Pattern abcPattern = Pattern.compile("^[a-z|A-Z]+$");
    
    /************************************************************************************************************/
    /**
     * ASCII表中可见字符从!开始，偏移位值为33(Decimal)
     */
    static final char DBC_CHAR_START = 33; // 半角!
    
    /**
     * ASCII表中可见字符到~结束，偏移位值为126(Decimal)
     */
    static final char DBC_CHAR_END = 126; // 半角~
    
    /**
     * 全角对应于ASCII表的可见字符从！开始，偏移值为65281
     */
    static final char SBC_CHAR_START = 65281; // 全角！
    
    /**
     * 全角对应于ASCII表的可见字符到～结束，偏移值为65374
     */
    static final char SBC_CHAR_END = 65374; // 全角～
    
    /**
     * ASCII表中除空格外的可见字符与对应的全角字符的相对偏移
     */
    static final int CONVERT_STEP = 65248; // 全角半角转换间隔
    
    /**
     * 全角空格的值，它没有遵从与ASCII的相对偏移，必须单独处理
     */
    static final char SBC_SPACE = 12288; // 全角空格 12288
    
    /**
     * 半角空格的值，在ASCII中为32(Decimal)
     */
    static final char DBC_SPACE = ' '; // 半角空格
    
    // //////////////////////////////////////////////
    // 检测SQL meta-characters的正则表达式 ：/(\%27)|(\’)|(\-\-)|(\%23)|(#)/ix
    String META_CHARACTERS = "/(\\%27)|(\\’)|(\\-\\-)|(\\%23)|(#)/ix";
    // 修正检测SQL meta-characters的正则表达式 ：/((\%3D)|(=))[^\n]*((\%27)|(\’)|(\-\-)|(\%3B)|(:))/i
    String META_CHARACTERS2 = "/((\\%3D)|(=))[^\\n]*((\\%27)|(\\’)|(\\-\\-)|(\\%3B)|(:))/i";
    // 典型的SQL 注入攻击的正则表达式 ：
    String SQL_CHARACTERS = "/\\w*((\\%27)|(\\’))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix";
    // 检测SQL注入，UNION查询关键字的正则表达式 ：
    String UNION = "/((\\%27)|(\\’))union/ix(\\%27)|(\\’)";
    // 检测MS SQL Server SQL注入攻击的正则表达式：
    String MSSQLServerSQL = "/exec(\\s\\+)+(s|x)p\\w+\\ix";
    
    /**
     * 日期时间类型格式
     */
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    String DATETIME_FORMAT2 = "yyyy/MM/dd HH:mm:ss";
    String DATETIME_FORMAT3 = "yyyy/MM/dd/HH/mm/ss";
    String DATETIME_FORMAT4 = "yyyyMMddHHmmss";
    
    /**
     * 日期类型格式
     */
    String DATE_FORMAT = "yyyy-MM-dd";
    
    /**
     * 时间类型的格式
     */
    String TIME_FORMAT = "HH:mm:ss";
    
    /**
     * 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块
     */
    String US_ASCII = "US-ASCII";
    
    /**
     * ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1
     */
    String ISO_8859_1 = "ISO-8859-1";
    
    /**
     * 8 位 UCS 转换格式
     */
    String UTF_8 = "UTF-8";
    
    /**
     * 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序
     */
    String UTF_16BE = "UTF-16BE";
    
    /**
     * 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序
     */
    String UTF_16LE = "UTF-16LE";
    
    /**
     * 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识
     */
    String UTF_16 = "UTF-16";
    
    /**
     * 中文超大字符集
     */
    String GBK = "GBK";
    
    /**
     * 最常见的中文字符集
     */
    String GB2312 = "GB2312";
    
    String AMPERSAND = "&";
    String AND = "and";
    String AT = "@";
    String ASTERISK = "*";
    String STAR = ASTERISK;
    String BACK_SLASH = "\\";
    String COLON = ":";
    String COMMA = ",";
    String DASH = "-";
    String DOLLAR = "$";
    String DOT = ".";
    String DOTDOT = "..";
    String DOT_CLASS = ".class";
    String DOT_JAVA = ".java";
    String EMPTY = "";
    String EQUALS = "=";
    String FALSE = "false";
    String SLASH = "/";
    String HASH = "#";
    String HAT = "^";
    String LEFT_BRACE = "{";
    String LEFT_BRACKET = "(";
    String LEFT_CHEV = "<";
    String NEWLINE = "\n";
    String N = "n";
    String NO = "no";
    String NULL = "null";
    String OFF = "off";
    String ON = "on";
    String PERCENT = "%";
    String PIPE = "|";
    String PLUS = "+";
    String QUESTION_MARK = "?";
    String EXCLAMATION_MARK = "!";
    String QUOTE = "\"";
    String RETURN = "\r";
    String TAB = "\t";
    String RIGHT_BRACE = "}";
    String RIGHT_BRACKET = ")";
    String RIGHT_CHEV = ">";
    String SEMICOLON = ";";
    String SINGLE_QUOTE = "'";
    String BACKTICK = "`";
    String SPACE = " ";
    String LEFT_SQ_BRACKET = "[";
    String RIGHT_SQ_BRACKET = "]";
    String TRUE = "true";
    String UNDERSCORE = "_";
    String Y = "y";
    String YES = "yes";
    String ONE = "1";
    String ZERO = "0";
    String DOLLAR_LEFT_BRACE = "${";
    String CRLF = "\r\n";
    
    String HTML_NBSP = "&nbsp;";
    String HTML_AMP = "&amp";
    String HTML_QUOTE = "&quot;";
    String HTML_LT = "&lt;";
    String HTML_GT = "&gt;";
    
    String HXWFH = "????????????◆◇?◎●??☉??????????????????????";
    // 常用的的标点符号
    String HXWFHS = ".。，、;：？!ˉˇ¨`~ 々～‖∶'`|·… — ～ - 〃‘’“”〝〞〔〕〈〉《》「」『』〖〗【】()[]{｝︻︼﹄﹃";
    
    // 常用的数学符号
    String MATH_SYMBOL = "+-×÷﹢﹣±/=∥∠≌∽≦≧≒﹤﹥≈≡≠=≤≥<>≮≯∷∶∫∮∝∞∧∨∑∏∪∩∈∵∴⊥∥∠⌒⊙√∟⊿㏒㏑%‰";
    
    // 计量符号
    String UNIT_SYMBOL = "㎎㎏㎜㎝㎞㎡㏄㏎㏑㏒㏕℡%‰℃℉°′″$￡￥￠♂♀℅";
    
    // 常用的数学符号
    String NUMBER_SYMBOL = "①②③④⑤⑥⑦⑧⑨⑩㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩№" + "⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇" + "ⅠⅡⅢⅣⅤⅥⅦⅧⅨⅩⅪⅫⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ";
    
    // 希腊符号
    String GREEK_SYMBOL = "ΓΔΛΞΠΣΦΨΩαβγδεζνξοπρσηθικλμτυφχψω";
    
    // 俄语
    String RUSSIAN_SYMBOL = "БВГДЁЖИЙКЛПФЦЧШЩЪЫЬЭЮЯ";
    
    // 中文符号
    String ZHCN_SYMBOL = "卍卐卄巜氺兀々〆のぁ〤〥";
    
    // 日语
    StringBuffer JAPANESE_SYMBOL = new StringBuffer("ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ")
        .append("ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをん ァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセゼソゾ")
        .append("タダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵヶ");
    
    // 常用的符号
    StringBuffer SYMBOL_ALL = new StringBuffer("、。·ˉˇ¨〃々—～‖…‘’“”〔〕〈 〉《》「」『』〖〗【】±+-×÷∧∨∑∏∪∩∈√⊥∥∠⌒⊙∫∮≡≌≈∽∝≠≮≯≤≥∞∶ ∵∴∷♂♀°′")
        .append("″℃$¤￠￡‰§№☆★〇○●◎◇◆ 回□■△▽⊿▲▼◣◤◢◥▁▂▃▄▅▆▇█▉▊▋▌▍▎▏▓※→←↑↓↖↗↘↙〓 ⅰⅱⅲⅳⅴⅵⅶⅷⅸⅹ①②③④⑤⑥⑦⑧⑨⑩")
        .append("⒈⒉⒊⒋ ⒌⒍⒎⒏⒐⒑⒒⒓⒔⒕⒖⒗⒘⒙⒚⒛⑴⑵⑶⑷⑸⑹⑺⑻⑼⑽⑾⑿⒀⒁⒂⒃⒄⒅⒆⒇㈠㈡㈢㈣㈤㈥㈦㈧㈨㈩ⅠⅡⅢⅣⅤⅥ")
        .append("ⅦⅧⅨⅩⅪⅫ!\"#￥%&'()*+，-./0123456789：;<=>？@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrs")
        .append("tuvwxyz{|｝ぁあぃいぅうぇえぉおかがきぎくぐけげこごさざしじすずせぜそぞただちぢっつづてでとどなにぬねのはばぱひびぴ")
        .append("ふぶぷへべぺほぼぽまみむめもゃやゅゆょよらりるれろゎわゐゑをんァアィイゥウェエォオカガキギクグケゲコゴサザシジスズセ")
        .append("ゼソゾタダチヂッツヅテデトドナニヌネノハバパヒビピフブプヘベペホボポマミムメモャヤュユョヨラリルレロヮワヰヱヲンヴヵ")
        .append("ヶΑΒΓΔΕΖΗΘΙΚΛΜΝΞΟΠΡΣΤΥΦΧΨΩαβγδεζηθ ικλμνξοπρστυφχψ ω︵︶︹︺︿﹀︽︾﹁﹂﹃﹄︻︼︷︸АБВГДЕЁЖЗИЙКЛМНОПРСТУ")
        .append("ФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыь эюāáǎàēéěèī íǐìōóǒòūúǔùǖǘǚǜüêɑ\uE7C7ńň\uE7C8ɡㄅㄆㄇㄈㄉㄊㄋㄌ")
        .append("ㄍㄎㄏㄐㄑㄒㄓㄔㄕㄖㄗㄘㄙㄚㄛㄜㄝㄞㄟㄠㄡㄢㄣㄤㄥㄦㄧㄨㄩ︱\uE796︳︴﹏﹋﹌─━│┃┄┅┆ ┇┈┉┊┋┌┍┎┏┐┑┒┓└")
        .append("┕┖┗┘┙┚┛├┝┞┟┠┡┢┣┤┥┦┧┨┩┪┫┬┭┮┯┰┱┲┳┴┵┶┷┸┹┺┻┼┽┾┿╀╁╂╃╄ ╅╆╇╈╉╊╋？㊣㈱曱甴")
        .append("囍∟┅﹊﹍╭ ╮╰ ╯ \uE83A_\uE83A ^︵^﹕﹗/\\ \" < > `,·。{}~～() -√ $ @ * & # 卐℡ ぁ〝〞ミ灬№*\uE7E7\uE7F3ㄨ")
        .append("≮≯ ﹢﹣/∝≌∽≦≧≒﹤﹥じぷ┗┛￥￡§я-―‥…‰′″℅℉№℡∕∝∣═║╒╓╔╕╖╗╘╙╚╛╜╝╞╟╠╡╢╣╤╥╦╧╨╩╪╫╬╱ ╲╳▔▕")
        .append("〆〒〡〢〣〤〥〦〧〨〩㎎ ㎏ ㎜ ㎝ ㎞ ㎡ ㏄ ㏎㏑㏒㏕\uE7C7\uE7C8\uE7E7\uE7E8\uE7E9\uE7EA\uE7EB\uE7EC\uE7ED\uE7EE")
        .append("\uE7EF\uE7F0\uE7F1\uE7F2\uE7F3兀︰﹍﹎ ------");
    
    // 必选包含数字、大写字母、小写字母、特殊字符，长度在8到15位
    String SEC_PASSWORD = "^(?=.*?[0-9])(?=.*?[a-z])(?=.*?[A-Z])(?=.*?[@!#$%^&*()_+\\.\\-\\?<>'\"|=]+).{8,15}$";
}
