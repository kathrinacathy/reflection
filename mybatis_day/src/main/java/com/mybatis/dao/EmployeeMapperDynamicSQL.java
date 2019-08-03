package com.mybatis.dao;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by admin on 2019/7/19 14:00
 *
 * @Author: created by admin
 * @Date: created in 14:00 2019/7/19
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description: 动态sql处理
 * @version:
 */

public interface EmployeeMapperDynamicSQL {
    //if  动态sql
    public List<Employee> getEmpByConditionIf(Employee employee) ;

    //trim
    public List<Employee> getEmpByConditionTrim(Employee employee);

    //choose
    public List<Employee> getEmpByConditionChoose(Employee employee);
    //set
    public Integer updateEmpByConditionSet(Employee employee);
    //foreach
    public List<Employee> getEmpByConditionForeach(@Param("ids")List<Integer> ids);
    //foreach
    public Integer addEmpByForeach(@Param("emps")List<Employee> emps) ;

    public List<Employee> getEmpTestInnerParameter(Employee employee);
}




