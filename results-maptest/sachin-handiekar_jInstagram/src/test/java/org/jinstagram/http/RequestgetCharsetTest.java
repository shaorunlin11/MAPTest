package org.jinstagram.http;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;

public class RequestgetCharsetTest {
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
    public void testGetCharsetReturnsDefaultWhenNull() {
        // Arrange
        // Use reflection to access private field
        try {
            java.lang.reflect.Field charsetField = Request.class.getDeclaredField("charset");
            charsetField.setAccessible(true);
            charsetField.set(request, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        String result = request.getCharset();

        // Assert
        Assert.assertEquals(Charset.defaultCharset().name(), result);
    }

    @Test
    public void testGetCharsetReturnsSetCharset() {
        // Arrange
        String expectedCharset = "UTF-8";
        // Use reflection to access private field
        try {
            java.lang.reflect.Field charsetField = Request.class.getDeclaredField("charset");
            charsetField.setAccessible(true);
            charsetField.set(request, expectedCharset);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Act
        String result = request.getCharset();

        // Assert
        Assert.assertEquals(expectedCharset, result);
    }
}
