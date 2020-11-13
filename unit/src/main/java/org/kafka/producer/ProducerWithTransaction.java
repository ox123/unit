package org.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class ProducerWithTransaction {
    public static void main(String[] args) {
        Properties properties = new Properties();
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
        producer.initTransactions();
        producer.beginTransaction();
        String topic = "";
        try {
            ProducerRecord<String, String> record1 = new ProducerRecord<>(topic, "msg1");
            producer.send(record1);
            ProducerRecord<String, String> record2 = new ProducerRecord<>(topic, "msg2");
            producer.send(record1);
            ProducerRecord<String, String> record3 = new ProducerRecord<>(topic, "msg3");
            producer.send(record1);
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            producer.abortTransaction();
            e.printStackTrace();
        } finally {
            if (producer != null) {
                producer.close();
            }
        }
    }
}
