package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.apache.commons.codec.EncoderException;

public class ColognePhoneticencode_5e8eb14dTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testEncodeWithNonStringInput() throws Exception {
        ColognePhonetic codec = new ColognePhonetic();
        exception.expect(EncoderException.class);
        exception.expectMessage("This method's parameter was expected to be of the type java.lang.String. But actually it was of the type java.lang.Integer.");
        codec.encode(123);
    }

    @Test
    public void testEncodeWithStringInput() throws Exception {
        ColognePhonetic codec = new ColognePhonetic();
        Object result = codec.encode("test");
        Assert.assertTrue(result instanceof String);
    }
}
