package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CountsgetMediaTest {
    @Test
    public void testGetMedia() throws Exception {
        Counts counts = new Counts();
        int expectedMedia = 42;
        Field mediaField = Counts.class.getDeclaredField("media");
        mediaField.setAccessible(true);
        mediaField.set(counts, expectedMedia);
        assertEquals(expectedMedia, counts.getMedia());
    }
}
