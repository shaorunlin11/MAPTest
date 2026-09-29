package org.jinstagram.entity.comments;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.comments.CommentData;
import java.util.List;
import java.util.ArrayList;

public class MediaCommentsFeedgetMetaTest {
    private MediaCommentsFeed mediaCommentsFeed;
    private Meta expectedMeta;

    @Before
    public void setUp() {
        mediaCommentsFeed = new MediaCommentsFeed();
        expectedMeta = new Meta();
        // Set the meta field using reflection to bypass access modifiers
        try {
            java.lang.reflect.Field metaField = MediaCommentsFeed.class.getDeclaredField("meta");
            metaField.setAccessible(true);
            metaField.set(mediaCommentsFeed, expectedMeta);
        } catch (Exception e) {
            Assert.fail("Failed to set up test: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        mediaCommentsFeed = null;
        expectedMeta = null;
    }

    @Test
    public void testGetMeta_returnsExpectedMetaObject() {
        Meta result = mediaCommentsFeed.getMeta();
        Assert.assertNotNull("getMeta should not return null", result);
        Assert.assertEquals("getMeta should return the expected Meta object", expectedMeta, result);
    }
}
