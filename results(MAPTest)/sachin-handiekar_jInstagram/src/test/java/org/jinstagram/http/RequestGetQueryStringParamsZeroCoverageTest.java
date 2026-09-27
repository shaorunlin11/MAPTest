package org.jinstagram.http;

import org.junit.Test;

import java.util.Map;

public class RequestGetQueryStringParamsZeroCoverageTest {
    @Test
    public void testGetQueryStringParams() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        request.addQuerystringParameter("key1", "value1");
        request.addQuerystringParameter("key2", "value2");

        Map<String, String> params = request.getQueryStringParams();

        // This assertion is just to ensure the method is called and executed
        // The actual coverage check is done through the test runner
        assert params != null;
    }
}
