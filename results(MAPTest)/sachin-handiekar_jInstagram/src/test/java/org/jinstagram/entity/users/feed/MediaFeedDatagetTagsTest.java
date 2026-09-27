package org.jinstagram.entity.users.feed;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Field;


public class MediaFeedDatagetTagsTest {

    @Test
    public void testGetTagsReturnsTagsField() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();
        List expectedTags = new ArrayList();
        expectedTags.add("tag1");
        expectedTags.add("tag2");

        // Use reflection to set the private 'tags' field
        Field tagsField = MediaFeedData.class.getDeclaredField("tags");
        tagsField.setAccessible(true);
        tagsField.set(mediaFeedData, expectedTags);

        List actualTags = mediaFeedData.getTags();
        assertSame("getTags should return the same list reference", expectedTags, actualTags);
    }

    @Test
    public void testGetTagsWhenTagsIsNull() throws Exception {
        MediaFeedData mediaFeedData = new MediaFeedData();

        // Use reflection to set the private 'tags' field to null
        Field tagsField = MediaFeedData.class.getDeclaredField("tags");
        tagsField.setAccessible(true);
        tagsField.set(mediaFeedData, null);

        List actualTags = mediaFeedData.getTags();
        assertNull("getTags should return null when tags is null", actualTags);
    }
}
