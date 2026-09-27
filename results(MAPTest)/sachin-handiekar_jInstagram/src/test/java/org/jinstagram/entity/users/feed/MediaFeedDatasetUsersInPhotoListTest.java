package org.jinstagram.entity.users.feed;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

import org.jinstagram.entity.common.UsersInPhoto;

public class MediaFeedDatasetUsersInPhotoListTest {
    private MediaFeedData mediaFeedData;
    private List<UsersInPhoto> usersInPhotoList;

    @Before
    public void setUp() {
        mediaFeedData = new MediaFeedData();
        usersInPhotoList = new ArrayList<UsersInPhoto>();
        usersInPhotoList.add(new UsersInPhoto());
    }

    @After
    public void tearDown() {
        mediaFeedData = null;
        usersInPhotoList = null;
    }

    @Test
    public void testSetUsersInPhotoList() {
        // Act
        mediaFeedData.setUsersInPhotoList(usersInPhotoList);

        // Assert
        List<UsersInPhoto> actual = (List<UsersInPhoto>) getPrivateField(mediaFeedData, "usersInPhotoList");
        Assert.assertEquals(usersInPhotoList, actual);
    }

    private Object getPrivateField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("Failed to access private field: " + fieldName, e);
        }
    }
}
