package com.atguigu.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by admin on 2019/1/30 19:27
 *
 * @Author: created by admin
 * @Date: created in 19:27 2019/1/30
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@Configuration
public class MySelfRule {

    //指定系统有的负载均衡算法
    @Bean
    public IRule myRule () {
//        return new RandomRule();
        return new RandomRule_YB();
    }

}
