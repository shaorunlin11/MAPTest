package org.apache.commons.csv;

import org.junit.Test;
import java.io.Reader;
import java.io.StringReader;
import java.io.IOException;

public class CSVParserparse_4ecb84b0Test {

    @Test
    public void testParse() throws IOException {
        Reader reader = new StringReader("test");
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser parser = CSVParser.parse(reader, format);
        // Verify that the parser is not null
        assert parser != null;
    }
}
