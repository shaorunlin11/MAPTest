package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;

public class UserInfoDatasetBioTest {
    private UserInfoData userInfoData;
    private Field bioField;

    @Before
    public void setUp() throws Exception {
        userInfoData = new UserInfoData();
        bioField = UserInfoData.class.getDeclaredField("bio");
        bioField.setAccessible(true);
    }

    @After
    public void tearDown() throws Exception {
        bioField.setAccessible(false);
    }

    @Test
    public void testSetBio() throws Exception {
        String testBio = "This is a test bio.";
        userInfoData.setBio(testBio);
        String actualBio = (String) bioField.get(userInfoData);
        Assert.assertEquals(testBio, actualBio);
    }
}
