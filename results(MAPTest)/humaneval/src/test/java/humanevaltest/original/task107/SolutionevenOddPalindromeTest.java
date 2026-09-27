package humanevaltest.original.task107;
import java.util.*;
import java.lang.*;
import org.junit.Test;
import org.junit.Assert;
public class SolutionevenOddPalindromeTest {
    @Test
    public void test() {
        Solution solution = new Solution();
        List<Integer> result = solution.evenOddPalindrome(10);
        Assert.assertEquals(Arrays.asList(4, 5), result);
    }
}
