package org.dubbo.basic.demo;

public interface GreetingService {
    String sayHello(String name);

    Result<String> testGeneric(POJO pojo);

}
