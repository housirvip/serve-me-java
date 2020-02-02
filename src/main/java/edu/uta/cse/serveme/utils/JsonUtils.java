package edu.uta.cse.serveme.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author housirvip
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static String convertToString(Object object) {

        if (object == null) {
            return null;
        }

        String result = null;
        try {
            result = mapper.writeValueAsString(object);
        } catch (IOException e) {
            log.error("Json: from " + object.getClass().getSimpleName() + " to String failed", e);
        }

        return result;
    }

    public static <T> T convertToObj(String json, Class<T> c) {

        if (json == null) {
            return null;
        }

        T result = null;
        try {
            result = mapper.readValue(json, c);
        } catch (IOException e) {
            log.error("Json: from String to " + c.getSimpleName() + " failed", e);
        }

        return result;
    }

    public static <T> T convertToObj(InputStream src, Class<T> c) {

        if (src == null) {
            return null;
        }

        T result = null;
        try {
            result = mapper.readValue(src, c);
        } catch (IOException e) {
            log.error("Json: from InputStream to " + c.getSimpleName() + " failed", e);
        }

        return result;
    }

    public static <T> List<T> convertToList(String json, Class<T> t) {

        if (json == null) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, t);

        List<T> result = null;
        try {
            result = mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("Json: from String to List of " + t.getSimpleName() + " failed", e);
        }

        return result;
    }

    public static <K, V> Map<K, V> convertToMap(String json, Class<K> k, Class<V> v) {

        if (json == null) {
            return null;
        }

        JavaType javaType = mapper.getTypeFactory().constructParametricType(HashMap.class, k, v);

        Map<K, V> result = null;
        try {
            result = mapper.readValue(json, javaType);
        } catch (IOException e) {
            log.error("Json: from String to Map of " + k.getSimpleName() + ":" + v.getSimpleName() + " failed", e);
        }

        return result;
    }
}
