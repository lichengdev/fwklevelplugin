package pers.bc.utils.cons;

/**
 * 定义一些处理接口，便于优雅的处理一些数据,实现的方法不返回数据
 * @author：licheng
 * @2019-7-31
 */
public interface ObjectHandler<T>
{
    void handler(T t);
}
