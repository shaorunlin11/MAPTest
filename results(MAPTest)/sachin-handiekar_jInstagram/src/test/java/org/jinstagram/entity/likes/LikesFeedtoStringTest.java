package org.jinstagram.entity.likes;

import java.util.ArrayList;
import java.util.List;
import org.jinstagram.InstagramObject;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.User;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;


public class LikesFeedtoStringTest {

    @Test
    public void testToString() throws Exception {
        // Create mock objects
        Meta mockMeta = new Meta();
        List<User> mockUserList = new ArrayList<User>();
        User mockUser = new User();
        mockUserList.add(mockUser);

        // Create a LikesFeed instance and set the fields using reflection
        LikesFeed likesFeed = new LikesFeed();
        Field metaField = LikesFeed.class.getDeclaredField("meta");
        metaField.setAccessible(true);
        metaField.set(likesFeed, mockMeta);

        Field userListField = LikesFeed.class.getDeclaredField("userList");
        userListField.setAccessible(true);
        userListField.set(likesFeed, mockUserList);

        // Call the toString method
        String result = likesFeed.toString();

        // Verify the result matches the expected format
        assertEquals("LikesFeed [meta=" + mockMeta.toString() + ", userList=" + mockUserList.toString() + "]", result);
    }
}
