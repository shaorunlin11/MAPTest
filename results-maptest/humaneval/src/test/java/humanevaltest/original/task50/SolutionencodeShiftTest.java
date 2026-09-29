package humanevaltest.original.task50;

import org.junit.Test;
import static org.junit.Assert.*;

public class SolutionencodeShiftTest {
    @Test
    public void testEncodeShiftEmptyString() {
        Solution solution = new Solution();
        assertEquals("", solution.encodeShift(""));
    }

    @Test
    public void testEncodeShiftSingleCharacter() {
        Solution solution = new Solution();
        assertEquals("f", solution.encodeShift("a"));
        assertEquals("e", solution.encodeShift("z"));
    }

    @Test
    public void testEncodeShiftMultipleCharacters() {
        Solution solution = new Solution();
        assertEquals("fghij", solution.encodeShift("abcde"));
        assertEquals("efg", solution.encodeShift("zab"));
    }

    @Test
    public void testEncodeShiftWithMixedCharacters() {
        Solution solution = new Solution();
        assertEquals("jklmnopqrst", solution.encodeShift("efghijklmno"));
    }
}
