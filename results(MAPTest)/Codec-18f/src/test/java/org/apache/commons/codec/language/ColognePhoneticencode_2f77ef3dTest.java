package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class ColognePhoneticencode_2f77ef3dTest {

    @Test
    public void testEncode() {
        ColognePhonetic colognePhonetic = new ColognePhonetic();
        String result = colognePhonetic.encode("test");
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    public void testEncodeWithNullInput() {
        ColognePhonetic colognePhonetic = new ColognePhonetic();
        String result = colognePhonetic.encode(null);
        assertNull(result);
    }

    @Test
    public void testEncodeWithEmptyString() {
        ColognePhonetic colognePhonetic = new ColognePhonetic();
        String result = colognePhonetic.encode("");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
