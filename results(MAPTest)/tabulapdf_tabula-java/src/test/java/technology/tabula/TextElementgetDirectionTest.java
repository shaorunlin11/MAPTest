package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class TextElementgetDirectionTest {

    @Test
    public void testGetDirection() throws Exception {
        // Create a TextElement instance with a specific direction value
        Class<?> textElementClass = Class.forName("technology.tabula.TextElement");
        Constructor<?> textElementConstructor1 = textElementClass.getConstructor(
            float.class, float.class, float.class, float.class,
            org.apache.pdfbox.pdmodel.font.PDFont.class, float.class, String.class, float.class
        );
        Object textElementInstance1 = textElementConstructor1.newInstance(
            0.0f, 0.0f, 100.0f, 50.0f,
            null, 12.0f, "test", 5.0f
        );

        // Verify that the default direction value is 0.0f
        Method getDirectionMethod = textElementClass.getMethod("getDirection");
        float result1 = (float) getDirectionMethod.invoke(textElementInstance1);
        assertEquals(0.0f, result1, 0.0f);

        // Create a TextElement instance with a specific direction value
        Constructor<?> textElementConstructor2 = textElementClass.getConstructor(
            float.class, float.class, float.class, float.class,
            org.apache.pdfbox.pdmodel.font.PDFont.class, float.class, String.class, float.class, float.class
        );
        Object textElementInstance2 = textElementConstructor2.newInstance(
            0.0f, 0.0f, 100.0f, 50.0f,
            null, 12.0f, "test", 5.0f, 45.0f
        );

        // Verify that the direction value is correctly returned
        float result2 = (float) getDirectionMethod.invoke(textElementInstance2);
        assertEquals(45.0f, result2, 0.0f);
    }
}
