package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.comments.CommentData;

public class CommentssetCommentsTest {
    private Comments commentsInstance;
    private List<CommentData> testComments;

    @Before
    public void setUp() {
        commentsInstance = new Comments();
        testComments = new ArrayList<CommentData>();
        testComments.add(new CommentData());
    }

    @Test
    public void testSetComments_setsCommentsFieldCorrectly() throws Exception {
        commentsInstance.setComments(testComments);

        java.lang.reflect.Field commentsField = Comments.class.getDeclaredField("comments");
        commentsField.setAccessible(true);
        List<CommentData> actualComments = (List<CommentData>) commentsField.get(commentsInstance);

        Assert.assertEquals(testComments, actualComments);
    }

    @Test
    public void testSetComments_withNullValue() throws Exception {
        commentsInstance.setComments(null);

        java.lang.reflect.Field commentsField = Comments.class.getDeclaredField("comments");
        commentsField.setAccessible(true);
        List<CommentData> actualComments = (List<CommentData>) commentsField.get(commentsInstance);

        Assert.assertNull(actualComments);
    }
}
