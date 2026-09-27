package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class PairgetLeftTest {
    @Test
    public void testGetLeftReturnsInitializedValue() {
        Pair<String, Integer> pair = new Pair<>("test", 123);
        assertEquals("test", pair.getLeft());
    }

    @Test
    public void testGetLeftWithNullValue() {
        Pair<String, Integer> pair = new Pair<>(null, 456);
        assertNull(pair.getLeft());
    }
}
