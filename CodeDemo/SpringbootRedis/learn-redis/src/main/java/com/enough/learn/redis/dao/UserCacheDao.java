package com.enough.learn.redis.dao;

import com.enough.common.utils.JSONUtils;
import com.enough.learn.redis.entity.UserCacheObject;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @program: learn-redis
 * @description: 用户信息缓存持久层
 * @author: lidong
 * @create: 2020/01/08
 */
@Repository
public class UserCacheDao {

    /**
     * 用户编号key
     */
    private static final String KEY_PATTERN = "user:%d";
    /**
     * 注入RedisTemplate的值操作对象，这里为字符串操作类型
     */
    @Resource(name = "redisTemplate")
    private ValueOperations <String, String> operations;

    /**
     * 格式化Key
     *
     * @param key
     * @return
     */
    private static String buildKey(Integer key) {
        return String.format(KEY_PATTERN, key);
    }

    /**
     * 根据ID获取用户缓存对象信息
     *
     * @param id
     * @return
     */
    public UserCacheObject get(Integer id) {
        String key = buildKey(id);
        String value = operations.get(key);
        return JSONUtils.parseObject(value, UserCacheObject.class);
    }

    /**
     * 缓存用户缓存对象信息
     *
     * @param id
     * @param userCacheObject
     */
    public void set(Integer id, UserCacheObject userCacheObject) {
        String key = buildKey(id);
        String value = JSONUtils.toJSONString(userCacheObject);
        operations.set(key, value);
    }
}
