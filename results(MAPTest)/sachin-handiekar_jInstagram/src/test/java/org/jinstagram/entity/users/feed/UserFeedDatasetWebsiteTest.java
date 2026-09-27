package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UserFeedDatasetWebsiteTest {
    private UserFeedData userFeedData;

    @Before
    public void setUp() {
        userFeedData = new UserFeedData();
    }

    @After
    public void tearDown() {
        userFeedData = null;
    }

    @Test
    public void testSetWebsite() throws Exception {
        String expectedWebsite = "https://example.com";
        userFeedData.setWebsite(expectedWebsite);
        Assert.assertEquals(expectedWebsite, userFeedData.getWebsite());
    }

    @Test
    public void testSetWebsiteWithNull() throws Exception {
        userFeedData.setWebsite(null);
        Assert.assertNull(userFeedData.getWebsite());
    }

    @Test
    public void testSetWebsiteWithEmptyString() throws Exception {
        userFeedData.setWebsite("");
        Assert.assertEquals("", userFeedData.getWebsite());
    }
}
