package com.mall.app.handler;

import com.alibaba.acm.shaded.com.google.common.primitives.Longs;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class BaseContextHandler {

    private static final String CONTEXT_KEY_USER_ID    = "CURRENT_USER_ID";

    private static final String CONTEXT_KEY_USER_TOKEN = "CURRENT_USER_TOKEN";

   // private static ThreadLocal<Map<String, Object>> threadLocal = new InheritableThreadLocal<>();
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    private BaseContextHandler() {
        super();
    }

    public static void set(String key, Object value) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = Maps.newHashMap();
            threadLocal.set(map);
        }
        map.put(key, value);
    }

    public static Object get(String key) {
        Map<String, Object> map = threadLocal.get();
        if (map == null) {
            map = new HashMap<>();
            threadLocal.set(map);
        }
        log.debug("BaseContextHandler == map:{}", map);
        return map.get(key);
    }

    public static Long getUserID() {
        Object value = get(CONTEXT_KEY_USER_ID);
        if (Objects.isNull(value)) {
            return null;
        }
        return Longs.tryParse(value.toString());
    }

    public static String getToken() {
        Object value = get(CONTEXT_KEY_USER_TOKEN);
        if (Objects.isNull(value)) {
            return null;
        }
        return value.toString();
    }
    public static void setToken(String token) {
        set(CONTEXT_KEY_USER_TOKEN, token);
    }

    public static void setUserID(Long userId) {
        set(CONTEXT_KEY_USER_ID, userId);
    }

    public static void remove() {
        threadLocal.remove();
    }
    

}
