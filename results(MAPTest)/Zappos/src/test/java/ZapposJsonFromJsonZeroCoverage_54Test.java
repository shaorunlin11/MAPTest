package com.zappos.json;

import org.junit.Test;

public class ZapposJsonFromJsonZeroCoverage_54Test {
    @Test
    public void testFromJson() {
        ZapposJson zapposJson = new ZapposJson();
        String json = "{\"key\":\"value\"}";
        Class<?> targetClass = TestPojo.class;
        zapposJson.fromJson(json, targetClass);
    }

    public static class TestPojo {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
