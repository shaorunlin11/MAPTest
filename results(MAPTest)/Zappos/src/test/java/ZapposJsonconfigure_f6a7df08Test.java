package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.lang.reflect.Field;

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

import com.zappos.json.JsonConfig.ReaderConfig;
import com.zappos.json.JsonConfig.WriterConfig;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.JavaDateFormatter;
import com.zappos.json.format.JavaSqlDateFormatter;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.JavaTimeLocalDateFormatter;
import com.zappos.json.format.JavaTimestampFormatter;
import com.zappos.json.format.ValueFormatter;
import com.zappos.json.util.JsonUtils;
import com.zappos.json.util.Reflections;
import com.zappos.json.util.Strings;

public class ZapposJsonconfigure_f6a7df08Test {
    private ZapposJson zapposJson;

    @Before
    public void setUp() {
        zapposJson = new ZapposJson();
    }

    @After
    public void tearDown() {
        zapposJson = null;
    }

    @Test
    public void testConfigure_ReaderConfig() throws Exception {
        // Arrange
        ReaderConfig[] configs = ReaderConfig.values();

        // Act
        for (ReaderConfig config : configs) {
            zapposJson.configure(config, true);
        }

        // Assert
        Field readerConfigsField = ZapposJson.class.getDeclaredField("READER_CONFIGS");
        readerConfigsField.setAccessible(true);
        boolean[] readerConfigs = (boolean[]) readerConfigsField.get(zapposJson);

        for (int i = 0; i < configs.length; i++) {
            Assert.assertTrue("READER_CONFIGS[" + i + "] should be true", readerConfigs[i]);
        }
    }
}
