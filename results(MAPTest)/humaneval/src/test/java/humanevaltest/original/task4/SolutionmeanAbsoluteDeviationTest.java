package humanevaltest.original.task4;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionmeanAbsoluteDeviationTest {
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
    public void testMeanAbsoluteDeviationWithSingleElement() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(5.0);
        double result = solution.meanAbsoluteDeviation(numbers);
        Assert.assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testMeanAbsoluteDeviationWithMultipleElements() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(4.0);
        numbers.add(5.0);
        double result = solution.meanAbsoluteDeviation(numbers);
        Assert.assertEquals(1.2, result, 0.0001);
    }

    @Test
    public void testMeanAbsoluteDeviationWithNegativeNumbers() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(-2.0);
        numbers.add(0.0);
        numbers.add(2.0);
        double result = solution.meanAbsoluteDeviation(numbers);
        Assert.assertEquals(1.3333, result, 0.0001);
    }

    @Test
    public void testMeanAbsoluteDeviationWithEmptyList() {
        List<Double> numbers = new ArrayList<>();
        double result = solution.meanAbsoluteDeviation(numbers);
        Assert.assertTrue(Double.isNaN(result));
    }
}
