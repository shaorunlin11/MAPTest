package humanevaltest.original.task32;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class SolutionpolyTest {
    @Test
    public void testPolyEmptyList() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        double x = 2.0;
        double result = solution.poly(xs, x);
        assertEquals(0.0, result, 0.0001);
    }

    @Test
    public void testPolySingleElement() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        xs.add(5.0);
        double x = 2.0;
        double result = solution.poly(xs, x);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void testPolyTwoElements() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        xs.add(2.0);
        xs.add(3.0);
        double x = 2.0;
        double result = solution.poly(xs, x);
        assertEquals(2.0 + 3.0 * 2.0, result, 0.0001);
    }

    @Test
    public void testPolyThreeElements() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        xs.add(1.0);
        xs.add(2.0);
        xs.add(3.0);
        double x = 2.0;
        double result = solution.poly(xs, x);
        assertEquals(1.0 + 2.0 * 2.0 + 3.0 * Math.pow(2.0, 2), result, 0.0001);
    }

    @Test
    public void testPolyNegativeX() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        xs.add(1.0);
        xs.add(-2.0);
        xs.add(3.0);
        double x = -2.0;
        double result = solution.poly(xs, x);
        assertEquals(1.0 + (-2.0) * (-2.0) + 3.0 * Math.pow(-2.0, 2), result, 0.0001);
    }

    @Test
    public void testPolyZeroX() {
        Solution solution = new Solution();
        List<Double> xs = new ArrayList<>();
        xs.add(5.0);
        xs.add(4.0);
        xs.add(3.0);
        double x = 0.0;
        double result = solution.poly(xs, x);
        assertEquals(5.0, result, 0.0001);
    }
}
