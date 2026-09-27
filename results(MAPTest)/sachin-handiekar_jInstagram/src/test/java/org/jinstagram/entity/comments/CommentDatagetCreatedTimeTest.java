package org.jinstagram.entity.comments;

import org.junit.Test;
import static org.junit.Assert.*;

public class CommentDatagetCreatedTimeTest {

    @Test
    public void testGetCreatedTime() throws Exception {
        CommentData commentData = new CommentData();
        String expectedCreatedTime = "1234567890";
        commentData.setCreatedTime(expectedCreatedTime);

        String actualCreatedTime = commentData.getCreatedTime();
        assertEquals(expectedCreatedTime, actualCreatedTime);
    }

    @Test
    public void testGetCreatedTimeWithNull() throws Exception {
        CommentData commentData = new CommentData();
        String expectedCreatedTime = null;
        commentData.setCreatedTime(expectedCreatedTime);

        String actualCreatedTime = commentData.getCreatedTime();
        assertNull(actualCreatedTime);
    }

    @Test
    public void testGetCreatedTimeWithEmptyString() throws Exception {
        CommentData commentData = new CommentData();
        String expectedCreatedTime = "";
        commentData.setCreatedTime(expectedCreatedTime);

        String actualCreatedTime = commentData.getCreatedTime();
        assertEquals(expectedCreatedTime, actualCreatedTime);
    }
}
