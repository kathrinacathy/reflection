package com.atguigu;

import com.atguigu.reflect.Person;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * Created by admin on 2019/5/10 10:21
 *
 * @Author: created by admin
 * @Date: created in 10:21 2019/5/10
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class reflectTest {


    @Test
    public void test3() throws ClassNotFoundException, IOException {

        ClassLoader load1 = ClassLoader.getSystemClassLoader();
        System.out.println(load1);//sun.misc.Launcher$AppClassLoader@18b4aac2


        ClassLoader load2 = load1.getParent();
        System.out.println(load2); //sun.misc.Launcher$ExtClassLoader@54bedef2


        ClassLoader load3 = load2.getParent();
        System.out.println(load3);//null

        String clasName  = "java.lang.String";
        Class<?> forName = Class.forName(clasName);
        ClassLoader load4 = forName.getClassLoader();
        System.out.println(load4);//sun.misc.Launcher$AppClassLoader@18b4aac2


        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        Class<?> aClass = classLoader.loadClass("com.atguigu.reflect.Person");
        System.out.println(aClass);

        //掌握加载配文文件
        ClassLoader classLoader1 = this.getClass().getClassLoader();
        FileInputStream ins = new FileInputStream(new File("jdbc.peoperties"));
        InputStream in = classLoader1.getResourceAsStream("com\\atguigu\\reflect\\jdbc.properties");
        Properties p = new Properties();
        p.load(in);
        String user = p.getProperty("user");
        String pwd = p.getProperty("pwd");
        System.out.println(user);
        System.out.println(pwd);


    }


    @Test
    public void test2() {
        Person p = new Person();
        Class<? extends Person> cla = p.getClass();
        //获取对应的Class对象，通过这个源头就可对这个类进行各种操作
        System.out.println(cla);
    }




    /**
     * 反射的应用
     * @throws Exception
     */
    @Test
    public void testReflect() throws Exception {

        Class<Person> cla = Person.class;
        //1 创建cla对应的运行时对象
        Person p =  cla.newInstance();
        //2 通过反射调用指定属性
        //2.1
        Field f1 = cla.getField("name");
        f1.set(p,"liudehua");
        System.out.println(p);

        //2.2
        Field f2 = cla.getDeclaredField("age");
        f2.setAccessible(true);
        f2.set(p,20);
        System.out.println(p);
        //3 通过反射调用运行时类的方法
        Method m1 = cla.getMethod("show");
        m1.invoke(p);

        Method m2 = cla.getMethod("display", String.class);
         m2.invoke(p, "CK");
        System.out.println(p);


    }


    @Test
    public void test()  {

        Person p = new Person();
        p.setAge(10);
        p.setName("tangwei");
        System.out.println(p);
        p.show();
//        p.display("HK");

    }
}
