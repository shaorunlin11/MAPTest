package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.jinstagram.entity.users.feed.MediaFeedData;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.common.Pagination;
import java.util.List;
import java.util.ArrayList;

public class TagMediaFeedgetDataTest {
    private TagMediaFeed tagMediaFeed;
    private List<MediaFeedData> testData;

    @Before
    public void setUp() {
        tagMediaFeed = new TagMediaFeed();
        testData = new ArrayList<MediaFeedData>();
        testData.add(new MediaFeedData());
        testData.add(new MediaFeedData());
    }

    @After
    public void tearDown() {
        tagMediaFeed = null;
        testData = null;
    }

    @Test
    public void testGetDataReturnsInitializedData() {
        // Arrange
        tagMediaFeed.setData(testData);

        // Act
        List<MediaFeedData> result = tagMediaFeed.getData();

        // Assert
        Assert.assertNotNull("getData should not return null", result);
        Assert.assertEquals("getData should return the same list instance", testData, result);
    }

    @Test
    public void testGetDataReturnsEmptyListWhenNotSet() {
        // Arrange
        tagMediaFeed.setData(null);

        // Act
        List<MediaFeedData> result = tagMediaFeed.getData();

        // Assert
        Assert.assertNull("getData should return null when data is not set", result);
    }
}
