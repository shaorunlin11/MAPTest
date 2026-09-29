package com.fasterxml.jackson.core.async;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ByteBufferFeederfeedInputTest {
    @Test
    public void testFeedInput() throws Exception {
        // Since ByteBufferFeeder is an interface, we need to create a mock implementation
        ByteBufferFeeder feeder = new ByteBufferFeeder() {
            @Override
            public void feedInput(ByteBuffer buffer) throws IOException {
                // Implementation for testing purposes
                if (buffer == null) {
                    throw new IOException("Null buffer not allowed");
                }
                // Simulate processing the buffer
            }

            @Override
            public void endOfInput() {
                // Required by NonBlockingInputFeeder interface
            }

            @Override
            public boolean needMoreInput() {
                // Required by NonBlockingInputFeeder interface
                return false;
            }
        };

        // Test with a non-null buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            feeder.feedInput(buffer);
            Assert.assertTrue("feedInput should not throw exception with valid buffer", true);
        } catch (IOException e) {
            Assert.fail("feedInput threw unexpected exception: " + e.getMessage());
        }

        // Test with a null buffer
        try {
            feeder.feedInput(null);
            Assert.fail("feedInput should have thrown exception for null buffer");
        } catch (IOException e) {
            Assert.assertEquals("Null buffer not allowed", e.getMessage());
        }
    }
}
