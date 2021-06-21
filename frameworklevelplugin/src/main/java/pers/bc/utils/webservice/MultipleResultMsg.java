
package pers.bc.utils.webservice;

import java.util.List;

/**
 * 批量操作结果
 * @qualiFild pers.bc.utils.webservice.MultipleResult.java<br>
 * @author：licheng<br>
 * @date Created on 2020年3月4日<br>
 * @version 1.0<br>
 */
public class MultipleResultMsg
{
    public static String FAIL = "全部失败！";
    public static String PARTSUCCESS = "部分成功！";
    public static String ALLSUCCESS = "全部成功！";
    public static String SUCCESS = "成功！";
    
    /**
     * 结果枚举
     * @qualiFild pers.bc.utils.webservice.MultipleResult.java<br>
     * @author：licheng<br>
     * @date Created on 2020年3月4日<br>
     * @version 1.0<br>
     */
    public static enum ResultState
    {
        /** 失败 */
        FAIL(1),
        /** 部分成功 */
        PARTSUCCESS(2),
        /** 成功 */
        SUCCESS(0);
        private int value;
        
        private ResultState(int value)
        {
            this.value = value;
        }
        
        public int getValue()
        {
            return this.value;
        }
        
    }
    
    /**
     * 返回数据
     */
    private SingleResultMsg[] data;
    
    /**
     * 失败条数
     */
    private int errorNum;
    
    /**
     * 失败条数 【 适配贷款失败条数字段赋值errorNum(尽量用errorNum)】
     */
    private int failNum;
    
    /**
     * 信息
     */
    private String msg;
    
    /**
     * 信息详情
     */
    private String[] msgDetail;
    
    /**
     * 状态标识
     */
    private int status;
    
    /**
     * 成功总数
     */
    private int successNum;
    
    /**
     * 总数
     */
    private int total;
    
    public MultipleResultMsg(ResultState state, String msg, String[] msgDetail, SingleResultMsg[] data, int total, int errorNum, int successNum,
            int failNum)
    {
        super();
        this.status = state.getValue();
        this.msg = msg;
        this.msgDetail = msgDetail;
        this.data = data;
        this.total = total;
        this.errorNum = errorNum;
        this.successNum = successNum;
        this.failNum = failNum;
    }
    
    private MultipleResultMsg(ResultState state, String msg, String[] msgDetail, SingleResultMsg[] data)
    {
        super();
        this.status = state.getValue();
        this.msg = msg;
        this.msgDetail = msgDetail;
        this.data = data;
    }
    
    /**
     * 构建操作结果
     *
     * @param hasSuccFlag 是否有成功
     * @param hasErrFlag 是否有失败
     * @param list 执行结果汇总列表
     * @return
     */
    public static MultipleResultMsg buildResult(boolean hasSuccFlag, boolean hasErrFlag, List<SingleResultMsg> list, List<String> errMsgDetail)
    {
        String msg = null;
        ResultState status = ResultState.FAIL;
        if (hasSuccFlag && hasErrFlag)
        {
            msg = PARTSUCCESS;
            status = ResultState.PARTSUCCESS;
        }
        else if (hasSuccFlag && !hasErrFlag)
        {
            msg = ALLSUCCESS;
            status = ResultState.SUCCESS;
        }
        else if (!hasSuccFlag && hasErrFlag)
        {
            msg = FAIL;
            status = ResultState.FAIL;
        }
        SingleResultMsg[] results = list.isEmpty() ? null : list.toArray(new SingleResultMsg[0]);
        String[] msgDetail = errMsgDetail.isEmpty() ? null : errMsgDetail.toArray(new String[errMsgDetail.size()]);
        return new MultipleResultMsg(status, msg, msgDetail, results);
    }
    
    /**
     * 构建操作结果
     *
     * @param hasSuccFlag 是否有成功
     * @param hasErrFlag 是否有失败
     * @param list 执行结果汇总列表
     * @return
     */
    public static MultipleResultMsg buildResult(boolean hasSuccFlag, boolean hasErrFlag, List<SingleResultMsg> list, List<String> errMsgDetail,
            int total, int errorNum, int successNum)
    {
        String msg = null;
        ResultState status = ResultState.FAIL;
        if (hasSuccFlag && hasErrFlag)
        {
            msg = PARTSUCCESS;
            status = ResultState.PARTSUCCESS;
        }
        else if (hasSuccFlag && !hasErrFlag)
        {
            msg = ALLSUCCESS;
            status = ResultState.SUCCESS;
        }
        else if (!hasSuccFlag && hasErrFlag)
        {
            msg = FAIL;
            status = ResultState.FAIL;
        }
        SingleResultMsg[] results = list.isEmpty() ? null : list.toArray(new SingleResultMsg[0]);
        String[] msgDetail = errMsgDetail.isEmpty() ? null : errMsgDetail.toArray(new String[errMsgDetail.size()]);
        return new MultipleResultMsg(status, msg, msgDetail, results, total, errorNum, successNum, errorNum);
    }
    
    public SingleResultMsg[] getData()
    {
        return this.data;
    }
    
    public int getErrorNum()
    {
        return this.errorNum;
    }
    
    public int getFailNum()
    {
        return this.failNum;
    }
    
    public String getMsg()
    {
        return this.msg;
    }
    
    public String[] getMsgDetail()
    {
        return this.msgDetail;
    }
    
    public int getStatus()
    {
        return this.status;
    }
    
    public int getSuccessNum()
    {
        return this.successNum;
    }
    
    public int getTotal()
    {
        return this.total;
    }
    
    public void setData(SingleResultMsg[] data)
    {
        this.data = data;
    }
    
    public void setErrorNum(int errorNum)
    {
        this.errorNum = errorNum;
    }
    
    public void setFailNum(int failNum)
    {
        this.failNum = failNum;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public void setMsgDetail(String[] msgDetail)
    {
        this.msgDetail = msgDetail;
    }
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public void setSuccessNum(int successNum)
    {
        this.successNum = successNum;
    }
    
    public void setTotal(int total)
    {
        this.total = total;
    }
}
