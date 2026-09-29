package technology.tabula;
import org.junit.Test;
import static org.junit.Assert.*;
public class ProjectionProfilegetAutocorrelationTest {

    @Test
    public void testGetAutocorrelationWithSingleElement() {
        float[] input = {100.0f};
        float[] result = ProjectionProfile.getAutocorrelation(input);

        assertEquals(0, result.length);
    }

}
