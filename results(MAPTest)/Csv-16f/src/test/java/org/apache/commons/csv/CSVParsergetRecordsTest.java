package org.apache.commons.csv;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.io.StringReader;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.*;
public class CSVParsergetRecordsTest {
    private CSVParser csvParser;
    private StringReader stringReader;

    @Before
    public void setUp() throws IOException {
        String csvData = "name,age\nAlice,30\nBob,25";
        stringReader = new StringReader(csvData);
        csvParser = new CSVParser(stringReader, CSVFormat.DEFAULT);
    }

    @After
    public void tearDown() throws IOException {
        if (stringReader != null) {
            stringReader.close();
        }
    }


    @Test
    public void testGetRecords_WithEmptyInput_ReturnsEmptyList() throws IOException {
        String emptyCsv = "";
        StringReader emptyReader = new StringReader(emptyCsv);
        CSVParser emptyParser = new CSVParser(emptyReader, CSVFormat.DEFAULT);
        List<CSVRecord> records = emptyParser.getRecords();
        assertTrue(records.isEmpty());
        emptyParser.close();
    }

}
