package org.jinstagram.utils;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import org.jinstagram.http.URLUtils;
public class MapUtilsdecodeAndAppendEntriesTest {
    private Map<String, String> sourceMap;
    private Map<String, String> targetMap;

    @Before
    public void setUp() {
        sourceMap = new HashMap<String, String>();
        targetMap = new LinkedHashMap<String, String>();
    }

    @After
    public void tearDown() {
        sourceMap = null;
        targetMap = null;
    }


    @Test
    public void testDecodeAndAppendEntriesWithEmptySource() {
        // Arrange
        sourceMap.clear();

        // Act
        MapUtils.decodeAndAppendEntries(sourceMap, targetMap);

        // Assert
        Assert.assertTrue("Target map should remain empty", targetMap.isEmpty());
    }

    @Test
    public void testDecodeAndAppendEntriesWithDuplicateKeys() {
        // Arrange
        sourceMap.put("key", "value1");
        sourceMap.put("key", "value2");

        // Act
        MapUtils.decodeAndAppendEntries(sourceMap, targetMap);

        // Assert
        Assert.assertEquals("Last entry should overwrite previous entry in target map", "value2", targetMap.get(URLUtils.percentEncode("key")));
    }
}
