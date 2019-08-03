import com.mybatis.dao.EmployeeMapperDynamicSQL;
import com.mybatis.daoTest.EmployeeMapper;
import com.mybatis.entity.Employee;
import com.mybatis.entityDao.EmployeeExample;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/7/24 16:29
 *
 * @Author: created by admin
 * @Date: created in 16:29 2019/7/24
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class MyBatisGeneratorTest {

    @Test
    public void test() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File("G:\\ideaIU-2018.1.4.win\\IdeaProjects\\mybatis_day\\src\\main\\resources\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }



    @Test
    public void testMyBatis3() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            //xxxExapmle就是查询条件
            //1 查询所有员工
            List<com.mybatis.entityDao.Employee> employees = mapper.selectByExample(null);
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
