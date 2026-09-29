package technology.tabula;
import org.junit.Test;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;
public class RulingfindIntersectionsTest {
    @Test
    public void testFindIntersectionsWithNoIntersections() {
        List<Ruling> horizontals = new ArrayList<>();
        List<Ruling> verticals = new ArrayList<>();

        Map<Point2D, Ruling[]> result = Ruling.findIntersections(horizontals, verticals);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }



    @Test
    public void testFindIntersectionsWithNoHorizontalRulings() {
        List<Ruling> horizontals = new ArrayList<>();
        List<Ruling> verticals = new ArrayList<>();
        verticals.add(new Ruling(0, 0, 10, 100));

        Map<Point2D, Ruling[]> result = Ruling.findIntersections(horizontals, verticals);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testFindIntersectionsWithNoVerticalRulings() {
        List<Ruling> horizontals = new ArrayList<>();
        horizontals.add(new Ruling(0, 0, 100, 10));
        List<Ruling> verticals = new ArrayList<>();

        Map<Point2D, Ruling[]> result = Ruling.findIntersections(horizontals, verticals);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
