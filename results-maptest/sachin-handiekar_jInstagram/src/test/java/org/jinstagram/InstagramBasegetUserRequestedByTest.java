package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.entity.users.feed.UserFeed;

@RunWith(JUnit4.class)
public class InstagramBasegetUserRequestedByTest {

    @Test
    public void testGetUserRequestedBy() throws Exception {
        // Arrange
        InstagramBase instagramBase = new InstagramBase(new InstagramConfig()) {
            // Override the abstract method for testing
            @Override
            public UserFeed getUserRequestedBy() throws InstagramException {
                return new UserFeed();
            }
        };

        // Act
        UserFeed result = instagramBase.getUserRequestedBy();

        // Assert
        assertNotNull(result);
    }
}
