package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by admin on 2019/1/23 0:33
 *
 * @Author: created by admin
 * @Date: created in 0:33 2019/1/23
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@RestController
public class DeptController_Controller {
//    private static final String REST_URL_PERFIX= "http://localhost:8001";
//    private static final String REST_URL_PERFIX= "http://microservicecloud-dept:8001";
//    @Autowired
//    RestTemplate restTemplate;
private static final Logger logger  = LoggerFactory.getLogger(DeptController_Controller.class);


    @Autowired
    private DeptClientService deptClientService;
    /**
     * 使用
     * 使用restTemplate访问restful接口非常的简单粗暴无脑。
     * (url, requestMap, ResponseBean.class)这三个参数分别代表
     * REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     */

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept) {
//        return restTemplate.postForObject(REST_URL_PERFIX+"/dept/add",dept,Boolean.class);
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
//        return restTemplate.getForObject(REST_URL_PERFIX+"/dept/get/"+id,Dept.class);
        logger.info("feign get方法中参数{}",id);
        Dept dept = deptClientService.get(id);
        logger.info("返回的参数：{}",new Object[]{dept});
        return dept;
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list() {
//        return restTemplate.getForObject(REST_URL_PERFIX+"/dept/list",List.class);
        logger.info("feign list method");
        return deptClientService.list();
    }

    @RequestMapping(value = "consumer/hello")
    public String hello() {
        return "hello 80";
    }





}
