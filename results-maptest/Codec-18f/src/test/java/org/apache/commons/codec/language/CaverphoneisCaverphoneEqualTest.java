package org.apache.commons.codec.language;

import org.junit.Test;
import org.junit.Assert;

public class CaverphoneisCaverphoneEqualTest {

    @Test
    public void testIsCaverphoneEqual_SameString_ReturnsTrue() {
        Caverphone caverphone = new Caverphone();
        boolean result = caverphone.isCaverphoneEqual("test", "test");
        Assert.assertTrue(result);
    }

    @Test
    public void testIsCaverphoneEqual_DifferentStrings_ReturnsFalse() {
        Caverphone caverphone = new Caverphone();
        boolean result = caverphone.isCaverphoneEqual("test", "different");
        Assert.assertFalse(result);
    }

    @Test
    public void testIsCaverphoneEqual_NullStrings_ReturnsFalse() {
        Caverphone caverphone = new Caverphone();
        boolean result = caverphone.isCaverphoneEqual(null, null);
        Assert.assertTrue(result);
    }

    @Test
    public void testIsCaverphoneEqual_OneNullString_ReturnsFalse() {
        Caverphone caverphone = new Caverphone();
        boolean result = caverphone.isCaverphoneEqual("test", null);
        Assert.assertFalse(result);
    }
}
