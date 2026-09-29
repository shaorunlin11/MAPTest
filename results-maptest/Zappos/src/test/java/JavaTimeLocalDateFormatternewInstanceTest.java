package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;

public class JavaTimeLocalDateFormatternewInstanceTest {
    @Test
    public void testNewInstanceReturnsInstanceOfJavaTimeLocalDateFormatter() {
        JavaTimeLocalDateFormatter formatter = new JavaTimeLocalDateFormatter();
        ValueFormatter<LocalDate> result = formatter.newInstance();
        assertTrue(result instanceof JavaTimeLocalDateFormatter);
    }

    @Test
    public void testNewInstanceReturnsImplementationOfValueFormatter() {
        JavaTimeLocalDateFormatter formatter = new JavaTimeLocalDateFormatter();
        ValueFormatter<LocalDate> result = formatter.newInstance();
        assertTrue(result instanceof ValueFormatter);
    }
}
