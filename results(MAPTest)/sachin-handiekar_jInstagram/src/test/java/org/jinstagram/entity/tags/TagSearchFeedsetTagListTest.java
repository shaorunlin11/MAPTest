package org.jinstagram.entity.tags;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.util.List;
import java.util.ArrayList;

import org.jinstagram.entity.common.Meta;
import org.jinstagram.entity.tags.TagInfoData;

public class TagSearchFeedsetTagListTest {
    private TagSearchFeed tagSearchFeed;
    private List<TagInfoData> tagList;

    @Before
    public void setUp() {
        tagSearchFeed = new TagSearchFeed();
        tagList = new ArrayList<TagInfoData>();
    }

    @After
    public void tearDown() {
        tagSearchFeed = null;
        tagList = null;
    }

    @Test
    public void testSetTagList() {
        // Arrange
        TagInfoData tagInfoData1 = new TagInfoData();
        TagInfoData tagInfoData2 = new TagInfoData();
        tagList.add(tagInfoData1);
        tagList.add(tagInfoData2);

        // Act
        tagSearchFeed.setTagList(tagList);

        // Assert
        Assert.assertEquals(tagList, tagSearchFeed.getTagList());
    }
}
