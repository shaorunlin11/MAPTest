package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class CountssetFollowedByTest {
    private Counts counts;

    @Before
    public void setUp() {
        counts = new Counts();
    }

    @After
    public void tearDown() {
        counts = null;
    }

    @Test
    public void testSetFollowedBy() {
        int expectedValue = 12345;
        counts.setFollowedBy(expectedValue);
        Assert.assertEquals("The followedBy value should be set correctly", expectedValue, counts.getFollowedBy());
    }
}
