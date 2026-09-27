package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;
import org.jinstagram.InstagramObject;

public class TagInfoFeedgetTagInfoTest {

    @Test
    public void testGetTagInfo() throws Exception {
        // Arrange
        TagInfoFeed tagInfoFeed = new TagInfoFeed();
        TagInfoData expectedTagInfo = new TagInfoData();

        // Use reflection to set the private field
        java.lang.reflect.Field tagInfoField = TagInfoFeed.class.getDeclaredField("tagInfo");
        tagInfoField.setAccessible(true);
        tagInfoField.set(tagInfoFeed, expectedTagInfo);

        // Act
        TagInfoData result = tagInfoFeed.getTagInfo();

        // Assert
        Assert.assertEquals(expectedTagInfo, result);
    }
}
