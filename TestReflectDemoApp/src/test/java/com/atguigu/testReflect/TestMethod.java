package com.atguigu.testReflect;

import com.atguigu.reflect.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.sql.Types;

/**
 * Created by admin on 2019/5/10 17:26
 *
 * @Author: created by admin
 * @Date: created in 17:26 2019/5/10
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

public class TestMethod {

    @Test
    public void test() {
        Class<Person> cla = Person.class;
        //获取运行时类中和父类中声明为public的方法
        Method[] methods = cla.getMethods();
        for(Method m:methods){
            System.out.println(m);
        }

        System.out.println();
        //获取运行时类本身声明的方法
        Method[] methods1 = cla.getDeclaredMethods();
        for(Method t : methods1){
            System.out.println(t);
        }
    }


    //注解，修饰符，返回值类型，方法名，形参列表，异常等
    @Test
    public void test2() {
        Class<Person> cla = Person.class;
        Method[] methods1 = cla.getDeclaredMethods();
        for(Method t : methods1){
            //注解
            Annotation[] as = t.getAnnotations();
            for(Annotation a :as){
                System.out.println(a);
            }

            //修饰符
            int modifiers = t.getModifiers();
            System.out.print(Modifier.toString(modifiers) +" ");

            //返回值类型
            Class<?> type = t.getReturnType();
            System.out.print(type+" ");
            //方法名
            System.out.print(t.getName()+" ");
            //形参列表
            System.out.print("(");
            Class<?>[] types = t.getParameterTypes();
            for(Class c : types){
                System.out.print(c.getName() +" ");
            }
            Parameter[] parameters = t.getParameters();
            for(Parameter p : parameters){
                System.out.print(p.getName()+" ");
            }
            System.out.print(")");

            //异常
            Class<?>[] types1 = t.getExceptionTypes();
            if(types1.length>0){
                System.out.print("throws  " );
            }
            for(Class c: types1){
                System.out.print(c.getName());
            }

            //
            Type[] types2 = t.getGenericExceptionTypes();

            for(Type c: types2){
                System.out.println(c.getTypeName());
            }
            System.out.println();
        }
    }


    /**
     * 获取运行时类的指定方法
     */
    @Test
    public void test3() throws Exception {

        Class<Person> cls = Person.class;
         //获取运行类中和父类中public的声明的方法
        Method show = cls.getMethod("show");
        System.out.println(show.getName());

        Person p = cls.newInstance();
        //调用方法
        Object invoke = show.invoke(p);
        System.out.println(invoke);

        Method toString = cls.getMethod("toString");
        Object invoke1 = toString.invoke(p);
        System.out.println(invoke1);

    }
}
