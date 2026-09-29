package org.jinstagram.entity.comments;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.comments.CommentData;

public class MediaCommentResponsetoStringTest {

    @Test
    public void testToStringWithNonNullFields() {
        MediaCommentResponse response = new MediaCommentResponse();
        CommentData commentData = new CommentData();
        Meta meta = new Meta();

        // Use reflection to set private fields
        try {
            java.lang.reflect.Field commentDataField = MediaCommentResponse.class.getDeclaredField("commentData");
            commentDataField.setAccessible(true);
            commentDataField.set(response, commentData);

            java.lang.reflect.Field metaField = MediaCommentResponse.class.getDeclaredField("meta");
            metaField.setAccessible(true);
            metaField.set(response, meta);
        } catch (Exception e) {
            fail("Failed to set private fields: " + e.getMessage());
        }

        String result = response.toString();
        assertTrue(result.contains("MediaCommentResponse [commentData="));
        assertTrue(result.contains("meta="));
    }

    @Test
    public void testToStringWithNullCommentData() {
        MediaCommentResponse response = new MediaCommentResponse();
        Meta meta = new Meta();

        // Use reflection to set private fields
        try {
            java.lang.reflect.Field metaField = MediaCommentResponse.class.getDeclaredField("meta");
            metaField.setAccessible(true);
            metaField.set(response, meta);
        } catch (Exception e) {
            fail("Failed to set private fields: " + e.getMessage());
        }

        String result = response.toString();
        assertTrue(result.contains("MediaCommentResponse [commentData=null, meta="));
    }

    @Test
    public void testToStringWithNullMeta() {
        MediaCommentResponse response = new MediaCommentResponse();
        CommentData commentData = new CommentData();

        // Use reflection to set private fields
        try {
            java.lang.reflect.Field commentDataField = MediaCommentResponse.class.getDeclaredField("commentData");
            commentDataField.setAccessible(true);
            commentDataField.set(response, commentData);
        } catch (Exception e) {
            fail("Failed to set private fields: " + e.getMessage());
        }

        String result = response.toString();
        assertTrue(result.contains("MediaCommentResponse [commentData="));
        assertTrue(result.contains("meta=null]"));
    }

    @Test
    public void testToStringWithBothNull() {
        MediaCommentResponse response = new MediaCommentResponse();

        String result = response.toString();
        assertTrue(result.contains("MediaCommentResponse [commentData=null, meta=null]"));
    }
}
