package pers.bc.utils.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.bc.utils.pub.LoggerUtilbc;
import pers.bc.utils.throwable.MylbcException;

/**
 * 用於webservice 传输数据接口实现类 需要fastjson-1.1.41.jar
 * @qualiFild pers.bc.utils.webservice.My_Impl_PubWebService.java<br>
 * @author：Li Bencheng<br>
 * @date Created on 2020-4-28<br>
 * @version 1.0<br>
 */
public class My_Impl_PubWebService implements My_Itf_PubWebService
{
    
    @Override
    public Object returnData(String json) throws Exception
    {
        
        Map<String, Object> map = new HashMap<>();
        List<Object> dataList = null;
        String jsonStr = null;
        int result = 0;
        StringBuffer msg = new StringBuffer();
        msg.append("接收到的参数:").append(json);
        try
        {
            if (null == json || json.length() == 0)
            {
                throw new MylbcException("当前json = [" + json + "] json is null, 请确认!");
            }
            
            char[] strChar = json.substring(0, 1).toCharArray();
            char firstChar = strChar[0];
            if ('{' == firstChar)
            {
                JSONObject jsonObj = JSON.parseObject(json);
                String tableName = jsonObj.getString("key");
                
                // 提前回收垃圾，防止OutOfMemoryError错误的解决
                // java.lang.OutOfMemoryError: Java heap space
                System.gc();
                jsonStr = String.valueOf(dataList);
                
            }
            else if ('[' == firstChar)
            {
                JSONArray parseArray = JSON.parseArray(json);
                Map<String, String> map2 = new HashMap<>();
                for (int i = 0, i$ = (parseArray == null) ? 0 : parseArray.size(); i < i$; i++)
                {
                    JSONObject jsonObj = parseArray.getJSONObject(i);
                }
                jsonStr = String.valueOf(dataList);
                msg.append("\r\n............................一共返回多少条:").append(result);
            }
            else
            {
                throw new MylbcException("当前json = [" + json + "] 数据格式存在问题, 请确认!");
            }
            LoggerUtilbc.getInstance("webservicelogs").info("接收到的参数：[" + json + "]" + LoggerUtilbc.CRLF + "返回数据条数：[" + result + "]");
        }
        catch (Exception e)
        {
            LoggerUtilbc.getInstance("webservicelogs").exception("returnDataThw", e);
            jsonStr = ("[" + json + "]" + e.toString());
            System.gc();
            dataList = null;
            return jsonStr;
        }
        System.gc();
        dataList = null;
        return jsonStr;
    }
    
    @Override
    public Object returnDataExt(String... prams) throws Exception
    {
        String strs[] = new String[prams.length];
        for (int i = 0, i$ = strs.length; i < i$; i++)
        {
            strs[i] = prams[i];
        }
        Map<String, Object> map = new HashMap<>();
        if (null != strs && strs.length != 0)
        {
            
        }
        
        return JSON.toJSONString(map);
    }
}
