package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class PairgetRightTest {

    @Test
    public void testGetRight() {
        Pair<String, Integer> pair = new Pair<>("test", 123);
        assertEquals(123, pair.getRight().intValue());
    }

    @Test
    public void testGetRightWithDifferentTypes() {
        Pair<Double, String> pair = new Pair<>(3.14, "hello");
        assertEquals("hello", pair.getRight());
    }

    @Test
    public void testGetRightWithNullValue() {
        Pair<Object, Object> pair = new Pair<>(new Object(), null);
        assertNull(pair.getRight());
    }
}
