package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;

public class RelationshipFeedsetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        // Arrange
        RelationshipFeed feed = new RelationshipFeed();
        Meta expectedMeta = new Meta();

        // Act
        feed.setMeta(expectedMeta);

        // Assert
        Assert.assertEquals(expectedMeta, feed.getMeta());
    }
}
