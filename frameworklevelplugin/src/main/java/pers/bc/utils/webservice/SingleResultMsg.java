package pers.bc.utils.webservice;

/**
 * 单个操作结果
 * @qualiFild pers.bc.utils.webservice.SingleResultMsg.java<br>
 * @author：Li Bencheng<br>
 * @date Created on 2020-4-28<br>
 * @version 1.0<br>
 */
public class SingleResultMsg
{
    
    /**
     * 消息
     */
    private String msg;
    
    /**
     * 主键
     */
    private String pk;
    
    /**
     * 处理结果
     */
    private Object result;
    
    /**
     * 行号
     */
    private Integer rowIndex;
    
    /**
     * +
     */
    private int state;
    
    /**
     * 单据编号
     */
    private String vbillno;
    
    public SingleResultMsg()
    {
        super();
    }
    
    private SingleResultMsg(MultipleResultMsg.ResultState state, String msg, Object result, String pk, String vbillno, Integer rowIndex)
    {
        super();
        this.state = state.getValue();
        this.msg = msg;
        this.result = result;
        this.pk = pk;
        this.vbillno = vbillno;
        this.rowIndex = rowIndex;
    }
    
    /**
     * 构建失败的单笔操作结果
     * 
     * @param pk 主键
     * @param vbillno 单据编号
     * @param rowIndex 行号
     * @param msg 操作消息
     * @return
     */
    public static SingleResultMsg buildErrResult(String pk, String vbillno, Integer rowIndex, String msg)
    {
        return new SingleResultMsg(MultipleResultMsg.ResultState.FAIL, msg, null, pk, vbillno, rowIndex);
    }
    
    /**
     * 构建成功的单笔操作结果
     * 
     * @param pk 主键
     * @param vbillno 单据编号
     * @param rowIndex 行号
     * @param result 操作结果
     * @return
     */
    public static SingleResultMsg buildSuccessResult(String pk, String vbillno, Integer rowIndex, Object result)
    {
        return new SingleResultMsg(MultipleResultMsg.ResultState.SUCCESS, MultipleResultMsg.SUCCESS, result, pk, vbillno, rowIndex);
    }
    
    public String getMsg()
    {
        return this.msg;
    }
    
    public String getPk()
    {
        return this.pk;
    }
    
    public Object getResult()
    {
        return this.result;
    }
    
    public Integer getRowIndex()
    {
        return this.rowIndex;
    }
    
    public int getState()
    {
        return this.state;
    }
    
    public String getVbillno()
    {
        return this.vbillno;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public void setPk(String pk)
    {
        this.pk = pk;
    }
    
    public void setResult(Object result)
    {
        this.result = result;
    }
    
    public void setRowIndex(Integer rowIndex)
    {
        this.rowIndex = rowIndex;
    }
    
    public void setState(int state)
    {
        this.state = state;
    }
    
    public void setVbillno(String vbillno)
    {
        this.vbillno = vbillno;
    }
    
}
