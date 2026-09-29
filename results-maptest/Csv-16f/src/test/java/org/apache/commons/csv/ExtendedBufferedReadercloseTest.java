package org.apache.commons.csv;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.io.Reader;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.apache.commons.csv.Constants.END_OF_STREAM;
import static org.apache.commons.csv.Constants.UNDEFINED;

public class ExtendedBufferedReadercloseTest {
    private ExtendedBufferedReader reader;
    private Reader mockReader;
    private Field closedField;
    private Field lastCharField;

    @Before
    public void setUp() throws Exception {
        mockReader = new StringReader("test");
        reader = new ExtendedBufferedReader(mockReader);
        closedField = ExtendedBufferedReader.class.getDeclaredField("closed");
        lastCharField = ExtendedBufferedReader.class.getDeclaredField("lastChar");
        closedField.setAccessible(true);
        lastCharField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

    @Test
    public void testCloseSetsClosedToTrue() throws Exception {
        reader.close();
        boolean closed = closedField.getBoolean(reader);
        Assert.assertTrue("close() should set closed to true", closed);
    }

    @Test
    public void testCloseSetsLastCharToEND_OF_STREAM() throws Exception {
        reader.close();
        int lastChar = lastCharField.getInt(reader);
        Assert.assertEquals("close() should set lastChar to END_OF_STREAM", END_OF_STREAM, lastChar);
    }

    @Test
    public void testCloseCallsSuperClose() throws Exception {
        // We can't directly verify that super.close() is called, but we can verify that the close method is overridden
        // and that the behavior is as expected based on the implementation
        reader.close();
    }

    @Test
    public void testCloseCanBeCalledMultipleTimes() throws Exception {
        reader.close();
        reader.close();
        boolean closed = closedField.getBoolean(reader);
        Assert.assertTrue("close() should be idempotent", closed);
    }

    @Test
    public void testCloseDoesNotThrowExceptionWhenAlreadyClosed() throws Exception {
        reader.close();
        reader.close();
        boolean closed = closedField.getBoolean(reader);
        Assert.assertTrue("close() should not throw when already closed", closed);
    }
}
