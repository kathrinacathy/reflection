import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entity.Employee;
import com.mybatis.entity.OraclePage;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by admin on 2019/7/25 17:28
 *
 * @Author: created by admin
 * @Date: created in 17:28 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class MyBatisTypeHandlerTest {
    @Test
    public void testTypeHander() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "testEnum", "1", "testEnum@com.cn");
//            mapper.addEmployee(employee);
//            System.out.println("保存成功"+employee.getId());
//            sqlSession.commit();

            Employee emp = mapper.getEmp(37);
            System.out.println(emp.getEmpStatus());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }


    }
}
