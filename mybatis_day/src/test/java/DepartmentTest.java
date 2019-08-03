import com.mybatis.dao.DepartmentMapper;
import com.mybatis.dao.EmployeeMapperPlus;
import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by admin on 2019/7/18 17:28
 *
 * @Author: created by admin
 * @Date: created in 17:28 2019/7/18
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class DepartmentTest {


    @Test
    public void TestGetDeptById() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptById(2);
            System.out.println(dept);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


    @Test
    public void testGetDeptByIdPlus() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department andDept = mapper.getDeptByIdPlus(1);
            System.out.println(andDept);
            System.out.println(andDept.getEmps());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
    @Test
    public void TestGeteptByIdStep() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department department = mapper.geteptByIdStep(2);
            System.out.println(department);
            System.out.println(department.getEmps());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


}
