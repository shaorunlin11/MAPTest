package humanevaltest.original.task148;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.*;
public class SolutionbfTest {

    @Test
    public void testBF_InvalidPlanet() {
        Solution solution = new Solution();
        List<String> result = solution.bf("Pluto", "Earth");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBF_SamePlanet() {
        Solution solution = new Solution();
        List<String> result = solution.bf("Earth", "Earth");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testBF_PlanetAtEnds() {
        Solution solution = new Solution();
        List<String> result = solution.bf("Mercury", "Neptune");
        assertEquals(Arrays.asList("Venus", "Earth", "Mars", "Jupiter", "Saturn", "Uranus"), result);
    }

    @Test
    public void testBF_PlanetBetween() {
        Solution solution = new Solution();
        List<String> result = solution.bf("Venus", "Mars");
        assertEquals(Arrays.asList("Earth"), result);
    }

@Test
    public void testBF_PlanetOrder() {
        Solution solution = new Solution();
        List<String> result = solution.bf("Mars", "Venus");
        assertEquals(Arrays.asList("Earth"), result);
    }
}
