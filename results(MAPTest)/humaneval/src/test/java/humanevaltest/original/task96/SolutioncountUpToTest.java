package humanevaltest.original.task96;

import java.util.*;
import java.lang.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Ignore;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SolutioncountUpToTest {
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
    public void testCountUpTo_NLessThan2_ReturnsEmptyList() {
        List<Integer> result = solution.countUpTo(1);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountUpTo_NEqualTo2_ReturnsEmptyList() {
        List<Integer> result = solution.countUpTo(2);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountUpTo_NGreaterThan2_ReturnsPrimesLessThanN() {
        List<Integer> result = solution.countUpTo(10);
        assertEquals(Arrays.asList(2, 3, 5, 7), result);
    }

    @Test
    public void testCountUpTo_NIs3_ReturnsOnly2() {
        List<Integer> result = solution.countUpTo(3);
        assertEquals(Arrays.asList(2), result);
    }

    @Test
    public void testCountUpTo_NIs5_Returns2And3() {
        List<Integer> result = solution.countUpTo(5);
        assertEquals(Arrays.asList(2, 3), result);
    }

    @Test
    public void testCountUpTo_NIs7_Returns235() {
        List<Integer> result = solution.countUpTo(7);
        assertEquals(Arrays.asList(2, 3, 5), result);
    }

    @Test
    public void testCountUpTo_NIs10_Returns2357() {
        List<Integer> result = solution.countUpTo(10);
        assertEquals(Arrays.asList(2, 3, 5, 7), result);
    }
}
