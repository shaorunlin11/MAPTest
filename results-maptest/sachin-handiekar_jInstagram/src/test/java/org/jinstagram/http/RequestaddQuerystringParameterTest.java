package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.Map;
import java.util.HashMap;

public class RequestaddQuerystringParameterTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com");
    }

    @After
    public void tearDown() {
        request = null;
    }

    @Test
    public void testAddQuerystringParameter() {
        String key = "testKey";
        String value = "testValue";

        request.addQuerystringParameter(key, value);

        Map<String, String> querystringParams = request.getQueryStringParams();
        assert querystringParams != null : "querystringParams should be initialized";
        assert querystringParams.containsKey(key) : "querystringParams should contain the added key";
        assert querystringParams.get(key).equals(value) : "querystringParams should have the correct value for the key";
    }
}
