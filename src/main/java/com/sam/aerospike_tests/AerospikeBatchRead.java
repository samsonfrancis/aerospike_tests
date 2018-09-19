package com.sam.aerospike_tests;

import com.aerospike.client.*;
import com.aerospike.client.policy.WritePolicy;
import com.aerospike.client.task.RegisterTask;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.*;

public class AerospikeBatchRead {
    private WritePolicy writePolicy = new WritePolicy();
    private AerospikeClient aerospikeClient = null;

    public void init(){
        aerospikeClient = new AerospikeClient("127.0.0.1", 3000);
        writePolicy.sendKey = true;
    }

    public void write(){
        // Write single value.
        Key key = new Key("test", "set1", "key1");
        Bin bin = new Bin("bin1", "value1");
        aerospikeClient.put(writePolicy, key, bin);

        Key key2 = new Key("test2", "set2", "key2");
        Bin bin2 = new Bin("bin2", "value2");
        aerospikeClient.put(writePolicy, key2, bin2);
    }

    public void read(){
        Key[] keys = new Key[2];

        keys[0] = new Key("test", "set1", "key1");
        keys[1] = new Key("test2", "set2", "key2");

        Record[] records = aerospikeClient.get(null, keys);

        for(Record record : records){
            System.out.println(record);
            System.out.println(record.bins);
        }
    }
    //close the connection
    public void shutdown() {
        aerospikeClient.close();
    }

    public static void main(String[] args) {
        AerospikeBatchRead a = new AerospikeBatchRead();
        a.init();
        a.write();
        a.read();
        a.shutdown();
    }
}
