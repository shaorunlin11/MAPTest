package com.zappos.json;

import org.junit.Test;
import org.junit.Assert;

import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Date;
import java.time.LocalDate;
import java.time.Instant;

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
import com.zappos.json.util.Reflections;

public class ZapposJsongetInstance_62a91bd3Test {
    @Test
    public void testGetInstance_returnsSingletonInstance() {
        ZapposJson instance1 = ZapposJson.getInstance();
        ZapposJson instance2 = ZapposJson.getInstance();

        Assert.assertNotNull("getInstance should return a non-null instance", instance1);
        Assert.assertEquals("Multiple calls to getInstance should return the same instance", instance1, instance2);
    }

    @Test
    public void testGetInstance_initializesValueFormatters() {
        ZapposJson instance = ZapposJson.getInstance();

        // Check if standard formatters are registered
        Assert.assertTrue("Should have a formatter for Date", instance.VALUE_FORMATTERS.containsKey(Date.class));
        Assert.assertTrue("Should have a formatter for java.sql.Date", instance.VALUE_FORMATTERS.containsKey(java.sql.Date.class));
        Assert.assertTrue("Should have a formatter for java.sql.Timestamp", instance.VALUE_FORMATTERS.containsKey(java.sql.Timestamp.class));
        Assert.assertTrue("Should have a formatter for java.math.BigInteger", instance.VALUE_FORMATTERS.containsKey(java.math.BigInteger.class));
        Assert.assertTrue("Should have a formatter for java.math.BigDecimal", instance.VALUE_FORMATTERS.containsKey(java.math.BigDecimal.class));

        // Check if Java Time formatters are registered if available
        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            Assert.assertTrue("Should have a formatter for LocalDate", instance.VALUE_FORMATTERS.containsKey(LocalDate.class));
            Assert.assertTrue("Should have a formatter for Instant", instance.VALUE_FORMATTERS.containsKey(Instant.class));
        }
    }

    @Test
    public void testGetInstance_initializesIntrospectorAndGenerators() {
        ZapposJson instance = ZapposJson.getInstance();

        // Use reflection to access private fields
        try {
            java.lang.reflect.Field jsonBeanIntrospectorField = ZapposJson.class.getDeclaredField("jsonBeanIntrospector");
            jsonBeanIntrospectorField.setAccessible(true);
            Assert.assertNotNull("jsonBeanIntrospector should be initialized", jsonBeanIntrospectorField.get(instance));

            java.lang.reflect.Field writerCodeGeneratorField = ZapposJson.class.getDeclaredField("writerCodeGenerator");
            writerCodeGeneratorField.setAccessible(true);
            Assert.assertNotNull("writerCodeGenerator should be initialized", writerCodeGeneratorField.get(instance));

            java.lang.reflect.Field readerCodeGeneratorField = ZapposJson.class.getDeclaredField("readerCodeGenerator");
            readerCodeGeneratorField.setAccessible(true);
            Assert.assertNotNull("readerCodeGenerator should be initialized", readerCodeGeneratorField.get(instance));
        } catch (Exception e) {
            Assert.fail("Failed to access private fields: " + e.getMessage());
        }
    }
}
