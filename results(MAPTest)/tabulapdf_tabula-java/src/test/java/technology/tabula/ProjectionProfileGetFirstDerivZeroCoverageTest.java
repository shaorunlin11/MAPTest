package technology.tabula;

import org.junit.Test;

public class ProjectionProfileGetFirstDerivZeroCoverageTest {
    @Test
    public void testGetFirstDerivWithValidInput() {
        float[] projection = {1.0f, 2.0f, 3.0f, 4.0f};
        float[] result = ProjectionProfile.getFirstDeriv(projection);

        // Expected values: [1.0, 2.0, 2.0, 1.0]
        assert result[0] == 1.0f;
        assert result[1] == 2.0f;
        assert result[2] == 2.0f;
        assert result[3] == 1.0f;
    }
}
