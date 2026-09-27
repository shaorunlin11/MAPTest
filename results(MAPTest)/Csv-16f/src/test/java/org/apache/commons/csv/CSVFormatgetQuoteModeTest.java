package org.apache.commons.csv;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class CSVFormatgetQuoteModeTest {




    @Test
    public void testGetQuoteMode_MYSQL() {
        assertEquals(QuoteMode.ALL_NON_NULL, CSVFormat.MYSQL.getQuoteMode());
    }

    @Test
    public void testGetQuoteMode_ORACLE() {
        assertEquals(QuoteMode.MINIMAL, CSVFormat.ORACLE.getQuoteMode());
    }

    @Test
    public void testGetQuoteMode_POSTGRESQL_CSV() {
        assertEquals(QuoteMode.ALL_NON_NULL, CSVFormat.POSTGRESQL_CSV.getQuoteMode());
    }

    @Test
    public void testGetQuoteMode_POSTGRESQL_TEXT() {
        assertEquals(QuoteMode.ALL_NON_NULL, CSVFormat.POSTGRESQL_TEXT.getQuoteMode());
    }

}
