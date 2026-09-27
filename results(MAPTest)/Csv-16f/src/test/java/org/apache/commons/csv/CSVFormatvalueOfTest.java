package org.apache.commons.csv;
import org.junit.Test;
import static org.junit.Assert.*;
public class CSVFormatvalueOfTest {








    @Test
    public void testRfc4180ValueOf() {
        CSVFormat format = CSVFormat.valueOf("RFC4180");
        assertNotNull(format);
        assertEquals(',', format.getDelimiter());
        assertEquals('"', format.getQuoteCharacter().charValue());
        assertFalse(format.getIgnoreEmptyLines());
    }

    @Test
    public void testTdfValueOf() {
        CSVFormat format = CSVFormat.valueOf("TDF");
        assertNotNull(format);
        assertEquals('\t', format.getDelimiter());
        assertTrue(format.getIgnoreSurroundingSpaces());
    }
}
