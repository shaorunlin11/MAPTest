package org.apache.commons.csv;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CSVFormatwithIgnoreHeaderCase_42cff125Test {

    @Test
    public void testWithIgnoreHeaderCase() {
        CSVFormat format = CSVFormat.DEFAULT.withIgnoreHeaderCase();
        assertEquals(true, format.getIgnoreHeaderCase());
    }
}
