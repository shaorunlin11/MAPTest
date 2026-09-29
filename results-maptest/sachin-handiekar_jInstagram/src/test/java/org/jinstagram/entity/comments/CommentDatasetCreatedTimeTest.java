package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class CommentDatasetCreatedTimeTest {
    private CommentData commentData;

    @Before
    public void setUp() {
        commentData = new CommentData();
    }

    @After
    public void tearDown() {
        commentData = null;
    }

    @Test
    public void testSetCreatedTime() throws Exception {
        String expectedCreatedTime = "2023-04-05T12:34:56Z";
        commentData.setCreatedTime(expectedCreatedTime);

        Assert.assertEquals("The createdTime should be set correctly", expectedCreatedTime, commentData.getCreatedTime());
    }
}
