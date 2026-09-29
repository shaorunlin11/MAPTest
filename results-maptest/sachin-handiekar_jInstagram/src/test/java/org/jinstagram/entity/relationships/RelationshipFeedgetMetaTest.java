package org.jinstagram.entity.relationships;

import org.jinstagram.entity.common.Meta;
import org.junit.Test;
import static org.junit.Assert.*;

public class RelationshipFeedgetMetaTest {

    @Test
    public void testGetMetaReturnsMetaObject() throws Exception {
        // Arrange
        RelationshipFeed feed = new RelationshipFeed();
        Meta expectedMeta = new Meta();

        // Use reflection to set the private 'meta' field
        java.lang.reflect.Field metaField = RelationshipFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(feed, expectedMeta);

        // Act
        Meta result = feed.getMeta();

        // Assert
        assertEquals(expectedMeta, result);
    }
}
