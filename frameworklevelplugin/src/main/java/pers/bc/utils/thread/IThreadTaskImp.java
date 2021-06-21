package pers.bc.utils.thread;

import java.util.Map;

/**
 * 任务执行方法接口实现方法
 * @qualiFild nc.pub.itf.tools.thread.IThreadTaskImp.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public class IThreadTaskImp implements IThreadTask<Object, Object>
{
    
    @Override
    public Object execute(Object e, Map<?, ?> params)
    {
        /**
         * 具体业务逻辑：将list中的元素加上辅助参数中的数据返回
         */
        int addNum = Integer.valueOf(String.valueOf(params.get("addNum")));
        e = Integer.valueOf((String) e) + addNum;
        ThreadResultBean<Object> resultBean = ThreadResultBean.newInstance();
        resultBean.setData(e.toString());
        return resultBean;
    }
    
}
