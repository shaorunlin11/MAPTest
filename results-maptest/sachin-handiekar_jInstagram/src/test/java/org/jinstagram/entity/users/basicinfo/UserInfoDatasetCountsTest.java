package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

public class UserInfoDatasetCountsTest {
    private UserInfoData userInfoData;
    private Counts counts;

    @Before
    public void setUp() {
        userInfoData = new UserInfoData();
        counts = new Counts();
    }

    @After
    public void tearDown() {
        userInfoData = null;
        counts = null;
    }

    @Test
    public void testSetCountsSetsCountsFieldCorrectly() {
        userInfoData.setCounts(counts);
        Assert.assertEquals(counts, userInfoData.getCounts());
    }

    @Test
    public void testSetCountsWithNullValue() {
        userInfoData.setCounts(null);
        Assert.assertNull(userInfoData.getCounts());
    }
}
