package com.zappos.json.format;

import org.junit.Test;
import org.junit.Before;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class JavaTimeInstantFormattersetPatternTest {
    private JavaTimeInstantFormatter formatterInstance;

    @Before
    public void setUp() {
        formatterInstance = new JavaTimeInstantFormatter();
    }

    @Test
    public void testSetPatternSetsFormatterWithSystemTimeZone() throws Exception {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        formatterInstance.setPattern(pattern);

        Field formatterField = JavaTimeInstantFormatter.class.getDeclaredField("formatter");
        formatterField.setAccessible(true);
        DateTimeFormatter resultFormatter = (DateTimeFormatter) formatterField.get(formatterInstance);

        assertNotNull("Formatter should not be null after setting pattern", resultFormatter);
        assertEquals("Formatter should use system default time zone", ZoneId.systemDefault(), resultFormatter.getZone());
    }

    @Test
    public void testSetPatternReturnsThis() {
        String pattern = "yyyy-MM-dd";
        ValueFormatter<Instant> result = formatterInstance.setPattern(pattern);

        assertSame("setPattern should return this instance", formatterInstance, result);
    }
}
