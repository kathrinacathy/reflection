package com.mybatis.dao;

import com.mybatis.entity.Department;

/**
 * Created by admin on 2019/7/18 14:04
 *
 * @Author: created by admin
 * @Date: created in 14:04 2019/7/18
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public interface DepartmentMapper {

    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department geteptByIdStep(Integer id);
}
