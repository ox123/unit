package org.dubbo.basic;

import java.util.Iterator;
import java.util.ServiceLoader;

public class LogMain {
    public static void main(String[] args) {
        ServiceLoader<Log> load = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = load.iterator();
        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.log("JDK SPI");
        }
    }
}
