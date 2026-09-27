package com.fasterxml.jackson.core.filter;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.filter.TokenFilter;

public class JsonPointerBasedFilterincludeElementTest {
    private JsonPointerBasedFilter filter;
    private JsonPointer mockPath;

    @Before
    public void setUp() throws Exception {
        mockPath = new JsonPointer() {
            @Override
            public JsonPointer matchElement(int index) {
                return null;
            }

            @Override
            public boolean matches() {
                return false;
            }
        };
        filter = new JsonPointerBasedFilter(mockPath);
    }

    @Test
    public void testIncludeElementReturnsNullWhenNextIsNull() {
        TokenFilter result = filter.includeElement(0);
        Assert.assertNull(result);
    }

    @Test
    public void testIncludeElementReturnsIncludeAllWhenNextMatches() {
        JsonPointer mockPathWithMatch = new JsonPointer() {
            @Override
            public JsonPointer matchElement(int index) {
                return this;
            }

            @Override
            public boolean matches() {
                return true;
            }
        };
        JsonPointerBasedFilter filterWithMatch = new JsonPointerBasedFilter(mockPathWithMatch);
        TokenFilter result = filterWithMatch.includeElement(0);
        Assert.assertEquals(TokenFilter.INCLUDE_ALL, result);
    }

    @Test
    public void testIncludeElementReturnsNewFilterWhenNeitherConditionIsMet() {
        JsonPointer mockPathWithNonMatchingElement = new JsonPointer() {
            @Override
            public JsonPointer matchElement(int index) {
                return new JsonPointer() {
                    @Override
                    public JsonPointer matchElement(int index) {
                        return null;
                    }

                    @Override
                    public boolean matches() {
                        return false;
                    }
                };
            }

            @Override
            public boolean matches() {
                return false;
            }
        };
        JsonPointerBasedFilter filterWithNonMatchingElement = new JsonPointerBasedFilter(mockPathWithNonMatchingElement);
        TokenFilter result = filterWithNonMatchingElement.includeElement(0);
        Assert.assertNotNull(result);
        Assert.assertTrue(result instanceof JsonPointerBasedFilter);
    }
}
