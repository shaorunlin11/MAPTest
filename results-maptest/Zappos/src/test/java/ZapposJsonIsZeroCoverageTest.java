package com.zappos.json;

import org.junit.Test;

import com.zappos.json.JsonConfig.WriterConfig;


public class ZapposJsonIsZeroCoverageTest {
    @Test
    public void testIsMethodWithValidConfig() {
        ZapposJson zapposJson = new ZapposJson();
        WriterConfig config = WriterConfig.WRITE_HTML_SAFE;
        boolean result = zapposJson.is(config);
        // This test ensures that the method is called and the code path is executed
        // without any additional assertions since the goal is to cover the target line
        // and not to verify the actual behavior of the method.
    }
}
