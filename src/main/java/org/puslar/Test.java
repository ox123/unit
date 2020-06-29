package org.puslar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;

public class Test {
    public static void main(String[] args) {
        try {
            PulsarClient client = PulsarClient.builder()
                    .serviceUrl("pulsar://90.84.179.11:6650")
                    .build();
            Producer<byte[]> producer = client.newProducer()
                    .topic("my-topic")
                    .create();
            producer.send("My message".getBytes());

        } catch (PulsarClientException e) {
            e.printStackTrace();
        }

    }
}
