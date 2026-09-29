package org.apache.commons.csv;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

public class ExtendedBufferedReaderisClosedTest {

    @Test
    public void testIsClosedInitiallyFalse() throws Exception {
        Reader reader = new StringReader("test");
        ExtendedBufferedReader bufferedReader = new ExtendedBufferedReader(reader);
        assertFalse(bufferedReader.isClosed());
    }

    @Test
    public void testIsClosedAfterClose() throws Exception {
        Reader reader = new StringReader("test");
        ExtendedBufferedReader bufferedReader = new ExtendedBufferedReader(reader);
        bufferedReader.close();
        assertTrue(bufferedReader.isClosed());
    }
}
