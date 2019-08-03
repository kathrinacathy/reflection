package com.mybatis.dao;

import com.mybatis.entity.Employee;

import java.util.List;

/**
 * Created by admin on 2019/7/17 15:00
 *
 * @Author: created by admin
 * @Date: created in 15:00 2019/7/17
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public interface EmployeeMapperPlus {

    public Employee getEmpById(Integer id);

    public Employee getEmpAndDept(Integer id);

    public Employee getEmpAndDepByStep(Integer id);

    public List<Employee> getEmpByDeptId(Integer deptId);

    public Employee getEmpByDiscriminator(Integer id);


}
