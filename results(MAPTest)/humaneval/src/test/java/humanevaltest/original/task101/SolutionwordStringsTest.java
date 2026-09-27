package humanevaltest.original.task101;
import org.junit.Test;
import org.junit.Assert;
import java.util.*;
public class SolutionwordStringsTest {
    @Test
    public void testEmptyString() {
        Solution solution = new Solution();
        List<String> result = solution.wordStrings("");
        Assert.assertEquals(new ArrayList<String>(), result);
    }

    @Test
    public void testCommasOnly() {
        Solution solution = new Solution();
        List<String> result = solution.wordStrings(",,,");
        Assert.assertEquals(new ArrayList<String>(), result);
    }

    @Test
    public void testMixedCharacters() {
        Solution solution = new Solution();
        List<String> result = solution.wordStrings("hello,world,this,is,a,test");
        Assert.assertEquals(Arrays.asList("hello", "world", "this", "is", "a", "test"), result);
    }

    @Test
    public void testMultipleSpacesAndCommas() {
        Solution solution = new Solution();
        List<String> result = solution.wordStrings("  hello   ,   world  ,   test ");
        Assert.assertEquals(Arrays.asList("", "hello", "world", "test"), result);
    }

    @Test
    public void testSingleWord() {
        Solution solution = new Solution();
        List<String> result = solution.wordStrings("java");
        Assert.assertEquals(Arrays.asList("java"), result);
    }
}
