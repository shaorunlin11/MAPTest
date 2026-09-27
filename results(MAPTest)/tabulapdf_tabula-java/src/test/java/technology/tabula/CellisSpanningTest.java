package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.geom.Point2D;

public class CellisSpanningTest {

    @Test
    public void testIsSpanningInitiallyFalse() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 100.0f, 50.0f);
        assertFalse(cell.isSpanning());
    }

    @Test
    public void testIsSpanningAfterSettingTrue() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 100.0f, 50.0f);
        cell.setSpanning(true);
        assertTrue(cell.isSpanning());
    }

    @Test
    public void testIsSpanningAfterSettingFalse() throws Exception {
        Cell cell = new Cell(0.0f, 0.0f, 100.0f, 50.0f);
        cell.setSpanning(true);
        cell.setSpanning(false);
        assertFalse(cell.isSpanning());
    }

    @Test
    public void testIsSpanningWithConstructorInitialization() throws Exception {
        Cell cell = new Cell(new Point2D.Float(0.0f, 0.0f), new Point2D.Float(100.0f, 50.0f));
        assertFalse(cell.isSpanning());
    }
}
