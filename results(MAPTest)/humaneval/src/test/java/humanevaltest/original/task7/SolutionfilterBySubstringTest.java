package humanevaltest.original.task7;

import org.junit.Test;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.List;

public class SolutionfilterBySubstringTest {
    @Test
    public void testFilterBySubstring() {
        Solution solution = new Solution();

        // Test case 1: Normal case
        List<String> input1 = new ArrayList<>();
        input1.add("apple");
        input1.add("banana");
        input1.add("cherry");
        input1.add("date");
        List<String> result1 = solution.filterBySubstring(input1, "an");
        Assert.assertEquals(1, result1.size());
        Assert.assertTrue(result1.contains("banana"));

        // Test case 2: Empty input list
        List<String> input2 = new ArrayList<>();
        List<String> result2 = solution.filterBySubstring(input2, "test");
        Assert.assertEquals(0, result2.size());

        // Test case 3: No matches
        List<String> input3 = new ArrayList<>();
        input3.add("hello");
        input3.add("world");
        List<String> result3 = solution.filterBySubstring(input3, "xyz");
        Assert.assertEquals(0, result3.size());

        // Test case 4: Multiple matches
        List<String> input4 = new ArrayList<>();
        input4.add("java");
        input4.add("javascript");
        input4.add("python");
        input4.add("typescript");
        List<String> result4 = solution.filterBySubstring(input4, "script");
        Assert.assertEquals(2, result4.size());
        Assert.assertTrue(result4.contains("javascript"));
        Assert.assertTrue(result4.contains("typescript"));

        // Test case 5: Substring at start
        List<String> input5 = new ArrayList<>();
        input5.add("start");
        input5.add("middle");
        input5.add("end");
        List<String> result5 = solution.filterBySubstring(input5, "sta");
        Assert.assertEquals(1, result5.size());
        Assert.assertTrue(result5.contains("start"));

        // Test case 6: Substring at end
        List<String> input6 = new ArrayList<>();
        input6.add("start");
        input6.add("middle");
        input6.add("end");
        List<String> result6 = solution.filterBySubstring(input6, "nd");
        Assert.assertEquals(1, result6.size());
        Assert.assertTrue(result6.contains("end"));

        // Test case 7: Substring in middle
        List<String> input7 = new ArrayList<>();
        input7.add("abcde");
        input7.add("12345");
        input7.add("xyz");
        List<String> result7 = solution.filterBySubstring(input7, "cd");
        Assert.assertEquals(1, result7.size());
        Assert.assertTrue(result7.contains("abcde"));
    }
}
