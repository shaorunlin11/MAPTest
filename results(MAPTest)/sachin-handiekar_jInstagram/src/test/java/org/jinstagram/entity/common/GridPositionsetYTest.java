package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

public class GridPositionsetYTest {
    private GridPosition gridPosition;

    @Before
    public void setUp() {
        gridPosition = new GridPosition();
    }

    @Test
    public void testSetYAssignsValueToY() throws Exception {
        double expectedY = 5.5;
        gridPosition.setY(expectedY);

        Field yField = GridPosition.class.getDeclaredField("y");
        yField.setAccessible(true);
        double actualY = (Double) yField.get(gridPosition);

        assertEquals(expectedY, actualY, 0.0001);
    }

    @Test
    public void testSetYDoesNotModifyX() throws Exception {
        double initialX = 3.14;
        double newY = 2.71;

        Field xField = GridPosition.class.getDeclaredField("x");
        xField.setAccessible(true);
        xField.set(gridPosition, initialX);

        gridPosition.setY(newY);

        double actualX = (Double) xField.get(gridPosition);
        assertEquals(initialX, actualX, 0.0001);
    }

    @Test
    public void testSetYWithZero() throws Exception {
        double expectedY = 0.0;
        gridPosition.setY(expectedY);

        Field yField = GridPosition.class.getDeclaredField("y");
        yField.setAccessible(true);
        double actualY = (Double) yField.get(gridPosition);

        assertEquals(expectedY, actualY, 0.0001);
    }

    @Test
    public void testSetYWithNegativeValue() throws Exception {
        double expectedY = -1.23;
        gridPosition.setY(expectedY);

        Field yField = GridPosition.class.getDeclaredField("y");
        yField.setAccessible(true);
        double actualY = (Double) yField.get(gridPosition);

        assertEquals(expectedY, actualY, 0.0001);
    }

    @Test
    public void testSetYWithNaN() throws Exception {
        double expectedY = Double.NaN;
        gridPosition.setY(expectedY);

        Field yField = GridPosition.class.getDeclaredField("y");
        yField.setAccessible(true);
        double actualY = (Double) yField.get(gridPosition);

        assertTrue(Double.isNaN(actualY));
    }
}
