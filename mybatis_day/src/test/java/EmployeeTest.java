import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by admin on 2019/4/22 14:33
 *
 * @Author: created by admin
 * @Date: created in 14:33 2019/4/22
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */


public class EmployeeTest {


    @Test
    public void testgetEmployeeById () throws IOException {
        SqlSession sqlSession = getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee e1 = mapper.getEmployeeById(1);

        System.out.println(e1);
    }




    //xml编程
    @Test
    public void test() throws IOException {
        SqlSession session = getSqlSession();
        try {
            Employee blog = (Employee) session.selectOne("com.mybatis.dao.EmployeeMapper.getEmployeeById", 2);
            System.out.println(blog.getClass());
            System.out.println(blog);
        } finally {
            session.close();
        }

    }

    /**
     * 接口式编程
     * @throws IOException
     */
    @Test
    public  void test2() throws IOException {
        //获取sqlSessionFactory对象
        SqlSession session = getSqlSession();

        //
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employeeById = mapper.getEmployeeById(2);
        System.out.println(mapper.getClass());
        System.out.println(employeeById);


    }

    @Test
    public void test3() throws IOException {
        //获取sqlSessionFactory对象
        SqlSession session = getSqlSession();

        //获取mapper接口，然后调用接口方法，具体的实现在配置文件中有sql
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employeeById = mapper.getEmp(2);
        System.out.println(employeeById.getClass());
        System.out.println(employeeById);
    }


    /**
     * 获取sqlSession对象
     * @return
     * @throws IOException
     */
    public static  SqlSession getSqlSession() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        //获取sqlSessin对象:这里没有传递参数，所有没有自动提交
        return sqlSessionFactory.openSession();
    }

    @Test
    public void testPageHelper() throws IOException {
        //获取sqlSessionFactory对象
        SqlSession session = getSqlSession();

        //获取mapper接口，然后调用接口方法，具体的实现在配置文件中有sql
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        //分页查询第一页，每页三条信息
        Page<Object> page = PageHelper.startPage(1, 3);

        List<Employee> emps = mapper.getEmps();
        for (Employee e: emps) {
            System.out.println(e);
        }

        System.out.println("页码大小"+page.getPageSize());
        System.out.println("总共多少记录数："+page.getTotal());

        /**************pageInfo******************/


        PageInfo<Employee> pageInfo = new PageInfo<>(emps);

        System.out.println("是否第一页"+pageInfo.isIsFirstPage());

        /********************************************************/
        PageInfo<Employee> pageInfos = new PageInfo<>(emps,4);
        System.out.println("开始连续显示页码数");

        int[] nums = pageInfos.getNavigatepageNums();
        for(int i :nums) {
            System.out.println(i);
        }
    }


}

