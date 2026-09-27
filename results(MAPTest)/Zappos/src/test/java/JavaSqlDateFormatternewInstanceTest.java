package com.zappos.json.format;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Date;
public class JavaSqlDateFormatternewInstanceTest {
    @Test
    public void testNewInstanceReturnsNonNullValueFormatter() {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter();
        ValueFormatter<Date> result = formatter.newInstance();
        assertNotNull("newInstance should return a non-null ValueFormatter", result);
    }

    @Test
    public void testNewInstanceReturnsInstanceOfJavaSqlDateFormatter() {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter();
        ValueFormatter<Date> result = formatter.newInstance();
        assertTrue("newInstance should return an instance of JavaSqlDateFormatter", result instanceof JavaSqlDateFormatter);
    }
}
