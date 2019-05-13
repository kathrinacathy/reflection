package com.atguigu;

import com.atguigu.reflect.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;


public class reflectTest1  {


    @Test
    public void test() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Class<?> cla = Class.forName("com.atguigu.reflect.Person");
        Person o = (Person)cla.newInstance();
        Constructor<?> constructor = cla.getDeclaredConstructor();
        

    }
}
