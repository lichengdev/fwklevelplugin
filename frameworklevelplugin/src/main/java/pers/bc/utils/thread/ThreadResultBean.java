package pers.bc.utils.thread;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 返回结果统一bean
 * @qualiFild nc.pub.itf.tools.thread.ThreadResultBean.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class ThreadResultBean<T> implements Serializable
{
    
    private static final long serialVersionUID = -4965902522838528402L;
    
    // 成功状态
    public static final int SUCCESS = 1;
    // 处理中状态
    public static final int PROCESSING = 0;
    // 失败状态
    public static final int FAIL = -1;
    // 描述
    private String msg = "success";
    // 状态默认成功
    private int code = SUCCESS;
    // 备注
    private String remark;
    // 返回数据
    private T data;
    // 处理耗时
    public long timeMillis = 0;
    // 线程名称
    private String threadName = "";
    
    public ThreadResultBean()
    {
        super();
    }
    
    public ThreadResultBean(T data)
    {
        super();
        this.data = data;
    }
    
    /**
     * 使用异常创建结果
     * @param e
     */
    public ThreadResultBean(Throwable e)
    {
        super();
        this.msg = e.toString();
        this.code = FAIL;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see 实例化结果默认成功状态<BR>
     *      方法名：newInstance<BR>
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:22:38
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> ThreadResultBean<T> newInstance()
    {
        ThreadResultBean<T> instance = new ThreadResultBean<T>();
        // 默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        return instance;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see 实例化结果默认成功状态和数据<BR>
     *      方法名：newInstance<BR>
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:22:17
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> ThreadResultBean<T> newInstance(T data)
    {
        ThreadResultBean<T> instance = new ThreadResultBean<T>();
        // 默认返回信息
        instance.code = SUCCESS;
        instance.msg = "success";
        instance.data = data;
        return instance;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：
     * @see 实例化返回结果<BR>
     *      方法名：newInstance<BR>
     * @param code
     * @param msg
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:22:04
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> ThreadResultBean<T> newInstance(int code, String msg)
    {
        ThreadResultBean<T> instance = new ThreadResultBean<T>();
        // 默认返回信息
        instance.code = code;
        instance.msg = msg;
        return instance;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 实例化返回结果<BR>
     *      方法名：newInstance<BR>
     * @param code
     * @param msg
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:23:17
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public static <T> ThreadResultBean<T> newInstance(int code, String msg, T data)
    {
        ThreadResultBean<T> instance = new ThreadResultBean<T>();
        // 默认返回信息
        instance.code = code;
        instance.msg = msg;
        instance.data = data;
        return instance;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置返回数据<BR>
     *      方法名：setData<BR>
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:23:28
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> setData(T data)
    {
        this.data = data;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置结果描述<BR>
     *      方法名：setMsg<BR>
     * @param msg
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:23:41
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> setMsg(String msg)
    {
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置状态<BR>
     *      方法名：setCode<BR>
     * @param code
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:23:57
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> setCode(int code)
    {
        this.code = code;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置备注)<BR>
     *      方法名：setRemark<BR>
     * @param remark
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:21:31
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> setRemark(String remark)
    {
        this.remark = remark;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置成功描述和返回数据<BR>
     *      方法名：success<BR>
     * @param msg
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:21:21
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> success(String msg, T data)
    {
        this.code = SUCCESS;
        this.data = data;
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置成功返回结果描述<BR>
     *      方法名：success<BR>
     * @param msg
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:21:08
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> success(String msg)
    {
        this.code = SUCCESS;
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置处理中描述和返回数据<BR>
     *      方法名：success<BR>
     * @param msg
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:24:14
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> processing(String msg, T data)
    {
        this.code = PROCESSING;
        this.data = data;
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置处理中返回结果描述<BR>
     *      方法名：success<BR>
     * @param msg
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:24:24
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> processing(String msg)
    {
        this.code = PROCESSING;
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置失败返回描述和返回数据<BR>
     *      方法名：fail<BR>
     * @param msg
     * @param data
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:24:32
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> fail(String msg, T data)
    {
        this.code = FAIL;
        this.data = data;
        this.msg = msg;
        return this;
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明：
     * @see 设置失败返回描述<BR>
     *      方法名：fail<BR>
     * @param msg
     * @return
     * @ResultBean<T>
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:17:23
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<T> fail(String msg)
    {
        this.code = FAIL;
        this.msg = msg;
        return this;
    }
    
    public T getData()
    {
        return data;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public long getTimeMillis()
    {
        return timeMillis;
    }
    
    public void setTimeMillis(long timeMillis)
    {
        this.timeMillis = timeMillis;
    }
    
    public String getThreadName()
    {
        return threadName;
    }
    
    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }
    
    /**
     * *********************************************************** <br>
     * 说明：此方法需要 fastjson-1.1.41.jar
     * @see 生成json字符串<BR>
     *      方法名：json<BR>
     * @return
     * @String
     * @author licheng
     * @date Created on 2019-8-1
     * @time 下午3:17:03
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public String json()
    {
        return JSON.toJSONString(this);
    }
}
