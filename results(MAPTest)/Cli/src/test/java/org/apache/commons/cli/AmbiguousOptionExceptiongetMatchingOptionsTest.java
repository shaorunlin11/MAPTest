package org.apache.commons.cli;

import org.junit.Test;
import org.junit.Assert;
import java.util.Collection;
import java.util.ArrayList;

public class AmbiguousOptionExceptiongetMatchingOptionsTest {

    @Test
    public void testGetMatchingOptionsReturnsExpectedCollection() {
        Collection<String> matchingOptions = new ArrayList<>();
        matchingOptions.add("option1");
        matchingOptions.add("option2");

        AmbiguousOptionException exception = new AmbiguousOptionException("test", matchingOptions);

        Collection<String> result = exception.getMatchingOptions();

        Assert.assertEquals("The returned collection should match the one passed to the constructor", matchingOptions, result);
    }

    @Test
    public void testGetMatchingOptionsReturnsEmptyCollectionWhenNoOptions() {
        Collection<String> matchingOptions = new ArrayList<>();

        AmbiguousOptionException exception = new AmbiguousOptionException("test", matchingOptions);

        Collection<String> result = exception.getMatchingOptions();

        Assert.assertTrue("The returned collection should be empty when no options were provided", result.isEmpty());
    }
}
