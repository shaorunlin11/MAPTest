package org.apache.commons.codec.net;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class QCodecsetEncodeBlanksTest {
    private QCodec qCodec;

    @Before
    public void setUp() {
        qCodec = new QCodec();
    }

    @After
    public void tearDown() {
        qCodec = null;
    }

    @Test
    public void testSetEncodeBlanks() throws Exception {
        // Test with false
        qCodec.setEncodeBlanks(false);
        Field encodeBlanksField = QCodec.class.getDeclaredField("encodeBlanks");
        encodeBlanksField.setAccessible(true);
        Assert.assertFalse("encodeBlanks should be false", (Boolean) encodeBlanksField.get(qCodec));

        // Test with true
        qCodec.setEncodeBlanks(true);
        Assert.assertTrue("encodeBlanks should be true", (Boolean) encodeBlanksField.get(qCodec));
    }
}
