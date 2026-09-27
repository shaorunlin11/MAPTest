package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;

public class CommentDatasetTextTest {

    @Test
    public void testSetText() throws Exception {
        CommentData commentData = new CommentData();
        String expectedText = "This is a test comment";

        commentData.setText(expectedText);

        // Use reflection to access the private 'text' field
        java.lang.reflect.Field textField = CommentData.class.getDeclaredField("text");
        textField.setAccessible(true);

        String actualText = (String) textField.get(commentData);

        Assert.assertEquals("The text should be set correctly", expectedText, actualText);
    }
}
