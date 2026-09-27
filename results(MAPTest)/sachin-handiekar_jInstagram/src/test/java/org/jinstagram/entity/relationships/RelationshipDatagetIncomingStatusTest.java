package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;


public class RelationshipDatagetIncomingStatusTest {
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
    public void testGetIncomingStatusReturnsSetValues() throws Exception {
        String expectedStatus = "test_status";
        Field field = RelationshipData.class.getDeclaredField("incomingStatus");
        field.setAccessible(true);
        field.set(relationshipData, expectedStatus);

        String result = relationshipData.getIncomingStatus();
        Assert.assertEquals(expectedStatus, result);
    }
}
