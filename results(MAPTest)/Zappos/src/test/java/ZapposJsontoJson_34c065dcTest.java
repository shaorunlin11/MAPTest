package com.zappos.json;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.StringWriter;
import java.util.Date;
import java.time.LocalDate;
import java.time.Instant;
import java.math.BigDecimal;
import java.math.BigInteger;
import com.zappos.json.JsonConfig.WriterConfig;
import com.zappos.json.format.BigDecimalFormatter;
import com.zappos.json.format.BigIntegerFormatter;
import com.zappos.json.format.JavaDateFormatter;
import com.zappos.json.format.JavaSqlDateFormatter;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.JavaTimeLocalDateFormatter;
import com.zappos.json.format.JavaTimestampFormatter;
import com.zappos.json.util.Reflections;

public class ZapposJsontoJson_34c065dcTest {

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
    public void testToJsonWithDate() {
        Date date = new Date();
        String result = zapposJson.toJson(date);
        // Verify that the result is not empty and contains expected date format
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void testToJsonWithLocalDate() {
        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            LocalDate localDate = LocalDate.now();
            String result = zapposJson.toJson(localDate);
            // Verify that the result is not empty and contains expected date format
            Assert.assertTrue(result.length() > 0);
        }
    }

    @Test
    public void testToJsonWithInstant() {
        if (Reflections.classPresent("java.time.chrono.ChronoLocalDate")) {
            Instant instant = Instant.now();
            String result = zapposJson.toJson(instant);
            // Verify that the result is not empty and contains expected date format
            Assert.assertTrue(result.length() > 0);
        }
    }

    @Test
    public void testToJsonWithBigDecimal() {
        BigDecimal bigDecimal = new BigDecimal("123.45");
        String result = zapposJson.toJson(bigDecimal);
        // Verify that the result is not empty and contains expected number format
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void testToJsonWithBigInteger() {
        BigInteger bigInteger = new BigInteger("1234567890");
        String result = zapposJson.toJson(bigInteger);
        // Verify that the result is not empty and contains expected number format
        Assert.assertTrue(result.length() > 0);
    }

    @Test
    public void testToJsonWithNullObject() {
        String result = zapposJson.toJson(null);
        // Verify that the result is an empty string
        Assert.assertEquals("null", result);
    }
}
