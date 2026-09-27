package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;

public class CommentDatagetTextTest {
    @Test
    public void testGetText() throws Exception {
        CommentData commentData = new CommentData();
        String expectedText = "This is a test comment";

        // Use reflection to set the private text field
        java.lang.reflect.Field textField = CommentData.class.getDeclaredField("text");
        textField.setAccessible(true);
        textField.set(commentData, expectedText);

        String actualText = commentData.getText();

        Assert.assertEquals(expectedText, actualText);
    }
}
