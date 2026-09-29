package org.jinstagram.entity.likes;

import org.junit.Test;
import org.junit.Assert;

import java.util.List;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.User;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


public class LikesFeedgetMetaTest {

    @Test
    public void testGetMeta() throws Exception {
        // Arrange
        LikesFeed likesFeed = new LikesFeed();

        // Set up a Meta object
        Meta expectedMeta = new Meta();

        // Use reflection to set the private 'meta' field
        Field metaField = LikesFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(likesFeed, expectedMeta);

        // Act
        Meta actualMeta = likesFeed.getMeta();

        // Assert
        Assert.assertEquals(expectedMeta, actualMeta);
    }
}
