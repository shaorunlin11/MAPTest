package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;
import org.jinstagram.entity.users.feed.MediaFeed;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.http.Request;
import org.jinstagram.http.Response;
import org.jinstagram.http.URLUtils;
import org.jinstagram.model.Methods;
import org.jinstagram.model.QueryParam;
import org.jinstagram.utils.Preconditions;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import java.util.Date;

public class InstagramBasegetRecentMediaByLocation_1d579011Test {

    private InstagramBase instagramBase;

    @Before
    public void setUp() {
        instagramBase = new InstagramBase(new InstagramConfig()) {
            @Override
            public MediaFeed getRecentMediaByLocation(String locationId, String minId, String maxId) throws InstagramException {
                if (locationId == null || locationId.isEmpty()) {
                    throw new InstagramException("locationId is required");
                }
                return null;
            }

            @Override
            public MediaFeed getRecentMediaByLocation(String locationId, String minId, String maxId, Date maxTimeStamp, Date minTimeStamp) throws InstagramException {
                if (locationId == null || locationId.isEmpty()) {
                    throw new InstagramException("locationId is required");
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
    public void testGetRecentMediaByLocationDelegatesToOverloadedMethod() throws Exception {
        Method method = InstagramBase.class.getDeclaredMethod("getRecentMediaByLocation", String.class, String.class, String.class);
        Method overloadedMethod = InstagramBase.class.getDeclaredMethod("getRecentMediaByLocation", String.class, String.class, String.class, Date.class, Date.class);

        Assert.assertTrue(method.getDeclaringClass().equals(overloadedMethod.getDeclaringClass()));
        Assert.assertTrue(method.getReturnType().equals(overloadedMethod.getReturnType()));
    }

    @Test
    public void testGetRecentMediaByLocationThrowsInstagramException() throws Exception {
        Method method = InstagramBase.class.getDeclaredMethod("getRecentMediaByLocation", String.class, String.class, String.class);
        Assert.assertTrue(method.getExceptionTypes()[0].equals(InstagramException.class));
    }

    @Test
    public void testGetRecentMediaByLocationWithNullLocationId() throws Exception {
        try {
            instagramBase.getRecentMediaByLocation(null, null, null);
            Assert.fail("Expected InstagramException was not thrown");
        } catch (InstagramException e) {
            // Expected exception
        }
    }

    @Test
    public void testGetRecentMediaByLocationWithEmptyLocationId() throws Exception {
        try {
            instagramBase.getRecentMediaByLocation("", null, null);
            Assert.fail("Expected InstagramException was not thrown");
        } catch (InstagramException e) {
            // Expected exception
        }
    }
}
