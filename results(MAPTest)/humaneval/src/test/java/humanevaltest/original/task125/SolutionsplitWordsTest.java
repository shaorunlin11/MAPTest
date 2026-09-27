package humanevaltest.original.task125;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;
public class SolutionsplitWordsTest {
    @Test
    public void testSplitWordsWithSpace() {
        Solution solution = new Solution();
        Object result = solution.splitWords("hello world");
        assertTrue(result instanceof List);
        assertEquals(Arrays.asList("hello", "world"), (List<String>) result);
    }

    @Test
    public void testSplitWordsWithComma() {
        Solution solution = new Solution();
        Object result = solution.splitWords("apple,banana");
        assertTrue(result instanceof List);
        assertEquals(Arrays.asList("apple", "banana"), (List<String>) result);
    }


    @Test
    public void testSplitWordsWithoutDelimiter() {
        Solution solution = new Solution();
        Object result = solution.splitWords("abcdef");
        assertTrue(result instanceof Integer);
        assertEquals(3, (int) result); // 'b', 'd', 'f' are lowercase letters at odd positions
    }

    @Test
    public void testSplitWordsWithMixedCharacters() {
        Solution solution = new Solution();
        Object result = solution.splitWords("AbC,dE");
        assertTrue(result instanceof List);
        assertEquals(Arrays.asList("AbC", "dE"), (List<String>) result);
    }


    @Test
    public void testSplitWordsWithOnlySpaces() {
        Solution solution = new Solution();
        Object result = solution.splitWords("   ");
        assertTrue(result instanceof List);
        assertEquals(0, ((List<String>) result).size());
    }

    @Test
    public void testSplitWordsWithOnlyCommas() {
        Solution solution = new Solution();
        Object result = solution.splitWords(",,,");
        assertTrue(result instanceof List);
        assertEquals(0, ((List<String>) result).size());
    }
}
