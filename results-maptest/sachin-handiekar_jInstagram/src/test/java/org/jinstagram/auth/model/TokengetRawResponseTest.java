package org.jinstagram.auth.model;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TokengetRawResponseTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetRawResponseWithNonNullRawResponse() throws Exception {
        Token token = new Token("token", "secret", "rawResponse");
        Assert.assertEquals("rawResponse", token.getRawResponse());
    }

    @Test
    public void testGetRawResponseWithNullRawResponse() throws Exception {
        thrown.expect(IllegalStateException.class);
        thrown.expectMessage("This token object was not constructed by jInstagramAuthService and does not have a rawResponse");

        Token token = new Token("token", "secret");
        token.getRawResponse();
    }
}
