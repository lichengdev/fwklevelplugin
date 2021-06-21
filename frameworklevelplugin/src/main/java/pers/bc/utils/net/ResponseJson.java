package pers.bc.utils.net;

import java.io.Serializable;

public class ResponseJson implements Serializable
{
    
    private static final long serialVersionUID = 1L;
    public ResultCode resultCode; // 业务响应码
    public String resultMsg = ""; // 返回信息描述
    public String errCode; // 错误代码
    public String errCodeDes = ""; // 错误代码
    public Object data; // 返回业务参数
    
    public ResponseJson(ResultCode resultCode, String resultMsg, Object data)
    {
        super();
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.data = data;
    }
    
    public ResponseJson(ResultCode resultCode, ErrorCode errCode, Object data)
    {
        super();
        this.resultCode = resultCode;
        this.errCode = errCode.getName();
        this.errCodeDes = errCode.getDes();
        this.resultMsg = errCode.getDes();
        this.data = data;
    }
    
    public ResponseJson(ResultCode resultCode, Object data)
    {
        super();
        this.resultCode = resultCode;
        this.data = data;
    }
    
    public ResponseJson(ResultCode resultCode, ErrorCode errCode)
    {
        super();
        this.resultCode = resultCode;
        this.errCode = errCode.getName();
        this.errCodeDes = errCode.getName();
        this.resultMsg = errCode.getDes();
    }
    
    public ResponseJson(ResultCode resultCode)
    {
        super();
        this.resultCode = resultCode;
    }
    
    public ResponseJson(ResultCode resultCode, ErrorCode errCode, String errCodeDes)
    {
        super();
        this.resultCode = resultCode;
        this.errCode = errCode.getName();
        this.errCodeDes = errCodeDes;
        this.resultMsg = errCodeDes;
    }
    
    public enum ResultCode
    {
        SUCCESS, // 业务处理成功
        FAIL; // 业务处理失败
    }
    
    // 可根据自己的写
    public enum ErrorCode
    {
        VALIDATE_ERROR("VALIDATE_ERROR", "校验异常"), ORDER_IS_NOTEXIST("ORDER_IS_NOTEXIST", "订单不存在");
        
        private String name;
        private String des;
        
        // 构造方法
        ErrorCode(String name, String des)
        {
            this.name = name;
            this.des = des;
        }
        
        public String getName()
        {
            return name;
        }
        
        public void setName(String name)
        {
            this.name = name;
        }
        
        public String getDes()
        {
            return des;
        }
        
        public void setDes(String des)
        {
            this.des = des;
        }
        
    }
    
    public ResultCode getResultCode()
    {
        return resultCode;
    }
    
    public void setResultCode(ResultCode resultCode)
    {
        this.resultCode = resultCode;
    }
    
    public String getResultMsg()
    {
        return resultMsg;
    }
    
    public void setResultMsg(String resultMsg)
    {
        this.resultMsg = resultMsg;
    }
    
    public String getErrCode()
    {
        return errCode;
    }
    
    public void setErrCode(String errCode)
    {
        this.errCode = errCode;
    }
    
    public String getErrCodeDes()
    {
        return errCodeDes;
    }
    
    public void setErrCodeDes(String errCodeDes)
    {
        this.errCodeDes = errCodeDes;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
}
