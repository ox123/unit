package org.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

public class KafkaConsumerAnalysis {
    public static void main(String[] args) {
        KafkaConsumer consumer = new KafkaConsumer(init());
        String topic = "demo";
        List<PartitionInfo> list = consumer.partitionsFor(topic);// 获取所有的partition
        List<TopicPartition> topicPartitionList = new ArrayList<>();
        if (list != null) {
            for (PartitionInfo partitionInfo :
                    list) {
                topicPartitionList.add(new TopicPartition(partitionInfo.topic(), partitionInfo.partition()));
            }
        }
        Set<TopicPartition> assignment = new HashSet<TopicPartition>();
        if (assignment.size() == 0) {
            consumer.poll(Duration.ofMillis(100));
            assignment = consumer.assignment(); // 获取当前消费者被分配到的分区
        }
        for (TopicPartition tp :
                assignment) {
            consumer.seek(tp, 10); // 指定位移消费
        }
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {
            // 再均衡开始之前和消费者停止读取消息之后被调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

            }

            //重新分配分区之后，消费者读取消息之前被调用。
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                for (TopicPartition topicPartition :
                        partitions) {

                }
            }
        });
        Map<TopicPartition, OffsetAndMetadata> map = new HashMap<TopicPartition, OffsetAndMetadata>();
        consumer.endOffsets(assignment); // 获取指定分区的末尾的消费位置
        consumer.subscribe(Collections.singleton("demo"));
        consumer.assign(topicPartitionList); // 手动指定添加订阅的分区信息
        consumer.commitAsync();
        consumer.commitSync();
        consumer.commitAsync(new OffsetCommitCallback() {
            @Override
            public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                System.out.println("");
            }
        });
        consumer.wakeup(); // 正常的跳出循环的方式
        consumer.paused();
        Set<TopicPartition> assignment1 = consumer.assignment();//用来获取消费者所分配到的分区信息
        consumer.beginningOffsets(topicPartitionList);// 获取分区起始位置，并不一定一直是0
//        consumer.resume();
        while (true) {
            // TODO consumer客户端如果提交offset
            ConsumerRecords<String, String> poll = consumer.poll(Duration.ofMillis(5)); // 自动提交在poll方法中完成
            for (ConsumerRecord<String, String> record :
                    poll) {
                record.partition();
                poll.partitions();
                TopicPartition partition = new TopicPartition(record.topic(), record.partition());
                // 根据offset提交位移
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(record.offset() + 1)));
                System.out.println(record.topic());
            }
        }

    }

    public static Properties init() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        return properties;
    }
}
