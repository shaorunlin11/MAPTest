package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import org.junit.Assert;

public class UserInfoDatasetWebsiteTest {
    @Test
    public void testSetWebsite() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedWebsite = "https://example.com";

        userInfoData.setWebsite(expectedWebsite);

        // Use reflection to verify the field was set correctly
        java.lang.reflect.Field websiteField = UserInfoData.class.getDeclaredField("website");
        websiteField.setAccessible(true);
        String actualWebsite = (String) websiteField.get(userInfoData);

        Assert.assertEquals(expectedWebsite, actualWebsite);
    }
}
