package humanevaltest.original.task86;
import org.junit.Test;
import java.util.*;
import static org.junit.Assert.assertEquals;
public class SolutionantiShuffleTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        String input = "hello world";
        String expected = "ehllo dlorw";
        String result = solution.antiShuffle(input);
        assertEquals(expected, result);
    }
}
