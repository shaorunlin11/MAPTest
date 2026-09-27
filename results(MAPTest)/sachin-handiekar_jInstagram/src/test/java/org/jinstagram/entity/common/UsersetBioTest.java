package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UsersetBioTest {
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
    public void testSetBio() throws Exception {
        String expectedBio = "This is a test bio.";
        user.setBio(expectedBio);

        Assert.assertEquals("The bio field should be set correctly.", expectedBio, user.getBio());
    }
}
