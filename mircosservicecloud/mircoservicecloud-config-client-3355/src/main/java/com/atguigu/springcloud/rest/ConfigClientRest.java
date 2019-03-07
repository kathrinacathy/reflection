package com.atguigu.springcloud.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2019/2/26 17:42
 *
 * @Author: created by admin
 * @Date: created in 17:42 2019/2/26
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@RestController
public class ConfigClientRest {
    private Logger logger = LoggerFactory.getLogger(ConfigClientRest.class);

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.port}")
    private String port ;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;

    /**
     * 测试：http://localhost:8201/config
     * @return
     */
    @RequestMapping("/config")
    public String getConfig() {
        String str = "applicationName:"+applicationName+"\t eurekaServers:"+eurekaServers +"\t port:"+port;
        logger.info("str={}",str);
        return str;
    }
}
