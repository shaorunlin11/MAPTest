package org.apache.commons.csv;
import org.junit.Test;
import java.io.StringReader;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
public class CSVParsergetFirstEndOfLineTest {

    @Test
    public void testGetFirstEndOfLineWithNoNewline() throws IOException {
        String csvContent = "header1,header2";
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser parser = new CSVParser(new StringReader(csvContent), format);

        String result = parser.getFirstEndOfLine();

        assertNull(result);
    }
}
