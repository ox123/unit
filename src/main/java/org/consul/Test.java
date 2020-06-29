package org.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        ConsulClient client = new ConsulClient("80.158.32.147");
        Response<List<String>> statusPeers = client.getStatusPeers();
        statusPeers.getValue().forEach(key->{
            System.out.println(key);
        });
        Response<List<String>> kvKeysOnly = client.getKVKeysOnly("prom/config/eu-de/scrape_configs/");
        kvKeysOnly.getValue().forEach(key->{
            System.out.println(key);
        });
        Response<List<GetBinaryValue>> kvBinaryValues = client.getKVBinaryValues("aa/");
        kvBinaryValues.getValue().forEach(key->{
            System.out.println(key.getKey()+"----"+ new String(key.getValue()));
        });
//        client.getNodes();
    }
}
