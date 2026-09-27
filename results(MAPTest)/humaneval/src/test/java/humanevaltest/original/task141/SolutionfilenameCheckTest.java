package humanevaltest.original.task141;
import org.junit.Test;
import static org.junit.Assert.*;
public class SolutionfilenameCheckTest {
    @Test
    public void testFilenameCheck() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("file_name");
        assertEquals("No", result);
    }

    @Test
    public void testFilenameCheckTargetLines() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("valid.txt");
        assertEquals("Yes", result);
    }

@Test
    public void testFilenameCheckTargetLines14() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("testfile.txt");
        assertEquals("Yes", result);
    }

@Test
    public void testFilenameCheckTargetLines14WithInvalidExtension() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("testfile.doc");
        assertEquals("No", result);
    }

@Test
    public void testFilenameCheckTargetLines14WithInvalidFormat() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("testfile");
        assertEquals("No", result);
    }

@Test
    public void testFilenameCheckTargetLines14WithEmptyName() {
        Solution solution = new Solution();
        String result = solution.filenameCheck(".txt");
        assertEquals("No", result);
    }

@Test
    public void testFilenameCheckTargetLines14WithInvalidFirstChar() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("1test.txt");
        assertEquals("No", result);
    }

@Test
    public void testFilenameCheckTargetLines14WithMoreThanThreeDigits() {
        Solution solution = new Solution();
        String result = solution.filenameCheck("test1234.txt");
        assertEquals("No", result);
    }
}
