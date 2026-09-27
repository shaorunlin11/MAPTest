package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDataisBusinessTest {

    @Test
    public void testIsBusinessReturnsCorrectValue() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        boolean expected = true;
        Field field = UserInfoData.class.getDeclaredField("isBusiness");
        field.setAccessible(true);
        field.set(userInfoData, expected);
        assertTrue(userInfoData.isBusiness());
    }

    @Test
    public void testIsBusinessReturnsFalseWhenNotSet() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        Field field = UserInfoData.class.getDeclaredField("isBusiness");
        field.setAccessible(true);
        assertFalse(userInfoData.isBusiness());
    }
}
