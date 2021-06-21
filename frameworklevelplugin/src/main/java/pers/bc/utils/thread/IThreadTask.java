package pers.bc.utils.thread;

import java.util.Map;

/**
 * 任务执行方法接口
 * @qualiFild nc.pub.itf.tools.thread.IThreadTask.java<br>
 * @author：licheng<br>
 * @date Created on 2019-8-12<br>
 * @version 1.0<br>
 */
public interface IThreadTask<T, E>
{
    
    /**
     * *********************************************************** <br>
     * 说明： 任务执行方法接口<BR>
     *  方法名：execute<BR>
     * @T 返回值类型
     * @author licheng
     * @date Created on 2019-7-12
     * @time 下午4:23:52
     * @version 1.0 <br>
     ************************************************************* <br>
     */
    T execute(E e, Map<?, ?> params);
    
}
