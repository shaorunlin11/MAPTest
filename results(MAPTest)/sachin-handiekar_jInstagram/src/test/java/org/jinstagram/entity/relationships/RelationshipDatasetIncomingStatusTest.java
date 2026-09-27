package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class RelationshipDatasetIncomingStatusTest {
    private RelationshipData relationshipData;

    @Before
    public void setUp() {
        relationshipData = new RelationshipData();
    }

    @After
    public void tearDown() {
        relationshipData = null;
    }

    @Test
    public void testSetIncomingStatus() throws Exception {
        String expectedIncomingStatus = "test_status";
        relationshipData.setIncomingStatus(expectedIncomingStatus);

        // Use reflection to verify the field value
        java.lang.reflect.Field field = RelationshipData.class.getDeclaredField("incomingStatus");
        field.setAccessible(true);
        String actualIncomingStatus = (String) field.get(relationshipData);

        Assert.assertEquals(expectedIncomingStatus, actualIncomingStatus);
    }
}
