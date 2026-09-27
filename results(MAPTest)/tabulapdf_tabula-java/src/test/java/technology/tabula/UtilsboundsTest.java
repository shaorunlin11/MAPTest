package technology.tabula;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.*;

public class UtilsboundsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testBoundsWithEmptyCollection() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("shapes can't be empty");

        Collection<Shape> shapes = new ArrayList<>();
        Utils.bounds(shapes);
    }

    @Test
    public void testBoundsWithSingleShape() throws Exception {
        Collection<Shape> shapes = new ArrayList<>();
        Shape shape = new Rectangle2D.Float(0, 0, 10, 20);
        shapes.add(shape);

        Rectangle result = Utils.bounds(shapes);
        Assert.assertEquals(0, result.getX(), 0.01f);
        Assert.assertEquals(0, result.getY(), 0.01f);
        Assert.assertEquals(10, result.getWidth(), 0.01f);
        Assert.assertEquals(20, result.getHeight(), 0.01f);
    }

    @Test
    public void testBoundsWithMultipleShapes() throws Exception {
        Collection<Shape> shapes = new ArrayList<>();
        Shape shape1 = new Rectangle2D.Float(0, 0, 10, 20);
        Shape shape2 = new Rectangle2D.Float(5, 5, 15, 25);
        shapes.add(shape1);
        shapes.add(shape2);

        Rectangle result = Utils.bounds(shapes);
        Assert.assertEquals(0, result.getX(), 0.01f);
        Assert.assertEquals(0, result.getY(), 0.01f);
        Assert.assertEquals(20, result.getWidth(), 0.01f);
        Assert.assertEquals(30, result.getHeight(), 0.01f);
    }
}
