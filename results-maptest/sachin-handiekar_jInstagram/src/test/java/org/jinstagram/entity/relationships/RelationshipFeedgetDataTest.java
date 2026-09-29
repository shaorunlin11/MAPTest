package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.relationships.RelationshipData;

public class RelationshipFeedgetDataTest {

    @Test
    public void testGetData_ReturnsDataField() {
        // Arrange
        RelationshipFeed feed = new RelationshipFeed();
        RelationshipData expectedData = new RelationshipData();
        feed.setData(expectedData);

        // Act
        RelationshipData result = feed.getData();

        // Assert
        Assert.assertEquals(expectedData, result);
    }

    @Test
    public void testGetData_WhenDataIsNull_ReturnsNull() {
        // Arrange
        RelationshipFeed feed = new RelationshipFeed();

        // Act
        RelationshipData result = feed.getData();

        // Assert
        Assert.assertNull(result);
    }
}
