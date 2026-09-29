package humanevaltest.original.task14;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

public class SolutionallPrefixesTest {
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
    public void testAllPrefixesEmptyString() {
        List<String> result = solution.allPrefixes("");
        Assert.assertTrue(result.isEmpty());
    }

    @Test
    public void testAllPrefixesSingleCharacter() {
        List<String> result = solution.allPrefixes("a");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("a", result.get(0));
    }

    @Test
    public void testAllPrefixesMultipleCharacters() {
        List<String> result = solution.allPrefixes("abc");
        Assert.assertEquals(3, result.size());
        Assert.assertEquals("a", result.get(0));
        Assert.assertEquals("ab", result.get(1));
        Assert.assertEquals("abc", result.get(2));
    }

    @Test
    public void testAllPrefixesNullString() {
        try {
            solution.allPrefixes(null);
            Assert.fail("Expected NullPointerException was not thrown");
        } catch (NullPointerException e) {
            // Expected exception
        }
    }
}
