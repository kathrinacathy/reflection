package com.mybatis.cn.mybatis;

import redis.clients.jedis.Jedis;

import java.util.Collections;

/**
 * Created by admin on 2019/4/25 0:31
 *
 * @Author: created by admin
 * @Date: created in 0:31 2019/4/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:基于redis的分布式锁
 */

public class RedisLockTool {
    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    /**
     * ms
     */
    private static final String PX_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁  利用redis set值的 NX参数
     * @param jedis reids客户端
     * @param lockKey 锁
     * @param requestId 锁拥有者标识
     * @param expireTime 超期时间 ms为单位
     * @return
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime){
        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, PX_EXPIRE_TIME, expireTime);
        return LOCK_SUCCESS.equals(result);
    }

    /**
     * 释放分布式锁  利用 LUA脚本保证操作的原子性（Redis单进程单线程并保证执行LUA脚本时不执行其它命令）
     * @param jedis redis客户端
     * @param lockKey 锁
     * @param requestId 锁拥有者标识
     * @return
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId){
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object res = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        return RELEASE_SUCCESS.equals(res);
    }


}
