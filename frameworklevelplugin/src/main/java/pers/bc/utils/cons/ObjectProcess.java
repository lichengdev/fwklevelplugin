package pers.bc.utils.cons;

/**
 * 定义一些处理接口，便于优雅的处理一些数据,实现的方法返回数据
 * @author：licheng
 * @2019-7-31
 */
public interface ObjectProcess<T, E>
{
    E process(T t);
}
