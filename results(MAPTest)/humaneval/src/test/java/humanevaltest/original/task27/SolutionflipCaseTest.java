package humanevaltest.original.task27;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionflipCaseTest {
    @Test
    public void testFlipCaseWithEmptyString() {
        Solution solution = new Solution();
        assertEquals("", solution.flipCase(""));
    }

    @Test
    public void testFlipCaseWithAllLowercaseLetters() {
        Solution solution = new Solution();
        assertEquals("HELLOWORLD", solution.flipCase("helloworld"));
    }

    @Test
    public void testFlipCaseWithAllUppercaseLetters() {
        Solution solution = new Solution();
        assertEquals("helloworld", solution.flipCase("HELLOWORLD"));
    }

    @Test
    public void testFlipCaseWithMixedCaseLetters() {
        Solution solution = new Solution();
        assertEquals("hEllOwORlD", solution.flipCase("HeLLoWorLd"));
    }

    @Test
    public void testFlipCaseWithNonAlphabeticCharacters() {
        Solution solution = new Solution();
        assertEquals("123AbC", solution.flipCase("123aBc"));
    }
}
