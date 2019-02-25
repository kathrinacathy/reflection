package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Created by admin on 2019/1/28 20:21
 *
 * @Author: created by admin
 * @Date: created in 20:21 2019/1/28
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7003_App {

    public static void main(String[] args)
    {
        SpringApplication.run(EurekaServer7003_App.class, args);
    }
}
