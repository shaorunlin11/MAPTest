package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RelationshipDatasetOutgoingStatusTest {
    private RelationshipData relationshipData;

    @Before
    public void setUp() {
        relationshipData = new RelationshipData();
    }

    @Test
    public void testSetOutgoingStatus() throws Exception {
        String expectedOutgoingStatus = "test_status";
        relationshipData.setOutgoingStatus(expectedOutgoingStatus);

        Field outgoingStatusField = RelationshipData.class.getDeclaredField("outgoingStatus");
        outgoingStatusField.setAccessible(true);
        String actualOutgoingStatus = (String) outgoingStatusField.get(relationshipData);

        assertEquals(expectedOutgoingStatus, actualOutgoingStatus);
    }
}
