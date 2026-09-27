package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.*;

public class OptionsetValueSeparatorTest {
    @Test
    public void testSetValueSeparator() throws Exception {
        Option option = new Option("t", "test");
        char expectedSep = ':';
        option.setValueSeparator(expectedSep);

        // Use reflection to access the private field valuesep
        java.lang.reflect.Field valuesepField = Option.class.getDeclaredField("valuesep");
        valuesepField.setAccessible(true);
        char actualSep = (char) valuesepField.get(option);

        assertEquals(expectedSep, actualSep);
    }
}
