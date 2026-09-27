package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class FromTagDatasetIdTest {
    private FromTagData fromTagData;

    @Before
    public void setUp() {
        fromTagData = new FromTagData();
    }

    @Test
    public void testSetIdSetsIdFieldCorrectly() throws Exception {
        String expectedId = "testId";
        fromTagData.setId(expectedId);

        Field idField = FromTagData.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(fromTagData);

        assertEquals(expectedId, actualId);
    }
}
