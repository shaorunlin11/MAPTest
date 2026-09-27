package org.jinstagram.entity.users.basicinfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class UserInfotoStringTest {

    @Test
    public void testToString() throws Exception {
        UserInfo userInfo = new UserInfo();
        UserInfoData data = new UserInfoData();
        Field dataField = UserInfo.class.getDeclaredField("data");
        dataField.setAccessible(true);
        dataField.set(userInfo, data);

        String result = userInfo.toString();
        assertTrue(result.contains("UserInfo [data="));
    }
}
