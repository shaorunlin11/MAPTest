package org.jinstagram.utils;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

import java.util.Map;

public class MapUtilsSortZeroCoverageTest {
    @Test
    public void testSort() {
        Map<String, String> inputMap = new HashMap<String, String>();
        inputMap.put("b", "valueB");
        inputMap.put("a", "valueA");
        inputMap.put("c", "valueC");

        Map<String, String> sortedMap = MapUtils.sort(inputMap);

        assertEquals("valueA", sortedMap.get("a"));
        assertEquals("valueB", sortedMap.get("b"));
        assertEquals("valueC", sortedMap.get("c"));
    }
}
