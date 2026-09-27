package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithDelimiterTest {

    @Test
    public void testWithDelimiter() {
        CSVFormat format = CSVFormat.DEFAULT;
        char newDelimiter = ';';
        CSVFormat newFormat = format.withDelimiter(newDelimiter);
        assertEquals(newDelimiter, newFormat.getDelimiter());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWithDelimiterLineBreak() {
        CSVFormat format = CSVFormat.DEFAULT;
        char lineBreak = '\n';
        format.withDelimiter(lineBreak);
    }
}
