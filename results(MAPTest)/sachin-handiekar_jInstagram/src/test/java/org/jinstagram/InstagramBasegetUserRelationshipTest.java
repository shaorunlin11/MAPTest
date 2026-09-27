package org.jinstagram;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.entity.relationships.RelationshipFeed;
import org.jinstagram.model.Methods;
import org.jinstagram.utils.Preconditions;
import org.jinstagram.entity.common.InstagramErrorResponse;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.entity.media.MediaInfoFeed;
import org.jinstagram.entity.tags.TagInfoFeed;
import org.jinstagram.entity.comments.MediaCommentsFeed;
import org.jinstagram.entity.likes.LikesFeed;
import org.jinstagram.entity.locations.LocationSearchFeed;
import org.jinstagram.entity.tags.TagSearchFeed;
import org.jinstagram.entity.tags.TagMediaFeed;
import org.jinstagram.entity.users.basicinfo.UserInfo;
import org.jinstagram.entity.comments.MediaCommentResponse;
import org.jinstagram.entity.locations.LocationInfo;
import org.jinstagram.http.Verbs;
import org.jinstagram.utils.PaginationHelper;
import org.jinstagram.utils.LogHelper;
import org.jinstagram.http.Request;
import org.jinstagram.http.Response;
import org.jinstagram.http.URLUtils;
import org.jinstagram.model.QueryParam;
import org.jinstagram.model.Relationship;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class InstagramBasegetUserRelationshipTest {
    private InstagramBase instagramBase;
    private String userId = "1234567890";

    @Before
    public void setUp() {
        instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            protected <T extends InstagramObject> T createInstagramObject(Verbs verbs, Class<T> clazz, String methodName, Map<String, String> params) throws InstagramException {
                if (clazz.equals(RelationshipFeed.class)) {
                    return (T) new RelationshipFeed();
                }
                return null;
            }
        };
    }

    @After
    public void tearDown() {
        instagramBase = null;
    }

    @Test
    public void testGetUserRelationshipWithValidUserId() throws InstagramException {
        // Act
        RelationshipFeed result = instagramBase.getUserRelationship(userId);

        // Assert
        Assert.assertNotNull(result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserRelationshipWithNullUserId() throws InstagramException {
        // Act
        instagramBase.getUserRelationship(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserRelationshipWithEmptyUserId() throws InstagramException {
        // Act
        instagramBase.getUserRelationship("");
    }

    @Test
    public void testGetUserRelationshipWithNonEmptyUserId() throws InstagramException {
        // Act
        RelationshipFeed result = instagramBase.getUserRelationship("validUserId");

        // Assert
        Assert.assertNotNull(result);
    }
}
