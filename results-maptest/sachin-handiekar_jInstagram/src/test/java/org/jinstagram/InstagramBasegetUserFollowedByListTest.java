package org.jinstagram;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.http.Request;
import org.jinstagram.http.Response;
import org.jinstagram.http.URLUtils;
import org.jinstagram.http.Verbs;
import org.jinstagram.model.Methods;
import org.jinstagram.model.QueryParam;
import org.jinstagram.utils.PaginationHelper;
import org.jinstagram.utils.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.jinstagram.entity.common.Pagination;
public class InstagramBasegetUserFollowedByListTest {
    private InstagramBase instagramBase;

    @Before
    public void setUp() {
        instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public UserFeed getUserFollowedByListNextPage(String userId, String cursor) throws InstagramException {
                return new UserFeed();
            }

            @Override
            public UserFeed getUserFollowedByListNextPage(Pagination pagination) throws InstagramException {
                return new UserFeed();
            }
        };
    }

    @After
    public void tearDown() {
        instagramBase = null;
    }

    @Test
    public void testGetUserFollowedByListDelegatesToNextPageWithNullCursor() throws Exception {
        // Arrange
        String userId = "testUserId";

        // Act
        UserFeed result = instagramBase.getUserFollowedByList(userId);

        // Assert
        Assert.assertNotNull(result);
    }

    @Test
    public void testGetUserFollowedByListThrowsInstagramExceptionWhenNextPageThrows() throws Exception {
        // Arrange
        String userId = "testUserId";
        instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public UserFeed getUserFollowedByListNextPage(String userId, String cursor) throws InstagramException {
                throw new InstagramException("Test exception");
            }

            @Override
            public UserFeed getUserFollowedByListNextPage(Pagination pagination) throws InstagramException {
                return new UserFeed();
            }
        };

        // Act & Assert
        try {
            instagramBase.getUserFollowedByList(userId);
            Assert.fail("Expected InstagramException was not thrown");
        } catch (InstagramException e) {
            Assert.assertEquals("Test exception", e.getMessage());
        }
    }

}
