package com.atguigu.reflect.instancetestProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.beans.EventHandler;
import java.lang.reflect.Method;

/**
 * 功能描述(cglib动态代理，cglib.jar依赖asm.jar。asm.jar专门用于生成字节码文件，做字节码类库操作)
 */

class LandLoad {
    public void rent() {
        System.out.println("中介，有房子租");
    }
}

class MyMethodInterceptor implements MethodInterceptor{
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 被代理的类不需要提供接口");
        Object o1 = methodProxy.invokeSuper(o, objects);

        return o1;
    }
}
public class TestCglibProxy {
    public static void main(String[] args) {
        MyMethodInterceptor myMethodInterceptor = new MyMethodInterceptor();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(LandLoad.class);

        enhancer.setCallback(myMethodInterceptor);
        LandLoad landLoad = (LandLoad) enhancer.create();
        landLoad.rent();
    }

}
