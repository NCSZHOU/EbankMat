package com.ebank.jwt.system.kafka;

import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import com.alibaba.fastjson.JSONObject;
import java.io.UnsupportedEncodingException;

@Configuration
public class EbankDeserializer implements Deserializer {
    private final static Logger logger = LoggerFactory.getLogger(EbankDeserializer.class);
    @Override
    public Object deserialize(String s, byte[] bytes) {
        try {
            String json = new String(bytes,"utf-8");
            return JSONObject.parse(json);
        } catch (UnsupportedEncodingException e) {
            logger.error(e.toString());
        }
        return null;
    }
}
