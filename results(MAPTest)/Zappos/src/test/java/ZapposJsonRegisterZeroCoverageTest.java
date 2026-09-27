package com.zappos.json;

import org.junit.Test;

public class ZapposJsonRegisterZeroCoverageTest {
    @Test
    public void testRegisterWithNonEmptyClassesArray() {
        ZapposJson zapposJson = new ZapposJson();
        Class<?>[] classes = { String.class };
        zapposJson.register(classes);
    }
}
