package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class FromTagDatasetUsernameTest {
    private FromTagData fromTagData;

    @Before
    public void setUp() {
        fromTagData = new FromTagData();
    }

    @After
    public void tearDown() {
        fromTagData = null;
    }

    @Test
    public void testSetUsername() {
        String expectedUsername = "testuser";
        fromTagData.setUsername(expectedUsername);
        Assert.assertEquals(expectedUsername, fromTagData.getUsername());
    }
}
