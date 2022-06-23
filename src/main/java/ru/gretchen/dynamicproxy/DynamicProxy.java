package ru.gretchen.dynamicproxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class DynamicProxy {
    private Object targetObject;
    private Object createProxy(Object obj){
        targetObject = obj;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(targetObject.getClass());
        enhancer.setCallback(new MyHandler());
        return enhancer.create();
    }
    class MyHandler implements MethodInterceptor{
        @Override
        public Object intercept(Object arg0, Method method, Object[] args,
                                MethodProxy arg3) throws Throwable {
            Object returnValue = method.invoke(targetObject, args);
            return returnValue;
        }
    }
}