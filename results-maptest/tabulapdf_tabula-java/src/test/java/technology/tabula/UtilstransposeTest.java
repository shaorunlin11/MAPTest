package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public class UtilstransposeTest {
    @Test
    public void testTransposeWithNonEmptyTable() {
        List<List<Integer>> table = new ArrayList<>();
        table.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        table.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        table.add(new ArrayList<>(Arrays.asList(7, 8, 9)));

        List<List<Integer>> result = Utils.transpose(table);

        assertEquals(3, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(1, (int) result.get(0).get(0));
        assertEquals(4, (int) result.get(0).get(1));
        assertEquals(7, (int) result.get(0).get(2));
        assertEquals(2, (int) result.get(1).get(0));
        assertEquals(5, (int) result.get(1).get(1));
        assertEquals(8, (int) result.get(1).get(2));
        assertEquals(3, (int) result.get(2).get(0));
        assertEquals(6, (int) result.get(2).get(1));
        assertEquals(9, (int) result.get(2).get(2));
    }


    @Test
    public void testTransposeWithSingleRow() {
        List<List<Integer>> table = new ArrayList<>();
        table.add(new ArrayList<>(Arrays.asList(1, 2, 3)));

        List<List<Integer>> result = Utils.transpose(table);

        assertEquals(3, result.size());
        assertEquals(1, result.get(0).size());
        assertEquals(1, (int) result.get(0).get(0));
        assertEquals(2, (int) result.get(1).get(0));
        assertEquals(3, (int) result.get(2).get(0));
    }

    @Test
    public void testTransposeWithSingleColumn() {
        List<List<Integer>> table = new ArrayList<>();
        table.add(new ArrayList<>(Arrays.asList(1)));
        table.add(new ArrayList<>(Arrays.asList(2)));
        table.add(new ArrayList<>(Arrays.asList(3)));

        List<List<Integer>> result = Utils.transpose(table);

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals(1, (int) result.get(0).get(0));
        assertEquals(2, (int) result.get(0).get(1));
        assertEquals(3, (int) result.get(0).get(2));
    }

    @Test
    public void testTransposeWithMultipleRowsAndColumns() {
        List<List<String>> table = new ArrayList<>();
        table.add(new ArrayList<>(Arrays.asList("A", "B", "C")));
        table.add(new ArrayList<>(Arrays.asList("D", "E", "F")));
        table.add(new ArrayList<>(Arrays.asList("G", "H", "I")));

        List<List<String>> result = Utils.transpose(table);

        assertEquals(3, result.size());
        assertEquals(3, result.get(0).size());
        assertEquals("A", result.get(0).get(0));
        assertEquals("D", result.get(0).get(1));
        assertEquals("G", result.get(0).get(2));
        assertEquals("B", result.get(1).get(0));
        assertEquals("E", result.get(1).get(1));
        assertEquals("H", result.get(1).get(2));
        assertEquals("C", result.get(2).get(0));
        assertEquals("F", result.get(2).get(1));
        assertEquals("I", result.get(2).get(2));
    }
}
