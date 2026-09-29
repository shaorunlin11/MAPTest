package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithIgnoreEmptyLines_77de7b43Test {

    @Test
    public void testWithIgnoreEmptyLines() {
        CSVFormat format = CSVFormat.DEFAULT.withIgnoreEmptyLines(false);
        CSVFormat newFormat = format.withIgnoreEmptyLines();

        assertFalse("Original format should have ignoreEmptyLines set to false", format.getIgnoreEmptyLines());
        assertTrue("New format should have ignoreEmptyLines set to true", newFormat.getIgnoreEmptyLines());
    }
}
