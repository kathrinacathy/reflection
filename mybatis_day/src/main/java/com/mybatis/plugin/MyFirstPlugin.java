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
 * Created by admin on 2019/7/25 11:08
 *
 * @Author: created by admin
 * @Date: created in 11:08 2019/7/25
 * @param: bindingResult
 * @param: result
 * @return:
 * @throws:
 * @Description:
 * @version:
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "parameterize",args=Statement.class)
}
)
public class MyFirstPlugin implements Interceptor {

    /**
     * 拦截的方式
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("MyFirstPlugin....拦截的方法intercept:"+invocation.getMethod());
        //执行方法，放行** 实际实行是这里的方法执行目标方法

        Object target = invocation.getTarget();

        MetaObject metaObject = SystemMetaObject.forObject(target);
        System.out.println("first拦截器的参数"+metaObject.getValue("parameterHandler.parameterObject"));
        metaObject.setValue("parameterHandler.parameterObject", 4);
        Object proceed = invocation.proceed();
        return proceed;
    }

    /**
     * 创建包装类，也就是代理对象
     * 包装目标对象的：包装：为目标对象创建一个代理对象
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象
        System.out.println("MyFirstPlugin...plugin:mybatis将要包装的对象"+target);
        Object wrap = Plugin.wrap(target, this);
        //返回为当前target创建的动态代理
        return wrap;
    }

    /**
     *setProperties：
     * 	 * 		将插件注册时 的property属性设置进来
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息："+properties);
    }
}
