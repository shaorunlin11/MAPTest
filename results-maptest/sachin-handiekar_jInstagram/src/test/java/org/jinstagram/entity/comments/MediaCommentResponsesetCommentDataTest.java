package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import com.google.gson.annotations.SerializedName;

public class MediaCommentResponsesetCommentDataTest {

    private MediaCommentResponse mediaCommentResponse;
    private CommentData commentData;

    @Before
    public void setUp() {
        mediaCommentResponse = new MediaCommentResponse();
        commentData = new CommentData();
    }

    @After
    public void tearDown() {
        mediaCommentResponse = null;
        commentData = null;
    }

    @Test
    public void testSetCommentData() {
        // Act
        mediaCommentResponse.setCommentData(commentData);

        // Assert
        Assert.assertEquals(commentData, mediaCommentResponse.getCommentData());
    }
}
