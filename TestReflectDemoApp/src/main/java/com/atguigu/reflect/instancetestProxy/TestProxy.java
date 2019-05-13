package com.atguigu.reflect.instancetestProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//接口
interface Subject {
    void action();
}

//被代理类
class  RealSubject implements  Subject{
    @Override
    public void action() {
        System.out.println("我是被代理类，记得执行我");
    }
}

class MyInvocationHandler implements InvocationHandler {

    Object obj;//实现了接口的被代理类的声明

    //1 给被代理类实例化，2 返回一个代理类对象
    public Object banding (Object obj) {
        this.obj= obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),this);
    }

    //当通过代理类对象发起对被重写方法的调用时，实际转化为对如下方法的调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}

public class TestProxy {
    public static void main(String[] args) {
        //被代理类对象
        RealSubject realSubject = new RealSubject();
        //创建一个实现InvocationHandler接口的对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        //调用banding方法动态返回一个同样实现real所在类实现的接口Subject的代理对象
        Subject banding = (Subject) myInvocationHandler.banding(realSubject);//此时banding就是代理对象
        banding.action();

        //再举一个例
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothProduct banding1 = (ClothProduct) myInvocationHandler.banding(nikeClothFactory);
        banding1.productCloth();

    }
}
