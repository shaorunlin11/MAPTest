package humanevaltest.original.task81;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import java.util.Arrays;

public class SolutionnumericalLetterGradeTest {
    @Test
    public void testNumericalLetterGrade() {
        Solution solution = new Solution();

        // Test case 1: GPA exactly 4.0
        List<Double> grades1 = new ArrayList<>();
        grades1.add(4.0);
        List<String> result1 = solution.numericalLetterGrade(grades1);
        assertEquals(Arrays.asList("A+"), result1);

        // Test case 2: GPA greater than 3.7 but less than 4.0
        List<Double> grades2 = new ArrayList<>();
        grades2.add(3.8);
        List<String> result2 = solution.numericalLetterGrade(grades2);
        assertEquals(Arrays.asList("A"), result2);

        // Test case 3: GPA greater than 3.3 but less than 3.7
        List<Double> grades3 = new ArrayList<>();
        grades3.add(3.5);
        List<String> result3 = solution.numericalLetterGrade(grades3);
        assertEquals(Arrays.asList("A-"), result3);

        // Test case 4: GPA greater than 3.0 but less than 3.3
        List<Double> grades4 = new ArrayList<>();
        grades4.add(3.1);
        List<String> result4 = solution.numericalLetterGrade(grades4);
        assertEquals(Arrays.asList("B+"), result4);

        // Test case 5: GPA greater than 2.7 but less than 3.0
        List<Double> grades5 = new ArrayList<>();
        grades5.add(2.8);
        List<String> result5 = solution.numericalLetterGrade(grades5);
        assertEquals(Arrays.asList("B"), result5);

        // Test case 6: GPA greater than 2.3 but less than 2.7
        List<Double> grades6 = new ArrayList<>();
        grades6.add(2.5);
        List<String> result6 = solution.numericalLetterGrade(grades6);
        assertEquals(Arrays.asList("B-"), result6);

        // Test case 7: GPA greater than 2.0 but less than 2.3
        List<Double> grades7 = new ArrayList<>();
        grades7.add(2.1);
        List<String> result7 = solution.numericalLetterGrade(grades7);
        assertEquals(Arrays.asList("C+"), result7);

        // Test case 8: GPA greater than 1.7 but less than 2.0
        List<Double> grades8 = new ArrayList<>();
        grades8.add(1.8);
        List<String> result8 = solution.numericalLetterGrade(grades8);
        assertEquals(Arrays.asList("C"), result8);

        // Test case 9: GPA greater than 1.3 but less than 1.7
        List<Double> grades9 = new ArrayList<>();
        grades9.add(1.5);
        List<String> result9 = solution.numericalLetterGrade(grades9);
        assertEquals(Arrays.asList("C-"), result9);

        // Test case 10: GPA greater than 1.0 but less than 1.3
        List<Double> grades10 = new ArrayList<>();
        grades10.add(1.1);
        List<String> result10 = solution.numericalLetterGrade(grades10);
        assertEquals(Arrays.asList("D+"), result10);

        // Test case 11: GPA greater than 0.7 but less than 1.0
        List<Double> grades11 = new ArrayList<>();
        grades11.add(0.8);
        List<String> result11 = solution.numericalLetterGrade(grades11);
        assertEquals(Arrays.asList("D"), result11);

        // Test case 12: GPA greater than 0.0 but less than 0.7
        List<Double> grades12 = new ArrayList<>();
        grades12.add(0.5);
        List<String> result12 = solution.numericalLetterGrade(grades12);
        assertEquals(Arrays.asList("D-"), result12);

        // Test case 13: GPA less than or equal to 0.0
        List<Double> grades13 = new ArrayList<>();
        grades13.add(0.0);
        List<String> result13 = solution.numericalLetterGrade(grades13);
        assertEquals(Arrays.asList("E"), result13);

        // Test case 14: Multiple GPA values
        List<Double> grades14 = new ArrayList<>();
        grades14.add(4.0);
        grades14.add(3.8);
        grades14.add(3.5);
        grades14.add(3.1);
        grades14.add(2.8);
        grades14.add(2.5);
        grades14.add(2.1);
        grades14.add(1.8);
        grades14.add(1.5);
        grades14.add(1.1);
        grades14.add(0.8);
        grades14.add(0.5);
        grades14.add(0.0);
        List<String> result14 = solution.numericalLetterGrade(grades14);
        assertEquals(Arrays.asList("A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "E"), result14);
    }
}
