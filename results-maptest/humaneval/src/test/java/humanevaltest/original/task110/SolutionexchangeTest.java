package humanevaltest.original.task110;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolutionexchangeTest {
    @Test
    public void testExchange() {
        Solution solution = new Solution();

        // Test case 1: lst1 has 2 odds, lst2 has 3 evens -> YES
        List<Integer> lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(3);
        lst1.add(5);
        List<Integer> lst2 = new ArrayList<>();
        lst2.add(2);
        lst2.add(4);
        lst2.add(6);
        assertEquals("YES", solution.exchange(lst1, lst2));

        // Test case 2: lst1 has 3 odds, lst2 has 2 evens -> NO
        lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(3);
        lst1.add(5);
        lst2 = new ArrayList<>();
        lst2.add(2);
        lst2.add(4);
        assertEquals("NO", solution.exchange(lst1, lst2));

        // Test case 3: lst1 has 0 odds, lst2 has 0 evens -> YES (0 >= 0)
        lst1 = new ArrayList<>();
        lst2 = new ArrayList<>();
        assertEquals("YES", solution.exchange(lst1, lst2));

        // Test case 4: lst1 has 1 odd, lst2 has 0 evens -> NO
        lst1 = new ArrayList<>();
        lst1.add(1);
        lst2 = new ArrayList<>();
        assertEquals("NO", solution.exchange(lst1, lst2));

        // Test case 5: lst1 has 0 odds, lst2 has 1 even -> YES (0 >= 0)
        lst1 = new ArrayList<>();
        lst2 = new ArrayList<>();
        lst2.add(2);
        assertEquals("YES", solution.exchange(lst1, lst2));

        // Test case 6: lst1 has 2 odds, lst2 has 2 evens -> YES
        lst1 = new ArrayList<>();
        lst1.add(1);
        lst1.add(3);
        lst2 = new ArrayList<>();
        lst2.add(2);
        lst2.add(4);
        assertEquals("YES", solution.exchange(lst1, lst2));
    }
}
