package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.apache.commons.cli.ParseException;
public class UtilsparsePagesOptionTest {
    @Test
    public void testParsePagesOptionAllReturnsNull() throws ParseException {
        List<Integer> result = Utils.parsePagesOption("all");
        assertNull(result);
    }

    @Test
    public void testParsePagesOptionSinglePage() throws ParseException {
        List<Integer> result = Utils.parsePagesOption("5");
        assertEquals(1, result.size());
        assertEquals(5, result.get(0).intValue());
    }

    @Test
    public void testParsePagesOptionSingleRange() throws ParseException {
        List<Integer> result = Utils.parsePagesOption("1-5");
        assertEquals(5, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(5, result.get(4).intValue());
    }

    @Test
    public void testParsePagesOptionMultipleRanges() throws ParseException {
        List<Integer> result = Utils.parsePagesOption("1-3,5");
        assertEquals(4, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(3, result.get(2).intValue());
        assertEquals(5, result.get(3).intValue());
    }

    @Test
    public void testParsePagesOptionRangeWithStartGreaterThanEnd() throws ParseException {
        try {
            Utils.parsePagesOption("5-1");
            fail("Expected ParseException");
        } catch (ParseException e) {
            // Expected exception
        }
    }

    @Test
    public void testParsePagesOptionInvalidNumericValue() throws ParseException {
        try {
            Utils.parsePagesOption("a-b");
            fail("Expected ParseException");
        } catch (ParseException e) {
            // Expected exception
        }
    }

    @Test
    public void testParsePagesOptionEmptyString() throws ParseException {
        try {
            Utils.parsePagesOption("");
            fail("Expected ParseException");
        } catch (ParseException e) {
            // Expected exception
        }
    }


    @Test
    public void testParsePagesOptionMixedValidAndInvalidRanges() throws ParseException {
        try {
            Utils.parsePagesOption("1-3,invalid");
            fail("Expected ParseException");
        } catch (ParseException e) {
            // Expected exception
        }
    }

    @Test
    public void testParsePagesOptionSortedResult() throws ParseException {
        List<Integer> result = Utils.parsePagesOption("3,1-2");
        assertEquals(3, result.size());
        assertEquals(1, result.get(0).intValue());
        assertEquals(2, result.get(1).intValue());
        assertEquals(3, result.get(2).intValue());
    }
}
