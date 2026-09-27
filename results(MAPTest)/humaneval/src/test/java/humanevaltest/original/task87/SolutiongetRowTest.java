package humanevaltest.original.task87;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.*;
import static org.junit.Assert.*;
public class SolutiongetRowTest {
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
    public void testGetRow_EmptyList_ReturnsEmptyList() {
        List<List<Integer>> lst = new ArrayList<>();
        int x = 5;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRow_ListWithNoOccurrencesOfX_ReturnsEmptyList() {
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        lst.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        lst.add(new ArrayList<>(Arrays.asList(7, 8, 9)));
        int x = 10;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGetRow_ListWithSingleOccurrenceOfX_ReturnsSingleCoordinate() {
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        lst.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        lst.add(new ArrayList<>(Arrays.asList(7, 8, 9)));
        int x = 5;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(1, 1), result.get(0));
    }

    @Test
    public void testGetRow_ListWithMultipleOccurrencesOfX_ReturnsAllCoordinates() {
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        lst.add(new ArrayList<>(Arrays.asList(4, 5, 5)));
        lst.add(new ArrayList<>(Arrays.asList(7, 5, 9)));
        int x = 5;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertEquals(3, result.size());
        assertTrue(result.contains(Arrays.asList(1, 1)));
        assertTrue(result.contains(Arrays.asList(1, 2)));
        assertTrue(result.contains(Arrays.asList(2, 1)));
    }


    @Test
    public void testGetRow_ListWithXAtEndOfSublists_ReturnsCorrectCoordinates() {
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        lst.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        lst.add(new ArrayList<>(Arrays.asList(7, 8, 9)));
        int x = 3;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(0, 2), result.get(0));
    }

    @Test
    public void testGetRow_ListWithXInMultipleSublists_ReturnsAllCoordinates() {
        List<List<Integer>> lst = new ArrayList<>();
        lst.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        lst.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        lst.add(new ArrayList<>(Arrays.asList(7, 8, 9)));
        int x = 5;
        List<List<Integer>> result = solution.getRow(lst, x);
        assertEquals(1, result.size());
        assertEquals(Arrays.asList(1, 1), result.get(0));
    }
}
