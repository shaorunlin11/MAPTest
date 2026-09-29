package org.apache.commons.csv;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class CSVFormatformatTest {
    @Test
    public void testFormatWithSingleValue() {
        CSVFormat format = CSVFormat.DEFAULT;
        String result = format.format("test");
        assertEquals("test", result);
    }

    @Test
    public void testFormatWithMultipleValues() {
        CSVFormat format = CSVFormat.DEFAULT;
        String result = format.format("a", "b", "c");
        assertEquals("a,b,c", result);
    }

    @Test
    public void testFormatWithEmptyValues() {
        CSVFormat format = CSVFormat.DEFAULT;
        String result = format.format();
        assertEquals("", result);
    }

    @Test
    public void testFormatWithNullValues() {
        CSVFormat format = CSVFormat.DEFAULT;
        String result = format.format(new Object[0]);
        assertEquals("", result);
    }

    @Test
    public void testFormatWithMixedValues() {
        CSVFormat format = CSVFormat.DEFAULT;
        String result = format.format("hello", 123, true);
        assertEquals("hello,123,true", result);
    }


    @Test
    public void testFormatWithQuoteModeAllNonNull() {
        CSVFormat format = CSVFormat.POSTGRESQL_CSV;
        String result = format.format("hello", "world", "test");
        assertEquals("\"hello\",\"world\",\"test\"", result);
    }

    @Test
    public void testFormatWithQuoteModeMinimal() {
        CSVFormat format = CSVFormat.ORACLE;
        String result = format.format("hello", "world", "test");
        assertEquals("hello,world,test", result);
    }


    @Test
    public void testFormatWithTrailingDelimiter() {
        CSVFormat format = CSVFormat.DEFAULT.withTrailingDelimiter(true);
        String result = format.format("a", "b", "c");
        assertEquals("a,b,c,", result);
    }

    @Test
    public void testFormatWithTrim() {
        CSVFormat format = CSVFormat.ORACLE.withTrim();
        String result = format.format("  hello  ", "  world  ");
        assertEquals("hello,world", result);
    }
}
