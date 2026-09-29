package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class HelpFormattergetLeftPaddingTest {
    private HelpFormatter formatter;

    @Before
    public void setUp() {
        formatter = new HelpFormatter();
    }

    @Test
    public void testGetLeftPaddingReturnsDefaultLeftPadValue() {
        assertEquals("Should return the default left padding value", HelpFormatter.DEFAULT_LEFT_PAD, formatter.getLeftPadding());
    }

    @Test
    public void testGetLeftPaddingReflectsChangesToDefaultLeftPad() throws Exception {
        // Modify the defaultLeftPad field using reflection
        Field field = HelpFormatter.class.getDeclaredField("defaultLeftPad");
        field.setAccessible(true);
        field.set(formatter, 5);

        assertEquals("Should reflect changes to defaultLeftPad", 5, formatter.getLeftPadding());
    }
}
