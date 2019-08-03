import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by admin on 2019/7/11 14:01
 *
 * @Author: created by admin
 * @Date: created in 14:01 2019/7/11
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class EmployeeTestResult {

    @Test
    public  void testOneParame() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp = mapper.getEmp(4);
            System.out.println(emp);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }

    }

    @Test
    public void testMorePareme() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee emp = mapper.getEmpByIdAndLastName(4, "sinosoft");
            System.out.println(emp);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


    @Test
    public void testParemeMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String, Object> conditions = new HashMap<>();
            conditions.put("id",2);
            conditions.put("lastName","sinosoft");
            conditions.put("tableName","tb_employee");
            conditions.put("order","desc");
            Employee emp = mapper.getEmpByMap(conditions);
            System.out.println(emp);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }



    @Test
    public void testgetempByLastNameLike() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            List<Employee> employeeList = mapper.getempByLastNameLike("%i%");
            for (Employee e: employeeList) {
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


    @Test
    public  void testGetEmpByIdReturnMap() {

        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<String, Object> returnMap = mapper.getEmpByIdReturnMap(11);
            System.out.println(returnMap);
            for(Map.Entry<String,Object> e:returnMap.entrySet()) {
                System.out.print("key:"+e.getKey()+",value:"+e.getValue());
            }
            System.out.println("");
            Set<String> keys = returnMap.keySet();
            for(String s:keys) {
                System.out.print("key:"+s+",value:"+returnMap.get(s));
            }
            System.out.println("");

            Collection<Object> values = returnMap.values();
            for(Object o : values) {
                System.out.print("value:"+o);
            }

            System.out.println("");
            Iterator<Map.Entry<String, Object>> iterator = returnMap.entrySet().iterator();
            while(iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                System.out.print("key:"+next.getKey()+",value:"+next.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


    @Test
    public void testGetEmpListByLastNameLikeReturnMap() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Map<Integer, Employee> map = mapper.getEmpListByLastNameLikeReturnMap("%in%");
            System.out.println(map);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }


}
