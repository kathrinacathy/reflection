package com.mybatis.cn;

import com.mybatis.cn.mybatis.RedisLockTool;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by admin on 2019/4/25 0:34
 *
 * @Author: created by admin
 * @Date: created in 0:34 2019/4/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class RedisTest {
    private Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private JedisPool jedisPool;

    @Test
    public void test() {


        try (Jedis jedis = jedisPool.getResource()) {
            boolean b = RedisLockTool.tryGetDistributedLock(jedis, "", "", 1000);
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.error(ExceptionUtils.getFullStackTrace(e));
        }

    }
}
