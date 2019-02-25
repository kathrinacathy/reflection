package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by admin on 2019/1/28 23:05
 *
 * @Author: created by admin
 * @Date: created in 23:05 2019/1/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7004_App {

    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServer7004_App.class, args);
    }
}
