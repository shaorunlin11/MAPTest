package org.jinstagram.entity.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class FromTagDatagetUsernameTest {

    @Test
    public void testGetUsername() throws Exception {
        FromTagData fromTagData = new FromTagData();
        String expectedUsername = "testUser";
        Field usernameField = FromTagData.class.getDeclaredField("username");
        usernameField.setAccessible(true);
        usernameField.set(fromTagData, expectedUsername);

        String actualUsername = fromTagData.getUsername();
        assertEquals(expectedUsername, actualUsername);
    }
}
