package org.jinstagram.entity.comments;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class CommentDatasetIdTest {

    @Test
    public void testSetId() throws Exception {
        CommentData commentData = new CommentData();
        String testId = "12345";

        commentData.setId(testId);

        Field idField = CommentData.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(commentData);

        assertEquals(testId, actualId);
    }
}
