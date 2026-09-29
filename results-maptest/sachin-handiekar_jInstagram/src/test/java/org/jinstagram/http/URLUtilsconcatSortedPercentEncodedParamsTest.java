package org.jinstagram.http;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
public class URLUtilsconcatSortedPercentEncodedParamsTest {

    @Test
    public void testSingleParam() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key", "value");
        String result = URLUtils.concatSortedPercentEncodedParams(params);
        assertEquals("key=value", result);
    }

    @Test
    public void testMultipleParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("key1", "value1");
        params.put("key2", "value2");
        String result = URLUtils.concatSortedPercentEncodedParams(params);
        assertEquals("key1=value1&key2=value2", result);
    }
}
