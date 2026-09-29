package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;

import java.util.Map;
import java.util.HashMap;

import org.jinstagram.exceptions.InstagramBadRequestException;
import org.jinstagram.exceptions.InstagramException;
import org.jinstagram.exceptions.InstagramRateLimitException;
import org.jinstagram.entity.common.Meta;

public class InstagramErrorResponsethrowExceptionTest {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private InstagramErrorResponse errorResponse;
    private Meta meta;
    private Map<String, String> headers;

    @Before
    public void setUp() {
        headers = new HashMap<String, String>();
        meta = new Meta();
        errorResponse = new InstagramErrorResponse(meta);
    }

    @After
    public void tearDown() {
        errorResponse = null;
        meta = null;
        headers = null;
    }

    @Test
    public void testThrowExceptionWithNullErrorMeta() throws Exception {
        errorResponse = new InstagramErrorResponse(null);
        exception.expect(InstagramException.class);
        exception.expectMessage("No metadata found in response");
        errorResponse.throwException();
    }

    @Test
    public void testThrowExceptionWith400ErrorCode() throws Exception {
        meta.setCode(400);
        meta.setErrorType("Bad Request");
        meta.setErrorMessage("Invalid request parameters");
        errorResponse = new InstagramErrorResponse(meta);
        exception.expect(InstagramBadRequestException.class);
        exception.expectMessage("Bad Request: Invalid request parameters");
        errorResponse.throwException();
    }

    @Test
    public void testThrowExceptionWith429ErrorCode() throws Exception {
        meta.setCode(429);
        meta.setErrorType("Rate Limit Exceeded");
        meta.setErrorMessage("Too many requests");
        errorResponse = new InstagramErrorResponse(meta);
        exception.expect(InstagramRateLimitException.class);
        exception.expectMessage("Rate Limit Exceeded: Too many requests");
        errorResponse.throwException();
    }

    @Test
    public void testThrowExceptionWithOtherErrorCode() throws Exception {
        meta.setCode(500);
        meta.setErrorType("Internal Server Error");
        meta.setErrorMessage("Something went wrong");
        errorResponse = new InstagramErrorResponse(meta);
        exception.expect(InstagramException.class);
        exception.expectMessage("Internal Server Error: Something went wrong");
        errorResponse.throwException();
    }
}
