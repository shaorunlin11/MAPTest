package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.junit.Test;
import static org.junit.Assert.*;

public class AbstractCaverphoneencodeTest {

    @Test
    public void testEncodeWithNonNullString() throws EncoderException {
        AbstractCaverphone caverphone = new AbstractCaverphone() {
            @Override
            public String encode(String source) throws EncoderException {
                return "encoded";
            }
        };
        Object result = caverphone.encode("test");
        assertEquals("encoded", result);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNonStringInput() throws EncoderException {
        AbstractCaverphone caverphone = new AbstractCaverphone() {
            @Override
            public String encode(String source) throws EncoderException {
                return "encoded";
            }
        };
        caverphone.encode(123);
    }

    @Test(expected = EncoderException.class)
    public void testEncodeWithNullInput() throws EncoderException {
        AbstractCaverphone caverphone = new AbstractCaverphone() {
            @Override
            public String encode(String source) throws EncoderException {
                if (source == null) {
                    throw new EncoderException("Parameter supplied to Caverphone encode is not of type java.lang.String");
                }
                return "encoded";
            }
        };
        caverphone.encode(null);
    }
}
