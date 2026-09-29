package org.jinstagram.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;

public class APILimitUtilsgetRemainingLimitStatusTest {

    @Test
    public void testGetRemainingLimitStatus_WithValidHeader_ReturnsValue() {
        // Arrange
        Map<String, String> headers = new HashMap();
        headers.put("X-Ratelimit-Remaining", "100");

        // Act
        int result = APILimitUtils.getRemainingLimitStatus(headers);

        // Assert
        Assert.assertEquals(100, result);
    }

    @Test
    public void testGetRemainingLimitStatus_WithMissingHeader_ReturnsDefaultValue() {
        // Arrange
        Map<String, String> headers = new HashMap();

        // Act
        int result = APILimitUtils.getRemainingLimitStatus(headers);

        // Assert
        // This test assumes that getIntegerValue returns -1 when the header is missing
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testGetRemainingLimitStatus_WithNonIntegerValue_ReturnsDefaultValue() {
        // Arrange
        Map<String, String> headers = new HashMap();
        headers.put("X-Ratelimit-Remaining", "invalid");

        // Act
        int result = APILimitUtils.getRemainingLimitStatus(headers);

        // Assert
        // This test assumes that getIntegerValue returns -1 when the value is not an integer
        Assert.assertEquals(-1, result);
    }
}
