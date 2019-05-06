package com.mybatis.cn.mybatis;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by admin on 2019/4/25 0:40
 *
 * @Author: created by admin
 * @Date: created in 0:40 2019/4/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class RedisLimitAspect {
    @Autowired
    private JedisPool jedisPool;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Around("execution(* com.example.redislock..*(..)) && @annotation(redisLimiter)")
    public Object redisLimiter(ProceedingJoinPoint proceedingJoinPoint, RedisLimiter redisLimiter){
        try(Jedis jedis = jedisPool.getResource()) {
            if (RedisLimitTool.limit(jedis, redisLimiter.keyPrefix(), redisLimiter.limit())){
                return proceedingJoinPoint.proceed();
            }else {
                logger.error("限流：" + redisLimiter.keyPrefix());
                return null;
            }
        } catch (Throwable throwable) {
            logger.error(ExceptionUtils.getFullStackTrace(throwable));
        }
        return null;
    }

    /*  实际使用：
    @RedisLimiter(keyPrefix = "testLimit", limit = "10")
    public Object testLimit(){
        return null;
    }*/

}
