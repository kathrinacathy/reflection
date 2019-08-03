import com.mybatis.dao.EmployeeMapperDynamicSQL;
import com.mybatis.dao.EmployeeMapperPlus;
import com.mybatis.entity.Department;
import com.mybatis.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by admin on 2019/7/19 14:14
 *
 * @Author: created by admin
 * @Date: created in 14:14 2019/7/19
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class EmployeeDynamicSQLTest {

    @Test
    public void testGetEmpByConditionIf() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> employeeList = mapper.getEmpByConditionIf(new Employee(null, "%i%", "1", "%sinosoft%@cpic.cn"));
            for (Employee e:employeeList) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testGetEmpByConditionTrim() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> employeeList = mapper.getEmpByConditionTrim(new Employee(null, "%i%", null, null));

            for (Employee e:employeeList) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testGetEmpByConditionChoose() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> employeeList = mapper.getEmpByConditionChoose(new Employee(2, "%i%", null, null));

            for (Employee e:employeeList) {
                System.out.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void  testUdateEmpByConditionSet() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            Integer count = mapper.updateEmpByConditionSet(new Employee(2, "Admin", null, null));
            System.out.println(count);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void  testGetEmpByConditionForeach() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = mapper.getEmpByConditionForeach(Arrays.asList(2, 11, 14));
            for(Employee e: emps) {
                System.out.println(e);
            }
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void  TestAddEmpByForeach() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> emps = new ArrayList<>();
            Employee smith = new Employee(null, "text003", "1", "bababa@com.cn", new Department(1));
            Employee allen = new Employee(null, "text004", "0", "catca@com.cn", new Department(2));
            emps.add(smith);
            emps.add(allen);

            Integer count = mapper.addEmpByForeach(emps);
            System.out.println(count);
            sqlSession.commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void  testGetEmpTestInnerParameter() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapperDynamicSQL mapper = sqlSession.getMapper(EmployeeMapperDynamicSQL.class);
            List<Employee> employees = mapper.getEmpTestInnerParameter(new Employee(null,"t","0","catca@com.cn"));
            for(Employee e: employees) {
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }


}

