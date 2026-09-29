package org.jinstagram.entity.users.basicinfo;

import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.users.basicinfo.UserInfoData;
import org.junit.Test;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;

public class UserInfosetMetaTest {

    @Test
    public void testSetMeta() throws Exception {
        UserInfo userInfo = new UserInfo();
        Meta meta = new Meta();

        userInfo.setMeta(meta);

        Field metaField = UserInfo.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        Meta resultMeta = (Meta) metaField.get(userInfo);

        assertEquals(meta, resultMeta);
    }
}
