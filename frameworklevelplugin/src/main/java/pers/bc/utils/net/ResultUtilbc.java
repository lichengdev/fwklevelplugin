package pers.bc.utils.net;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 自定义响应结构
 * @qualiFild pers.bc.utils.net.TaotaoResult.java<br>
 * @author：李本城<br>
 * @date Created on 2020年4月3日<br>
 * @version 1.0<br>
 */
public class ResultUtilbc
{
    
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    // 响应业务状态
    private Integer status;
    // 响应消息
    private String msg;
    // 响应中的数据
    private Object data;
    
    public ResultUtilbc()
    {
        
    }
    
    public ResultUtilbc(Object data)
    {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public ResultUtilbc(Integer status, String msg, Object data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public static ResultUtilbc build(Integer status, String msg, Object data)
    {
        return new ResultUtilbc(status, msg, data);
    }
    
    public static ResultUtilbc build(Integer status, String msg)
    {
        return new ResultUtilbc(status, msg, null);
    }
    
    public static ResultUtilbc ok(Object data)
    {
        return new ResultUtilbc(data);
    }
    
    public static ResultUtilbc ok()
    {
        return new ResultUtilbc(null);
    }
    
    // public Boolean isOK() {
    // return this.status == 200;
    // }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
    /**
     * *********************************************************** <br>
     * 说明: 将json结果集转化为TaotaoResult对象
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return <br>
     * @ResultUtilbc <br>
     * @methods pers.bc.utils.net.ResultUtilbc#formatToPojo <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:03:22 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static ResultUtilbc formatToPojo(String jsonData, Class<?> clazz)
    {
        try
        {
            if (clazz == null)
            {
                return MAPPER.readValue(jsonData, ResultUtilbc.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null)
            {
                if (data.isObject())
                {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                }
                else if (data.isTextual())
                {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * *********************************************************** <br>
     * 说明：没有object对象的转化 <br>
     * @see <br>
     * @param json
     * @return <br>
     * @ResultUtilbc <br>
     * @methods pers.bc.utils.net.ResultUtilbc#format <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:02:55 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static ResultUtilbc format(String json)
    {
        try
        {
            return MAPPER.readValue(json, ResultUtilbc.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * *********************************************************** <br>
     * 说明:Object是集合转化
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return <br>
     * @ResultUtilbc <br>
     * @methods pers.bc.utils.net.ResultUtilbc#formatToList <br>
     * @author licheng <br>
     * @date Created on 2020年4月3日 <br>
     * @time 下午6:03:43 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public static ResultUtilbc formatToList(String jsonData, Class<?> clazz)
    {
        try
        {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0)
            {
                obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
}
