import com.mybatis.dao.EmployeeMapper;
import com.mybatis.entity.Employee;
import com.mybatis.entity.OraclePage;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * Created by admin on 2019/7/25 16:37
 *
 * @Author: created by admin
 * @Date: created in 16:37 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class MyBatisProcedureTest {
    @Test
    public void testProcedure() {
        SqlSession sqlSession = null;
        try {
            sqlSession = EmployeeTest.getSqlSession();
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            OraclePage page = new OraclePage();
            page.setStart(1);
            page.setEnd(4);
            mapper.getPageByProcedure(page);

            System.out.println("总记录数："+page.getCount());
            System.out.println("查询的数据员工有几个："+page.getEmps().size());
            System.out.println("员工数据："+page.getEmps());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }


    }
}
