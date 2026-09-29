package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class CellsetSpanningTest {
    @Test
    public void testSetSpanning() throws Exception {
        Cell cell = new Cell(0, 0, 100, 100);
        assertFalse(cell.isSpanning());

        cell.setSpanning(true);
        assertTrue(cell.isSpanning());

        cell.setSpanning(false);
        assertFalse(cell.isSpanning());
    }
}
