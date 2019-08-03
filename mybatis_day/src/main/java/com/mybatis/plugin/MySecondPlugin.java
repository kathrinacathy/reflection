package com.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Statement;
import java.util.Properties;

/**
 * Created by admin on 2019/7/25 13:50
 *
 * @Author: created by admin
 * @Date: created in 13:50 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */

@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args=Statement.class)
})
public class MySecondPlugin implements Interceptor {


    /**
     * 这段代码的意图就是理解运行过程
     * 首先配置的插件是first 然后是second
     * 所有在生成代理对象的时候会吃产生多级代理，并且最里面的事真是的被代理对象，然后是first代理对象，然后是secode代理对象
     *
     * 但是在执行的时候是外面的second代理对象先执行设置参数的，这里的代码获取的参数就是测试方法传递的2，然后通过while循环，找到最里面的被代理对象
     * 将参数更改为5，执行设置参数，放行后开始执行first的插件，此时获取的目标对象参数是5，经过更改然后改为4，最后执行的就是查询4号员工的信息
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MySecondPlugin....intercept:"+invocation.getMethod());

        //动态改变传入的参数，以前运行的事1号员工，现在改为3号员工
        //被拦截的对象
        Object target = invocation.getTarget();
        System.out.println("被拦截的目标对象是："+target);
        //获取目标对象的原始数据
//        MetaObject metaObject = SystemMetaObject.forObject(target);

        //拿到：StatementHandler==>ParameterHandler===>parameterObject
//        Object value = metaObject.getValue("parameterHandler.parameterObject");
//        System.out.println("sql语句用的参数是："+value);
        //修改完sql语句要用的参数
//        metaObject.setValue("parameterHandler.parameterObject", 3);
        //执行目标方法




        /*******************while 循环分离出最终被代理对象，从而方便提取信息*******************************/

//1、分离代理对象。由于会形成多次代理，所以需要通过一个

        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = null;
        while (metaObject.hasGetter("h")) {
            Object h = metaObject.getValue("h");
            metaObject = SystemMetaObject.forObject(h);
        }
//2、获取到代理对象中包含的被代理的真实对象
        Object obj = metaObject.getValue("target");
//3、获取被代理对象的MetaObject(元数据)方便进行信息提取，这里获取的就是最里面包装的真是被代理对象数据
        MetaObject forObject = SystemMetaObject.forObject(obj);
        value = forObject.getValue("parameterHandler.parameterObject");
        System.out.println("最里面包装的真是代理对象用到的参数是："+value);

        forObject.setValue("parameterHandler.parameterObject",5);

        Object proceed = invocation.proceed();
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("MySecondPlugin....plugin代理："+target);
        Object wrap = Plugin.wrap(target, this);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("MySecondPlugin...setProperties:" +properties);

    }
}
