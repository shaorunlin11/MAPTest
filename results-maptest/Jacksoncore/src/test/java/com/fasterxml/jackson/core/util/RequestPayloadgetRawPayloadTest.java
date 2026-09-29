package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class RequestPayloadgetRawPayloadTest {
    @Test
    public void testGetRawPayload_WhenBytesArePresent_ReturnsBytes() throws Exception {
        byte[] bytes = "test".getBytes();
        String charset = "UTF-8";
        RequestPayload payload = new RequestPayload(bytes, charset);

        Object result = payload.getRawPayload();

        assertSame(bytes, result);
    }

    @Test
    public void testGetRawPayload_WhenBytesAreNull_ReturnsText() throws Exception {
        CharSequence text = "test";
        RequestPayload payload = new RequestPayload(text);
        payload._payloadAsBytes = null;

        Object result = payload.getRawPayload();

        assertSame(text, result);
    }
}
