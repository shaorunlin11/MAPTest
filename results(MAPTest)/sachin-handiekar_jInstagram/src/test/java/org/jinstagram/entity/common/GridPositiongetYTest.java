package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class GridPositiongetYTest {
    @Test
    public void testGetY() throws Exception {
        GridPosition gridPosition = new GridPosition();
        double expectedY = 5.5;
        // Use reflection to set the private field y
        Field yField = GridPosition.class.getDeclaredField("y");
        yField.setAccessible(true);
        yField.setDouble(gridPosition, expectedY);

        double actualY = gridPosition.getY();
        assertEquals(expectedY, actualY, 0.0001);
    }
}
