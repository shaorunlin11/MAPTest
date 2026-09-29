package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Assert;

public class UserFeedDatasetBioTest {
    @Test
    public void testSetBio() throws Exception {
        UserFeedData userFeedData = new UserFeedData();
        String expectedBio = "This is a test bio.";

        userFeedData.setBio(expectedBio);

        Assert.assertEquals(expectedBio, userFeedData.getBio());
    }
}
