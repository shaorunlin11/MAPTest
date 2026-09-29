package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

import static org.junit.Assert.assertEquals;

public class RulingsetTopTest {
    private Ruling ruling;

    @Before
    public void setUp() {
        ruling = new Ruling(10.0f, 20.0f, 30.0f, 40.0f);
    }

    @Test
    public void testSetTopUpdatesTopCoordinate() {
        float newTop = 50.0f;
        ruling.setTop(newTop);

        // Verify that the top coordinate is updated
        assertEquals(newTop, ruling.getY1(), 0.0001f);
    }
}
