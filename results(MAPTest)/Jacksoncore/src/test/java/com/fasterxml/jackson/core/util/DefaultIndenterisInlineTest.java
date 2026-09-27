package com.fasterxml.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

public class DefaultIndenterisInlineTest {
    @Test
    public void testIsInlineReturnsFalse() {
        DefaultIndenter indenter = new DefaultIndenter();
        assertFalse(indenter.isInline());
    }
}
