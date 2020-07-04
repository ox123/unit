package org.consul;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.coordinate.model.Datacenter;
import com.ecwid.consul.v1.kv.model.GetBinaryValue;
import com.orbitz.consul.Consul;

import java.util.List;

public class Test {
    public static void main(String[] args) throws IllegalAccessException {
//
        System.out.println( 1& 1);
        Consul client = Consul.builder().build();
//
//        Field[] declaredFields = Consul.class.getDeclaredFields();
//        for (Field f:
//             declaredFields) {
//           if (Consul.DEFAULT_HTTP_HOST.equals(f.getName())){
//               if (f.getType() == String.class){
//                   f.setAccessible(true);
//                   f.set(client,"80.158.32.147");
//               }
//           }
//        }
//        System.out.println("---");
//        client.DEFAULT_HTTP_HOST;
//        client.keyValueClient();

//        consulTest1();
    }

    private static void consulTest1() {
        ConsulClient client = new ConsulClient("80.158.32.147");
        client.setKVValue("aa/cc/dd","asdfd");
        Response<List<Datacenter>> datacenters = client.getDatacenters();
        System.out.println( datacenters.getValue());
        System.out.println(client.getKVValue("aa/cc/dd").getValue().getDecodedValue());
        Response<List<String>> statusPeers = client.getStatusPeers();
        statusPeers.getValue().forEach(key->{
            System.out.println(key);
        });
        Response<List<String>> kvKeysOnly = client.getKVKeysOnly("prom/config/eu-de/scrape_configs/ntp_internal_offset_latency_probe_1/");
        kvKeysOnly.getValue().forEach(key->{
            System.out.println(client.getKVValue(key).getValue().getDecodedValue()+"----"+key);
            Response<GetBinaryValue> kvBinaryValue = client.getKVBinaryValue(key);
            System.out.println(new String(kvBinaryValue.getValue().getValue()));
        });
//        Response<List<GetBinaryValue>> kvBinaryValues = client.getKVBinaryValues("aa/");
//        kvBinaryValues.getValue().forEach(key->{
//            System.out.println(key.getKey()+"----"+ new String(key.getValue()));
//        });
//        client.getNodes();
    }
}
