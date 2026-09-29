package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.jinstagram.entity.common.Pagination;
import org.jinstagram.entity.users.feed.UserFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.http.Request;
import org.jinstagram.http.Response;
import org.jinstagram.http.URLUtils;
import org.jinstagram.model.Methods;
import org.jinstagram.model.QueryParam;
import org.jinstagram.utils.LogHelper;
import org.jinstagram.utils.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.Proxy;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InstagramBasegetUserFollowedByListNextPage_a5734386Test {

    @Test
    public void testGetUserFollowedByListNextPage() throws Exception {
        // Arrange
        InstagramBase instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public UserFeed getUserFeedInfoNextPage(Pagination pagination) throws InstagramException {
                return new UserFeed();
            }
        };

        Pagination pagination = new Pagination();

        // Act
        UserFeed result = instagramBase.getUserFollowedByListNextPage(pagination);

        // Assert
        Assert.assertNotNull(result);
    }
}
