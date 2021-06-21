package pers.bc.utils.sql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings({"unchecked"})
public final class RedisUtil
{

    @Autowired(required = false)
    private RedisTemplate<String, Object> redisTemplate;
    // =============================common============================

    /**
     * *********************************************************** <br>
     * *说明： 指定缓存失效时间 <br>
     *  <br>
     * @param key 键
     * @param time 时间(秒)
     * @return <br>
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#expire <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:37:52 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean expire(String key, long time)
    {
        return expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * *********************************************************** <br>
     * *说明： 指定缓存失效时间 <br>
     *  <br>
     * @param key 键
     * @param time 时间
     * @param unit 时间单位
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#expire <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:02:22 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean expire(String key, long time, TimeUnit unit)
    {
        try
        {
            if (time > 0)
            {
                redisTemplate.expire(key, time, unit);
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  根据key 获取过期时间
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     * @long <br>
     * @methods game.web.interfaces.RedisUtil#getExpire <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:49:52 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long getExpire(String key)
    {
        return getExpire(key, TimeUnit.SECONDS);
    }

    public long getExpire(String key, TimeUnit timeUnit)
    {
        return redisTemplate.getExpire(key, timeUnit);
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  判断key是否存在
     * @param key 键判断key是否存在
     * @return <br>
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#hasKey <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:50:31 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hasKey(String key)
    {
        try
        {
            return redisTemplate.hasKey(key);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明：判断缓存中是否有对应的value <br>
     *  <br>
     * @param key
     * @return <br>
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#exists <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:51:39 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean exists(final String key)
    {
        return hasKey(key);
    }

    /**
     * *********************************************************** <br>
     * *说明：删除缓存 <br>
     *  <br>
     * @param key <br>
     * @void <br>
     * @methods game.web.interfaces.RedisUtil#del <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:54:04 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void del(String... key)
    {
        if (key != null && key.length > 0)
        {
            if (key.length == 1)
            {
                redisTemplate.delete(key[0]);
            }
            else
            {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    // ============================String=============================

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  普通缓存获取
     * @param key 键
     * @return 值
     * @Object <br>
     * @methods game.web.interfaces.RedisUtil#get <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:54:32 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object get(String key)
    {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * *********************************************************** <br>
     * *说明：普通缓存放入 <br>
     *
     * @param key 键
     * @param value 值
     * @return true成功 false失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#set <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:54:54 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean set(String key, Object value)
    {
        try
        {
            redisTemplate.opsForValue().set(key, value);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 写入缓存设置时效时间<br>
     *  <br>
     * @param key
     * @param value
     * @param expireTime
     * @param timeUnit
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#set <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午5:55:35 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean set(final String key, Object value, Long expireTime, TimeUnit timeUnit)
    {
        boolean result = false;
        try
        {
            ValueOperations<String, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, timeUnit);
            result = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#set <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:55:22 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean set(String key, Object value, long time)
    {
        try
        {
            if (time > 0)
            {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            }
            else
            {
                set(key, value);
            }
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return <br>
     * @long <br>
     * @methods game.web.interfaces.RedisUtil#incr <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:56:05 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long incr(String key, long delta)
    {
        if (delta < 0)
        {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * *********************************************************** <br>
     * *说明：递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return <br>
     * @long <br>
     * @methods game.web.interfaces.RedisUtil#decr <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:56:30 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long decr(String key, long delta)
    {
        if (delta < 0)
        {
            throw new RuntimeException("递减因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, -delta);
    }

    // ================================Map=================================

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return <br>
     * @Object <br>
     * @methods game.web.interfaces.RedisUtil#hget <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:57:26 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object hget(String key, String item)
    {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * *********************************************************** <br>
     * *说明：获取hashKey对应的所有键值
     * @param key 键
     * @return 对应的多个键值
     * @Map<Object,Object> <br>
     * @methods game.web.interfaces.RedisUtil#hmget <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:57:40 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Map<Object, Object> hmget(String key)
    {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * *********************************************************** <br>
     * *说明： HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#hmset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:57:59 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hmset(String key, Map<String, Object> map)
    {
        try
        {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * 说明：HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#hmset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:58:26 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hmset(String key, Map<String, Object> map, long time)
    {
        return hmset(key, map, time, TimeUnit.SECONDS);
    }

    /**
     * *********************************************************** <br>
     * 说明： 说明：HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @param unit
     * @return true成功 false失败
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#hmset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:24:51 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hmset(String key, Map<String, Object> map, long time, TimeUnit unit)
    {
        try
        {
            redisTemplate.opsForHash().putAll(key, map);
            if (time > 0)
            {
                expire(key, time, unit);
            }
            return true;

        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明：向一张hash表中放入数据,如果不存在将创建 <br>
     *  向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return true 成功 false失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#hset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:59:05 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hset(String key, String item, Object value)
    {
        try
        {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明：向一张hash表中放入数据,如果不存在将创建 <br>
     *  向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     * @boolean <br>
     * @methods game.web.interfaces.RedisUtil#hset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午3:59:36 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hset(String key, String item, Object value, long time)
    {
        return hset(key, item, value, time, TimeUnit.SECONDS);
    }

    /**
     * *********************************************************** <br>
     * *说明：向一张hash表中放入数据,如果不存在将创建 <br>
     *  向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @param unit
     * @return true 成功 false失败
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#hset <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:23:07 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hset(String key, String item, Object value, long time, TimeUnit unit)
    {
        try
        {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0)
            {
                expire(key, time, unit);
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 删除hash表中的值<br>
     *  删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     * @void <br>
     * @methods game.web.interfaces.RedisUtil#hdel <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午4:00:20 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public void hdel(String key, Object... item)
    {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * *********************************************************** <br>
     * *说明： 判断hash表中是否有该项的值<br>
     *  判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#hHasKey <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:21:36 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean hHasKey(String key, String item)
    {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * *********************************************************** <br>
     * *说明：hash递增 如果不存在,就会创建一个 并把新增后的值返回 <br>
     *  hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return <br>
     * @double <br>
     * @methods game.web.utils.RedisUtil#hincr <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:20:43 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public double hincr(String key, String item, double by)
    {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * *********************************************************** <br>
     * *说明： hash递减<br>
     *  hash递减
     * @param key 键
     * @param item 项
     * @param by 要减少记(小于0)
     * @return <br>
     * @double <br>
     * @methods game.web.utils.RedisUtil#hdecr <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:20:25 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public double hdecr(String key, String item, double by)
    {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    // ============================set=============================

    /**
     * *********************************************************** <br>
     * *说明：根据key获取Set中的所有值 <br>
     *  根据key获取Set中的所有值
     * @param key 键
     * @return <br>
     * @Set<Object> <br>
     * @methods game.web.utils.RedisUtil#sGet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:19:44 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Set<Object> sGet(String key)
    {
        try
        {
            return redisTemplate.opsForSet().members(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return true 存在 false不存在
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#sHasKey <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:17:58 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean sHasKey(String key, Object value)
    {
        try
        {
            return redisTemplate.opsForSet().isMember(key, value);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将数据放入set缓存<br>
     *  将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     * @long <br>
     * @methods game.web.utils.RedisUtil#sSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:14:51 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long sSet(String key, Object... values)
    {
        try
        {
            return redisTemplate.opsForSet().add(key, values);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将set数据放入缓存<br>
     *  将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     * @long <br>
     * @methods game.web.utils.RedisUtil#sSetAndTime <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:14:24 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long sSetAndTime(String key, long time, Object... values)
    {
        return sSetAndTime(key, time, TimeUnit.SECONDS, values);
    }

    /**
     * *********************************************************** <br>
     * *说明： <br>
     *  将set数据放入缓存<br>
     *  将set数据放入缓存
     * @param key 键
     * @param time 时间
     * @param values 值 可以是多个
     * @param unit 时间单位
     * @return 成功个数
     * @long <br>
     * @methods game.web.utils.RedisUtil#sSetAndTime <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:17:02 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long sSetAndTime(String key, long time, TimeUnit unit, Object... values)
    {
        try
        {
            Long count = redisTemplate.opsForSet().add(key, values);
            if (time > 0) expire(key, time, unit);
            return count;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 获取set缓存的长度<br>
     *  获取set缓存的长度
     * @param key 键
     * @return <br>
     * @long <br>
     * @methods game.web.utils.RedisUtil#sGetSetSize <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:13:52 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long sGetSetSize(String key)
    {
        try
        {
            return redisTemplate.opsForSet().size(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 移除值为value的<br>
     *  移除值为value的
     * @param key 键
     * @param values 值 可以是多个
     * @return 移除的个数
     * @long <br>
     * @methods game.web.utils.RedisUtil#setRemove <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:13:17 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long setRemove(String key, Object... values)
    {
        try
        {
            Long count = redisTemplate.opsForSet().remove(key, values);
            return count;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * *********************************************************** <br>
     * *说明： 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return <br>
     * @List<Object> <br>
     * @methods game.web.utils.RedisUtil#lGet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:12:42 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public List<Object> lGet(String key, long start, long end)
    {
        try
        {
            return redisTemplate.opsForList().range(key, start, end);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 获取list缓存的长度
     * @param key 键
     * @return <br>
     * @long <br>
     * @methods game.web.utils.RedisUtil#lGetListSize <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:12:01 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long lGetListSize(String key)
    {
        try
        {
            return redisTemplate.opsForList().size(key);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return <br>
     * @Object <br>
     * @methods game.web.utils.RedisUtil#lGetIndex <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:11:34 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public Object lGetIndex(String key, long index)
    {
        try
        {
            return redisTemplate.opsForList().index(key, index);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:10:39 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, Object value)
    {
        try
        {
            redisTemplate.opsForList().rightPush(key, value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:08:29 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, Object value, long time)
    {
        return lSet(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间
     * @param unit
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:09:32 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, Object value, long time, TimeUnit unit)
    {
        try
        {
            redisTemplate.opsForList().rightPush(key, value);
            if (time > 0) expire(key, time, unit);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:00:49 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, List<Object> value)
    {
        try
        {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:00:02 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, List<Object> value, long time)
    {
        return lSet(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * *********************************************************** <br>
     * *说明： 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间
     * @param unit
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lSet <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午6:09:59 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lSet(String key, List<Object> value, long time, TimeUnit unit)
    {
        try
        {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) expire(key, time, unit);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * *********************************************************** <br>
     * *说明： 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return <br>
     * @boolean <br>
     * @methods game.web.utils.RedisUtil#lUpdateIndex <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午5:58:40 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public boolean lUpdateIndex(String key, long index, Object value)
    {
        try
        {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * *********************************************************** <br>
     * *说明：移除N个值为value <br>
     *  移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     * @long <br>
     * @methods game.web.utils.RedisUtil#lRemove <br>
     * @author LiBencheng <br>
     * @date Created on 2021年6月3日 <br>
     * @time 下午5:58:17 <br>
     * @version 1.0 <br>
     *************************************************************          <br>
     */
    public long lRemove(String key, long count, Object value)
    {
        try
        {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return 0;
        }
    }

}
