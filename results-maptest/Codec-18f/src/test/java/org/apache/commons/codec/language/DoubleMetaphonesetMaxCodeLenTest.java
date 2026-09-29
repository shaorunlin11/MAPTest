package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class DoubleMetaphonesetMaxCodeLenTest {
    private DoubleMetaphone doubleMetaphone;

    @Before
    public void setUp() {
        doubleMetaphone = new DoubleMetaphone();
    }

    @Test
    public void testSetMaxCodeLen() throws Exception {
        int[] testValues = {0, 1, 2, 3, 4, 5, 10, Integer.MAX_VALUE};

        for (int value : testValues) {
            doubleMetaphone.setMaxCodeLen(value);
            Field maxCodeLenField = DoubleMetaphone.class.getDeclaredField("maxCodeLen");
            maxCodeLenField.setAccessible(true);
            int actualValue = (int) maxCodeLenField.get(doubleMetaphone);
            assertEquals("Failed to set maxCodeLen to " + value, value, actualValue);
        }
    }
}
