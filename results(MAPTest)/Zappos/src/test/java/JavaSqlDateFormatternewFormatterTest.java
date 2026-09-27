package com.zappos.json.format;

import java.text.SimpleDateFormat;
import org.junit.Test;
import static org.junit.Assert.*;

public class JavaSqlDateFormatternewFormatterTest {
    @Test
    public void testNewFormatterWithPattern() throws Exception {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter() {
            @Override
            public String getPattern() {
                return "yyyy/MM/dd";
            }
        };
        SimpleDateFormat result = formatter.newFormatter();
        assertEquals("yyyy/MM/dd", result.toPattern());
    }

    @Test
    public void testNewFormatterWithNullPattern() throws Exception {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter() {
            @Override
            public String getPattern() {
                return null;
            }
        };
        SimpleDateFormat result = formatter.newFormatter();
        assertEquals("yyyy-MM-dd", result.toPattern());
    }
}
