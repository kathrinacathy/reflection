import com.mybatis.daoTest.EmployeeMapper;
import com.mybatis.entityDao.Employee;
import com.mybatis.entityDao.EmployeeExample;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by admin on 2019/7/25 11:06
 *
 * @Author: created by admin
 * @Date: created in 11:06 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class MyBatisPluginTest {

    /**
     * 插件原理
     * 在四大对象创建的时候
     * 1、每个创建出来的对象不是直接返回的，而是
     * 		interceptorChain.pluginAll(parameterHandler);
     * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；
     * 		调用interceptor.plugin(target);返回target包装后的对象
     * 3、插件机制，我们可以使用插件为目标对象创建一个代理对象；AOP（面向切面）
     * 		我们的插件可以为四大对象创建出代理对象；
     * 		代理对象就可以拦截到四大对象的每一个执行；
     *
     public Object pluginAll(Object target) {
     for (Interceptor interceptor : interceptors) {
     target = interceptor.plugin(target);
     }
     return target;
     }

     */
    /**
     * 插件编写：
     * 1、编写Interceptor的实现类
     * 2、使用@Intercepts注解完成插件签名
     * 3、将写好的插件注册到全局配置文件中
     *
     */
    @Test
    public void testPlugin(){

        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //xxxExapmle就是查询条件
            //1 查询所有员工
            List<Employee> employees = mapper.selectByExample(null);
            for(com.mybatis.entityDao.Employee e: employees) {
                System.out.println(e);
            }
            System.out.println("******************************");
            //2 查询员工中名称带有i的并且性别是1的员工

            EmployeeExample example = new EmployeeExample();
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%i%");
            criteria.andGenderEqualTo("1");

            EmployeeExample.Criteria criteria1 = example.createCriteria();
            criteria1.andEmailLike("e");

            example.or(criteria1);
            List<com.mybatis.entityDao.Employee> employeeList = mapper.selectByExample(example);
            for(com.mybatis.entityDao.Employee e: employeeList) {
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }
}
