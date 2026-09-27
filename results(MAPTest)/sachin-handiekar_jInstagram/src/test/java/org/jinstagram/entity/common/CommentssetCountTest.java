package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class CommentssetCountTest {
    private Comments comments;
    private Field countField;

    @Before
    public void setUp() throws Exception {
        comments = new Comments();
        countField = Comments.class.getDeclaredField("count");
        countField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        countField.setAccessible(false);
    }

    @Test
    public void testSetCount() throws Exception {
        int expectedCount = 42;
        comments.setCount(expectedCount);
        Integer actualCount = (Integer) countField.get(comments);
        Assert.assertEquals("The count should be set correctly", (long) expectedCount, (long) actualCount);
    }
}
