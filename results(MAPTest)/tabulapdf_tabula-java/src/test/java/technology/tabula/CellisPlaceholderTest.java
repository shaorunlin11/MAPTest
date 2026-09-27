package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellisPlaceholderTest {
    @Test
    public void testIsPlaceholderInitiallyFalse() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 1.0f, 1.0f);
        assertFalse(cell.isPlaceholder());
    }

    @Test
    public void testIsPlaceholderAfterSetPlaceholderTrue() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 1.0f, 1.0f);
        cell.setPlaceholder(true);
        assertTrue(cell.isPlaceholder());
    }

    @Test
    public void testIsPlaceholderAfterSetPlaceholderFalse() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 1.0f, 1.0f);
        cell.setPlaceholder(true);
        cell.setPlaceholder(false);
        assertFalse(cell.isPlaceholder());
    }
}
