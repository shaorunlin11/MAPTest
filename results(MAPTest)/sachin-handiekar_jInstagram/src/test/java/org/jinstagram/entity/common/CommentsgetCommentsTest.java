package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.gson.annotations.SerializedName;
import org.jinstagram.entity.comments.CommentData;
import java.util.List;
import java.util.ArrayList;

import java.lang.reflect.Field;


@RunWith(JUnit4.class)
public class CommentsgetCommentsTest {
    private Comments commentsInstance;
    private List<CommentData> testComments;

    @Before
    public void setUp() {
        commentsInstance = new Comments();
        testComments = new ArrayList<CommentData>();
        testComments.add(new CommentData());
        testComments.add(new CommentData());
    }

    @After
    public void tearDown() {
        commentsInstance = null;
        testComments = null;
    }

    @Test
    public void testGetCommentsReturnsInitializedList() throws Exception {
        // Arrange
        Field commentsField = Comments.class.getDeclaredField("comments");
        commentsField.setAccessible(true);
        commentsField.set(commentsInstance, testComments);

        // Act
        List<CommentData> result = commentsInstance.getComments();

        // Assert
        Assert.assertNotNull("getComments should not return null", result);
        Assert.assertEquals("getComments should return the same list that was set", testComments, result);
    }

    @Test
    public void testGetCommentsReturnsEmptyListWhenNotSet() throws Exception {
        // Arrange
        Field commentsField = Comments.class.getDeclaredField("comments");
        commentsField.setAccessible(true);
        commentsField.set(commentsInstance, null);

        // Act
        List<CommentData> result = commentsInstance.getComments();

        // Assert
        Assert.assertNull("getComments should return null when comments are not set", result);
    }
}
