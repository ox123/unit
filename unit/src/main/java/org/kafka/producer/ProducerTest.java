package org.kafka.producer;

import org.utils.Constant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.kafka.KafkaUtils;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ProducerTest {
    private final Producer<String, String> kafkaProducer;

    public final static String TOPIC = "JAVA_TOPIC";

    private ProducerTest() {
        kafkaProducer = createKafkaProducer();
    }

    private Producer<String, String> createKafkaProducer() {
        Properties props = KafkaUtils.buildProducer();

        Producer<String, String> kafkaProducer = new KafkaProducer<>(props);
        return kafkaProducer;
    }

    void produce() {
        for (int i = 1; i < 1000; i++) {
            String key = String.valueOf("key" + i);
            String data = "hello kafka message:" + key;
            Future<RecordMetadata> send = kafkaProducer.send(new ProducerRecord<>(Constant.TEST_TOPIC00, key, data));
            try {
                RecordMetadata cc = send.get();
                System.out.println(cc.topic() + "----" + cc.offset());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(data);
        }
    }

    public static void main(String[] args) {
        new ProducerTest().produce();
    }
}
