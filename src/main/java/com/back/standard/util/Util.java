package com.back.standard.util;

public class Util {
    public static class reflection {
        public static void setField(Object object, String fieldName, Object value) {
            try {
                var field = object.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
