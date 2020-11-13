//package org.puslar;
//
//import org.apache.pulsar.client.api.Message;
//import org.apache.pulsar.client.api.PulsarClient;
//import org.apache.pulsar.client.api.PulsarClientException;
//
//public class Consumer {
//    public static void main(String[] args) {
//        PulsarClient client = null;
//        try {
//            client = PulsarClient.builder()
//                    .serviceUrl("pulsar://90.84.179.11:6650").build();
//            Consumer consumer = client.newConsumer()
//                    .topic("my-topic")
//                    .subscriptionName("my-subscription")
//                    .subscribe();
//            while (true) {
//                // Wait for a message
////                consumer.re
//                Message msg = consumer.receive();
//
//                try {
//                    // Do something with the message
//                    System.out.printf("Message received: %s", new String(msg.getData()));
//
//                    // Acknowledge the message so that it can be deleted by the message broker
//                    consumer.acknowledge(msg);
//                } catch (Exception e) {
//                    // Message failed to process, redeliver later
//                    consumer.negativeAcknowledge(msg);
//                }
//            }
//        } catch (PulsarClientException e) {
//            e.printStackTrace();
//        }
//
//    }
//}
