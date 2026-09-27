package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;

public class CommentsgetCountTest {
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
        comments = null;
        countField = null;
    }

    @Test
    public void testGetCount_ReturnsInitializedValue() throws Exception {
        int expectedCount = 42;
        countField.set(comments, expectedCount);
        int actualCount = comments.getCount();
        Assert.assertEquals(expectedCount, actualCount);
    }
}
