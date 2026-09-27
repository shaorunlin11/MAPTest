package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.comments.CommentData;
import org.jinstagram.entity.comments.MediaCommentsFeed;

public class MediaCommentsFeedsetCommentDataListTest {
    private MediaCommentsFeed mediaCommentsFeed;
    private List<CommentData> commentDataList;

    @Before
    public void setUp() {
        mediaCommentsFeed = new MediaCommentsFeed();
        commentDataList = new ArrayList<CommentData>();
    }

    @After
    public void tearDown() {
        mediaCommentsFeed = null;
        commentDataList = null;
    }

    @Test
    public void testSetCommentDataList() throws Exception {
        // Arrange
        List<CommentData> expected = commentDataList;

        // Act
        mediaCommentsFeed.setCommentDataList(expected);

        // Assert
        Assert.assertEquals(expected, mediaCommentsFeed.getCommentDataList());
    }
}
