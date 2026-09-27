package org.apache.commons.csv;
import org.junit.Test;
import static org.junit.Assert.*;
public class CSVFormatwithFirstRecordAsHeaderTest {
    @Test
    public void test() {
        // Test method body
    }

@Test
    public void testWithFirstRecordAsHeader() {
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        assertNotNull("withHeader() must return a non-null CSVFormat instance", format);
        format.withSkipHeaderRecord();
    }
}
