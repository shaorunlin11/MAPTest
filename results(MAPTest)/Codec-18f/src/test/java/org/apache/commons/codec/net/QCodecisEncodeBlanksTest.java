package org.apache.commons.codec.net;

import org.junit.Test;
import static org.junit.Assert.*;

public class QCodecisEncodeBlanksTest {

    @Test
    public void testIsEncodeBlanks_DefaultValue() throws Exception {
        QCodec qCodec = new QCodec();
        assertFalse("Default value of encodeBlanks should be false", qCodec.isEncodeBlanks());
    }

    @Test
    public void testIsEncodeBlanks_SetValue() throws Exception {
        QCodec qCodec = new QCodec();
        qCodec.setEncodeBlanks(true);
        assertTrue("encodeBlanks should return true after being set", qCodec.isEncodeBlanks());
    }
}
