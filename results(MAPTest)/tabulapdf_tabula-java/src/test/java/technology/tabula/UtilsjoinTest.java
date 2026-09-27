package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class UtilsjoinTest {

    @Test
    public void testJoinWithEmptyArray() {
        String result = Utils.join(";", new String[0]);
        assertNull(result);
    }

    @Test
    public void testJoinWithSingleElement() {
        String result = Utils.join(";", "hello");
        assertEquals("hello", result);
    }

    @Test
    public void testJoinWithMultipleElements() {
        String result = Utils.join(",", "a", "b", "c");
        assertEquals("a,b,c", result);
    }

    @Test
    public void testJoinWithMultipleElementsAndEmptyGlue() {
        String result = Utils.join("", "x", "y", "z");
        assertEquals("xyz", result);
    }

    @Test
    public void testJoinWithNullGlue() {
        String result = Utils.join(null, "1", "2", "3");
        assertEquals("1null2null3", result);
    }

    @Test
    public void testJoinWithNullStrings() {
        String result = Utils.join(":", "null", null, "non-null");
        assertEquals("null:null:non-null", result);
    }
}
