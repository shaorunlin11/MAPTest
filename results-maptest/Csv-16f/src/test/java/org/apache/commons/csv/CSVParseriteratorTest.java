package org.apache.commons.csv;
import org.junit.Test;
import org.junit.Assert;
import java.io.StringReader;
import java.util.Iterator;
public class CSVParseriteratorTest {
    @Test
    public void testIteratorReturnsSameInstance() throws Exception {
        String csvData = "name,age\nJohn,30";
        StringReader reader = new StringReader(csvData);
        CSVFormat format = CSVFormat.DEFAULT;
        CSVParser parser = new CSVParser(reader, format);

        Iterator<CSVRecord> iterator1 = parser.iterator();
        Iterator<CSVRecord> iterator2 = parser.iterator();

        Assert.assertSame("The iterator method should return the same instance each time", iterator1, iterator2);
    }
}
