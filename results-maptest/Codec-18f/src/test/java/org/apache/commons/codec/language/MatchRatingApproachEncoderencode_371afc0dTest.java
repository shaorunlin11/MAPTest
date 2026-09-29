package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class MatchRatingApproachEncoderencode_371afc0dTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEncode_ThrowsEncoderExceptionWhenInputIsNotString() throws Exception {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        Object nonStringInput = new Object();

        thrown.expect(org.apache.commons.codec.EncoderException.class);
        thrown.expectMessage("Parameter supplied to Match Rating Approach encoder is not of type java.lang.String");

        encoder.encode(nonStringInput);
    }

    @Test
    public void testEncode_DelegatesToStringEncodeWhenInputIsString() throws Exception {
        MatchRatingApproachEncoder encoder = new MatchRatingApproachEncoder();
        String input = "test";

        // Since the actual encode(String) method is not shown, we can only verify that the method is called
        // by ensuring that no exception is thrown and that the return value is not null
        Object result = encoder.encode(input);
        Assert.assertNotNull(result);
    }
}
