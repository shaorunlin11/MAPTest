package org.jinstagram.entity.common;

import org.junit.Test;

import org.jinstagram.entity.comments.CommentData;


public class CommentsToStringZeroCoverageTest {
    @Test
    public void testToString() {
        Comments comments = new Comments();
        comments.setComments(java.util.Collections.singletonList(new CommentData()));
        comments.setCount(5);
        String result = comments.toString();
        // Ensure the toString method is executed and the result is not null
        assert result != null;
    }
}
