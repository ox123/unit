//package org.puslar;
//
//import org.apache.pulsar.client.api.PulsarClient;
//
//public class Consumer {
//    public static void main(String[] args) {
//        PulsarClient client = PulsarClient.builder()
//                .serviceUrl("pulsar://90.84.179.11:6650")
//                .build();
//        Consumer consumer = client.newConsumer()
//                .topic("my-topic")
//                .subscriptionName("my-subscription")
//                .subscribe();
//        while (true) {
//            // Wait for a message
//            consumer.re
//            Message msg = consumer.receive();
//
//            try {
//                // Do something with the message
//                System.out.printf("Message received: %s", new String(msg.getData()));
//
//                // Acknowledge the message so that it can be deleted by the message broker
//                consumer.acknowledge(msg);
//            } catch (Exception e) {
//                // Message failed to process, redeliver later
//                consumer.negativeAcknowledge(msg);
//            }
//        }
//    }
//}
