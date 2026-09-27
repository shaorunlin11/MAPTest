package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;

import java.util.Date;

public class TypeHandlercreateDateTest {
    @Test
    public void testCreateDate() {
        try {
            Date result = TypeHandler.createDate("test");
            Assert.fail("Expected UnsupportedOperationException but no exception was thrown");
        } catch (UnsupportedOperationException e) {
            // Expected exception, test passes
        }
    }
}
