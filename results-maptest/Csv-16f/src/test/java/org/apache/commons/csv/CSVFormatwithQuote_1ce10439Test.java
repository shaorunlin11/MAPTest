package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithQuote_1ce10439Test {

    @Test
    public void testWithQuote() {
        CSVFormat format = CSVFormat.DEFAULT;
        char newQuoteChar = '"';
        CSVFormat newFormat = format.withQuote(newQuoteChar);

        assertEquals(Character.valueOf(newQuoteChar), newFormat.getQuoteCharacter());
    }
}
