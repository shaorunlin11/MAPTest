package humanevaltest.original.task29;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class SolutionfilterByPrefixTest {
    @Test
    public void testFilterByPrefix() {
        Solution solution = new Solution();

        // Test case 1: Normal case
        List<String> input1 = new ArrayList<>();
        input1.add("apple");
        input1.add("app");
        input1.add("banana");
        input1.add("applesauce");
        String prefix1 = "app";
        List<String> result1 = solution.filterByPrefix(input1, prefix1);
        Assert.assertEquals("[apple, app, applesauce]", result1.toString());

        // Test case 2: Empty list
        List<String> input2 = new ArrayList<>();
        String prefix2 = "app";
        List<String> result2 = solution.filterByPrefix(input2, prefix2);
        Assert.assertEquals("[]", result2.toString());

        // Test case 3: No matching strings
        List<String> input3 = new ArrayList<>();
        input3.add("banana");
        input3.add("orange");
        String prefix3 = "app";
        List<String> result3 = solution.filterByPrefix(input3, prefix3);
        Assert.assertEquals("[]", result3.toString());

        // Test case 4: Prefix is empty string
        List<String> input4 = new ArrayList<>();
        input4.add("apple");
        input4.add("banana");
        String prefix4 = "";
        List<String> result4 = solution.filterByPrefix(input4, prefix4);
        Assert.assertEquals("[apple, banana]", result4.toString());

        // Test case 5: Null prefix
        List<String> input5 = new ArrayList<>();
        input5.add("apple");
        String prefix5 = null;
        try {
            solution.filterByPrefix(input5, prefix5);
            Assert.fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }

        // Test case 6: Null input list
        List<String> input6 = null;
        String prefix6 = "app";
        try {
            solution.filterByPrefix(input6, prefix6);
            Assert.fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
