package com.asxy.util.json;


import com.asxy.util.spring.SpringBeanUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.Objects;


public class JsonUtils {

    private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
    private volatile static ObjectMapper json;

    public static ObjectMapper getJson() {
        try {
            if (Objects.isNull(JsonUtils.json)) {
                synchronized (JsonUtils.class) {
                    if (Objects.isNull(JsonUtils.json)) {
                        JsonUtils.json = SpringBeanUtils.getBean(ObjectMapper.class);
                    }
                }
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            ObjectMapper result = new ObjectMapper();
            result.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return result;
        }
        return JsonUtils.json;
    }

    public static String writeValueAsString(Object value) {
        if (Objects.isNull(value)) {
            return null;
        }
        try {
            return JsonUtils.getJson().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("序列化异常！", e);
            return null;
        }
    }

    public static <T> T readValue(String content, Class<T> valueType) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return JsonUtils.getJson().readValue(content, valueType);
        } catch (JsonProcessingException e) {
            log.error(String.format("反序列化异常：【%s】-> %s", content, valueType.getName()), e);
            return null;
        }
    }

    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return JsonUtils.getJson().readValue(content, valueTypeRef);
        } catch (JsonProcessingException e) {
            log.error(String.format("反序列化异常：【%s】-> %s", content, valueTypeRef.getType().getTypeName()), e);
            return null;
        }
    }

    public static <T> T readValue(String content, JavaType javaType) {
        if (Objects.isNull(content)) {
            return null;
        }
        try {
            return JsonUtils.getJson().readValue(content, javaType);
        } catch (JsonProcessingException e) {
            log.error(String.format("反序列化异常：【%s】-> %s", content, javaType.getTypeName()), e);
            return null;
        }
    }
}