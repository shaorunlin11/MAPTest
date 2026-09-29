package org.jinstagram.entity.users.feed;

import org.junit.Test;
import static org.junit.Assert.*;

public class MediaFeedDataisUserHasLikedTest {

    @Test
    public void testIsUserHasLiked() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();

        // Test case 1: userHasLiked is false
        mediaFeedData.setUserHasLiked(false);
        assertFalse(mediaFeedData.isUserHasLiked());

        // Test case 2: userHasLiked is true
        mediaFeedData.setUserHasLiked(true);
        assertTrue(mediaFeedData.isUserHasLiked());
    }
}
