package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import java.util.Map;
import java.util.HashMap;

public class RequestgetBodyParamsTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @Test
    public void testGetBodyParamsReturnsInitializedMap() {
        Map<String, String> bodyParams = request.getBodyParams();
        assert bodyParams instanceof HashMap;
        assert bodyParams.isEmpty();
    }
}
