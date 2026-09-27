package humanevaltest.original.task143;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
public class SolutionwordsInSentenceTest {
    @Test
    public void testExample() {
        // This is a placeholder test method to satisfy JUnit requirements
        // Replace with actual test logic when available
    }

@Test
    public void testWordsInSentenceWithValidInput() {
        Solution solution = new Solution();
        String result = solution.wordsInSentence("a bc def");
        assertEquals("bc def", result);
    }
}
