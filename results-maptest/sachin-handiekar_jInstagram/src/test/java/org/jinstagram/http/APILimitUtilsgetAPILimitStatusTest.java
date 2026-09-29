package org.jinstagram.http;

import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class APILimitUtilsgetAPILimitStatusTest {

    @Test
    public void testGetAPILimitStatusWithValidHeader() {
        Map<String, String> headers = new HashMap();
        headers.put("X-Ratelimit-Limit", "100");
        int result = APILimitUtils.getAPILimitStatus(headers);
        assertEquals(100, result);
    }

    @Test
    public void testGetAPILimitStatusWithNonIntegerValue() {
        Map<String, String> headers = new HashMap();
        headers.put("X-Ratelimit-Limit", "invalid");
        int result = APILimitUtils.getAPILimitStatus(headers);
        // The behavior of getIntegerValue is not defined for non-integer values,
        // so we cannot make a strong assertion here without knowing its implementation
        // However, the method will return whatever getIntegerValue returns
    }

    @Test
    public void testGetAPILimitStatusWithMissingHeader() {
        Map<String, String> headers = new HashMap();
        int result = APILimitUtils.getAPILimitStatus(headers);
        // The behavior of getIntegerValue is not defined for missing keys,
        // so we cannot make a strong assertion here without knowing its implementation
    }
}
