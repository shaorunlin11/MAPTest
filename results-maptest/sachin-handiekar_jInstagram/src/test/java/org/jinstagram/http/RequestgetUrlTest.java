package org.jinstagram.http;

import org.junit.Test;
import org.junit.Assert;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RequestgetUrlTest {
    @Test
    public void testGetUrl() throws Exception {
        // Arrange
        Verbs verb = Verbs.GET;
        String url = "https://api.instagram.com/test";
        Request request = new Request(verb, url);

        // Act
        String result = request.getUrl();

        // Assert
        Assert.assertEquals(url, result);
    }

    @Test
    public void testGetUrlWithNullUrl() throws Exception {
        // Arrange
        Verbs verb = Verbs.GET;
        Request request = new Request(verb, null);

        // Act
        String result = request.getUrl();

        // Assert
        Assert.assertNull(result);
    }
}
