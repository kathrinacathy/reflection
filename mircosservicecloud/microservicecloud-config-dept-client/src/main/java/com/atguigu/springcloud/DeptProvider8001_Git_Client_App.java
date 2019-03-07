package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by admin on 2019/1/10 0:55
 *
 * @Author: created by admin
 * @Date: created in 0:55 2019/1/10
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@EnableEurekaClient
@SpringBootApplication
public class DeptProvider8001_Git_Client_App {
    public static void main(String[] args)
    {
        SpringApplication.run(DeptProvider8001_Git_Client_App.class, args);
    }

}
