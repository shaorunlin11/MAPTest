package com.zappos.json;

import org.junit.Test;
import org.junit.Assert;
import java.io.StringWriter;
import java.io.Writer;
import java.io.IOException;

public class JsonWriterwriteEnumTest {
    @Test
    public void testWriteEnum() throws IOException {
        // Arrange
        ZapposJson zapposJson = new ZapposJson();
        Writer writer = new StringWriter();

        // Act
        JsonWriter.writeEnum(zapposJson, Enum.valueOf(Color.class, "RED"), writer);

        // Assert
        Assert.assertEquals("\"RED\"", writer.toString());
    }

    private enum Color {
        RED, GREEN, BLUE
    }
}
