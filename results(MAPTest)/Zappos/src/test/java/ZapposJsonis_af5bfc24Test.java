package com.zappos.json;

import org.junit.Test;
import org.junit.Assert;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import java.lang.reflect.Field;

import com.zappos.json.JsonConfig.ReaderConfig;

public class ZapposJsonis_af5bfc24Test {
    @Test
    public void testIs() throws Exception {
        // Create an instance of ZapposJson
        ZapposJson zapposJson = new ZapposJson();

        // Get the READER_CONFIGS array using reflection
        java.lang.reflect.Field readerConfigsField = ZapposJson.class.getDeclaredField("READER_CONFIGS");
        readerConfigsField.setAccessible(true);
        boolean[] readerConfigs = (boolean[]) readerConfigsField.get(zapposJson);

        // Verify that the array is initialized with the correct length
        Assert.assertEquals(ReaderConfig.values().length, readerConfigs.length);

        // Test each ReaderConfig value
        for (ReaderConfig config : ReaderConfig.values()) {
            boolean result = zapposJson.is(config);
            Assert.assertEquals(readerConfigs[config.ordinal()], result);
        }
    }
}
