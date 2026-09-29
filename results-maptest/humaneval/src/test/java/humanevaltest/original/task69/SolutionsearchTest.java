package humanevaltest.original.task69;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.util.*;
import static org.junit.Assert.*;
public class SolutionsearchTest {
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
    public void testSearchWithEmptyList() {
        List<Integer> lst = new ArrayList<>();
        try {
            solution.search(lst);
            fail("Expected exception when passing an empty list");
        } catch (NoSuchElementException e) {
            // Expected exception
        }
    }


    @Test
    public void testSearchWithMultipleElementsSatisfyingCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 3));
        int result = solution.search(lst);
        assertEquals(3, result);
    }

    @Test
    public void testSearchWithSingleElementSatisfyingCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(3, 3, 3));
        int result = solution.search(lst);
        assertEquals(3, result);
    }

    @Test
    public void testSearchWithElementAtBoundaryCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 3));
        int result = solution.search(lst);
        assertEquals(3, result);
    }

    @Test
    public void testSearchWithAllElementsSatisfyingCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4));
        int result = solution.search(lst);
        assertEquals(4, result);
    }

    @Test
    public void testSearchWithOneElementThatMeetsCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(5, 5, 5, 5, 5));
        int result = solution.search(lst);
        assertEquals(5, result);
    }

    @Test
    public void testSearchWithMultipleElementsButOnlyOneMeetsCondition() {
        List<Integer> lst = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5));
        int result = solution.search(lst);
        assertEquals(5, result);
    }
}
