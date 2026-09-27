package org.jinstagram;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;

import org.jinstagram.auth.model.Token;

import java.lang.reflect.Field;


public class InstagramsetAccessTokenTest {
    private Instagram instagram;
    private Token testToken;

    @Before
    public void setUp() {
        testToken = new Token("test_token", "test_secret");
        instagram = new Instagram(testToken);
    }

    @Test
    public void testSetAccessTokenSetsTheAccessToken() throws Exception {
        Token newToken = new Token("new_token", "new_secret");
        instagram.setAccessToken(newToken);

        Field accessTokenField = Instagram.class.getDeclaredField("accessToken");
        accessTokenField.setAccessible(true);
        Token actualToken = (Token) accessTokenField.get(instagram);

        Assert.assertEquals("AccessToken should be set correctly", newToken, actualToken);
    }
}
