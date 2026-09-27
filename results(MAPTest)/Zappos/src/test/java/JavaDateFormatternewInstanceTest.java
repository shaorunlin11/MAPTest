package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class JavaDateFormatternewInstanceTest {
    @Test
    public void testNewInstance_returnsNonNullValueFormatter() {
        JavaDateFormatter formatter = new JavaDateFormatter();
        ValueFormatter<Date> result = formatter.newInstance();
        assertNotNull("newInstance should return a non-null ValueFormatter", result);
    }

    @Test
    public void testNewInstance_returnsInstanceOfJavaDateFormatter() {
        JavaDateFormatter formatter = new JavaDateFormatter();
        ValueFormatter<Date> result = formatter.newInstance();
        assertTrue("newInstance should return an instance of JavaDateFormatter", result instanceof JavaDateFormatter);
    }
}
