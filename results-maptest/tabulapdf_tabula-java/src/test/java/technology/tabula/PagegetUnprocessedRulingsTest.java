package technology.tabula;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class PagegetUnprocessedRulingsTest {
    @Test
    public void testGetUnprocessedRulings_returnsRulingsList() throws Exception {
        // Arrange
        List<Ruling> expectedRulings = new ArrayList<>();
        Ruling ruling1 = new Ruling(0, 0, 100, 10);
        Ruling ruling2 = new Ruling(0, 10, 100, 10);
        expectedRulings.add(ruling1);
        expectedRulings.add(ruling2);

        // Create a Page instance with the rulings field initialized
        Page page = new Page(0, 0, 100, 100, 0, 1, null, null, new ArrayList<>(), expectedRulings);

        // Act
        List<Ruling> actualRulings = page.getUnprocessedRulings();

        // Assert
        assertEquals(expectedRulings, actualRulings);
    }
}
