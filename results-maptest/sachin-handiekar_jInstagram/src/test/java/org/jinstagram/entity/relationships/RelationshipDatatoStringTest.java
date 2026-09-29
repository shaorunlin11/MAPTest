package org.jinstagram.entity.relationships;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RelationshipDatatoStringTest {

    @Test
    public void testToString() throws Exception {
        RelationshipData data = new RelationshipData();
        // Use reflection to set private fields
        Field incomingStatusField = RelationshipData.class.getDeclaredField("incomingStatus");
        incomingStatusField.setAccessible(true);
        incomingStatusField.set(data, "pending");

        Field outgoingStatusField = RelationshipData.class.getDeclaredField("outgoingStatus");
        outgoingStatusField.setAccessible(true);
        outgoingStatusField.set(data, "followed");

        String result = data.toString();
        assertEquals("RelationshipData [incomingStatus=pending, outgoingStatus=followed]", result);
    }
}
