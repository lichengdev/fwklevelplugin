package pers.bc.utils.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pers.bc.utils.pub.PubEnvUtilbc;

/**
 * 
 * @qualiFild pers.bc.utils.thread.MultiThreadUtils.java<br>
 * @author：licheng<br>
 * @date Created on 2018-4-11<br>
 * @version 1.0<br>
 */
public class MultiThreadUtils<T>
{
    // 线程个数，如不赋值，默认为5
    // ((线程等待时间+线程cpu时间)/线程cpu时间) * N
    // 对于计算密集型线程，线程等待时间接近0
    private int threadCount = 5;
    // 具体业务任务
    private IThreadTask<ThreadResultBean<String>, T> task = null;
    // 线程池管理器
    private CompletionService<ThreadResultBean> pool = null;
    private ExecutorService threadpool = null;
    
    public MultiThreadUtils()
    {
    }
    
    public MultiThreadUtils(int threadCount)
    {
        this.threadCount = threadCount;
    }
    
    /**
     * 初始化线程池和线程个数<BR>
     */
    public static MultiThreadUtils newInstance(int threadCount)
    {
        return new MultiThreadUtils(threadCount);
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 多线程分批执行list中的任务,收集结果<br>
     * @see <br>
     * @param data
     * @param params
     * @param task
     * @return
     * @throws InterruptedException
     * @throws ExecutionException <br>
     * @ThreadResultBean <br>
     * @methods pers.bc.utils.thread.MultiThreadUtils#execute <br>
     * @author licheng <br>
     * @date Created on 2018-4-11 <br>
     * @time 下午9:58:25 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public ThreadResultBean execute(List<T> data, Map<String, Object> params, IThreadTask<ThreadResultBean<Object>, T> task)
            throws InterruptedException, ExecutionException
    {
        // 创建线程池
        ExecutorService threadpool = getThreadpool();
        // 根据线程池初始化线程池管理器
        pool = new ExecutorCompletionService<ThreadResultBean>(threadpool);
        // 开始时间（ms）
        long start = System.currentTimeMillis();
        // 数据量大小
        int length = data.size();
        // 每个线程处理的数据个数
        int taskCount = length / threadCount;
        // 划分每个线程调用的数据
        for (int i = 0; i < threadCount; i++)
        {
            // 每个线程任务数据list
            List<T> subData = null;
            if (i == (threadCount - 1))
            {
                subData = data.subList(i * taskCount, length);
            }
            else
            {
                subData = data.subList(i * taskCount, (i + 1) * taskCount);
            }
            // 将数据分配给各个线程
            String threadName = "Thread#[" + (i + 1) + "]-bc";
            params.put("threadName", threadName);
            HandleCallable<T> execute = new HandleCallable<T>(threadName, subData, params, task);
            // 将线程加入到线程池
            pool.submit(execute);
        }
        
        // 总的返回结果集
        List<ThreadResultBean<String>> result = new ArrayList<ThreadResultBean<String>>();
        for (int i = 0; i < threadCount; i++)
        {
            // 每个线程处理结果集
            ThreadResultBean<List<ThreadResultBean<String>>> threadResult;
            threadResult = pool.take().get();
            result.addAll(threadResult.getData());
            
        }
        // 关闭线程池
        threadpool.shutdownNow();
        // 执行结束时间
        long end = System.currentTimeMillis();
        
        ThreadResultBean<Object> resultBean = ThreadResultBean.newInstance();
        resultBean.setData(result);
        resultBean.setTimeMillis(end - start);
        
        return resultBean;
    }
    
    /**
     * *********************************************************** <br>
     * 说明： 多线程分批执行list中的任务,不收集结果， <br>
     * @see <br>
     * @param data
     * @param params
     * @param task
     * @throws InterruptedException
     * @throws ExecutionException <br>
     * @void <br>
     * @methods pers.bc.utils.thread.MultiThreadUtils#execute2 <br>
     * @author licheng <br>
     * @date Created on 2020-4-11 <br>
     * @time 下午9:58:57 <br>
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    public void execute2(List<T> data, Map<String, Object> params, IThreadTask<ThreadResultBean<Object>, T> task)
            throws InterruptedException, ExecutionException
    {
        // 创建线程池
        ExecutorService threadpool = getThreadpool();
        // 根据线程池初始化线程池管理器
        pool = new ExecutorCompletionService<ThreadResultBean>(threadpool);
        // 开始时间（ms）
        long start = System.currentTimeMillis();
        // 数据量大小
        int length = data.size();
        // 每个线程处理的数据个数
        int taskCount = length / threadCount;
        // 划分每个线程调用的数据
        for (int i = 0; i < threadCount; i++)
        {
            // 每个线程任务数据list
            List<T> subData = null;
            if (i == (threadCount - 1))
            {
                subData = data.subList(i * taskCount, length);
            }
            else
            {
                subData = data.subList(i * taskCount, (i + 1) * taskCount);
            }
            // 将数据分配给各个线程
            String threadName = "Thread#[" + (i + 1) + "]-bc";
            params.put("threadName", threadName);
            HandleCallable<T> execute = new HandleCallable<T>(threadName, subData, params, task);
            // 将线程加入到线程池
            pool.submit(execute);
        }
        
        // 总的返回结果集
        for (int i = 0; i < threadCount; i++)
        {
            // 每个线程处理结果集
            pool.take().get();
        }
        // 关闭线程池
        threadpool.shutdownNow();
        // 执行结束时间
        long end = System.currentTimeMillis();
        
    }
    
    public ExecutorService getThreadpool()
    {
        if (PubEnvUtilbc.isEmptyObj(threadpool)) threadpool = Executors.newFixedThreadPool(threadCount);
        return threadpool;
    }
    
    public void setThreadpool(ExecutorService threadpool)
    {
        this.threadpool = threadpool;
    }
    
    public int getThreadCount()
    {
        return threadCount;
    }
    
    public void setThreadCount(int threadCount)
    {
        this.threadCount = threadCount;
    }
}
