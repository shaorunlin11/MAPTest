package org.jinstagram.http;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public class StreamUtilsgetStreamContentsTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetStreamContentsWithNonNullInputStream() throws IOException {
        String testContent = "Hello, world!";
        InputStream is = new ByteArrayInputStream(testContent.getBytes("UTF-8"));
        String result = StreamUtils.getStreamContents(is);
        Assert.assertEquals(testContent, result);
    }

    @Test
    public void testGetStreamContentsWithEmptyInputStream() throws IOException {
        InputStream is = new ByteArrayInputStream(new byte[0]);
        String result = StreamUtils.getStreamContents(is);
        Assert.assertEquals("", result);
    }

    @Test
    public void testGetStreamContentsWithNullInputStream() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Cannot get String from a null object");
        StreamUtils.getStreamContents(null);
    }

    @Test
    public void testGetStreamContentsWithIOException() throws IOException {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("Error while reading response body");
        InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated error");
            }
        };
        StreamUtils.getStreamContents(is);
    }
}
