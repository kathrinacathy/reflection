package com.atguigu.reflect.instancetestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//接口
interface Human {
    void info();

    void fly();
}

class  SupperMan implements Human {
    @Override
    public void info() {
        System.out.println("我是超人");
    }

    @Override
    public void fly() {
        System.out.println("我可以飞");
    }
}

class HulMan {
    public void method1() {
        System.out.println("++++++++方法一+++++++++++++");
    }

    public void method2() {
        System.out.println("++++++++方法二+++++++++++++");
    }
}

class  MyInvocationHandlered implements InvocationHandler {

    Object obj;

    public MyInvocationHandlered(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HulMan h = new HulMan();
        h.method1();
        Object invoke = method.invoke(obj, args);
        h.method2();
        return invoke;
    }
}


class MyProxy {
    public static Object createProxy (Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new MyInvocationHandlered(obj));
    }
}

public class TestAOP {
    public static void main(String[] args) {
        SupperMan s = new SupperMan();
        Human proxy = (Human) MyProxy.createProxy(s);
        proxy.info();
        proxy.fly();

        /**********************/
        NikeClothFactory  n = new NikeClothFactory();
        ClothProduct proxy1 = (ClothProduct) MyProxy.createProxy(n);
        proxy1.productCloth();
    }
}
