package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithRecordSeparator_c6cfed17Test {

    @Test
    public void testWithRecordSeparator() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT;

        // Act
        CSVFormat newFormat = format.withRecordSeparator('\n');

        // Assert
        assertNotNull("Should return a non-null CSVFormat instance", newFormat);
        assertNotSame("Should return a new instance", format, newFormat);
        assertEquals("Record separator should be set to \\n", "\n", newFormat.getRecordSeparator());
    }

    @Test
    public void testWithRecordSeparatorWithExistingRecordSeparator() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator('\r');

        // Act
        CSVFormat newFormat = format.withRecordSeparator('\n');

        // Assert
        assertNotNull("Should return a non-null CSVFormat instance", newFormat);
        assertNotSame("Should return a new instance", format, newFormat);
        assertEquals("Record separator should be set to \\n", "\n", newFormat.getRecordSeparator());
    }

    @Test
    public void testWithRecordSeparatorWithNullCharacter() {
        // Arrange
        CSVFormat format = CSVFormat.DEFAULT;

        // Act
        CSVFormat newFormat = format.withRecordSeparator((char) 0);

        // Assert
        assertNotNull("Should return a non-null CSVFormat instance", newFormat);
        assertNotSame("Should return a new instance", format, newFormat);
        assertEquals("Record separator should be set to \\0", "\0", newFormat.getRecordSeparator());
    }
}
