package org.jinstagram.entity.users.feed;
import java.util.ArrayList;
import java.util.List;
import org.jinstagram.entity.common.UsersInPhoto;
import org.junit.Test;
import static org.junit.Assert.*;
public class MediaFeedDatagetUsersInPhotoListTest {
    @Test
    public void testGetUsersInPhotoList_returnsNullWhenNotInitialized() {
        MediaFeedData mediaFeedData = new MediaFeedData();
        assertNull(mediaFeedData.getUsersInPhotoList());
    }

}
