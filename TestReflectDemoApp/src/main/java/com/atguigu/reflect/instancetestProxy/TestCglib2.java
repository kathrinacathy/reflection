package com.atguigu.reflect.instancetestProxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

interface UserManager {
    public void addUser(String id, String password);
    public void delUser(String id);
}

class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String id, String password) {
        System.out.println("调用了UserManagerImpl.addUser()方法！");
    }

    @Override
    public void delUser(String id) {
        System.out.println("调用了UserManagerImpl.delUser()方法！");
    }
}

class CGLibProxy implements MethodInterceptor {
    // CGlib需要代理的目标对象
    private Object targetObject;

    public Object createProxyObject(Object obj) {
        this.targetObject = obj;
        //返回代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        Object proxyObj = enhancer.create();
        return proxyObj;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object obj = null;
        // 过滤方法
        if ("addUser".equals(method.getName())) {
            // 检查权限
            checkPopedom();
        }
        obj = method.invoke(targetObject, args);
        return obj;


    }
    private void checkPopedom() {
        System.out.println("检查权限：checkPopedom()!");
    }
}

public class TestCglib2 {
    public static void main(String[] args) {
        UserManagerImpl u = new UserManagerImpl();
        CGLibProxy cgLibProxy = new CGLibProxy();
        UserManager proxyObject = (UserManager) cgLibProxy.createProxyObject(u);
        proxyObject.addUser("1","123456");
        proxyObject.delUser("1");
    }

}
