package humanevaltest.original.task17;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;
public class SolutionparseMusicTest {
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
    public void testParseMusicWithValidNotes() {
        String input = "o o| .| o o|";
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(2);
        expected.add(1);
        expected.add(4);
        expected.add(2);
        List<Integer> result = solution.parseMusic(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseMusicWithEmptyString() {
        String input = "";
        List<Integer> expected = new ArrayList<>();
        List<Integer> result = solution.parseMusic(input);
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testParseMusicWithUnsupportedNotes() {
        String input = "o x .| o|";
        List<Integer> expected = new ArrayList<>();
        expected.add(4);
        expected.add(1);
        expected.add(2);
        List<Integer> result = solution.parseMusic(input);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testParseMusicWithMixedNotes() {
        String input = "o| .| o o|| .|";
        List<Integer> expected = new ArrayList<>();
        expected.add(2);
        expected.add(1);
        expected.add(4);
        expected.add(1);
        List<Integer> result = solution.parseMusic(input);
        Assert.assertEquals(expected, result);
    }
}
