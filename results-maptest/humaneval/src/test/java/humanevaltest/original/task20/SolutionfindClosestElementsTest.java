package humanevaltest.original.task20;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.*;

public class SolutionfindClosestElementsTest {
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
    public void testFindClosestElementsWithTwoElements() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        List<Double> result = solution.findClosestElements(numbers);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(1.0, result.get(0), 0.0001);
        Assert.assertEquals(2.0, result.get(1), 0.0001);
    }

    @Test
    public void testFindClosestElementsWithMultipleElements() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(3.0);
        numbers.add(4.0);
        numbers.add(5.0);
        numbers.add(7.0);
        List<Double> result = solution.findClosestElements(numbers);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(3.0, result.get(0), 0.0001);
        Assert.assertEquals(4.0, result.get(1), 0.0001);
    }

    @Test
    public void testFindClosestElementsWithDuplicateElements() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(2.0);
        numbers.add(2.0);
        numbers.add(3.0);
        List<Double> result = solution.findClosestElements(numbers);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(2.0, result.get(0), 0.0001);
        Assert.assertEquals(2.0, result.get(1), 0.0001);
    }

    @Test
    public void testFindClosestElementsWithNegativeNumbers() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(-5.0);
        numbers.add(-3.0);
        numbers.add(-1.0);
        List<Double> result = solution.findClosestElements(numbers);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(-5.0, result.get(0), 0.0001);
        Assert.assertEquals(-3.0, result.get(1), 0.0001);
    }

    @Test
    public void testFindClosestElementsWithAllElementsSameDistance() {
        List<Double> numbers = new ArrayList<>();
        numbers.add(1.0);
        numbers.add(2.0);
        numbers.add(3.0);
        numbers.add(4.0);
        List<Double> result = solution.findClosestElements(numbers);
        Assert.assertEquals(2, result.size());
        Assert.assertEquals(1.0, result.get(0), 0.0001);
        Assert.assertEquals(2.0, result.get(1), 0.0001);
    }
}
