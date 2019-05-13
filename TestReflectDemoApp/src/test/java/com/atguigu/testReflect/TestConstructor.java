package com.atguigu.testReflect;


import com.atguigu.reflect.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by admin on 2019/5/13 21:25
 *
 * @Author: created by admin
 * @Date: created in 21:25 2019/5/13
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class TestConstructor {
    @Test
    public void test() throws Exception {
        Class<?> cls = Class.forName("com.atguigu.reflect.Person");
        //创建对应的运行时对象，使用newInstance方法实际上调用的运行时类的空参构造器
        //要想能能创建成功，必须有空参构造器，并且权限要能足够
        Object o = cls.newInstance();
        Person p = (Person)o;
        System.out.println(p);
    }

    @Test
    public void  test1() throws Exception {
        //获取声明的所有的构造器
        Class<?> cls = Class.forName("com.atguigu.reflect.Person");
        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        for(Constructor c:constructors){
            System.out.println(c);
        }
    }

    /**
     * 获取指定的构造器
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        Class<?> cls = Class.forName("com.atguigu.reflect.Person");
        Constructor constructor = cls.getDeclaredConstructor(String.class, int.class);
        constructor.setAccessible(true);
        Person person = (Person) constructor.newInstance("王五", 23);
        System.out.println(person);
    }
}
