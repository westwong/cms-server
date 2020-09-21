package com.yunque.cms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yunque.cms.core.bo.InfoDTO;
import com.yunque.cms.core.bo.InfoImage;
import com.yunque.cms.core.gen.entity.Information;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author West
 * @date create in 2019/12/17
 */
@RunWith(JUnit4.class)
public class SomethingTest {

    SerializerFeature[] features = {SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteNullStringAsEmpty,
            SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty};

    @Test
    public void test01(){
        InfoDTO dto = new InfoDTO();
        dto.images  = new InfoImage();
        dto.info = new Information();
        dto.tagList = new ArrayList<>();

        String jsonString = JSON.toJSONString(dto, features);
        System.out.println(jsonString);
    }
    @Test
    public void test02(){
    }

}
