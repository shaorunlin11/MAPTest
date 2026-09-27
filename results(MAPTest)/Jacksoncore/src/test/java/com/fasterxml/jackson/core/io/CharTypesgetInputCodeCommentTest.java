package com.fasterxml.jackson.core.io;

import org.junit.Test;
import org.junit.Assert;

public class CharTypesgetInputCodeCommentTest {
    @Test
    public void testGetInputCodeComment() {
        int[] result = CharTypes.getInputCodeComment();
        Assert.assertNotNull("getInputCodeComment should not return null", result);
    }
}
