package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

public class GridPositionsetXTest {
    @Test
    public void testSetX() throws Exception {
        GridPosition gridPosition = new GridPosition();
        double expectedX = 10.5;

        gridPosition.setX(expectedX);

        Field xField = GridPosition.class.getDeclaredField("x");
        xField.setAccessible(true);
        Double actualX = (Double) xField.get(gridPosition);

        Assert.assertEquals(expectedX, actualX, 0.0);
    }
}
