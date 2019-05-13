package com.atguigu.testReflect;


import com.atguigu.reflect.Person;
import org.junit.Test;

import java.io.FileDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 *测试属性
 */

public class TestFileld {

    /**
     *  获取运行时类的属性
     */
    @Test
    public void test() {

        Class<Person> cla = Person.class;
        //只能获取运行时类及父类到属性修饰符为public的属性
        Field[] fields = cla.getFields();
        for(Field field:fields){
            System.out.println(field);
        }

        /**
         * getDeclaredFields  :只能获取运行时类本身定义的属性
         */
        Field[] f2 = cla.getDeclaredFields();
        for(Field f : f2){
            System.out.println(f);
        }
    }

    /**
     * 属性的：权限修饰符，变量类型，变量名称
     * 获取属性的各个部分
     */
    @Test
    public void test2() {
        Class<Person> cla = Person.class;
        Field[] fields = cla.getDeclaredFields();
        for(Field f : fields){
            //权限修饰符
            int i = f.getModifiers();
            String s = Modifier.toString(i);
            System.out.print(s + " ");
            //变量类型
            Class<?> type = f.getType();
            System.out.print(type.getName()+" ");
            //变量名称
            String name = f.getName();
            System.out.println(name);
            System.out.println();
        }
    }


    /**
     * 获取指定类的属性
     */
    @Test
    public void test3() throws Exception {

        Class<Person> cls = Person.class;
        //获取指定的属性getField  只能获取运行时和父类中public的属性
        Field name = cls.getField("name");
        //创建运行时对象
        Person person = cls.newInstance();
        name.set(person,"王法");

        System.out.println(person);

        Object o = name.get(person);
        System.out.println(o);

        //可以获取运行时类的声明的属性，包括私有的
        Field age = cls.getDeclaredField("age");
        //由于属性修饰符的限制，可以设置setAccessible为true，就可以操作私有属性
        age.setAccessible(true);
        age.set(person,10);
        System.out.println(person);

        Field id = cls.getDeclaredField("id");
        id.setAccessible(true);
        id.set(person,111);
        System.out.println(person);//Person{name='王法', age=10, id=111}


        //调用静态方法
        Method info = cls.getMethod("info");
//        info.invoke(null);
        info.invoke(Person.class);


        //获取运行时中声明的方法
        Method method = cls.getDeclaredMethod("fly", String.class);
        method.setAccessible(true);
        Object china = method.invoke(person, "China");
        System.out.println(china);//1

    }

}

