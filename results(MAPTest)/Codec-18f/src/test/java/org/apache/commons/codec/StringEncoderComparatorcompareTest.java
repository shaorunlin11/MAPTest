package org.apache.commons.codec;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.EncoderException;

public class StringEncoderComparatorcompareTest {
    private StringEncoderComparator comparator;
    private StringEncoder stringEncoder;

    @Before
    public void setUp() {
        stringEncoder = new StringEncoder() {
            @Override
            public String encode(String source) throws EncoderException {
                return source.toString();
            }

            @Override
            public Object encode(Object source) throws EncoderException {
                return source.toString();
            }
        };
        comparator = new StringEncoderComparator(stringEncoder);
    }

    @After
    public void tearDown() {
        comparator = null;
        stringEncoder = null;
    }

    @Test
    public void testCompareSuccessfulEncoding() throws Exception {
        // Test with two strings
        int result = comparator.compare("apple", "banana");
        Assert.assertTrue("Expected 'apple' to be less than 'banana'", result < 0);

        result = comparator.compare("banana", "apple");
        Assert.assertTrue("Expected 'banana' to be greater than 'apple', but got: " + result, result > 0);

        result = comparator.compare("apple", "apple");
        Assert.assertEquals("Expected equal comparison", 0, result);
    }

    @Test
    public void testCompareWithEncoderException() throws Exception {
        // Create a StringEncoder that throws EncoderException
        StringEncoder faultyEncoder = new StringEncoder() {
            @Override
            public String encode(String source) throws EncoderException {
                throw new EncoderException("Simulated encoding failure");
            }

            @Override
            public Object encode(Object source) throws EncoderException {
                throw new EncoderException("Simulated encoding failure");
            }
        };

        StringEncoderComparator faultyComparator = new StringEncoderComparator(faultyEncoder);
        int result = faultyComparator.compare("test1", "test2");
        Assert.assertEquals("Expected 0 when encoding fails", 0, result);
    }
}
