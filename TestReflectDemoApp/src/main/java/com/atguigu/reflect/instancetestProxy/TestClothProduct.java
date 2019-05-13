package com.atguigu.reflect.instancetestProxy;

/**
 * Created by admin on 2019/5/13 22:52
 *
 * @Author: created by admin
 * @Date: created in 22:52 2019/5/13
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:代理设计模式的原理:
 *      使用一个代理将对象包装起来, 然后用该代理对象取代原始对象. 任何对原始对象的调用都要通过代理. 代理对象决定是否以及何时将方法调用转到原始对象上
 * @version:
 */

//静态代理：

/**
 * 接口
 */
interface ClothProduct {
    void productCloth();
}

/**
 * 接口实现类
 */
class NikeClothFactory implements  ClothProduct{
    @Override
    public void productCloth() {
        System.out.println("Nike工厂生产衣服 ");
    }
}

//代理类
class ProxyFactory implements ClothProduct{
    NikeClothFactory nikeClothFactory;

    //代理类执行
    public ProxyFactory(NikeClothFactory nikeClothFactory) {
        System.out.println("代理类执行");
        this.nikeClothFactory = nikeClothFactory;
    }

    @Override
    public void productCloth() {
        nikeClothFactory.productCloth();
    }
}

public class TestClothProduct {
    public static void main(String[] args) {
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ProxyFactory proxyFactory = new ProxyFactory(nikeClothFactory);
        proxyFactory.productCloth();
    }
}
