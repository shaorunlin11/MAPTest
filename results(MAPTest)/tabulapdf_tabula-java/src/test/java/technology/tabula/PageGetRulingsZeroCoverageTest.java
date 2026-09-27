package technology.tabula;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class PageGetRulingsZeroCoverageTest {
    @Test
    public void testGetRulingsWithCleanRulingsNotNull() {
        // Arrange
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null);
        List<Ruling> cleanRulings = new ArrayList<>();
        cleanRulings.add(new Ruling(0, 0, 100, 0));

        // Use public method to set cleanRulings (if available)
        // Since no public setter exists, we'll use reflection to set the field
        try {
            java.lang.reflect.Field cleanRulingsField = Page.class.getDeclaredField("cleanRulings");
            cleanRulingsField.setAccessible(true);
            cleanRulingsField.set(page, cleanRulings);

            java.lang.reflect.Field minCharWidthField = Page.class.getDeclaredField("minCharWidth");
            minCharWidthField.setAccessible(true);
            minCharWidthField.setFloat(page, 1.0f);

            java.lang.reflect.Field minCharHeightField = Page.class.getDeclaredField("minCharHeight");
            minCharHeightField.setAccessible(true);
            minCharHeightField.setFloat(page, 1.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        List<Ruling> result = page.getRulings();

        // Assert
        assertEquals(cleanRulings, result);
    }
}
