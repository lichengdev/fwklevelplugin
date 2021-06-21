package pers.bc.utils.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import pers.bc.utils.pub.StringUtilbc;

public class TestTask
{
    public static void main(String[] args)
    {
        
        // 需要多线程处理的大量数据list
        List<Integer> data = new ArrayList<>(100);
        for (int i = 0; i < 100; i++)
        {
            data.add(i + 1);
        }
        // 创建多线程处理任务
        MultiThreadUtils<Integer> threadUtils = MultiThreadUtils.newInstance(4);
        // 辅助参数 加数
        Map<String, Object> params = new HashMap<>();
        params.put("addNum", 4);
        // 执行多线程处理，并返回处理结果
        try
        {
            ThreadResultBean<List<ThreadResultBean<Object>>> resultBean =
                threadUtils.execute(data, params, new IThreadTask<ThreadResultBean<Object>, Integer>()
                {
                    @Override
                    public ThreadResultBean<Object> execute(Integer e, Map<?, ?> params)
                    {
                        int tick = 20;
                        /**
                         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
                         */
                        int addNum = Integer.valueOf(String.valueOf(params.get("addNum")));
                        e = e + addNum;
                        
                        ThreadResultBean<Object> resultBean = ThreadResultBean.newInstance();
                        resultBean.setData(e.toString());
                        try
                        {
                            System.err.println(StringUtilbc.toString(resultBean));
                        }
                        catch (Exception e1)
                        {
                        }
                        return resultBean;
                    }
                });
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
    
}
