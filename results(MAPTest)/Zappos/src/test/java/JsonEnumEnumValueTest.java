package com.zappos.json.annot;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonEnumEnumValueTest {
    @Test
    public void testEnumValues() {
        assertEquals("ORDINAL", JsonEnum.EnumValue.ORDINAL.name());
        assertEquals("STRING", JsonEnum.EnumValue.STRING.name());
        assertNotSame(JsonEnum.EnumValue.ORDINAL, JsonEnum.EnumValue.STRING);
    }
}
