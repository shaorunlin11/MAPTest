package com.zappos.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonConfigReaderConfigTest {

    @Test
    public void testReaderConfigEnumConstants() {
        assertEquals("READ_HTML_SAFE", JsonConfig.ReaderConfig.READ_HTML_SAFE.toString());
        assertEquals("READ_ENUM_USING_NAME", JsonConfig.ReaderConfig.READ_ENUM_USING_NAME.toString());
        assertEquals("READ_ENUM_USING_ORDINAL", JsonConfig.ReaderConfig.READ_ENUM_USING_ORDINAL.toString());
    }
}
