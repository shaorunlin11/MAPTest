package org.jinstagram.http;
import org.junit.Test;
import java.util.Map;
import java.util.HashMap;
import static org.junit.Assert.*;
public class URLUtilsqueryStringToMapTest {
    @Test
    public void testQueryStringToMap_NullInput_ReturnsEmptyMap() {
        Map<String, String> result = URLUtils.queryStringToMap(null);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testQueryStringToMap_EmptyString_ReturnsEmptyMap() {
        Map<String, String> result = URLUtils.queryStringToMap("");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testQueryStringToMap_SingleParameter_ReturnsSingleEntry() {
        Map<String, String> result = URLUtils.queryStringToMap("key=value");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("value", result.get("key"));
    }

    @Test
    public void testQueryStringToMap_MultipleParameters_ReturnsMultipleEntries() {
        Map<String, String> result = URLUtils.queryStringToMap("key1=value1&key2=value2");
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("value1", result.get("key1"));
        assertEquals("value2", result.get("key2"));
    }

    @Test
    public void testQueryStringToMap_ParameterWithoutValue_HasEmptyStringValue() {
        Map<String, String> result = URLUtils.queryStringToMap("key");
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("", result.get("key"));
    }
}
