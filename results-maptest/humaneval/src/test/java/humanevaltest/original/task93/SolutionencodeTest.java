package humanevaltest.original.task93;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class SolutionencodeTest {
    @Test
    public void testExample() {
        // Example test case - replace with actual test logic
        assertEquals("example", "example");
    }

@Test
    public void testEncodeWithUppercaseVowel() {
        Solution solution = new Solution();
        String result = solution.encode("Apple");
        assertEquals("cPPLG", result);
    }
}
