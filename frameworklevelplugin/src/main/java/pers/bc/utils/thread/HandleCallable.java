package pers.bc.utils.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * 
 * @qualiFild nc.pub.itf.tools.thread.HandleCallable.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
@SuppressWarnings("rawtypes")
public class HandleCallable<E> implements Callable<ThreadResultBean>
{
    // 线程名称
    private String threadName = "";
    // 需要处理的数据
    private List<E> data;
    // 辅助参数
    private Map<String, Object> params;
    // 具体执行任务
    private IThreadTask<ThreadResultBean<Object>, E> task;
    
    public HandleCallable()
    {
    }
    
    public HandleCallable(String threadName, List<E> data, Map<String, Object> params, IThreadTask<ThreadResultBean<Object>, E> task)
    {
        this.threadName = threadName;
        this.data = data;
        this.params = params;
        this.task = task;
    }
    
    @Override
    public ThreadResultBean<List<ThreadResultBean<Object>>> call() throws Exception
    {
        return exeCall();
    }
    
    /**
     * 
     * *********************************************************** <br>
     * 说明： 执行结果
     * @see
     * @return
     * @throws Exception
     * @ThreadResultBean<List<ThreadResultBean<Object>>>
     * @author licheng
     * @date Created on 2019-8-4
     * @time 下午3:14:43
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean<List<ThreadResultBean<Object>>> exeCall() throws Exception
    {
        // 该线程中所有数据处理返回结果
        ThreadResultBean<List<ThreadResultBean<Object>>> resultBean = ThreadResultBean.newInstance();
        
        // 返回结果集
        List<ThreadResultBean<Object>> resultList = new ArrayList<ThreadResultBean<Object>>();
        // 循环处理每个数据
        for (int i = 0, j = data == null ? 0 : data.size(); i < j; i++)
        {
            // 需要执行的数据
            E e = data.get(i);
            // 将数据执行结果加入到结果集中
            resultList.add(task.execute(e, params));
        }
        resultBean.setData(resultList);
        resultBean.setThreadName(getThreadName());
        return resultBean;
    }
    
    public String getThreadName()
    {
        return threadName;
    }
    
    public void setThreadName(String threadName)
    {
        this.threadName = threadName;
    }
    
}
