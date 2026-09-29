package org.apache.commons.codec.language;

import org.junit.Test;
import static org.junit.Assert.*;

public class NysiisisStrictTest {

    @Test
    public void testIsStrictWithDefaultConstructor() {
        Nysiis nysiis = new Nysiis();
        assertTrue(nysiis.isStrict());
    }

    @Test
    public void testIsStrictWithCustomConstructor() {
        Nysiis nysiis = new Nysiis(false);
        assertFalse(nysiis.isStrict());
    }
}
