package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

public class MediaCommentResponsesetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        MediaCommentResponse response = new MediaCommentResponse();
        Meta expectedMeta = new Meta();

        response.setMeta(expectedMeta);

        // Use reflection to verify the private field
        java.lang.reflect.Field metaField = MediaCommentResponse.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta actualMeta = (Meta) metaField.get(response);

        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
