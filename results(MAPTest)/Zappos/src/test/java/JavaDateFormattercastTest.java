package com.zappos.json.format;

import java.util.Date;
import org.junit.Test;
import org.junit.Assert;

public class JavaDateFormattercastTest {
    @Test
    public void testCastWithDateObject() {
        JavaDateFormatter formatter = new JavaDateFormatter();
        Date date = new Date();
        Date result = formatter.cast(date);
        Assert.assertEquals(date, result);
    }

    @Test(expected = ClassCastException.class)
    public void testCastWithNonDateObject() {
        JavaDateFormatter formatter = new JavaDateFormatter();
        String nonDate = "test";
        formatter.cast(nonDate);
    }
}
