package org.jinstagram.entity.relationships;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class RelationshipDatasetTargetUserPrivateTest {
    private RelationshipData relationshipData;
    private Field targetUserPrivateField;

    @Before
    public void setUp() throws Exception {
        relationshipData = new RelationshipData();
        targetUserPrivateField = RelationshipData.class.getDeclaredField("targetUserPrivate");
        targetUserPrivateField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        targetUserPrivateField.setAccessible(false);
    }

    @Test
    public void testSetTargetUserPrivateWithTrue() throws Exception {
        boolean value = true;
        relationshipData.setTargetUserPrivate(value);
        Assert.assertEquals(value, targetUserPrivateField.getBoolean(relationshipData));
    }

    @Test
    public void testSetTargetUserPrivateWithFalse() throws Exception {
        boolean value = false;
        relationshipData.setTargetUserPrivate(value);
        Assert.assertEquals(value, targetUserPrivateField.getBoolean(relationshipData));
    }
}
