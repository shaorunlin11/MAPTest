package technology.tabula;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RectangleboundingBoxOfTest {

    @Test
    public void testBoundingBoxOfWithSingleRectangle() {
        Rectangle rect = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(rect);

        Rectangle result = Rectangle.boundingBoxOf(rectangles);

        assertEquals(20.0f, result.getX(), 0.0f);
        assertEquals(10.0f, result.getY(), 0.0f);
        assertEquals(30.0f, result.getWidth(), 0.0f);
        assertEquals(40.0f, result.getHeight(), 0.0f);
    }

    @Test
    public void testBoundingBoxOfWithMultipleRectangles() {
        Rectangle rect1 = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Rectangle rect2 = new Rectangle(5.0f, 15.0f, 25.0f, 35.0f);
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(rect1);
        rectangles.add(rect2);

        Rectangle result = Rectangle.boundingBoxOf(rectangles);

        assertEquals(15.0f, result.getX(), 0.0f);
        assertEquals(5.0f, result.getY(), 0.0f);
        assertEquals(35.0f, result.getWidth(), 0.0f);
        assertEquals(45.0f, result.getHeight(), 0.0f);
    }

    @Test
    public void testBoundingBoxOfWithIdenticalRectangles() {
        Rectangle rect1 = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        Rectangle rect2 = new Rectangle(10.0f, 20.0f, 30.0f, 40.0f);
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(rect1);
        rectangles.add(rect2);

        Rectangle result = Rectangle.boundingBoxOf(rectangles);

        assertEquals(20.0f, result.getX(), 0.0f);
        assertEquals(10.0f, result.getY(), 0.0f);
        assertEquals(30.0f, result.getWidth(), 0.0f);
        assertEquals(40.0f, result.getHeight(), 0.0f);
    }
}
