package humanevaltest.original.task12;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.*;
import java.lang.*;

public class SolutionlongestTest {
    private Solution solution;

    @Before
    public void setUp() {
        solution = new Solution();
    }

    @After
    public void tearDown() {
        solution = null;
    }

    @Test
    public void testLongestEmptyList() {
        List<String> strings = new ArrayList<>();
        Optional<String> result = solution.longest(strings);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testLongestSingleElement() {
        List<String> strings = new ArrayList<>();
        strings.add("hello");
        Optional<String> result = solution.longest(strings);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("hello", result.get());
    }

    @Test
    public void testLongestMultipleElements() {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("ab");
        strings.add("abc");
        Optional<String> result = solution.longest(strings);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("abc", result.get());
    }

    @Test
    public void testLongestEqualLengthStrings() {
        List<String> strings = new ArrayList<>();
        strings.add("abc");
        strings.add("def");
        strings.add("ghi");
        Optional<String> result = solution.longest(strings);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("abc", result.get());
    }
}
