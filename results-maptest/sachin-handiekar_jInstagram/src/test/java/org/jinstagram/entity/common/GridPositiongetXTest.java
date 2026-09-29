package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

public class GridPositiongetXTest {
    @Test
    public void testGetX() throws Exception {
        GridPosition gridPosition = new GridPosition();
        double expectedX = 10.5;

        // Use reflection to set the private field x
        java.lang.reflect.Field xField = GridPosition.class.getDeclaredField("x");
        xField.setAccessible(true);
        xField.setDouble(gridPosition, expectedX);

        double actualX = gridPosition.getX();
        Assert.assertEquals(expectedX, actualX, 0.0);
    }
}
