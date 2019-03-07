package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by admin on 2019/2/26 12:32
 *
 * @Author: created by admin
 * @Date: created in 12:32 2019/2/26
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

/**
 * 配置文件读取规则：
 * /{application}/{profile}/{label}   比如 application/dev/master
 * /{application}-{profile}.yml           application-dev.yml
 * /{label}/{application}-{profile}.yml   master/application-dev.yml
 * /{application}-{profile}.properties
 * /{label}/{application}-{profile}.properties
 */
@SpringBootApplication

public class Config_3355_StartSpringCloud_Client_App {

    public static void main(String[] args)
    {
        SpringApplication.run(Config_3355_StartSpringCloud_Client_App.class, args);
    }
}
