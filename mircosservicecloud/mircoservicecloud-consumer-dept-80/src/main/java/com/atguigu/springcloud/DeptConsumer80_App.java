package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * Created by admin on 2019/1/23 0:44
 *
 * @Author: created by admin
 * @Date: created in 0:44 2019/1/23
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(value = "microservicecloud-provider-dept",configuration = MySelfRule.class)
public class DeptConsumer80_App {

    public static void main(String[] args)
    {
        SpringApplication.run(DeptConsumer80_App.class, args);
    }
}
