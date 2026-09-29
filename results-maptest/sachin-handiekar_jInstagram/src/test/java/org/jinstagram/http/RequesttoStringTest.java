package org.jinstagram.http;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import java.lang.reflect.Field;

public class RequesttoStringTest {
    @Test
    public void testToString() throws Exception {
        // Create a Request object with a verb and URL
        Verbs verb = Verbs.GET; // Use a valid enum value instead of instantiating an enum
        String url = "https://api.example.com";
        Request request = new Request(verb, url);

        // Set up getVerb() and getUrl() to return the expected values
        // Since these are private methods, we use reflection to set the fields
        Field verbField = Request.class.getDeclaredField("verb");
        verbField.setAccessible(true);
        verbField.set(request, verb);

        Field urlField = Request.class.getDeclaredField("url");
        urlField.setAccessible(true);
        urlField.set(request, url);

        // Call toString()
        String result = request.toString();

        // Verify the output format
        assertEquals("@Request(" + verb + " " + url + ")", result);
    }
}
