package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import java.lang.reflect.Field;


public class FromTagDatagetProfilePictureTest {
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
    public void testGetProfilePictureReturnsInitializedValue() throws Exception {
        String expectedProfilePicture = "https://example.com/profile.jpg";
        Field profilePictureField = FromTagData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        profilePictureField.set(fromTagData, expectedProfilePicture);

        String actualProfilePicture = fromTagData.getProfilePicture();
        Assert.assertEquals(expectedProfilePicture, actualProfilePicture);
    }

    @Test
    public void testGetProfilePictureReturnsNullWhenNotInitialized() throws Exception {
        Field profilePictureField = FromTagData.class.getDeclaredField("profilePicture");
        profilePictureField.setAccessible(true);
        profilePictureField.set(fromTagData, null);

        String actualProfilePicture = fromTagData.getProfilePicture();
        Assert.assertNull(actualProfilePicture);
    }
}
