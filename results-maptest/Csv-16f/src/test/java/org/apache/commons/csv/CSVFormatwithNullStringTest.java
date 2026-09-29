package org.apache.commons.csv;

import org.junit.Test;
import static org.junit.Assert.*;

public class CSVFormatwithNullStringTest {

    @Test
    public void testWithNullString() throws Exception {
        CSVFormat format = CSVFormat.DEFAULT.withNullString("NULL");
        assertEquals("NULL", format.getNullString());

        CSVFormat format2 = CSVFormat.DEFAULT.withNullString("");
        assertEquals("", format2.getNullString());

        CSVFormat format3 = CSVFormat.DEFAULT.withNullString(null);
        assertNull(format3.getNullString());
    }
}
