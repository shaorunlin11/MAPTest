package org.apache.commons.codec.language;

import org.junit.Test;

public class Caverphone2EncodeZeroCoverageTest {
    @Test
    public void testEncodeTargetLine46() {
        Caverphone2 caverphone2 = new Caverphone2();
        String source = "example";
        String result = caverphone2.encode(source);
    }

@Test
    public void testEncodeTargetLine47() {
        Caverphone2 caverphone2 = new Caverphone2();
        String result1 = caverphone2.encode(null);
        String result2 = caverphone2.encode("");
    }
}
