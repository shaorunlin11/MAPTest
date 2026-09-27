package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.relationships.RelationshipData;

public class RelationshipFeedsetDataTest {
    private RelationshipFeed relationshipFeed;

    @Before
    public void setUp() {
        relationshipFeed = new RelationshipFeed();
    }

    @After
    public void tearDown() {
        relationshipFeed = null;
    }

    @Test
    public void testSetData_WithValidData_AssignsDataToInstanceVariable() throws Exception {
        // Arrange
        RelationshipData testData = new RelationshipData();

        // Act
        relationshipFeed.setData(testData);

        // Assert
        java.lang.reflect.Field field = relationshipFeed.getClass().getDeclaredField("data");
        field.setAccessible(true);
        Assert.assertEquals(testData, field.get(relationshipFeed));
    }

    @Test
    public void testSetData_WithNullData_AssignsNullToInstanceVariable() throws Exception {
        // Arrange
        RelationshipData testData = null;

        // Act
        relationshipFeed.setData(testData);

        // Assert
        java.lang.reflect.Field field = relationshipFeed.getClass().getDeclaredField("data");
        field.setAccessible(true);
        Assert.assertNull(field.get(relationshipFeed));
    }
}
