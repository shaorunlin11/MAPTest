package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;

public class MediaCommentsFeedsetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        MediaCommentsFeed mediaCommentsFeed = new MediaCommentsFeed();
        Meta meta = new Meta();

        mediaCommentsFeed.setMeta(meta);

        // Use reflection to verify the private field 'meta' was set
        java.lang.reflect.Field metaField = MediaCommentsFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta resultMeta = (Meta) metaField.get(mediaCommentsFeed);

        Assert.assertEquals(meta, resultMeta);
    }
}
