package org.dubbo.basic.demo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

public class ApiProvider {
    public static void main(String[] args) throws IOException {
//        ServiceConfig
        ServiceConfig serviceConfig = new ServiceConfig<GreetingService>();
        serviceConfig.setApplication(new ApplicationConfig("first-dubbo-app"));
        RegistryConfig registryConfig = new RegistryConfig("zookeeper://106.15.234.239:2181");
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(GreetingService.class);
        serviceConfig.setRef(new GreetingServiceImpl());
        serviceConfig.setVersion("1.0");
        serviceConfig.setGroup("group");
        serviceConfig.export();
        System.out.println("app started");
        System.in.read();
    }
}
