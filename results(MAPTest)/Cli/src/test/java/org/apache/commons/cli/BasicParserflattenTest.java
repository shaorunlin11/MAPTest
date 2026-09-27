package org.apache.commons.cli;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class BasicParserflattenTest {
    @Test
    public void testFlattenReturnsInputArguments() {
        BasicParser parser = new BasicParser();
        String[] arguments = {"arg1", "arg2", "arg3"};
        String[] result = parser.flatten(new Options(), arguments, false);
        assertArrayEquals(arguments, result);
    }
}
