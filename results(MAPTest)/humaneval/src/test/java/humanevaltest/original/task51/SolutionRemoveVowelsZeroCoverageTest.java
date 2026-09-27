package humanevaltest.original.task51;

import org.junit.Test;

public class SolutionRemoveVowelsZeroCoverageTest {
    @Test
    public void testRemoveVowels() {
        humanevaltest.original.task51.Solution solution = new humanevaltest.original.task51.Solution();
        String result = solution.removeVowels("hello");
        assert result.equals("hll");
    }
}
