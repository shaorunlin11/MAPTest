package org.apache.commons.csv;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class CSVFormatwithTrailingDelimiter_69fa55c8Test {

    @Test
    public void testWithTrailingDelimiter() {
        CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter();
        assertTrue(format.getTrailingDelimiter());
    }
}
