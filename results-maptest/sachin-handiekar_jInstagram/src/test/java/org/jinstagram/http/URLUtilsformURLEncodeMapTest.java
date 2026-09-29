package org.jinstagram.http;

import org.jinstagram.utils.Preconditions;
import java.util.Map;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

public class URLUtilsformURLEncodeMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void testFormURLEncodeMap_nullMap_throwsException() {
        URLUtils.formURLEncodeMap(null);
    }

    @Test
    public void testFormURLEncodeMap_emptyMap_returnsEmptyString() {
        Map<String, String> emptyMap = new HashMap();
        assertEquals("", URLUtils.formURLEncodeMap(emptyMap));
    }

    @Test
    public void testFormURLEncodeMap_nonEmptyMap_delegatesToDoFormUrlEncode() throws Exception {
        Map<String, String> nonEmptyMap = new HashMap();
        nonEmptyMap.put("key", "value");

        // Since doFormUrlEncode is private, we can't directly verify it's called,
        // but we can verify the return value if we know what to expect
        String result = URLUtils.formURLEncodeMap(nonEmptyMap);
        assertEquals("key=value", result);
    }
}
