package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.FromTagData;

public class CommentDatasetCommentFromTest {
    private CommentData commentData;
    private FromTagData fromTagData;

    @Before
    public void setUp() {
        commentData = new CommentData();
        fromTagData = new FromTagData();
    }

    @After
    public void tearDown() {
        commentData = null;
        fromTagData = null;
    }

    @Test
    public void testSetCommentFrom() {
        // Act
        commentData.setCommentFrom(fromTagData);

        // Assert
        Assert.assertEquals(fromTagData, commentData.getCommentFrom());
    }

    @Test
    public void testSetCommentFromWithNull() {
        // Act
        commentData.setCommentFrom(null);

        // Assert
        Assert.assertNull(commentData.getCommentFrom());
    }
}
