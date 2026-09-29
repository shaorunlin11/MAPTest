package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

public class MediaCommentsFeedgetCommentDataListTest {
    private MediaCommentsFeed mediaCommentsFeed;
    private List<CommentData> expectedCommentDataList;

    @Before
    public void setUp() {
        mediaCommentsFeed = new MediaCommentsFeed();
        expectedCommentDataList = new ArrayList<CommentData>();
        // Add some sample CommentData objects if needed
    }

    @Test
    public void testGetCommentDataList() {
        // Set the commentDataList field using reflection to avoid using setters
        try {
            java.lang.reflect.Field field = MediaCommentsFeed.class.getDeclaredField("commentDataList");
            field.setAccessible(true);
            field.set(mediaCommentsFeed, expectedCommentDataList);
        } catch (Exception e) {
            Assert.fail("Failed to set private field: " + e.getMessage());
        }

        List<CommentData> actualCommentDataList = mediaCommentsFeed.getCommentDataList();
        Assert.assertEquals(expectedCommentDataList, actualCommentDataList);
    }
}
