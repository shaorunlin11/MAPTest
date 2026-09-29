package humanevaltest.original.task152;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.*;

import static org.junit.Assert.*;

public class SolutioncompareTest {
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
    public void testCompareWithEmptyLists() {
        List<Integer> game = new ArrayList<>();
        List<Integer> guess = new ArrayList<>();
        List<Integer> result = solution.compare(game, guess);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCompareWithSingleElement() {
        List<Integer> game = new ArrayList<>();
        game.add(5);
        List<Integer> guess = new ArrayList<>();
        guess.add(3);
        List<Integer> result = solution.compare(game, guess);
        assertEquals(1, result.size());
        assertEquals(2, result.get(0).intValue());
    }

    @Test
    public void testCompareWithMultipleElements() {
        List<Integer> game = new ArrayList<>();
        game.add(10);
        game.add(20);
        game.add(30);
        List<Integer> guess = new ArrayList<>();
        guess.add(15);
        guess.add(25);
        guess.add(35);
        List<Integer> result = solution.compare(game, guess);
        assertEquals(3, result.size());
        assertEquals(5, result.get(0).intValue());
        assertEquals(5, result.get(1).intValue());
        assertEquals(5, result.get(2).intValue());
    }

    @Test
    public void testCompareWithDifferentLengths() {
        List<Integer> game = new ArrayList<>();
        game.add(1);
        game.add(2);
        List<Integer> guess = new ArrayList<>();
        guess.add(3);
        try {
            solution.compare(game, guess);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // Expected exception
        }
    }
}
