package humanevaltest.original.task144;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionsimplifyTest {
    @Test
    public void testSimplifyWithIntegerProduct() {
        Solution solution = new Solution();
        assertTrue(solution.simplify("2/1", "3/1"));
        assertTrue(solution.simplify("4/2", "3/1"));
        assertTrue(solution.simplify("1/2", "4/1"));
    }



}
