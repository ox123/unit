package org.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class ProducerTest {
    private  final Producer<String,String> kafkaProducer;

    public final static String TOPIC="JAVA_TOPIC";

    private ProducerTest(){
        kafkaProducer=createKafkaProducer() ;
    }
    private Producer<String,String> createKafkaProducer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "90.84.179.11:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
        return kafkaProducer;
    }

    void produce(){
        for(int i=1;i<1000;i++){
            String key=String.valueOf("key"+i);
            String data="hello kafka message:"+key;
            kafkaProducer.send(new ProducerRecord<>(TOPIC, key, data));
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        new ProducerTest().produce();
    }
}
