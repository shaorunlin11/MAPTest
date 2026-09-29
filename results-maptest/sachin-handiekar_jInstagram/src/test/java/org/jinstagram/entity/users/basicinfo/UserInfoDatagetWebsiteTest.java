package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDatagetWebsiteTest {
    @Test
    public void testGetWebsite() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        String expectedWebsite = "https://example.com";
        Field websiteField = UserInfoData.class.getDeclaredField("website");
        websiteField.setAccessible(true);
        websiteField.set(userInfoData, expectedWebsite);

        String actualWebsite = userInfoData.getWebsite();
        assertEquals(expectedWebsite, actualWebsite);
    }
}
