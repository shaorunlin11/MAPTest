package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class FromTagDatasetProfilePictureTest {
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
    public void testSetProfilePicture() throws Exception {
        String expectedProfilePicture = "https://example.com/profile.jpg";
        fromTagData.setProfilePicture(expectedProfilePicture);

        Assert.assertEquals(expectedProfilePicture, fromTagData.getProfilePicture());
    }
}
