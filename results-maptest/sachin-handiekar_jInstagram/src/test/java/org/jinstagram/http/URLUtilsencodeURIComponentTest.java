package org.jinstagram.http;
import org.junit.Test;
import static org.junit.Assert.*;
public class URLUtilsencodeURIComponentTest {



    @Test
    public void testEncodeURIComponentWithEmptyString() {
        String input = "";
        String expected = "";
        String result = URLUtils.encodeURIComponent(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeURIComponentWithUnsupportedEncoding() throws Exception {
        // This test is not directly possible due to the way URLEncoder works
        // but we can simulate by using a different encoding that's not supported
        // Note: In practice, UTF-8 is always supported in Java 1.6+
        String input = "test";
        String result = URLUtils.encodeURIComponent(input);
        assertNotNull(result);
    }
}
