package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * Created by admin on 2019/1/31 22:17
 *
 * @Author: created by admin
 * @Date: created in 22:17 2019/1/31
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumer_DashBoard_App {

    public static void main(String[] args)
    {
        SpringApplication.run(DeptConsumer_DashBoard_App.class, args);
    }
}
