package com.mybatis.dao;

import com.mybatis.entity.Employee;
import com.mybatis.entity.OraclePage;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2019/4/22 15:02
 *
 * @Author: created by admin
 * @Date: created in 15:02 2019/4/22
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    Employee getEmp(Integer id);


    //mybatis中增删改可以有返回值：int long  boolean 以及相应的包装类
    public void addEmployee(Employee e);

    public void updateEmployee(Employee e);

    public void deleteEmployee(Integer id);

    //多个参数的处理
    public Employee getEmpByIdAndLastName(Integer id,String lastName);

    public Employee getEmpByMap(Map<String,Object> conditions);

    //返回List
    public List<Employee> getempByLastNameLike(String lastName);

    //返回一条记录，key是列明，值就是对应的值
    public Map<String,Object> getEmpByIdReturnMap(Integer id);

    //多条记录map:key是指定某个列，值就是javaeBean对象
    //@Mapkey告诉mabatis中用哪个javabean的属性作为封装的key
    @MapKey("id")
    public Map<Integer ,Employee> getEmpListByLastNameLikeReturnMap(String lastName);

    //查询所有的员工数据
    public List<Employee> getEmps ();


    //调用存储过程查询分页
    public void getPageByProcedure(OraclePage page);

}
