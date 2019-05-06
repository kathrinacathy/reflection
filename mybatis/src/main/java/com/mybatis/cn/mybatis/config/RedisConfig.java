package com.mybatis.cn.mybatis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

/**
 * Created by admin on 2019/4/25 0:30
 *
 * @Author: created by admin
 * @Date: created in 0:30 2019/4/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@Configuration
public class RedisConfig {
    @Autowired
    private JedisPoolConfigProperties jedisPoolConfigProperties;

    @Bean
    public JedisPool getJedisPool(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(jedisPoolConfigProperties.getMaxIdle());
        jedisPoolConfig.setMaxTotal(jedisPoolConfigProperties.getMaxTotal());
        jedisPoolConfig.setMaxWaitMillis(jedisPoolConfigProperties.getMaxWaitMillis());
        return new JedisPool(jedisPoolConfig, jedisPoolConfigProperties.getHost(), jedisPoolConfigProperties.getPort(), Protocol.DEFAULT_TIMEOUT);
    }

}
