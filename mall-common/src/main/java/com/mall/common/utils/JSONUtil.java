package com.mall.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description:
 * @author: weijiazheng
 * @createDate: 2019/12/30
 */
public class JSONUtil {


    /**
     * 将Date转换成long字符串
     * @param converters
     * @return
     */
    public static MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter(List<HttpMessageConverter<?>> converters) {
        List<MappingJackson2HttpMessageConverter> originalConverters = new ArrayList();
        Iterator var2 = converters.iterator();
        while(var2.hasNext()) {
            HttpMessageConverter<?> converter = (HttpMessageConverter)var2.next();
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                originalConverters.add((MappingJackson2HttpMessageConverter)converter);
            }
        }
        if (!CollectionUtils.isEmpty(originalConverters)) {
            converters.removeAll(originalConverters);
        }
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return jackson2HttpMessageConverter;
    }
}
