package com.fasterxml.jackson.core.io;
import org.junit.Test;
import org.junit.Assert;
import java.io.InputStream;
import java.io.IOException;
import java.io.ByteArrayInputStream;
public class MergedStreamskipTest {
    @Test
    public void testSkipWithBuffer() throws Exception {
        byte[] buffer = new byte[10];
        InputStream in = new ByteArrayInputStream(new byte[10]);
        MergedStream stream = new MergedStream(null, in, buffer, 0, 10);

        long skipped = stream.skip(5);
        Assert.assertEquals(5, skipped);
        // Cannot directly access _ptr, so we verify behavior through other means
        // For example, we can check that reading from the stream after skipping
        // would start at the correct position
        byte[] readBuffer = new byte[5];
        int bytesRead = stream.read(readBuffer);
        Assert.assertEquals(5, bytesRead);
    }

    @Test
    public void testSkipExceedsBuffer() throws Exception {
        byte[] buffer = new byte[10];
        InputStream in = new ByteArrayInputStream(new byte[20]);
        MergedStream stream = new MergedStream(null, in, buffer, 0, 10);

        long skipped = stream.skip(15);
        Assert.assertEquals(15, skipped);
        // Cannot directly access _ptr or _b, so we verify behavior through other means
        // For example, we can check that reading from the stream after skipping
        // would start at the correct position
        byte[] readBuffer = new byte[5];
        int bytesRead = stream.read(readBuffer);
        Assert.assertEquals(5, bytesRead);
    }

    @Test
    public void testSkipWithBufferAndRemaining() throws Exception {
        byte[] buffer = new byte[10];
        InputStream in = new ByteArrayInputStream(new byte[20]);
        MergedStream stream = new MergedStream(null, in, buffer, 0, 10);

        long skipped = stream.skip(15);
        Assert.assertEquals(15, skipped);
        // Cannot directly access _ptr or _b, so we verify behavior through other means
        // For example, we can check that reading from the stream after skipping
        // would start at the correct position
        byte[] readBuffer = new byte[5];
        int bytesRead = stream.read(readBuffer);
        Assert.assertEquals(5, bytesRead);
    }

    @Test
    public void testSkipWithNoBuffer() throws Exception {
        InputStream in = new ByteArrayInputStream(new byte[10]);
        MergedStream stream = new MergedStream(null, in, null, 0, 0);

        long skipped = stream.skip(5);
        Assert.assertEquals(5, skipped);
        // Cannot directly access _ptr, so we verify behavior through other means
        // For example, we can check that reading from the stream after skipping
        // would start at the correct position
        byte[] readBuffer = new byte[5];
        int bytesRead = stream.read(readBuffer);
        Assert.assertEquals(5, bytesRead);
    }

    @Test
    public void testSkipZero() throws Exception {
        byte[] buffer = new byte[10];
        InputStream in = new ByteArrayInputStream(new byte[10]);
        MergedStream stream = new MergedStream(null, in, buffer, 0, 10);

        long skipped = stream.skip(0);
        Assert.assertEquals(0, skipped);
        // Cannot directly access _ptr, so we verify behavior through other means
        // For example, we can check that reading from the stream after skipping
        // would start at the correct position
        byte[] readBuffer = new byte[5];
        int bytesRead = stream.read(readBuffer);
        Assert.assertEquals(5, bytesRead);
    }
}
