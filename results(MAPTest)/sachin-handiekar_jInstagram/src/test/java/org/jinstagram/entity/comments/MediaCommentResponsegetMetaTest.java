package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;

public class MediaCommentResponsegetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        MediaCommentResponse mediaCommentResponse = new MediaCommentResponse();
        Meta expectedMeta = new Meta();

        // Use reflection to set the meta field
        java.lang.reflect.Field metaField = MediaCommentResponse.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(mediaCommentResponse, expectedMeta);

        Meta actualMeta = mediaCommentResponse.getMeta();
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
