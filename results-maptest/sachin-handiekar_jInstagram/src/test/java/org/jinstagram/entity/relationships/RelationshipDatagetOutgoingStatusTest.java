package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Assert;

public class RelationshipDatagetOutgoingStatusTest {
    @Test
    public void testGetOutgoingStatus() throws Exception {
        RelationshipData data = new RelationshipData();
        String expected = "test_status";
        java.lang.reflect.Field field = RelationshipData.class.getDeclaredField("outgoingStatus");
        field.setAccessible(true);
        field.set(data, expected);

        String result = data.getOutgoingStatus();
        Assert.assertEquals(expected, result);
    }
}
