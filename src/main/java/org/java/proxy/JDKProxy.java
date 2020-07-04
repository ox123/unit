package org.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
    private Object target;

    public JDKProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        return ((RealHello) target).invoke();
    }

    public interface Hello {
        String say();
    }

    public static void main(String[] args) {
        JDKProxy proxy = new JDKProxy(new RealHello());
        ClassLoader classLoader = JDKProxy.class.getClassLoader();
        // 设置此环境变量，将会将生产的代理类文件保存在本地 $Proxy0.class
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Hello test = (Hello) Proxy.newProxyInstance(classLoader, new Class[]{Hello.class}, proxy);
        System.out.println(test.say());
    }
}
