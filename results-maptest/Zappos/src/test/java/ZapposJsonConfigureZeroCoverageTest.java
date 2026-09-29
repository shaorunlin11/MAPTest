package com.zappos.json;

import org.junit.Test;

import com.zappos.json.JsonConfig.WriterConfig;


public class ZapposJsonConfigureZeroCoverageTest {
    @Test
    public void testConfigureWithValidConfig() {
        ZapposJson zapposJson = new ZapposJson();
        WriterConfig config = WriterConfig.WRITE_ENUM_USING_NAME;
        boolean value = true;

        zapposJson.configure(config, value);
    }
}
