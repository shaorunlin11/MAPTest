package com.fasterxml.jackson.core;

import org.junit.Test;
import static org.junit.Assert.*;

import java.lang.reflect.Method;


public class ObjectCodeccreateObjectNodeTest {
    @Test
    public void testCreateObjectNodeIsAbstract() throws Exception {
        // Verify that the method is declared as abstract
        Method method = ObjectCodec.class.getMethod("createObjectNode");
        assertTrue("Method createObjectNode should be abstract", java.lang.reflect.Modifier.isAbstract(method.getModifiers()));
    }

    @Test
    public void testCreateObjectNodeReturnsTreeNode() throws Exception {
        // Since this is an abstract method, we cannot directly call it
        // Instead, we verify that the return type is TreeNode
        Method method = ObjectCodec.class.getMethod("createObjectNode");
        assertEquals("Method createObjectNode should return TreeNode", TreeNode.class, method.getReturnType());
    }
}
