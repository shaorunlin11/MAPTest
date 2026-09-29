package com.zappos.json;

import org.junit.Test;
import static org.junit.Assert.*;

public class JsonConfigWriterConfigTest {

    @Test
    public void testWriterConfigConstants() {
        assertEquals("WRITE_HTML_SAFE", JsonConfig.WriterConfig.WRITE_HTML_SAFE.toString());
        assertEquals("WRITE_ENUM_USING_NAME", JsonConfig.WriterConfig.WRITE_ENUM_USING_NAME.toString());
        assertEquals("WRITE_ENUM_USING_ORDINAL", JsonConfig.WriterConfig.WRITE_ENUM_USING_ORDINAL.toString());
    }
}
