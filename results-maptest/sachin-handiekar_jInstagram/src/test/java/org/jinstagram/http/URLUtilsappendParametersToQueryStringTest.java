package org.jinstagram.http;

import org.jinstagram.utils.Preconditions;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class URLUtilsappendParametersToQueryStringTest {

    @Test
    public void testAppendParametersToQueryStringWithEmptyParams() throws Exception {
        String url = "http://example.com";
        Map<String, String> params = new HashMap();
        String result = URLUtils.appendParametersToQueryString(url, params);
        assertEquals("http://example.com", result);
    }

    @Test
    public void testAppendParametersToQueryStringWithNonNullUrlAndNonEmptyParams() throws Exception {
        String url = "http://example.com";
        Map<String, String> params = new HashMap();
        params.put("key1", "value1");
        String result = URLUtils.appendParametersToQueryString(url, params);
        assertEquals("http://example.com?key1=value1", result);
    }

    @Test
    public void testAppendParametersToQueryStringWithExistingQueryString() throws Exception {
        String url = "http://example.com?existingKey=existingValue";
        Map<String, String> params = new HashMap();
        params.put("key1", "value1");
        String result = URLUtils.appendParametersToQueryString(url, params);
        assertEquals("http://example.com?existingKey=existingValue&key1=value1", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAppendParametersToQueryStringWithNullUrl() throws Exception {
        String url = null;
        Map<String, String> params = new HashMap();
        URLUtils.appendParametersToQueryString(url, params);
    }
}
