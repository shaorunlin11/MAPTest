package com.fasterxml.jackson.core.util;
import org.junit.Test;
import java.lang.ref.SoftReference;
import com.fasterxml.jackson.core.io.JsonStringEncoder;
import static org.junit.Assert.*;
import java.lang.reflect.Field;

public class BufferRecyclersgetBufferRecyclerTest {
    @Test
    public void testGetBufferRecycler() {
        // Act
        BufferRecycler recycler1 = BufferRecyclers.getBufferRecycler();
        BufferRecycler recycler2 = BufferRecyclers.getBufferRecycler();

        // Assert
        assertNotNull(recycler1);
        assertEquals(recycler1, recycler2);
    }
}
