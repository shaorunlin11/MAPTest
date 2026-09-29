package org.jinstagram.http;

import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class RequestSetConnectTimeoutZeroCoverageTest {
    @Test
    public void testSetConnectTimeoutWithValidParameters() {
        Request request = new Request(Verbs.GET, "http://example.com");
        request.setConnectTimeout(0, TimeUnit.MILLISECONDS);
    }
}
