package org.jinstagram.entity.relationships;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class RelationshipDataisTargetUserPrivateTest {

    @Test
    public void testIsTargetUserPrivate_returnsFalseWhenNotPrivate() throws Exception {
        RelationshipData data = new RelationshipData();
        assertFalse(data.isTargetUserPrivate());
    }

    @Test
    public void testIsTargetUserPrivate_returnsTrueWhenPrivate() throws Exception {
        RelationshipData data = new RelationshipData();
        Field field = RelationshipData.class.getDeclaredField("targetUserPrivate");
        field.setAccessible(true);
        field.set(data, true);
        assertTrue(data.isTargetUserPrivate());
    }
}
