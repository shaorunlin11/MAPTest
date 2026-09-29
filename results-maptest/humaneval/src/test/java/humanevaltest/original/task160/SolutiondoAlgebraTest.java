package humanevaltest.original.task160;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

public class SolutiondoAlgebraTest {
    @Test
    public void testDoAlgebra() {
        Solution solution = new Solution();

        // Test case 1: Basic operations with exponentiation
        List<String> operators1 = new ArrayList<>();
        operators1.add("+");
        operators1.add("*");
        operators1.add("**");
        List<Integer> operands1 = new ArrayList<>();
        operands1.add(2);
        operands1.add(3);
        operands1.add(4);
        operands1.add(5);
        assertEquals(2 + 3 * (4 * 4 * 4 * 4 * 4), solution.doAlgebra(operators1, operands1));

        // Test case 2: Only exponentiation
        List<String> operators2 = new ArrayList<>();
        operators2.add("**");
        List<Integer> operands2 = new ArrayList<>();
        operands2.add(2);
        operands2.add(3);
        assertEquals(8, solution.doAlgebra(operators2, operands2));

        // Test case 3: Multiplication and division
        List<String> operators3 = new ArrayList<>();
        operators3.add("*");
        operators3.add("/");
        List<Integer> operands3 = new ArrayList<>();
        operands3.add(6);
        operands3.add(3);
        operands3.add(2);
        assertEquals(6 * 3 / 2, solution.doAlgebra(operators3, operands3));

        // Test case 4: Addition and subtraction
        List<String> operators4 = new ArrayList<>();
        operators4.add("+");
        operators4.add("-");
        List<Integer> operands4 = new ArrayList<>();
        operands4.add(10);
        operands4.add(5);
        operands4.add(3);
        assertEquals(10 + 5 - 3, solution.doAlgebra(operators4, operands4));

        // Test case 5: Mixed operations with exponentiation first
        List<String> operators5 = new ArrayList<>();
        operators5.add("+");
        operators5.add("*");
        operators5.add("**");
        operators5.add("/");
        List<Integer> operands5 = new ArrayList<>();
        operands5.add(2);
        operands5.add(3);
        operands5.add(4);
        operands5.add(5);
        operands5.add(2);
        assertEquals(2 + 3 * (4 * 4 * 4 * 4 * 4) / 2, solution.doAlgebra(operators5, operands5));
    }
}
