package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Assert;

import java.lang.reflect.Field;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.relationships.RelationshipData;

public class RelationshipFeedtoStringTest {

    @Test
    public void testToString() throws Exception {
        // Arrange
        RelationshipFeed feed = new RelationshipFeed();
        RelationshipData data = new RelationshipData();
        Meta meta = new Meta();

        // Set fields using reflection to bypass private access
        Field dataField = RelationshipFeed.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(feed, data);

        Field metaField = RelationshipFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(feed, meta);

        // Act
        String result = feed.toString();

        // Assert
        Assert.assertTrue(result.startsWith("RelationshipFeed [data="));
        Assert.assertTrue(result.contains(", meta="));
        Assert.assertTrue(result.endsWith("]"));
    }
}
