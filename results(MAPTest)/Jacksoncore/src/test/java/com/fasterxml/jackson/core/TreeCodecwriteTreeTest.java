package com.fasterxml.jackson.core;

import org.junit.Test;
import org.junit.Assert;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.TreeNode;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TreeCodecwriteTreeTest {
    @Test
    public void testWriteTreeSignature() throws Exception {
        Method method = TreeCodec.class.getMethod("writeTree", JsonGenerator.class, TreeNode.class);
        Assert.assertEquals(void.class, method.getReturnType());
        Assert.assertArrayEquals(new Class<?>[]{JsonGenerator.class, TreeNode.class}, method.getParameterTypes());
        Assert.assertTrue(Modifier.isAbstract(method.getModifiers()));
    }
}
