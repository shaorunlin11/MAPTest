package technology.tabula;

import org.junit.Test;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RulingCollapseOrientedRulingsZeroCoverageTest {
    @Test
    public void testCollapseOrientedRulings() {
        // Create a list of Ruling objects
        List<Ruling> lines = new ArrayList<>();

        // Create two Ruling objects that are colinear or parallel
        Point2D p1 = new Point2D.Float(0, 0);
        Point2D p2 = new Point2D.Float(100, 0);
        Ruling ruling1 = new Ruling(p1, p2);
        Ruling ruling2 = new Ruling(new Point2D.Float(50, 0), new Point2D.Float(150, 0));

        lines.add(ruling1);
        lines.add(ruling2);

        // Call the method under test
        List<Ruling> collapsedLines = Ruling.collapseOrientedRulings(lines);

        // Verify that the method returns a non-null list
        assertEquals("Collapsed lines should not be null", 1, collapsedLines.size());
    }
}
