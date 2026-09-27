package org.jinstagram.entity.comments;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.FromTagData;
public class CommentDatagetCommentFromTest {
    private CommentData commentData;
    private FromTagData expectedFromTagData;

    @Before
    public void setUp() {
        commentData = new CommentData();
        expectedFromTagData = new FromTagData();
        try {
            commentData.getClass().getDeclaredField("commentFrom").setAccessible(true);
            commentData.getClass().getDeclaredField("commentFrom").set(commentData, expectedFromTagData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        commentData = null;
        expectedFromTagData = null;
    }
}
