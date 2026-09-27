package org.jinstagram.exceptions;

import org.junit.Test;
import org.junit.Assert;
import java.util.Map;
import java.util.HashMap;
import org.jinstagram.http.APILimitUtils;

public class InstagramExceptiongetAPILimitStatusTest {

    @Test
    public void testGetAPILimitStatusWithNullHeaders() {
        InstagramException exception = new InstagramException("Test message");
        int result = exception.getAPILimitStatus();
        Assert.assertEquals(-1, result);
    }

    @Test
    public void testGetAPILimitStatusWithNonNullHeaders() {
        Map<String, String> headers = new HashMap();
        headers.put("X-Rate-Limit-Remaining", "50");
        InstagramException exception = new InstagramException("Test message", headers);
        int result = exception.getAPILimitStatus();
        Assert.assertEquals(APILimitUtils.getAPILimitStatus(headers), result);
    }
}
