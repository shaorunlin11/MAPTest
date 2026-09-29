package com.fasterxml.jackson.core.util;

import org.junit.Test;
import org.junit.Assert;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.io.StringWriter;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.lang.ref.SoftReference;
import com.fasterxml.jackson.core.io.JsonStringEncoder;

public class BufferRecyclersquoteAsJsonText_3519fcc5Test {

    @Test
    public void testQuoteAsJsonText() throws Exception {
        StringBuilder output = new StringBuilder();
        CharSequence input = "test";
        BufferRecyclers.quoteAsJsonText(input, output);
        Assert.assertEquals("test", output.toString());
    }

    @Test
    public void testQuoteAsJsonTextWithEmptyInput() throws Exception {
        StringBuilder output = new StringBuilder();
        CharSequence input = "";
        BufferRecyclers.quoteAsJsonText(input, output);
        Assert.assertEquals("", output.toString());
    }

    @Test
    public void testQuoteAsJsonTextWithNullInput() throws Exception {
        StringBuilder output = new StringBuilder();
        CharSequence input = null;
        try {
            BufferRecyclers.quoteAsJsonText(input, output);
            Assert.fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }

    @Test
    public void testQuoteAsJsonTextWithNullOutput() throws Exception {
        StringBuilder output = null;
        CharSequence input = "test";
        try {
            BufferRecyclers.quoteAsJsonText(input, output);
            Assert.fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // Expected
        }
    }
}
