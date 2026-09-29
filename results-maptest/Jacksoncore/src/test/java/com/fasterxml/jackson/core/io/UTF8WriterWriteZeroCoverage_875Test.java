package com.fasterxml.jackson.core.io;

import org.junit.Test;
import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import com.fasterxml.jackson.core.util.BufferRecycler;
import java.lang.Object;

public class UTF8WriterWriteZeroCoverage_875Test {
    @Test
    public void testWriteWithLenLessThanTwo() throws Exception {
        // Create a real instance of OutputStream
        OutputStream out = new ByteArrayOutputStream();

        // Create an instance of UTF8Writer with a valid IOContext
        BufferRecycler bufferRecycler = new BufferRecycler();
        Object object = new Object();
        boolean booleanValue = false;
        UTF8Writer writer = new UTF8Writer(new IOContext(bufferRecycler, object, booleanValue), out);

        // Prepare input data with len < 2
        char[] cbuf = {'a'};
        int off = 0;
        int len = 1;

        // Call the method under test
        writer.write(cbuf, off, len);
    }
}
