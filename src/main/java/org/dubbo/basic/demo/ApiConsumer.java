package org.dubbo.basic.demo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.RpcContext;

public class ApiConsumer {
    public static void main(String[] args) {
        try {
            ReferenceConfig<GreetingService> referenceConfig = new ReferenceConfig<GreetingService>();
            referenceConfig.setApplication(new ApplicationConfig("first-dubbo-consumer"));
            referenceConfig.setRegistry(new RegistryConfig("zookeeper://106.15.234.239:2181"));
            referenceConfig.setInterface(GreetingService.class);
            referenceConfig.setTimeout(5000);
            referenceConfig.setGroup("duboo");
            referenceConfig.setVersion("1.0");
            GreetingService greetingService = referenceConfig.get();
            RpcContext.getContext().setAttachment("company", "aaaa");
            System.out.println(greetingService.sayHello("world"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
