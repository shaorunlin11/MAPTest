package humanevaltest.original.task95;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;
public class SolutioncheckDictCaseTest {
    @Test
    public void testEmptyDictionary() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        assertFalse(solution.checkDictCase(dict));
    }

    @Test
    public void testNonStringKey() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put(123, "value");
        dict.put("key", "value");
        assertFalse(solution.checkDictCase(dict));
    }

    @Test
    public void testMixedCasing() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put("Key", "value");
        dict.put("anotherKey", "value");
        assertFalse(solution.checkDictCase(dict));
    }



    @Test
    public void testMixedCasingWithNonStringKeys() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put("Key", "value");
        dict.put(123, "value");
        assertFalse(solution.checkDictCase(dict));
    }

    @Test
    public void testSingleKeyUppercase() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put("SINGLE", "value");
        assertTrue(solution.checkDictCase(dict));
    }

    @Test
    public void testSingleKeyLowercase() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put("single", "value");
        assertTrue(solution.checkDictCase(dict));
    }

    @Test
    public void testMultipleKeysWithMixedCasing() {
        Solution solution = new Solution();
        Map<Object, Object> dict = new HashMap<>();
        dict.put("Key1", "value");
        dict.put("key2", "value");
        assertFalse(solution.checkDictCase(dict));
    }
}
