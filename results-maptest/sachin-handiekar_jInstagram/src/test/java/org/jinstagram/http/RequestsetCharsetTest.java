package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RequestsetCharsetTest {
    @Test
    public void testSetCharset() throws Exception {
        Request request = new Request(Verbs.GET, "http://example.com");
        String expectedCharset = "UTF-8";

        request.setCharset(expectedCharset);

        Field charsetField = Request.class.getDeclaredField("charset");
        charsetField.setAccessible(true);
        String actualCharset = (String) charsetField.get(request);

        assertEquals(expectedCharset, actualCharset);
    }
}
