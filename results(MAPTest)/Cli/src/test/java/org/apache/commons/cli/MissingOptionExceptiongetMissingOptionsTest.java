package org.apache.commons.cli;

import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class MissingOptionExceptiongetMissingOptionsTest {

    @Test
    public void testGetMissingOptions() throws Exception {
        List<String> missingOptions = new ArrayList<>();
        missingOptions.add("option1");
        missingOptions.add("option2");

        MissingOptionException exception = new MissingOptionException(missingOptions);
        List<String> result = exception.getMissingOptions();

        assertNotNull("getMissingOptions should not return null", result);
        assertEquals("getMissingOptions should return the correct number of missing options", 2, result.size());
        assertTrue("getMissingOptions should contain 'option1'", result.contains("option1"));
        assertTrue("getMissingOptions should contain 'option2'", result.contains("option2"));
    }
}
