package com.ebank.jwt.system.kafka;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.common.serialization.Serializer;

public class EbankSerializer implements Serializer {

    @Override
    public byte[] serialize(String s, Object o) {
        String json = JSONObject.toJSONString(o);
        return json.getBytes();
    }
}
