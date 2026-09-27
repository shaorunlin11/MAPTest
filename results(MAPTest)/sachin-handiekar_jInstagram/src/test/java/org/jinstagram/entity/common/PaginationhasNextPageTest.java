package org.jinstagram.entity.common;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Rule;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

public class PaginationhasNextPageTest {
    private Pagination pagination;

    @Before
    public void setUp() {
        pagination = new Pagination();
    }

    @After
    public void tearDown() {
        pagination = null;
    }

    @Test
    public void testHasNextPageWithNullNextUrl() {
        Assert.assertFalse(pagination.hasNextPage());
    }

    @Test
    public void testHasNextPageWithEmptyStringNextUrl() {
        // Use reflection to set private field
        try {
            java.lang.reflect.Field field = Pagination.class.getDeclaredField("nextUrl");
            field.setAccessible(true);
            field.set(pagination, "");
        } catch (Exception e) {
            Assert.fail("Failed to set nextUrl: " + e.getMessage());
        }
        Assert.assertFalse(pagination.hasNextPage());
    }

    @Test
    public void testHasNextPageWithWhitespaceOnlyNextUrl() {
        // Use reflection to set private field
        try {
            java.lang.reflect.Field field = Pagination.class.getDeclaredField("nextUrl");
            field.setAccessible(true);
            field.set(pagination, "   ");
        } catch (Exception e) {
            Assert.fail("Failed to set nextUrl: " + e.getMessage());
        }
        Assert.assertFalse(pagination.hasNextPage());
    }

    @Test
    public void testHasNextPageWithNonEmptyNextUrl() {
        // Use reflection to set private field
        try {
            java.lang.reflect.Field field = Pagination.class.getDeclaredField("nextUrl");
            field.setAccessible(true);
            field.set(pagination, "https://example.com");
        } catch (Exception e) {
            Assert.fail("Failed to set nextUrl: " + e.getMessage());
        }
        Assert.assertTrue(pagination.hasNextPage());
    }
}
