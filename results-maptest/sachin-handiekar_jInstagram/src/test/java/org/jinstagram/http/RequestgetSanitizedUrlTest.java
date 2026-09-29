package org.jinstagram.http;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
public class RequestgetSanitizedUrlTest {
    private Request request;

    @Before
    public void setUp() {
        request = new Request(Verbs.GET, "http://example.com:8080?param=1");
    }

    @Test
    public void testGetSanitizedUrlRemovesQueryAndPort() {
        String sanitizedUrl = request.getSanitizedUrl();
        assertEquals("http://example.com:8080", sanitizedUrl);
    }

    @Test
    public void testGetSanitizedUrlWithoutQueryOrPort() {
        request = new Request(Verbs.GET, "http://example.com");
        String sanitizedUrl = request.getSanitizedUrl();
        assertEquals("http://example.com", sanitizedUrl);
    }

    @Test
    public void testGetSanitizedUrlWithOnlyQuery() {
        request = new Request(Verbs.GET, "http://example.com?param=1");
        String sanitizedUrl = request.getSanitizedUrl();
        assertEquals("http://example.com", sanitizedUrl);
    }

    @Test
    public void testGetSanitizedUrlWithOnlyPort() {
        request = new Request(Verbs.GET, "http://example.com:8080");
        String sanitizedUrl = request.getSanitizedUrl();
        assertEquals("http://example.com:8080", sanitizedUrl);
    }

    @Test
    public void testGetSanitizedUrlWithEmptyUrl() {
        request = new Request(Verbs.GET, "");
        String sanitizedUrl = request.getSanitizedUrl();
        assertEquals("", sanitizedUrl);
    }
}
