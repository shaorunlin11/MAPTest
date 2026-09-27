package org.jinstagram.exceptions;

import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class InstagramExceptionGetRemainingLimitStatusZeroCoverageTest {
    @Test
    public void testGetRemainingLimitStatusWithNullHeaders() {
        // Create an instance of InstagramException with null headers
        Map<String, String> headers = null;
        InstagramException exception = new InstagramException("Test message", headers);

        // Call the method under test
        int result = exception.getRemainingLimitStatus();

        // Assert that the method returns -1 when headers are null
        assert result == -1;
    }
}
