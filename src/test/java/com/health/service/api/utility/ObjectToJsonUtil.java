package com.health.service.api.utility;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String objectToString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
