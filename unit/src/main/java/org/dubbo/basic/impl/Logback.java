package org.dubbo.basic.impl;

import org.dubbo.basic.Log;

public class Logback implements Log {
    @Override
    public void log(String info) {
        System.out.println("Logback:" + info);
    }
}
