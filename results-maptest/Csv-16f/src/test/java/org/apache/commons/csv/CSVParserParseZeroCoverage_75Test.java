package org.apache.commons.csv;

import org.junit.Test;

public class CSVParserParseZeroCoverage_75Test {
    @Test
    public void testParseMethod() throws Exception {
        String string = "test";
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser parser = CSVParser.parse(string, format);
    }
}
