package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CSVFormatwithEscape_09099068Test {

    @Test
    public void testWithEscape() {
        CSVFormat format = CSVFormat.DEFAULT.withEscape('a');
        assertEquals(Character.valueOf('a'), format.getEscapeCharacter());
    }

    @Test
    public void testWithEscapeWithDifferentChar() {
        CSVFormat format = CSVFormat.DEFAULT.withEscape('\t');
        assertEquals(Character.valueOf('\t'), format.getEscapeCharacter());
    }

    @Test
    public void testWithEscapeWithBackslash() {
        CSVFormat format = CSVFormat.DEFAULT.withEscape(Constants.BACKSLASH);
        assertEquals(Character.valueOf(Constants.BACKSLASH), format.getEscapeCharacter());
    }

    @Test
    public void testWithEscapeWithDoubleQuote() {
        CSVFormat format = CSVFormat.DEFAULT.withEscape(Constants.DOUBLE_QUOTE_CHAR);
        assertEquals(Character.valueOf(Constants.DOUBLE_QUOTE_CHAR), format.getEscapeCharacter());
    }

    @Test
    public void testWithEscapeWithNull() {
        CSVFormat format = CSVFormat.DEFAULT.withEscape((char) 0);
        assertEquals(Character.valueOf((char) 0), format.getEscapeCharacter());
    }
}
