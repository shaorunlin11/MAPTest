package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CellsetPlaceholderTest {

    @Test
    public void testSetPlaceholderSetsPlaceholderField() throws Exception {
        Cell cell = new Cell(0, 0, 100, 100);
        cell.setPlaceholder(true);
        Field placeholderField = Cell.class.getDeclaredField("placeholder");
        placeholderField.setAccessible(true);
        assertTrue((boolean) placeholderField.get(cell));
    }

    @Test
    public void testSetPlaceholderWithFalseValue() throws Exception {
        Cell cell = new Cell(0, 0, 100, 100);
        cell.setPlaceholder(false);
        Field placeholderField = Cell.class.getDeclaredField("placeholder");
        placeholderField.setAccessible(true);
        assertFalse((boolean) placeholderField.get(cell));
    }
}
