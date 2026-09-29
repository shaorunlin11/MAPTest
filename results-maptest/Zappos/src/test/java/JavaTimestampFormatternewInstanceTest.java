package com.zappos.json.format;

import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.Timestamp;
import com.zappos.json.format.ValueFormatter;
import com.zappos.json.format.JavaTimestampFormatter;

public class JavaTimestampFormatternewInstanceTest {
    @Test
    public void testNewInstanceReturnsInstanceOfJavaTimestampFormatter() {
        JavaTimestampFormatter formatter = new JavaTimestampFormatter();
        ValueFormatter<Timestamp> result = formatter.newInstance();
        assertTrue(result instanceof JavaTimestampFormatter);
    }

    @Test
    public void testNewInstanceReturnsValueFormatterOfTimestamp() {
        JavaTimestampFormatter formatter = new JavaTimestampFormatter();
        ValueFormatter<Timestamp> result = formatter.newInstance();
        assertTrue(result instanceof ValueFormatter);
    }
}
