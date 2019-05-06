package com.mybatis.cn.mybatis.config;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2019/4/25 0:28
 *
 * @Author: created by admin
 * @Date: created in 0:28 2019/4/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

@Configuration
@Data
public class JedisPoolConfigProperties {
    @Value("${jedis.pool.host}")
    private String host;
    @Value("${jedis.pool.port}")
    private Integer port;
    @Value("${jedis.pool.config.maxTotal}")
    private Integer maxTotal;
    @Value("${jedis.pool.config.maxIdle}")
    private Integer maxIdle;
    @Value("${jedis.pool.config.maxWaitMillis}")
    private Long maxWaitMillis;
    @Value("${jedis.pool.config.password}")
    private String password;

    public JedisPoolConfigProperties() {
    }

}
