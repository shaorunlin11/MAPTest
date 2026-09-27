package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.Instant;
import com.zappos.json.format.JavaTimeInstantFormatter;
import com.zappos.json.format.ValueFormatter;

public class JavaTimeInstantFormatternewInstanceTest {

    @Test
    public void testNewInstanceReturnsInstanceOfJavaTimeInstantFormatter() {
        JavaTimeInstantFormatter formatter = new JavaTimeInstantFormatter();
        ValueFormatter<Instant> result = formatter.newInstance();
        assertTrue(result instanceof JavaTimeInstantFormatter);
    }

    @Test
    public void testNewInstanceReturnsNonNullValueFormatter() {
        JavaTimeInstantFormatter formatter = new JavaTimeInstantFormatter();
        ValueFormatter<Instant> result = formatter.newInstance();
        assertNotNull(result);
    }
}
