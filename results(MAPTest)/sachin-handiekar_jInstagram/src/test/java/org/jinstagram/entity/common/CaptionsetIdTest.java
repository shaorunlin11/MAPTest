package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CaptionsetIdTest {
    private Caption caption;

    @Before
    public void setUp() {
        caption = new Caption();
    }

    @Test
    public void testSetId() throws Exception {
        String expectedId = "12345";
        caption.setId(expectedId);

        Field idField = Caption.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(caption);

        assertEquals(expectedId, actualId);
    }
}
