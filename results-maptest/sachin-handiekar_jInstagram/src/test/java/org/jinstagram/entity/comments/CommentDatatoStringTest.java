package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.FromTagData;

public class CommentDatatoStringTest {

    @Test
    public void testToString() {
        CommentData commentData = new CommentData();
        FromTagData fromTagData = new FromTagData();

        // Use reflection to set private fields
        try {
            java.lang.reflect.Field commentFromField = CommentData.class.getDeclaredField("commentFrom");
            commentFromField.setAccessible(true);
            commentFromField.set(commentData, fromTagData);

            java.lang.reflect.Field createdTimeField = CommentData.class.getDeclaredField("createdTime");
            createdTimeField.setAccessible(true);
            createdTimeField.set(commentData, "2023-04-01T12:00:00Z");

            java.lang.reflect.Field idField = CommentData.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(commentData, "12345");

            java.lang.reflect.Field textField = CommentData.class.getDeclaredField("text");
            textField.setAccessible(true);
            textField.set(commentData, "This is a comment.");
        } catch (Exception e) {
            Assert.fail("Failed to set private fields: " + e.getMessage());
        }

        String result = commentData.toString();

        String expected = "CommentData [commentFrom=" + fromTagData + ", createdTime=2023-04-01T12:00:00Z, id=12345, text=This is a comment.]";

        Assert.assertEquals(expected, result);
    }
}
