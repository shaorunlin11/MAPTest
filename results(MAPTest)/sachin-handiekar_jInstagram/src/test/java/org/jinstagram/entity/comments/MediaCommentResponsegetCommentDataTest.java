package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.comments.CommentData;
import org.jinstagram.entity.common.Meta;

public class MediaCommentResponsegetCommentDataTest {

    @Test
    public void testGetCommentData() throws Exception {
        MediaCommentResponse mediaCommentResponse = new MediaCommentResponse();
        CommentData expectedCommentData = new CommentData();

        // Use reflection to set the private field
        java.lang.reflect.Field commentDataField = MediaCommentResponse.class.getDeclaredField("commentData");
        commentDataField.setAccessible(true);
        commentDataField.set(mediaCommentResponse, expectedCommentData);

        CommentData actualCommentData = mediaCommentResponse.getCommentData();
        Assert.assertEquals(expectedCommentData, actualCommentData);
    }
}
