package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class HelpFormattercreatePaddingTest {
    @Test
    public void testCreatePaddingWithZeroLength() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.createPadding(0);
        assertEquals("", result);
    }

    @Test
    public void testCreatePaddingWithPositiveLength() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.createPadding(5);
        assertEquals("     ", result);
    }

    @Test
    public void testCreatePaddingWithLargeLength() {
        HelpFormatter formatter = new HelpFormatter();
        String result = formatter.createPadding(100);
        assertEquals("                                                                                                    ", result);
    }
}
