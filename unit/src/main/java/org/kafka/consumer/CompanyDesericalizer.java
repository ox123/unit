package org.kafka.consumer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.kafka.bean.Company;

import java.nio.ByteBuffer;
import java.util.Map;

public class CompanyDesericalizer implements Deserializer<Company> {
    /**
     * Configure this class.
     *
     * @param configs configs in key/value pairs
     * @param isKey   whether is for key or value
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    /**
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic   topic associated with the data
     * @param headers headers associated with the record; may be empty.
     * @param data    serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public Company deserialize(String topic, Headers headers, byte[] data) {
        return null;
    }

    /**
     * Close this deserializer.
     * <p>
     * This method must be idempotent as it may be called multiple times.
     */
    @Override
    public void close() {

    }

    /**
     * Deserialize a record value from a byte array into a value or object.
     *
     * @param topic topic associated with the data
     * @param data  serialized bytes; may be null; implementations are recommended to handle null by returning a value or null rather than throwing an exception.
     * @return deserialized typed data; may be null
     */
    @Override
    public Company deserialize(String topic, byte[] data) {

        ByteBuffer buffer = ByteBuffer.wrap(data);
        int nameLen = buffer.getInt();
        byte[] nameBytes = new byte[nameLen];
        buffer.get(nameBytes);
        int addressLen = buffer.getInt();
        byte[] addressByte = new byte[addressLen];
        buffer.get(addressByte);
        try {
            String name = new String(nameBytes, "UTF-8");
            String address = new String(addressByte, "UTF-8");
            Company company = new Company();
            company.setName(name);
            company.setAddress(address);
            return company;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
