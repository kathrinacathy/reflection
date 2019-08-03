package com.mybatis.dao;

import com.mybatis.entity.Employee;
import org.apache.ibatis.annotations.Select;

/**
 * Created by admin on 2019/6/19 16:22
 *
 * @Author: created by admin
 * @Date: created in 16:22 2019/6/19
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public interface EmployeeMapperAnnotation {

    @Select(" select * from employee where id =#{id} ")
    public Employee getEmployeeById(Integer id);
}
