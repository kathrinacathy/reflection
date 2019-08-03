import com.mybatis.dao.EmployeeMapper;
import com.mybatis.dao.EmployeeMapperPlus;
import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by admin on 2019/7/17 15:25
 *
 * @Author: created by admin
 * @Date: created in 15:25 2019/7/17
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class EmployeeTestResultMap {


    @Test
    public void testGetEmpListByLastNameLikeReturnMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee emp = mapper.getEmpById(2);
            System.out.println(emp);


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


    @Test
    public void testGetEmpAndDept() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee andDept = mapper.getEmpAndDept(14);
            System.out.println(andDept);
            System.out.println(andDept.getDept());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testGetEmpAndDepByStep() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);
            Employee andDept = mapper.getEmpAndDepByStep(2);
            System.out.println(andDept);
            System.out.println(andDept.getDept());

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testgGtEmpByDiscriminator() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperPlus mapper = sqlSession.getMapper(EmployeeMapperPlus.class);

            Employee emp = mapper.getEmpByDiscriminator(14);
            System.out.println(emp);
            System.out.println(emp.getDept());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


}
