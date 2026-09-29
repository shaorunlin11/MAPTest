package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectionProfilefilterTest {
    @Test
    public void testFilterWithValidInput() {
        float[] data = {1.0f, 2.0f, 3.0f, 4.0f};
        float alpha = 0.5f;

        float[] result = ProjectionProfile.filter(data, alpha);

        assertEquals(data.length, result.length);
        assertEquals(data[0], result[0], 0.0f);
        assertEquals(1.5f, result[1], 0.0f);
        assertEquals(2.25f, result[2], 0.0f);
        assertEquals(3.125f, result[3], 0.0f);
    }

    @Test
    public void testFilterWithSingleElement() {
        float[] data = {10.0f};
        float alpha = 0.1f;

        float[] result = ProjectionProfile.filter(data, alpha);

        assertEquals(data.length, result.length);
        assertEquals(data[0], result[0], 0.0f);
    }

    @Test
    public void testFilterWithAlphaZero() {
        float[] data = {1.0f, 2.0f, 3.0f};
        float alpha = 0.0f;

        float[] result = ProjectionProfile.filter(data, alpha);

        assertEquals(data.length, result.length);
        assertEquals(data[0], result[0], 0.0f);
        assertEquals(data[0], result[1], 0.0f);
        assertEquals(data[0], result[2], 0.0f);
    }

    @Test
    public void testFilterWithAlphaOne() {
        float[] data = {1.0f, 2.0f, 3.0f};
        float alpha = 1.0f;

        float[] result = ProjectionProfile.filter(data, alpha);

        assertEquals(data.length, result.length);
        assertEquals(data[0], result[0], 0.0f);
        assertEquals(data[1], result[1], 0.0f);
        assertEquals(data[2], result[2], 0.0f);
    }
}
