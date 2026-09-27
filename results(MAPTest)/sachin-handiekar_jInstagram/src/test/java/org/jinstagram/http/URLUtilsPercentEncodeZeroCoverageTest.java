package org.jinstagram.http;

import org.junit.Test;

public class URLUtilsPercentEncodeZeroCoverageTest {
    @Test
    public void testPercentEncodeWithEncodingRules() {
        String input = "test string";
        String encoded = URLUtils.percentEncode(input);
    }
}
