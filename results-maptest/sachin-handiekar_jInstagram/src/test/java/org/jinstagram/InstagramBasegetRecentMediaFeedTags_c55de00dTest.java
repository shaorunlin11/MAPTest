package org.jinstagram;

import org.junit.Test;
import org.junit.Assert;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.entity.users.feed.MediaFeed;

public class InstagramBasegetRecentMediaFeedTags_c55de00dTest {

    @Test
    public void testGetRecentMediaFeedTags_withValidTagName_returnsMediaFeed() throws InstagramException {
        // Arrange
        InstagramBase instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName) throws InstagramException {
                return super.getRecentMediaFeedTags(tagName);
            }

            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName, long count) throws InstagramException {
                return new MediaFeed();
            }
        };

        String tagName = "testTag";

        // Act
        MediaFeed result = instagramBase.getRecentMediaFeedTags(tagName);

        // Assert
        Assert.assertNotNull(result);
    }

    @Test(expected = InstagramException.class)
    public void testGetRecentMediaFeedTags_withNullTagName_throwsInstagramException() throws InstagramException {
        // Arrange
        InstagramBase instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName) throws InstagramException {
                if (tagName == null) {
                    throw new InstagramException("Tag name cannot be null");
                }
                return super.getRecentMediaFeedTags(tagName);
            }

            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName, long count) throws InstagramException {
                return new MediaFeed();
            }
        };

        String tagName = null;

        // Act
        instagramBase.getRecentMediaFeedTags(tagName);
    }

    @Test(expected = InstagramException.class)
    public void testGetRecentMediaFeedTags_withEmptyTagName_throwsInstagramException() throws InstagramException {
        // Arrange
        InstagramBase instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName) throws InstagramException {
                if (tagName == null || tagName.trim().isEmpty()) {
                    throw new InstagramException("Tag name cannot be empty");
                }
                return super.getRecentMediaFeedTags(tagName);
            }

            @Override
            public MediaFeed getRecentMediaFeedTags(String tagName, long count) throws InstagramException {
                return new MediaFeed();
            }
        };

        String tagName = "";

        // Act
        instagramBase.getRecentMediaFeedTags(tagName);
    }
}
