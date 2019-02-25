package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by admin on 2019/1/23 0:28
 *
 * @Author: created by admin
 * @Date: created in 0:28 2019/1/23
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@Configuration
public class ConfigBean {

    @Bean
    @LoadBalanced
    public RestTemplate myRestTemplate() {
        return  new RestTemplate();
    }
}
