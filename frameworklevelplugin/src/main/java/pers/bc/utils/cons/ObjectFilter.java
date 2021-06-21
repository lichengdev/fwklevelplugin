package pers.bc.utils.cons;

/**
 * 对象过滤接口
 * @author：licheng
 * @2019-7-31
 */
public interface ObjectFilter<T>
{
    boolean filter(T t);
}
