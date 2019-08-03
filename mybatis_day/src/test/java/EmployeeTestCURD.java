import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by admin on 2019/7/10 16:48
 *
 * @Author: created by admin
 * @Date: created in 16:48 2019/7/10
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class EmployeeTestCURD {

    @Test
    public void testdelete() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            mapper.deleteEmployee(1);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(3, "kathria", "0", "sinosoft@cpic.cn");
//            mapper.addEmployee(employee);
            mapper.updateEmployee(employee);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }

    }


    @Test
    public void testInsert()  {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = new Employee(null, "sinosoft001", null, "sinosoft001@cpic.cn");
            mapper.addEmployee(employee);
            System.out.println(employee.getId());


            //提交
            sqlSession.commit();


        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }



    }


    @Test
    public void testBatch() {
        SqlSession sqlSession = null;
        try {

            String config = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(config);
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = factory.openSession(ExecutorType.BATCH);

//            sqlSession = EmployeeTest.getSqlSession();



            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            long start = System.currentTimeMillis();
            for(int i = 0;i<1000;i++) {
                mapper.addEmployee(new Employee(null, UUID.randomUUID().toString().substring(0,6), "1", "sinosoft001@cpic.cn"));
            }

            //提交
            sqlSession.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间："+(end-start));

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }

}
