package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UsersetIdTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @Test
    public void testSetId() throws Exception {
        String expectedId = "12345";
        user.setId(expectedId);
        Field idField = User.class.getDeclaredField("id");
        idField.setAccessible(true);
        String actualId = (String) idField.get(user);
        assertEquals(expectedId, actualId);
    }
}
