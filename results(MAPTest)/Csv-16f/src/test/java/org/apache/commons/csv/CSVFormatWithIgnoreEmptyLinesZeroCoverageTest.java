package org.apache.commons.csv;

import org.junit.Test;

public class CSVFormatWithIgnoreEmptyLinesZeroCoverageTest {
    @Test
    public void testWithIgnoreEmptyLines() {
        CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines(true);
        assert format.getIgnoreEmptyLines() == true;

        format = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        assert format.getIgnoreEmptyLines() == false;
    }
}
