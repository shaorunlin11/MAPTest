package technology.tabula;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class PagegetHorizontalRulingsTest {
    private Page page;

    @Before
    public void setUp() throws Exception {
        // Create a mock Page instance with horizontalRulingLines initialized
        page = new Page(0, 0, 100, 100, 0, 1, null, null);
        // Use reflection to set private fields
        java.lang.reflect.Field field = Page.class.getDeclaredField("horizontalRulingLines");
        field.setAccessible(true);
        field.set(page, new ArrayList<>());
    }

    @After
    public void tearDown() {
        page = null;
    }

    @Test
    public void testGetHorizontalRulings_returnsPrecomputedValue() throws Exception {
        // Arrange
        List<Ruling> expected = new ArrayList<>();
        java.lang.reflect.Field field = Page.class.getDeclaredField("horizontalRulingLines");
        field.setAccessible(true);
        field.set(page, expected);

        // Act
        List<Ruling> result = page.getHorizontalRulings();

        // Assert
        assertEquals(expected, result);
    }

    @Test
    public void testGetHorizontalRulings_callsGetRulingsWhenNull() throws Exception {
        // Arrange
        java.lang.reflect.Field field = Page.class.getDeclaredField("horizontalRulingLines");
        field.setAccessible(true);
        field.set(page, null);

        field = Page.class.getDeclaredField("rulings");
        field.setAccessible(true);
        field.set(page, new ArrayList<>());

        // Act
        List<Ruling> result = page.getHorizontalRulings();

        // Assert
        assertNotNull(result);
    }
}
