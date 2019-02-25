package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Created by admin on 2019/1/31 23:38
 *
 * @Author: created by admin
 * @Date: created in 23:38 2019/1/31
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class Zuul_9527_StartSpringCloudApp {

    public static void main(String[] args)
    {
        SpringApplication.run(Zuul_9527_StartSpringCloudApp.class, args);
    }
}
