package com.atguigu.testReflect;

import com.atguigu.reflect.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by admin on 2019/5/13 21:41
 *
 * @Author: created by admin
 * @Date: created in 21:41 2019/5/13
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class TestOther  {

    //获取运行时的父类
    @Test
    public void test() {
        Class<Person> cls = Person.class;
        Class<? super Person> aClass = cls.getSuperclass();
        System.out.println(aClass);
    }

    //获取带泛型的父类getGenericSuperclass
    @Test
    public void test2() {
        Class<Person> cls = Person.class;
        Type type = cls.getGenericSuperclass();
        System.out.println(type);

    }

    //获取父类的泛型
    @Test
    public void test3() {
        Class<Person> cls = Person.class;
        Type type = cls.getGenericSuperclass();
        ParameterizedType t = (ParameterizedType)type;
        Type[] arguments = t.getActualTypeArguments();
        System.out.println(arguments.getClass());

        String typeName = arguments[0].getTypeName();
        //得到弗雷德泛型
        System.out.println(typeName);
        for(Type m:arguments) {
            System.out.println(m);
        }

    }

    /**
     * 获取实现的接口
     */
    @Test
    public void test4() {

        Class<Person> cls = Person.class;
        Class<?>[] interfaces = cls.getInterfaces();
        for(Class c: interfaces) {
            System.out.println(c);
        }
    }

    /**
     * 获取所在包
     */
    @Test
    public void test5() {

        Class<Person> cls = Person.class;
        Package aPackage = cls.getPackage();

        System.out.println(aPackage);
    }

    /**
     * 获取类注解
     */
    @Test
    public void test6() {

        Class<Person> cls = Person.class;
        Annotation[] annotations = cls.getAnnotations();
        for(Annotation a : annotations) {
            System.out.println(a );
        }
    }
}
