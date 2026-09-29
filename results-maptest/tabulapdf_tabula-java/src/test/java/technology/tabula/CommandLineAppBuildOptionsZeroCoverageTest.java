package technology.tabula;

import org.junit.Test;
import static org.junit.Assert.*;

import org.apache.commons.cli.Options;


public class CommandLineAppBuildOptionsZeroCoverageTest {
    @Test
    public void testBuildOptionsInstantiatesOptionsObject() {
        Options options = CommandLineApp.buildOptions();
        assertNotNull("Options object should be instantiated", options);
    }
}
