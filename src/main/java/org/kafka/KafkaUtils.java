package org.kafka;

import java.util.Properties;

public class KafkaUtils {

    public static Properties buildProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", Constant.KAFKA_ADDRESS);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }
}
