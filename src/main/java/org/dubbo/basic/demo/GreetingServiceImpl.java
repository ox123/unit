package org.dubbo.basic.demo;

import org.apache.dubbo.common.json.JSON;
import org.apache.dubbo.rpc.RpcContext;

import java.io.IOException;

public class GreetingServiceImpl implements GreetingService {


    @Override
    public String sayHello(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello"+ name+" " + RpcContext.getContext().getAttachment("company");
    }

    @Override
    public Result<String> testGeneric(POJO pojo) {
        Result<String> result = new Result<String>();
        result.setSuccess(true);
        try {
            result.setData(JSON.json(pojo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
