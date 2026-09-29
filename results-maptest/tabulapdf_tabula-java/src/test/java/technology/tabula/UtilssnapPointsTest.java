package technology.tabula;
import org.junit.Test;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class UtilssnapPointsTest {
    @Test
    public void testSnapPointsWithValidInput() {
        List<Line2D.Float> rulings = new ArrayList<>();
        Line2D.Float line1 = new Line2D.Float(10, 20, 30, 40);
        Line2D.Float line2 = new Line2D.Float(15, 25, 35, 45);
        rulings.add(line1);
        rulings.add(line2);

        Utils.snapPoints(rulings, 5.0f, 5.0f);

        // Check if points are snapped
        assertEquals(10.0f, line1.getX1(), 0.01f);
        assertEquals(20.0f, line1.getY1(), 0.01f);
        assertEquals(30.0f, line1.getX2(), 0.01f);
        assertEquals(40.0f, line1.getY2(), 0.01f);

        assertEquals(15.0f, line2.getX1(), 0.01f);
        assertEquals(25.0f, line2.getY1(), 0.01f);
        assertEquals(35.0f, line2.getX2(), 0.01f);
        assertEquals(45.0f, line2.getY2(), 0.01f);
    }



    @Test
    public void testSnapPointsWithSingleLine() {
        List<Line2D.Float> rulings = new ArrayList<>();
        Line2D.Float line = new Line2D.Float(10, 20, 30, 40);
        rulings.add(line);

        Utils.snapPoints(rulings, 5.0f, 5.0f);

        // No snapping should occur for a single line
        assertEquals(10.0f, line.getX1(), 0.01f);
        assertEquals(20.0f, line.getY1(), 0.01f);
        assertEquals(30.0f, line.getX2(), 0.01f);
        assertEquals(40.0f, line.getY2(), 0.01f);
    }



    @Test
    public void testSnapPointsWithNegativeThresholds() {
        List<Line2D.Float> rulings = new ArrayList<>();
        Line2D.Float line1 = new Line2D.Float(10, 20, 30, 40);
        Line2D.Float line2 = new Line2D.Float(15, 25, 35, 45);
        rulings.add(line1);
        rulings.add(line2);

        Utils.snapPoints(rulings, -5.0f, -5.0f);

        // Negative thresholds should not affect the snapping
        assertEquals(10.0f, line1.getX1(), 0.01f);
        assertEquals(20.0f, line1.getY1(), 0.01f);
        assertEquals(30.0f, line1.getX2(), 0.01f);
        assertEquals(40.0f, line1.getY2(), 0.01f);

        assertEquals(15.0f, line2.getX1(), 0.01f);
        assertEquals(25.0f, line2.getY1(), 0.01f);
        assertEquals(35.0f, line2.getX2(), 0.01f);
        assertEquals(45.0f, line2.getY2(), 0.01f);
    }
}
