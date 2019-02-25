package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.Dept;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by admin on 2019/1/30 11:36
 *
 * @Author: created by admin
 * @Date: created in 11:36 2019/1/30
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

@FeignClient(value = "microservicecloud-provider-dept",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {


    @RequestMapping(value="/dept/add",method=RequestMethod.POST)
    public boolean add(Dept dept);

    @RequestMapping(value="/dept/get/{id}",method=RequestMethod.GET)
    public Dept get(@PathVariable("id")Long id);

    @RequestMapping(value="/dept/list",method=RequestMethod.GET)
    public List<Dept> list();

}
