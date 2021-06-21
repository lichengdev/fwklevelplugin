package pers.bc.utils.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 不需要导入外部包，自行编写的json字符串转map工具类
 * @qualiFild pers.bc.utils.pub.JsonUtilbc.java<br>
 * @author：licheng<br>
 * @date Created on 2020年4月4日<br>
 * @version 1.0<br>
 */
public class JsonUtilbc
{
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param ori
     * @return <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.pub.JsonUtilbc#toMap <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午8:01:29 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Map<String, Object> toMap(String ori)
    {
        ori = removeBlack(ori);
        return toMapHelp(ori);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 必须首尾是{} <br>
     * @see <br>
     * @param ori
     * @return <br>
     * @Map<String,Object> <br>
     * @methods pers.bc.utils.pub.JsonUtilbc#toMapHelp <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午8:01:19 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static Map<String, Object> toMapHelp(String ori)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] manyMaps = splitToSingeMap(ori);
        for (String e : manyMaps)
        {
            int indexOfF = 0;
            int shuangyh = 0;
            for (indexOfF = 0; indexOfF < e.length(); indexOfF++)
            {
                if (e.charAt(indexOfF) == '\"' && shuangyh == 0)
                {
                    shuangyh++;
                    continue;
                }
                if (e.charAt(indexOfF) == '\"' && shuangyh == 1)
                {
                    shuangyh--;
                    continue;
                }
                if (shuangyh == 0 && e.charAt(indexOfF) == ':')
                {
                    break;
                }
            }
            if (indexOfF < e.length() - 1)
            {
                String fieldName = e.substring(0, indexOfF);
                String value = e.substring(indexOfF + 1, e.length());
                if (fieldName.charAt(0) == '\"')
                {// 字符串需要截取
                    fieldName = fieldName.substring(1, fieldName.length() - 1);
                }
                if (value.charAt(0) == '{')
                {// 子map元素
                    map.put(fieldName, toMapHelp(value));
                }
                else if (value.charAt(0) == '[')
                {// 子list元素
                    map.put(fieldName, toList(value));
                }
                else if (value.charAt(0) == '\"')
                {// 字符串元素
                    map.put(fieldName, value.substring(1, value.length() - 1));
                }
                else
                {// 其他元素
                    map.put(fieldName, value);
                }
            }
        }
        return map;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 必须首尾是[] <br>
     * @see <br>
     * @param ori
     * @return <br>
     * @List<Object> <br>
     * @methods pers.bc.utils.pub.JsonUtilbc#toList <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午8:00:38 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static List<Object> toList(String ori)
    {
        List<Object> list = new ArrayList<Object>();
        String[] manyMaps = splitToSingeMap(ori);
        for (String e : manyMaps)
        {
            list.add(toMapHelp(e));
        }
        return list;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 必须首尾是{}或者[]<br>
     * @see <br>
     * @param ori
     * @return <br>
     * @String[] <br>
     * @methods pers.bc.utils.pub.JsonUtilbc#splitToSingeMap <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午8:00:30 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static String[] splitToSingeMap(String ori)
    {
        if (ori == null)
        {
            return null;
        }
        int lastIndex = 1;// 从第二个，第一个是{
        int lastLeftC = 0;// 左边的{数量，起始是0
        int lastLeftR = 0;// 左边的[数量，起始是0
        int splotLength = 0;// map个数
        int shuangyhao = 0;// 考虑{}有可能在""里面
        for (int index = 0; index < ori.length(); index++)
        {
            if (ori.charAt(index) == '\"' && shuangyhao == 0)
            {
                shuangyhao++;
                continue;
            }
            if (ori.charAt(index) == '\"' && shuangyhao == 1)
            {
                shuangyhao--;
                continue;
            }
            if (shuangyhao == 1)
            {
                continue;
            }
            if (ori.charAt(index) == '{')
            {
                lastLeftC++;
                continue;
            }
            if (ori.charAt(index) == '[')
            {
                lastLeftR++;
                continue;
            }
            if (lastLeftR + lastLeftC == 1 && (ori.charAt(index) == ',' || index >= ori.length() - 1))
            {
                splotLength++;
            }
            if (ori.charAt(index) == '}')
            {
                lastLeftC--;
                continue;
            }
            if (ori.charAt(index) == ']')
            {
                lastLeftR--;
                continue;
            }
        }
        String[] maps = new String[splotLength];
        lastIndex = 1;// 从第二个，第一个是{
        lastLeftC = 0;// 左边的{数量，起始是0
        int mapIndex = 0;
        for (int index = 0; index < ori.length(); index++)
        {
            if (ori.charAt(index) == '\"' && shuangyhao == 0)
            {
                shuangyhao++;
                continue;
            }
            if (ori.charAt(index) == '\"' && shuangyhao == 1)
            {
                shuangyhao--;
                continue;
            }
            if (shuangyhao == 1)
            {
                continue;
            }
            if (ori.charAt(index) == '{')
            {
                lastLeftC++;
                continue;
            }
            if (ori.charAt(index) == '[')
            {
                lastLeftR++;
                continue;
            }
            if (lastLeftR + lastLeftC == 1 && (ori.charAt(index) == ',' || index >= ori.length() - 1))
            {
                maps[mapIndex] = ori.substring(lastIndex, index);
                lastIndex = index + 1;
                mapIndex++;
            }
            if (ori.charAt(index) == ']')
            {
                lastLeftR--;
                continue;
            }
            if (ori.charAt(index) == '}')
            {
                lastLeftC--;
                continue;
            }
        }
        return maps;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： <br>
     * @see <br>
     * @param ori
     * @return <br>
     * @String <br>
     * @methods pers.bc.utils.pub.JsonUtilbc#removeBlack <br>
     * @author licheng <br>
     * @date Created on 2020年4月5日 <br>
     * @time 下午8:01:09 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    private static String removeBlack(String ori)
    {
        if (ori == null)
        {
            return null;
        }
        int shuangyhao = 0;// 考虑{}有可能在""里面
        boolean isblanck[] = new boolean[ori.length()];
        for (int index = 0; index < ori.length(); index++)
        {
            if (ori.charAt(index) == '\"' && shuangyhao == 0)
            {
                shuangyhao++;
                continue;
            }
            if (ori.charAt(index) == '\"' && shuangyhao == 1)
            {
                shuangyhao--;
                continue;
            }
            if (shuangyhao == 1)
            {
                continue;
            }
            if (ori.charAt(index) == ' ' || ori.charAt(index) == '\n' || ori.charAt(index) == '\t' || ori.charAt(index) == '\r')
            {
                isblanck[index] = true;
            }
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < ori.length(); i++)
        {
            if (!isblanck[i])
            {
                sb.append(ori.charAt(i));
            }
        }
        return sb.toString();
    }
    
}
