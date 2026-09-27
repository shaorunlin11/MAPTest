package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import java.util.List;
import java.util.ArrayList;
import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;

public class TagSearchFeedgetTagListTest {
    private TagSearchFeed tagSearchFeed;
    private List<TagInfoData> expectedTagList;

    @Before
    public void setUp() {
        tagSearchFeed = new TagSearchFeed();
        expectedTagList = new ArrayList<TagInfoData>();
        // Add some sample TagInfoData objects to the expected list
        expectedTagList.add(new TagInfoData());
        expectedTagList.add(new TagInfoData());
    }

    @After
    public void tearDown() {
        tagSearchFeed = null;
        expectedTagList = null;
    }

    @Test
    public void testGetTagListReturnsExpectedList() throws Exception {
        // Set the tagList field using reflection
        java.lang.reflect.Field tagListField = TagSearchFeed.class.getDeclaredField("tagList");
        tagListField.setAccessible(true);
        tagListField.set(tagSearchFeed, expectedTagList);

        // Call the method under test
        List<TagInfoData> result = tagSearchFeed.getTagList();

        // Verify the result
        Assert.assertEquals(expectedTagList, result);
    }
}
