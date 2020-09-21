package com.k2future.cms.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunque.commons.util.RespBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author West
 * @date create in 2019/9/10
 */
@Configuration
public class JsonConfig {

    @Bean
    public ObjectMapper serializingObjectMapper(){
        return RespBuilder.objectMapper;
    }


}
