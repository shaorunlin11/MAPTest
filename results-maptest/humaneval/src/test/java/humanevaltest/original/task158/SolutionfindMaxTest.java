package humanevaltest.original.task158;

import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class SolutionfindMaxTest {

    @Test
    public void testFindMaxWithDifferentUniqueCharacterCounts() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("cherry");
        assertEquals("cherry", solution.findMax(words));
    }

    @Test
    public void testFindMaxWithSameUniqueCharacterCounts() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("cat");
        words.add("dog");
        words.add("hat");
        assertEquals("cat", solution.findMax(words));
    }

    @Test
    public void testFindMaxWithEmptyStrings() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("");
        words.add("a");
        words.add("aa");
        assertEquals("a", solution.findMax(words));
    }

    @Test
    public void testFindMaxWithMultipleStringsHavingSameUniqueCharacters() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        words.add("apple");
        assertEquals("world", solution.findMax(words));
    }

    @Test
    public void testFindMaxWithSingleString() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("test");
        assertEquals("test", solution.findMax(words));
    }

    @Test
    public void testFindMaxWithStringsContainingDuplicateCharacters() {
        Solution solution = new Solution();
        List<String> words = new ArrayList<>();
        words.add("aabba");
        words.add("cccdd");
        words.add("eee");
        assertEquals("aabba", solution.findMax(words));
    }
}
