package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfoDatagetCountsTest {

    @Test
    public void testGetCountsReturnsNullWhenCountsNotSet() {
        UserInfoData userInfoData = new UserInfoData();
        assertNull(userInfoData.getCounts());
    }

    @Test
    public void testGetCountsReturnsSetCountsObject() throws Exception {
        UserInfoData userInfoData = new UserInfoData();
        Counts expectedCounts = new Counts();
        Field countsField = UserInfoData.class.getDeclaredField("counts");
        countsField.setAccessible(true);
        countsField.set(userInfoData, expectedCounts);

        assertEquals(expectedCounts, userInfoData.getCounts());
    }
}
