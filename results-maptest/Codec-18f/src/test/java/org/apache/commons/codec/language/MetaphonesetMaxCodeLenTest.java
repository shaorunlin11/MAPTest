package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class MetaphonesetMaxCodeLenTest {
    private Metaphone metaphone;

    @Before
    public void setUp() {
        metaphone = new Metaphone();
    }

    @After
    public void tearDown() {
        metaphone = null;
    }

    @Test
    public void testSetMaxCodeLen() throws Exception {
        // Arrange
        int expectedMaxCodeLen = 6;

        // Act
        metaphone.setMaxCodeLen(expectedMaxCodeLen);

        // Assert
        Field maxCodeLenField = Metaphone.class.getDeclaredField("maxCodeLen");
        maxCodeLenField.setAccessible(true);
        int actualMaxCodeLen = (int) maxCodeLenField.get(metaphone);
        Assert.assertEquals(expectedMaxCodeLen, actualMaxCodeLen);
    }
}
