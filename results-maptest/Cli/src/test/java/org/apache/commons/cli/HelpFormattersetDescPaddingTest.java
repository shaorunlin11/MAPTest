package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class HelpFormattersetDescPaddingTest {
    private HelpFormatter formatter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
    }

    @Test
    public void testSetDescPaddingSetsDefaultDescPad() throws Exception {
        int expectedPadding = 5;
        formatter.setDescPadding(expectedPadding);

        Field field = HelpFormatter.class.getDeclaredField("defaultDescPad");
        field.setAccessible(true);
        int actualPadding = (int) field.get(formatter);

        assertEquals("The desc padding should be set correctly", expectedPadding, actualPadding);
    }
}
