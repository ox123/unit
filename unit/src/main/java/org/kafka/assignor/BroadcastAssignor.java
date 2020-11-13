package org.kafka.assignor;

import org.apache.kafka.clients.consumer.internals.AbstractPartitionAssignor;
import org.apache.kafka.common.TopicPartition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastAssignor extends AbstractPartitionAssignor {

    @Override
    public Map<String, List<TopicPartition>> assign(Map<String, Integer> partitionsPerTopic,
                                                    Map<String, Subscription> subscriptions) {
        Map<String, List<String>> consumerPerTopic = new HashMap<>();
        Map<String, List<TopicPartition>> assignment = new HashMap<>();
        subscriptions.keySet().forEach(memberId -> {
            assignment.put(memberId, new ArrayList<>());
        });
        consumerPerTopic.entrySet().forEach(topicEntry -> {
            String topic = topicEntry.getKey();
            List<String> members = topicEntry.getValue();
            Integer numPartitionsForTopic = partitionsPerTopic.get(topic);
            if (numPartitionsForTopic == null || members.isEmpty()) {
                return;
            }
            List<TopicPartition> partitions = AbstractPartitionAssignor.partitions(topic, numPartitionsForTopic);
            if (!partitions.isEmpty()) {
                members.forEach(memberId -> {
                    assignment.get(memberId).addAll(partitions);
                });
            }
        });
        return assignment;
    }

    @Override
    public String name() {
        return "broadcast";
    }
}
