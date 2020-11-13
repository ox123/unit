package org.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComsumerInterceptorTTL implements ConsumerInterceptor<String, String> {

    private static final long expire_time = 10 * 1000;

    /**
     * This is called just before the records are returned by
     * {@link KafkaConsumer#poll(Duration)}
     * <p>
     * This method is allowed to modify consumer records, in which case the new records will be
     * returned. There is no limitation on number of records that could be returned from this
     * method. I.e., the interceptor can filter the records or generate new records.
     * <p>
     * Any exception thrown by this method will be caught by the caller, logged, but not propagated to the client.
     * <p>
     * Since the consumer may run multiple interceptors, a particular interceptor's onConsume() callback will be called
     * in the order specified by {@link ConsumerConfig#INTERCEPTOR_CLASSES_CONFIG}.
     * The first interceptor in the list gets the consumed records, the following interceptor will be passed the records returned
     * by the previous interceptor, and so on. Since interceptors are allowed to modify records, interceptors may potentially get
     * the records already modified by other interceptors. However, building a pipeline of mutable interceptors that depend on the output
     * of the previous interceptor is discouraged, because of potential side-effects caused by interceptors potentially failing
     * to modify the record and throwing an exception. If one of the interceptors in the list throws an exception from onConsume(),
     * the exception is caught, logged, and the next interceptor is called with the records returned by the last successful interceptor
     * in the list, or otherwise the original consumed records.
     *
     * @param records records to be consumed by the client or records returned by the previous interceptors in the list.
     * @return records that are either modified by the interceptor or same as records passed to this method.
     */
    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        Map<TopicPartition, List<ConsumerRecord<String, String>>> map = new HashMap<>();
        long now = System.currentTimeMillis();
        for (TopicPartition tp :
                records.partitions()) {
            List<ConsumerRecord<String, String>> records1 = records.records(tp);
            ArrayList<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record :
                    records1) {
                if (now - record.timestamp() < expire_time) {
                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                map.put(tp, newTpRecords);
            }
        }
        return new ConsumerRecords<>(map);
    }

    /**
     * This is called when offsets get committed.
     * <p>
     * Any exception thrown by this method will be ignored by the caller.
     *
     * @param offsets A map of offsets by partition with associated metadata
     */
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        offsets.forEach((topicPartition, offsetAndMetadata) -> {
            System.out.println(topicPartition + ":" + offsetAndMetadata.offset());
        });

    }

    /**
     * This is called when interceptor is closed
     */
    @Override
    public void close() {

    }

    /**
     * Configure this class with the given key-value pairs
     *
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {

    }
}
