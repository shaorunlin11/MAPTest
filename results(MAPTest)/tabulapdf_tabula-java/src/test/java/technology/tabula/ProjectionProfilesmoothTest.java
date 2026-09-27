package technology.tabula;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
public class ProjectionProfilesmoothTest {





    @Test
    public void testSmoothWithEmptyData() {
        float[] data = {};
        int kernelSize = 3;
        float[] result = ProjectionProfile.smooth(data, kernelSize);
        assertTrue(result.length == 0);
    }
}
