package org.kafka.assignor;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;

import java.util.*;


public class RandomAssignor extends AbstractPartitionAssignor {
    public static void main(String[] args) {
        Properties properties = new Properties();
        // 定义之后使用
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RandomAssignor.class.getName());
    }

    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic,
                                                    Map<String, Subscription> subscriptions) {
        HashMap<String, List<String>> consumerPerTopc = consumerPerTopic(subscriptions);
        Map<String, List<TopicPartition>> assignment = new HashMap<>();
        for (String memberId :
                subscriptions.keySet()) {
            assignment.put(memberId, new ArrayList<>());
        }
        for (Map.Entry<String, List<String>> topicEntry :
                consumerPerTopc.entrySet()) {
            String topic = topicEntry.getKey();
            List<String> consumerForTopic = topicEntry.getValue();
            int consumerSize = consumerForTopic.size();
            Integer numPartitionsForTopic = partitionsPerTopic.get(topic);
            if (numPartitionsForTopic == null) {
                continue;
            }
            List<TopicPartition> partitions = AbstractPartitionAssignor.partitions(topic, numPartitionsForTopic);
            for (TopicPartition partition :
                    partitions) {
                int rand = new Random().nextInt(consumerSize);
                String randomConsumer = consumerForTopic.get(rand);
                assignment.get(randomConsumer).add(partition);
            }
        }
        return assignment;
    }

    private HashMap<String, List<String>> consumerPerTopic(Map<String, Subscription> consumerMetaData) {
        Map<String, List<String>> res = new HashMap<>();
        for (Map.Entry<String, Subscription> subscriptionEntry :
                consumerMetaData.entrySet()) {
            String consumerId = subscriptionEntry.getKey();
            for (String topic :
                    subscriptionEntry.getValue().topics()) {
                put(res, topic, consumerId);
            }
        }
        return null;
    }


    @Override
    public String name() {
        return "random";
    }
}
