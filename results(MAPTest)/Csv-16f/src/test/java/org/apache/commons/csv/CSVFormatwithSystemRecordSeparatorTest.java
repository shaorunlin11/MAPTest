package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CSVFormatwithSystemRecordSeparatorTest {
    @Test
    public void testWithSystemRecordSeparator() {
        CSVFormat format = CSVFormat.DEFAULT.withSystemRecordSeparator();
        String expectedSeparator = System.getProperty("line.separator");
        assertEquals("Record separator should be set to system line separator", expectedSeparator, format.getRecordSeparator());
    }
}
