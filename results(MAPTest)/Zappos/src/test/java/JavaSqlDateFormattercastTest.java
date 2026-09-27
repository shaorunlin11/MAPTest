package com.zappos.json.format;

import org.junit.Test;
import org.junit.Assert;
import java.sql.Date;

public class JavaSqlDateFormattercastTest {
    @Test
    public void testCastWithValidDate() {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter();
        Date date = new Date(System.currentTimeMillis());
        Date result = formatter.cast(date);
        Assert.assertEquals(date, result);
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithInvalidType() {
        JavaSqlDateFormatter formatter = new JavaSqlDateFormatter();
        String invalidObject = "invalid";
        formatter.cast(invalidObject);
    }
}
