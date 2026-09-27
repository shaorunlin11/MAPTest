package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.DataOutput;
import java.io.IOException;

public class JsonFactorycreateGenerator_973f6a6aTest {
    @Test
    public void testCreateGenerator() throws Exception {
        JsonFactory factory = new JsonFactory();
        DataOutput out = new DataOutput() {
            @Override
            public void write(int b) throws IOException {
                // No-op
            }

            @Override
            public void write(byte[] b) throws IOException {
                // No-op
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                // No-op
            }

            @Override
            public void writeBoolean(boolean v) throws IOException {
                // No-op
            }

            @Override
            public void writeByte(int v) throws IOException {
                // No-op
            }

            @Override
            public void writeBytes(String s) throws IOException {
                // No-op
            }

            @Override
            public void writeChar(int v) throws IOException {
                // No-op
            }

            @Override
            public void writeChars(String s) throws IOException {
                // No-op
            }

            @Override
            public void writeDouble(double v) throws IOException {
                // No-op
            }

            @Override
            public void writeFloat(float v) throws IOException {
                // No-op
            }

            @Override
            public void writeInt(int v) throws IOException {
                // No-op
            }

            @Override
            public void writeLong(long v) throws IOException {
                // No-op
            }

            @Override
            public void writeShort(int v) throws IOException {
                // No-op
            }

            @Override
            public void writeUTF(String s) throws IOException {
                // No-op
            }
        };

        JsonEncoding enc = JsonEncoding.UTF8;

        JsonGenerator generator = factory.createGenerator(out, enc);
        Assert.assertNotNull(generator);
    }
}
