package com.mybatis.cn.mybatis;

import com.mybatis.cn.mybatis.entity.Employee;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisApplicationTests {

    //根据xml文件创建一个sqlSessionFactory对象 ·
    @Test
    public void contextLoads() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);


        SqlSession session = sqlSessionFactory.openSession();
        try {
            Employee blog = (Employee) session.selectOne("com.mybatis.cn.Employee.selectEmp", 1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }

}
