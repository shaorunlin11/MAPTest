package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UsergetIdTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void testGetIdReturnsInitializedValue() throws Exception {
        String expectedId = "1234567890";
        user.setId(expectedId);
        String actualId = user.getId();
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void testGetIdReturnsNullIfNotSet() throws Exception {
        String actualId = user.getId();
        Assert.assertNull(actualId);
    }
}
