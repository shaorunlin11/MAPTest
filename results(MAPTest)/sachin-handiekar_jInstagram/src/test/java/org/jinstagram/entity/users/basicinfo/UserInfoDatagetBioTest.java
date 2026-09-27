package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;


public class UserInfoDatagetBioTest {
    private UserInfoData userInfoData;

    @Before
    public void setUp() {
        userInfoData = new UserInfoData();
    }

    @After
    public void tearDown() {
        userInfoData = null;
    }

    @Test
    public void testGetBioReturnsInitializedValue() throws Exception {
        String expectedBio = "This is a test bio.";
        Field bioField = UserInfoData.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(userInfoData, expectedBio);

        String actualBio = userInfoData.getBio();
        Assert.assertEquals(expectedBio, actualBio);
    }

    @Test
    public void testGetBioReturnsNullWhenNotSet() throws Exception {
        Field bioField = UserInfoData.class.getDeclaredField("bio");
        bioField.setAccessible(true);
        bioField.set(userInfoData, null);

        String actualBio = userInfoData.getBio();
        Assert.assertNull(actualBio);
    }
}
