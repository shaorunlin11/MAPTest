package org.jinstagram.http;

import org.junit.Test;

import java.util.concurrent.TimeUnit;


public class RequestSetReadTimeoutZeroCoverageTest {
    @Test
    public void testSetReadTimeoutWithNonNegativeDuration() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        request.setReadTimeout(0, TimeUnit.MILLISECONDS);
    }
}
