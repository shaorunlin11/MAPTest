package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;

public class CommentDatagetIdTest {
    @Test
    public void testGetId() throws Exception {
        CommentData commentData = new CommentData();
        String expectedId = "test-id";

        // Use reflection to set private field
        java.lang.reflect.Field idField = CommentData.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(commentData, expectedId);

        String actualId = commentData.getId();
        Assert.assertEquals(expectedId, actualId);
    }
}
