package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithIgnoreSurroundingSpaces_519ead7cTest {

    @Test
    public void testWithIgnoreSurroundingSpaces() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT;
        CSVFormat newFormat = format.withIgnoreSurroundingSpaces();

        assertFalse("Original format should have ignoreSurroundingSpaces set to false", format.getIgnoreSurroundingSpaces());
        assertTrue("New format should have ignoreSurroundingSpaces set to true", newFormat.getIgnoreSurroundingSpaces());
    }
}
