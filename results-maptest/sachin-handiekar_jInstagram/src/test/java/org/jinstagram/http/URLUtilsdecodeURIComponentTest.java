package org.jinstagram.http;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
public class URLUtilsdecodeURIComponentTest {
    @Test
    public void testDecodeURIComponentWithValidInput() throws UnsupportedEncodingException {
        String encodedString = "Hello%20World";
        String expected = "Hello World";
        String result = URLUtils.decodeURIComponent(encodedString);
        assertEquals(expected, result);
    }

    @Test
    public void testDecodeURIComponentWithUnsupportedEncoding() {
        String input = "Test";
        String result = URLUtils.decodeURIComponent(input);
        assertEquals(input, result);
    }
}
