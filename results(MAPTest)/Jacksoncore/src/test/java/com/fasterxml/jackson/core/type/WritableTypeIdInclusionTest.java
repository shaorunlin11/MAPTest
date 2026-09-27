package com.fasterxml.jackson.core.type;

import org.junit.Test;
import static org.junit.Assert.*;

import com.fasterxml.jackson.core.type.WritableTypeId.Inclusion;

public class WritableTypeIdInclusionTest {
    @Test
    public void testRequiresObjectContextForMetadataProperty() {
        Inclusion inclusion = Inclusion.METADATA_PROPERTY;
        assertTrue(inclusion.requiresObjectContext());
    }

    @Test
    public void testRequiresObjectContextForPayloadProperty() {
        Inclusion inclusion = Inclusion.PAYLOAD_PROPERTY;
        assertTrue(inclusion.requiresObjectContext());
    }

    @Test
    public void testRequiresObjectContextForWrapperArray() {
        Inclusion inclusion = Inclusion.WRAPPER_ARRAY;
        assertFalse(inclusion.requiresObjectContext());
    }

    @Test
    public void testRequiresObjectContextForWrapperObject() {
        Inclusion inclusion = Inclusion.WRAPPER_OBJECT;
        assertFalse(inclusion.requiresObjectContext());
    }

    @Test
    public void testRequiresObjectContextForParentProperty() {
        Inclusion inclusion = Inclusion.PARENT_PROPERTY;
        assertFalse(inclusion.requiresObjectContext());
    }
}
