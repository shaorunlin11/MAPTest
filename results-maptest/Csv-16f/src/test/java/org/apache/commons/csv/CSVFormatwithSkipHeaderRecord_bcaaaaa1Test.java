package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithSkipHeaderRecord_bcaaaaa1Test {

    @Test
    public void testWithSkipHeaderRecord() {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVFormat newFormat = format.withSkipHeaderRecord();

        assertTrue("skipHeaderRecord should be true", newFormat.getSkipHeaderRecord());
    }
}
