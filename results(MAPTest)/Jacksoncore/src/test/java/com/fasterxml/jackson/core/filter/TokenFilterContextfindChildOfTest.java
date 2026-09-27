package com.fasterxml.jackson.core.filter;
import org.junit.Test;
import static org.junit.Assert.*;
public class TokenFilterContextfindChildOfTest {
    @Test
    public void testFindChildOfDirectParent() {
        TokenFilterContext parent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext child = new TokenFilterContext(0, parent, null, false);
        assertEquals(child, child.findChildOf(parent));
    }

    @Test
    public void testFindChildOfNestedParent() {
        TokenFilterContext grandparent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext parent = new TokenFilterContext(0, grandparent, null, false);
        TokenFilterContext child = new TokenFilterContext(0, parent, null, false);
        assertEquals(parent, child.findChildOf(grandparent));
    }

    @Test
    public void testFindChildOfNoMatch() {
        TokenFilterContext parent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext child = new TokenFilterContext(0, null, null, false);
        assertNull(child.findChildOf(parent));
    }

@Test
    public void testFindChildOfWithDifferentParent() {
        TokenFilterContext parent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext otherParent = new TokenFilterContext(0, null, null, false);
        TokenFilterContext child = new TokenFilterContext(0, otherParent, null, false);
        assertNull(child.findChildOf(parent));
    }
}
